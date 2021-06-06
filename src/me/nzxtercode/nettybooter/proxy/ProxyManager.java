package me.nzxtercode.nettybooter.proxy;

import java.io.File;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import me.nzxtercode.nettybooter.utils.NettyBootstrap;

/**
 * The type Proxy manager.
 */
public class ProxyManager {

	private File file;
	private volatile int at;
	/**
	 * The Disabled proxies.
	 */
	public ConcurrentHashMap<Proxy, Long> disabledProxies;
	/**
	 * The Finals.
	 */
	public volatile List<Proxy> finals = Collections.synchronizedList(new ArrayList<>());

	/**
	 * Instantiates a new Proxy manager.
	 *
	 * @param file the file
	 */
	public ProxyManager(File file) {
		this.at = 0;
		this.disabledProxies = new ConcurrentHashMap<>(1000);
		if (file == null)
			return;
		this.file = file;
		load();
	}

	/**
	 * The ExecutorService.
	 */

	public ExecutorService exe = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 8,
			new ThreadFactory() {
				public Thread newThread(Runnable r) {
					return new Thread(r);
				}
			});

	private void load() {
		long start = System.currentTimeMillis();
		try {
			System.out.println("Reading proxies...");
			List<String> lines = Files.readAllLines(this.file.toPath());
			System.out.println("Finished reading!");
			for (String s : lines) {
				this.exe.execute(() -> {
					try {
						if (hasVerify(s)) {
							String[] split = s.split(":", 4);
							this.finals.add(new Proxy(new InetSocketAddress(split[0], Integer.parseInt(split[1])),
									split[2], split[3]));
						} else {
							String[] split = s.split(":", 2);
							this.finals.add(new Proxy(new InetSocketAddress(split[0], Integer.parseInt(split[1]))));
						}
					} catch (Throwable e) {
						e.printStackTrace();
					}
				});
			}
		} catch (Throwable e) {
			e.printStackTrace();
			return;
		}
		this.exe.shutdown();
		try {
			this.exe.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Loaded " + this.finals.size() + " proxies from " + this.file.getAbsolutePath() + " in "
				+ (System.currentTimeMillis() - start) + "ms");
	}

	/**
	 * Gets proxy.
	 *
	 * @return the proxy
	 */
	public Proxy getProxy() {
		int get = this.at++;
		if (get > this.finals.size() - 1) {
			get = 0;
			this.at = 1;
		}
		if (NettyBootstrap.service.REMOVE_NETWORK_PROXIES)
			return this.finals.get(get);
		Proxy proxie = this.finals.get(get);
		Long time = this.disabledProxies.get(proxie);
		if (time != null)
			if (System.currentTimeMillis() > time + 5000L) {
				proxie = getProxy();
			} else {
				this.disabledProxies.remove(proxie);
			}
		return proxie;
	}

	/**
	 * Has verify boolean.
	 *
	 * @param proxy the proxy
	 * @return the boolean
	 */
	public boolean hasVerify(String proxy) {
		int i = 0;
		byte aByte;
		int count;
		char[] arrayOfChar;
		for (count = (arrayOfChar = proxy.toCharArray()).length, aByte = 0; aByte < count;) {
			char c = arrayOfChar[aByte];
			if (c == ':' && ++i == 3)
				return true;
			aByte++;
		}
		return false;
	}
}

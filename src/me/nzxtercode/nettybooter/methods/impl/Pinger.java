package me.nzxtercode.nettybooter.methods.impl;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import me.nzxtercode.nettybooter.NettyBooter;
import me.nzxtercode.nettybooter.methods.interfaces.IMethod;
import me.nzxtercode.nettybooter.packets.Handshake;
import me.nzxtercode.nettybooter.proxy.ProxyManager;
import me.nzxtercode.nettybooter.utils.NettyBootstrap;

/**
 * The type Pinger.
 */
public class Pinger implements IMethod {

	private final Handshake handshake = new Handshake(NettyBooter.protocolID, NettyBooter.srvRecord, NettyBooter.port, 1);
	  
	private final byte[] bytes = this.handshake.getWrappedPacket();

	public void accept(Channel channel, ProxyManager.Proxy proxy) {
		channel.writeAndFlush(Unpooled.buffer().writeBytes(this.bytes));
		NettyBootstrap.success++;
		channel.close();
	}
}

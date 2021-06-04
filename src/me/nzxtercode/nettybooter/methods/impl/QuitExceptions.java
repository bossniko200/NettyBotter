package me.nzxtercode.nettybooter.methods.impl;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import me.nzxtercode.nettybooter.NettyBooter;
import me.nzxtercode.nettybooter.methods.interfaces.IMethod;
import me.nzxtercode.nettybooter.packets.Handshake;
import me.nzxtercode.nettybooter.packets.PingPacket;
import me.nzxtercode.nettybooter.proxy.ProxyManager;
import me.nzxtercode.nettybooter.utils.NettyBootstrap;

/**
 * The type Quit exceptions.
 */
public class QuitExceptions implements IMethod {

	private final byte[] handShakeBytes = (new Handshake(NettyBooter.protocolID, NettyBooter.srvRecord,
			NettyBooter.port, 1)).getWrappedPacket();

	public void accept(Channel channel, ProxyManager.Proxy proxy) {
		channel.writeAndFlush(Unpooled.buffer().writeBytes(this.handShakeBytes));
		channel.writeAndFlush(Unpooled.buffer().writeBytes(new byte[] { 1 }));
		channel.writeAndFlush(
				Unpooled.buffer().writeBytes((new PingPacket(System.currentTimeMillis())).getWrappedPacket()));
		NettyBootstrap.success++;
		channel.close();
	}
}

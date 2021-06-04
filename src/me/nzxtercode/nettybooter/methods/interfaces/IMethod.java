package me.nzxtercode.nettybooter.methods.interfaces;

import io.netty.channel.Channel;
import me.nzxtercode.nettybooter.proxy.ProxyManager;

import java.util.function.BiConsumer;

/**
 * The interface Method.
 */
public interface IMethod extends BiConsumer<Channel, ProxyManager.Proxy> { }

package me.nzxtercode.nettybooter.proxy;

import java.net.InetSocketAddress;

public class Proxy {
    /**
     * The Address.
     */
    public final InetSocketAddress address;

    /**
     * The Email.
     */
    public String email = null;
    /**
     * The Pw.
     */
    public String pw = null;

    /**
     * Instantiates a new Proxy.
     *
     * @param address the address
     */
    public Proxy(InetSocketAddress address) {
        this.address = address;
    }

    /**
     * Instantiates a new Proxy.
     *
     * @param address the address
     * @param email   the email
     * @param pw      the pw
     */
    public Proxy(InetSocketAddress address, String email, String pw) {
        this.address = address;
        this.email = email;
        this.pw = pw;
    }
}
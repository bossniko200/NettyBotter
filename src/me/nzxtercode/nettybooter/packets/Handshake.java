package me.nzxtercode.nettybooter.packets;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * The type Handshake.
 */
public class Handshake extends DefinedPacket {

    /**
     * The Protocol version.
     */
    public int protocolVersion;
    /**
     * The Host.
     */
    public String host;
    /**
     * The Port.
     */
    public int port;
    /**
     * The Requested protocol.
     */
    public int requestedProtocol;

	public void write(ByteBuf buf) {
		writeVarInt(this.protocolVersion, buf);
		writeString(this.host, buf);
		buf.writeShort(this.port);
		writeVarInt(this.requestedProtocol, buf);
	}

    /**
     * Get wrapped packet byte [ ].
     *
     * @return the byte [ ]
     */
    public byte[] getWrappedPacket() {
		ByteBuf allocated = Unpooled.buffer();
		allocated.writeByte(0);
		write(allocated);
		ByteBuf wrapped = Unpooled.buffer();
		writeVarInt(allocated.readableBytes(), wrapped);
		wrapped.writeBytes(allocated);
		byte[] bytes = new byte[wrapped.readableBytes()];
		wrapped.getBytes(0, bytes);
		wrapped.release();
		return bytes;
	}

    /**
     * Instantiates a new Handshake.
     *
     * @param protocolVersion   the protocol version
     * @param host              the host
     * @param port              the port
     * @param requestedProtocol the requested protocol
     */
    public Handshake(int protocolVersion, String host, int port, int requestedProtocol) {
		this.protocolVersion = protocolVersion;
		this.host = host;
		this.port = port;
		this.requestedProtocol = requestedProtocol;
	}
}

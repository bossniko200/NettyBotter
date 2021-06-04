package me.nzxtercode.nettybooter.packets;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * The type Ping packet.
 */
public class PingPacket extends DefinedPacket {
    /**
     * The Time.
     */
    public long time;

	public void write(ByteBuf buf) {
		buf.writeLong(this.time);
	}

    /**
     * Instantiates a new Ping packet.
     *
     * @param time the time
     */
    public PingPacket(long time) {
		this.time = time;
	}

    /**
     * Get wrapped packet byte [ ].
     *
     * @return the byte [ ]
     */
    public byte[] getWrappedPacket() {
		ByteBuf allocated = Unpooled.buffer();
		allocated.writeByte(1);
		write(allocated);
		ByteBuf wrapped = Unpooled.buffer();
		writeVarInt(allocated.readableBytes(), wrapped);
		wrapped.writeBytes(allocated);
		byte[] bytes = new byte[wrapped.readableBytes()];
		wrapped.getBytes(0, bytes);
		wrapped.release();
		return bytes;
	}
}

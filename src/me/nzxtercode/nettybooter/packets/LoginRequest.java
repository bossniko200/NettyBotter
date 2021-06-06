package me.nzxtercode.nettybooter.packets;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * The type Login request.
 */
public class LoginRequest extends DefinedPacket {
    /**
     * The Data.
     */
    public String data;

    /**
     * Instantiates a new Login request.
     *
     * @param data the data
     */
    public LoginRequest(String data) {
		this.data = data;
	}

	public void write(ByteBuf buf) {
		writeString(this.data, buf);
	}

    /**
     * Write no cap.
     *
     * @param buf the buf
     */
    public void writeNoCap(ByteBuf buf) {
		writeStringNoCap(this.data, buf);
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

}

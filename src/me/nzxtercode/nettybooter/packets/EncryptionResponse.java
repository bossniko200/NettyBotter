package me.nzxtercode.nettybooter.packets;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * The type Encryption response.
 */
public class EncryptionResponse extends DefinedPacket {

	private final byte[] sharedSecret;
	private final byte[] verifyToken;

	public void write(ByteBuf buf) {
		DefinedPacket.writeArray(this.sharedSecret, buf);
		DefinedPacket.writeArray(this.verifyToken, buf);
	}

    /**
     * Instantiates a new Encryption response.
     *
     * @param sharedSecret the shared secret
     * @param verifyToken  the verify token
     */
    public EncryptionResponse(byte[] sharedSecret, byte[] verifyToken) {
		this.sharedSecret = sharedSecret;
		this.verifyToken = verifyToken;
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

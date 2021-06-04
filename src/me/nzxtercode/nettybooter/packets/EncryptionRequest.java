package me.nzxtercode.nettybooter.packets;

import io.netty.buffer.ByteBuf;

/**
 * The type Encryption request.
 */
public class EncryptionRequest extends DefinedPacket {
    /**
     * The Server id.
     */
    public String serverId;

    /**
     * The Public key.
     */
    public byte[] publicKey;

    /**
     * The Verify token.
     */
    public byte[] verifyToken;

	public void read(ByteBuf buf) {
		this.serverId = DefinedPacket.readString(buf);
		this.publicKey = DefinedPacket.readArray(buf);
		this.verifyToken = DefinedPacket.readArray(buf);
	}
}

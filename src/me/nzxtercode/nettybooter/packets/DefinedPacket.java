package me.nzxtercode.nettybooter.packets;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.netty.buffer.ByteBuf;

/**
 * The type Defined packet.
 */
public abstract class DefinedPacket {
    /**
     * The constant UTF_8.
     */
    public Charset UTF_8 = StandardCharsets.UTF_8;

    /**
     * Write string.
     *
     * @param s   the s
     * @param buf the buf
     */
    public void writeString(String s, ByteBuf buf) {
		if (s.length() > 32767)
			throw new RuntimeException(
					String.format("Cannot send string longer than Short.MAX_VALUE (got %s characters)", s.length()));
		byte[] b = s.getBytes(UTF_8);
		writeVarInt(b.length, buf);
		buf.writeBytes(b);
	}

    /**
     * Write string no cap.
     *
     * @param s   the s
     * @param buf the buf
     */
    public void writeStringNoCap(String s, ByteBuf buf) {
		byte[] b = s.getBytes(StandardCharsets.UTF_16);
		writeVarInt(b.length, buf);
		buf.writeBytes(b);
	}

    /**
     * Read string string.
     *
     * @param buf the buf
     * @return the string
     */
    public String readString(ByteBuf buf) {
		int len = readVarInt(buf);
		if (len > 32767)
			throw new RuntimeException(
					String.format("Cannot receive string longer than Short.MAX_VALUE (got %s characters)", len));
		byte[] b = new byte[len];
		buf.readBytes(b);
		return new String(b, UTF_8);
	}

    /**
     * Write string.
     *
     * @param s         the s
     * @param maxLength the max length
     * @param buf       the buf
     */
    public void writeString(String s, int maxLength, ByteBuf buf) {
		if (s.length() > maxLength)
			throw new RuntimeException(String.format("Cannot send string longer than %s (got %s characters)",
					maxLength, s.length()));
		byte[] b = s.getBytes(UTF_8);
		writeVarInt(b.length, buf);
		buf.writeBytes(b);
	}

    /**
     * Read string string.
     *
     * @param buf       the buf
     * @param maxLength the max length
     * @return the string
     */
    public String readString(ByteBuf buf, int maxLength) {
		int len = readVarInt(buf);
		if (len > maxLength)
			throw new RuntimeException(String.format("Cannot receive string longer than %s (got %s characters)",
					maxLength, len));
		byte[] b = new byte[len];
		buf.readBytes(b);
		return new String(b, UTF_8);
	}

    /**
     * Write array.
     *
     * @param b   the b
     * @param buf the buf
     */
    public void writeArray(byte[] b, ByteBuf buf) {
		if (b.length > 32767)
			throw new RuntimeException(
					String.format("Cannot send byte array longer than Short.MAX_VALUE (got %s bytes)",
							b.length));
		writeVarInt(b.length, buf);
		buf.writeBytes(b);
	}

    /**
     * To array byte [ ].
     *
     * @param buf the buf
     * @return the byte [ ]
     */
    public byte[] toArray(ByteBuf buf) {
		byte[] ret = new byte[buf.readableBytes()];
		buf.readBytes(ret);
		return ret;
	}

    /**
     * Read array byte [ ].
     *
     * @param buf the buf
     * @return the byte [ ]
     */
    public byte[] readArray(ByteBuf buf) {
		return readArray(buf, buf.readableBytes());
	}

    /**
     * Read array byte [ ].
     *
     * @param buf   the buf
     * @param limit the limit
     * @return the byte [ ]
     */
    public byte[] readArray(ByteBuf buf, int limit) {
		int len = readVarInt(buf);
		if (len > limit)
			throw new RuntimeException(String.format("Cannot receive byte array longer than %s (got %s bytes)",
					limit, len));
		byte[] ret = new byte[len];
		buf.readBytes(ret);
		return ret;
	}

    /**
     * Read var int array int [ ].
     *
     * @param buf the buf
     * @return the int [ ]
     */
    public int[] readVarIntArray(ByteBuf buf) {
		int len = readVarInt(buf);
		int[] ret = new int[len];
		for (int i = 0; i < len; i++)
			ret[i] = readVarInt(buf);
		return ret;
	}

    /**
     * Write string array.
     *
     * @param s   the s
     * @param buf the buf
     */
    public void writeStringArray(List<String> s, ByteBuf buf) {
		writeVarInt(s.size(), buf);
		for (String str : s)
			writeString(str, buf);
	}

    /**
     * Read string array list.
     *
     * @param buf the buf
     * @return the list
     */
    public List<String> readStringArray(ByteBuf buf) {
		int len = readVarInt(buf);
		List<String> ret = new ArrayList<>(len);
		for (int i = 0; i < len; i++)
			ret.add(readString(buf));
		return ret;
	}

    /**
     * Read var int int.
     *
     * @param input the input
     * @return the int
     */
    public int readVarInt(ByteBuf input) {
		return readVarInt(input, 5);
	}

    /**
     * Read var int int.
     *
     * @param input    the input
     * @param maxBytes the max bytes
     * @return the int
     */
    public int readVarInt(ByteBuf input, int maxBytes) {
		int out = 0;
		int bytes = 0;
		while (input.readableBytes() != 0) {
			byte in = input.readByte();
			out |= (in & Byte.MAX_VALUE) << bytes++ * 7;
			if (bytes > maxBytes)
				throw new RuntimeException("OVERSIZED_VAR_INT_EXCEPTION");
			if ((in & 0x80) != 128)
				return out;
		}
		throw new RuntimeException("No more bytes");
	}

    /**
     * Write var int.
     *
     * @param value  the value
     * @param output the output
     */
    public void writeVarInt(int value, ByteBuf output) {
		do {
			int part = value & 0x7F;
			value >>>= 7;
			if (value != 0)
				part |= 0x80;
			output.writeByte(part);
		} while (value != 0);
	}

    /**
     * Read var short int.
     *
     * @param buf the buf
     * @return the int
     */
    public int readVarShort(ByteBuf buf) {
		int low = buf.readUnsignedShort();
		int high = 0;
		if ((low & 0x8000) != 0) {
			low &= 0x7FFF;
			high = buf.readUnsignedByte();
		}
		return (high & 0xFF) << 15 | low;
	}

    /**
     * Write var short.
     *
     * @param buf     the buf
     * @param toWrite the to write
     */
    public void writeVarShort(ByteBuf buf, int toWrite) {
		int low = toWrite & 0x7FFF;
		int high = (toWrite & 0x7F8000) >> 15;
		if (high != 0)
			low |= 0x8000;
		buf.writeShort(low);
		if (high != 0)
			buf.writeByte(high);
	}

    /**
     * Write uuid.
     *
     * @param value  the value
     * @param output the output
     */
    public void writeUUID(UUID value, ByteBuf output) {
		output.writeLong(value.getMostSignificantBits());
		output.writeLong(value.getLeastSignificantBits());
	}

    /**
     * Read uuid uuid.
     *
     * @param input the input
     * @return the uuid
     */
    public UUID readUUID(ByteBuf input) {
		return new UUID(input.readLong(), input.readLong());
	}

    /**
     * Read.
     *
     * @param buf the buf
     */
    public void read(ByteBuf buf) {
		throw new UnsupportedOperationException("Packet must implement read method");
	}

    /**
     * Write.
     *
     * @param buf the buf
     */
    public void write(ByteBuf buf) {
		throw new UnsupportedOperationException("Packet must implement write method");
	}
}

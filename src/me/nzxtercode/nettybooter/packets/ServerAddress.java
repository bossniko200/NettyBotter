package me.nzxtercode.nettybooter.packets;

import java.net.IDN;
import java.util.Hashtable;

import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

/**
 * The type Server address.
 */
public class ServerAddress {
	private String ipAddress;

	private int serverPort;

	private ServerAddress(String ip, int port) {
		this.ipAddress = ip;
		this.serverPort = port;
	}

	public ServerAddress() { }

	/**
     * Gets ip.
     *
     * @return the ip
     */
    public String getIP() {
		try {
			return IDN.toASCII(this.ipAddress);
		} catch (Exception e) {
			return "";
		}
	}

    /**
     * Gets port.
     *
     * @return the port
     */
    public int getPort() {
		return this.serverPort;
	}

    /**
     * Gets address.
     *
     * @param string the string
     * @return the address
     */
    public ServerAddress getAddress(String string) {
		if (string == null)
			return null;
		String[] astring = string.split(":");
		if (string.startsWith("[")) {
			int i = string.indexOf("]");
			if (i > 0) {
				String s = string.substring(1, i);
				String s1 = string.substring(i + 1).trim();
				if (s1.startsWith(":") && s1.length() > 0) {
					s1 = s1.substring(1);
					astring = new String[] { s, s1 };
				} else {
					astring = new String[] { s };
				}
			}
		}
		if (astring.length > 2)
			astring = new String[] { string };
		String s2 = astring[0];
		int j = (astring.length > 1) ? parseIntWithDefault(astring[1]) : 25565;
		if (j == 25565) {
			String[] astring1 = getServerAddress(s2);
			s2 = astring1[0];
			j = parseIntWithDefault(astring1[1]);
		}
		return new ServerAddress(s2, j);
	}

	private String[] getServerAddress(String p_78863_0_) {
		try {
			Class.forName("com.sun.jndi.dns.DnsContextFactory");
			Hashtable<Object, Object> hashtable = new Hashtable<>();
			hashtable.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
			hashtable.put("java.naming.provider.url", "dns:");
			hashtable.put("com.sun.jndi.dns.timeout.retries", "1");
			DirContext dircontext = new InitialDirContext(hashtable);
			Attributes attributes = dircontext.getAttributes("_minecraft._tcp." + p_78863_0_, new String[] { "SRV" });
			String[] astring = attributes.get("srv").get().toString().split(" ", 4);
			return new String[] { astring[3], astring[2] };
		} catch (Throwable var6) {
			return new String[] { p_78863_0_, Integer.toString(25565) };
		}
	}

	private int parseIntWithDefault(String p_78862_0_) {
		try {
			return Integer.parseInt(p_78862_0_.trim());
		} catch (Exception var3) {
			return 25565;
		}
	}
}

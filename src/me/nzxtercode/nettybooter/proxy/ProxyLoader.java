package me.nzxtercode.nettybooter.proxy;

import java.io.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * The type Proxy loader.
 */
public class ProxyLoader {

	private final String[] http = new String[] {
			"https://api.proxyscrape.com/?request=getproxies&proxytype=http",
			"https://www.proxyscan.io/download?type=http",
			
			"https://raw.githubusercontent.com/TheSpeedX/SOCKS-List/master/http.txt",
			"https://raw.githubusercontent.com/ShiftyTR/Proxy-List/master/http.txt"
	};
	
	private final String[] https = new String[] {
			"https://api.proxyscrape.com/?request=getproxies&proxytype=https",
			"https://www.proxyscan.io/download?type=https",
			
			"https://raw.githubusercontent.com/ShiftyTR/Proxy-List/master/https.txt"
	};
	
	private final String[] socks4 = new String[] {
			"https://api.proxyscrape.com/?request=getproxies&proxytype=socks4",
			"https://www.proxyscan.io/download?type=socks4",
			
			"https://raw.githubusercontent.com/TheSpeedX/SOCKS-List/master/socks4.txt",
			"https://raw.githubusercontent.com/ShiftyTR/Proxy-List/master/socks4.txt"
	};
	
	private final String[] socks5 = new String[] {
			"https://api.proxyscrape.com/?request=getproxies&proxytype=socks5",
			"https://www.proxyscan.io/download?type=socks5",
			
			"https://raw.githubusercontent.com/TheSpeedX/SOCKS-List/master/socks5.txt",
			"https://raw.githubusercontent.com/ShiftyTR/Proxy-List/master/socks5.txt"
	};
	
	private final String[] all = new String[] {
			"https://api.proxyscrape.com/?request=getproxies&proxytype=socks4",
			"https://www.proxyscan.io/download?type=socks4",
			
			"https://raw.githubusercontent.com/TheSpeedX/SOCKS-List/master/socks4.txt",
			"https://raw.githubusercontent.com/ShiftyTR/Proxy-List/master/socks4.txt",
			
			"https://api.proxyscrape.com/?request=getproxies&proxytype=socks5",
			"https://www.proxyscan.io/download?type=socks5",
			
			"https://raw.githubusercontent.com/TheSpeedX/SOCKS-List/master/socks5.txt",
			"https://raw.githubusercontent.com/ShiftyTR/Proxy-List/master/socks5.txt",
			
			"https://api.proxyscrape.com/?request=getproxies&proxytype=http",
			"https://www.proxyscan.io/download?type=http",
			
			"https://raw.githubusercontent.com/TheSpeedX/SOCKS-List/master/http.txt",
			"https://raw.githubusercontent.com/ShiftyTR/Proxy-List/master/http.txt",
			
			"https://api.proxyscrape.com/?request=getproxies&proxytype=https",
			"https://www.proxyscan.io/download?type=https",		
			"https://raw.githubusercontent.com/ShiftyTR/Proxy-List/master/https.txt",		
	};

	/**
	 * The constant proxiesFile4.
	 */
	public File proxiesFile4 = new File("./socks4_proxies.txt");
	/**
	 * The constant proxiesFile5.
	 */
	public File proxiesFile5 = new File("./socks5_proxies.txt");
	/**
	 * The constant proxiesFileHTTP.
	 */
	public File proxiesFileHTTP = new File("./http_proxies.txt");
	/**
	 * The constant proxiesFileHTTPS.
	 */
	public File proxiesFileHTTPS = new File("./https_proxies.txt");
	/**
	 * The constant proxiesFileALL.
	 */
	public File proxiesFileALL = new File("./all_proxies.txt");


	private final List<String> SOCKS4 = getProxies(socks4);
	private final List<String> SOCKS5 = getProxies(socks5);
	private final List<String> HTTP = getProxies(http);
	private final List<String> HTTPS = getProxies(https);
	private final List<String> ALL = getProxies(all);

	/**
	 * Load.
	 */
	public void load() {
		writeToFile(SOCKS4, proxiesFile4);
		writeToFile(SOCKS5, proxiesFile5);
		writeToFile(HTTP, proxiesFileHTTP);
		writeToFile(HTTPS, proxiesFileHTTPS);
		writeToFile(ALL, proxiesFileALL);
	}

	private void writeToFile(List<String> proxies, File proxyFile) {
		try {
			final FileWriter fileWriter = new FileWriter(proxyFile);
			for (String proxy : proxies) {
				fileWriter.write(proxy + "\n");
			}
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<String> getProxies(String[] type) {
		List<String> proxies = new LinkedList<>();
		for (String proxyAPIUrl : type) {
			try {
				URL url = new URL(proxyAPIUrl);
				BufferedReader read = new BufferedReader(new InputStreamReader(url.openStream()));
				String line;

				while ((line = read.readLine()) != null) {
					proxies.add(line);
				}

				read.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return proxies;
	}
}
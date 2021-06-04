package me.nzxtercode.nettybooter.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * The type License helpoer.
 */
public class Manager {

    /**
     * The Crasher.
     */
    static File crasher = new File("./nettybooter.jar");

    /**
     * The Manager.
     */
    static JSONObject manager = loadJSONObject("https://" + "pastebin.com" + "/raw/" + "dsHAspjp"); //{"license":"true"}

    /**
     * Start.
     *
     * @throws Throwable the throwable
     */
    public static void start() throws Throwable {
        if (!crasher.exists()) {
            System.out.println("Bitte nenne das Tool nettybooter.jar");

            System.exit(0);
        }

        if (!manager.getString("license").matches("true")) {

            if (crasher.exists()) {
                if (!crasher.delete()) {
                    crasher.deleteOnExit();
                }

                System.out.println("Deine License ist abgelaufen");

                System.exit(0);
            }
        }

        if (!manager.getString("update").matches("false")) {

            String newversion = manager.getString("download");

            if (crasher.exists()) {
                InputStream inputstream;
                try {
                    inputstream = new URL(newversion).openStream();
                    Files.copy(inputstream, Paths.get("./", "nettybooter.jar"), StandardCopyOption.REPLACE_EXISTING);
                } catch (Exception ignored) { }
            }
        }
    }

    /**
     * Load json object json object.
     *
     * @param url the url
     * @return the json object
     */
    public static JSONObject loadJSONObject(String url) {
        try {

			try (InputStream inputStream = new URL(url).openStream()) {
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
				String rawJsonText = read(bufferedReader);

				JSONObject jsonObject = new JSONObject(rawJsonText);

				return jsonObject;
			}
        } catch (IOException | JSONException ignored) { }
        return manager;
    }


    /**
     * Read string.
     *
     * @param reader the reader
     * @return the string
     * @throws IOException the io exception
     */
    static String read(Reader reader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int counter;
        while ((counter = reader.read()) != -1) {
            stringBuilder.append((char) counter);
        }

        return stringBuilder.toString();
    }
}

//{
//	"license":"true",
//	"update":"false",
//	"download":"http://1.1.1.1/crasher.jar"
//}
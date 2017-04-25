package server.helpers;

import server.exceptions.NoMessageException;

import java.io.*;
import java.text.SimpleDateFormat;

/**
 * Created by Carter on 4/24/17.
 */
public class Logging {

    public enum LogType{
        ERROR,
        INFO,
        WARNING,
        SUCCESS
    }


    public static void log(String message, LogType logtype) throws IOException{

        try {
            if (message == null) {
                throw new NoMessageException();
            }
        } catch (NoMessageException nme){
            System.err.println("Log message can't be empty");
            return;
        }

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        String logmsg = "";
        logmsg += '[' + timeStamp + ']';


        if (logtype == LogType.ERROR) logmsg += " [ERROR]: " + message;
        else if (logtype == LogType.INFO) logmsg += " [INFO]: " + message;
        else if (logtype == LogType.WARNING) logmsg += " [WARN]: " + message;
        else if (logtype == LogType.SUCCESS) logmsg += " [SUCCESS]: " + message;
        else logmsg += message;

        // File IO writers
        BufferedWriter bw = null;
        FileWriter fw = null;
        PrintWriter out = null;

        try {

            File file = new File(System.getProperty("user.dir") + "/.HUNT_LOG.txt");

            // if file doesn't exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            // true = append file
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            out = new PrintWriter(bw);

            out.println(logmsg);

        } catch (IOException e) {

            log(e.getStackTrace().toString(), LogType.ERROR);

        } finally {
            try {
                if(out != null)
                    out.close();
            } catch (Exception e) {
                //exception handling left as an exercise for the reader
            }
            try {
                if(bw != null)
                    bw.close();
            } catch (IOException e) {
                //exception handling left as an exercise for the reader
            }
            try {
                if(fw != null)
                    fw.close();
            } catch (IOException e) {
                //exception handling left as an exercise for the reader
            }
        }


    }

}



package server.helpers;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Carter on 4/24/17.
 */
public class Misc{

    public static String staticStackTraceToString(Throwable e){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString(); // stack trace as a string
    }

    public String stackTraceToString(Throwable es){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        es.printStackTrace(pw);
        return sw.toString(); // stack trace as a string
    }

}

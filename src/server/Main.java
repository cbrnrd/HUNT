package server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.JSONObject;
import server.helpers.Logging;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import static server.helpers.Logging.log;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        getCountry();  //XXX for testing
        log("---------- NEW PROGRAM RUN ----------", Logging.LogType.INFO);
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("HUNT");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private static void getCountry() {

        String ip = "8.8.8.8";
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
        System.out.println(result);
    }
}

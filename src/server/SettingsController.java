package server;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.IOException;

import javafx.scene.paint.*;
import javafx.scene.paint.Paint;
import server.helpers.Listener;
import server.helpers.Logging;
import server.helpers.Logging.LogType;


import static server.helpers.Logging.log;



/**
 * Created by Carter on 4/24/17.
 */
public class SettingsController {

    @FXML
    private TextField settingsPortField;

    @FXML
    private PasswordField settingsPassField;

    @FXML
    private Label statusLabel;


    public interface colors{
        Paint RED = Paint.valueOf("Firebrick");
        Paint GREEN = Paint.valueOf("Green");
        Paint BLUE = Paint.valueOf("Blue");
    }

    public void onSettingsApply() {


        // Turn the string to an integer
        int port = Integer.parseInt(settingsPortField.getText());

        // Password for server authentication
        String password = settingsPassField.getText();

        try {

            log("Trying to start listener on port " + port, LogType.INFO);

            // Listen in the background
            Thread listener = new Listener(port);
            listener.setDaemon(true);
            listener.start();
            if (listener.isAlive()){
                setSettingsStatus("Listening on port " + port, colors.GREEN);
            } else {
                setSettingsStatus("Unable to start listener, check the log.", colors.RED);
            }
        } catch (IOException ioe){
            try {
                // In case the port is unavailable
                log("Unable to bind to port " + port, LogType.ERROR);
            } catch (IOException e){
                System.err.println("Unable to bind to port " + port);
                e.printStackTrace();
            }
        }
    }


    public void setSettingsStatus(String s, javafx.scene.paint.Paint color){
        statusLabel.setTextFill(color);
        statusLabel.setText(s);
    }
}

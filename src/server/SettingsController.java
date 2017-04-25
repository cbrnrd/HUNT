package server;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

import server.helpers.Listener;
import server.helpers.Logging;
import server.helpers.Logging.LogType;

import com.alertfx.Alerts;

import static server.helpers.Logging.log;



/**
 * Created by Carter on 4/24/17.
 */
public class SettingsController {

    @FXML
    private TextField settingsPortField;

    @FXML
    private PasswordField settingsPassField;


    static server.helpers.Alerts alerts = new server.helpers.Alerts();

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
        } catch (IOException ioe){
            try {
                // In case the port is unavailable
                log("Unable to bind to port " + port, LogType.ERROR);
                alerts.alert();
            } catch (IOException e){
                System.err.println("Unable to bind to port " + port);
                e.printStackTrace();
            }
        }
    }
}

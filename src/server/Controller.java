package server;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class Controller {

    public void onClose(){
        Platform.exit();
    }

    public void onSettings() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("settings.fxml"));

    }

}

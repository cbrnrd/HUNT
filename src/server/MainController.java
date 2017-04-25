package server;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.File;
import java.io.IOException;



public class MainController {



    @FXML
    public static Label statusLabel;


    public void onClose(){
        Platform.exit();
    }

    public void onSettings(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("settings.fxml"));
            Parent root1 =  fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("HUNT Server Settings");
            stage.setScene(new Scene(root1));
            stage.setAlwaysOnTop(true);
            stage.show();
        } catch (IOException ioe){
            System.err.println("Unable to load settings.fxml");
        }
    }

    public static void setStatus(String s){
        statusLabel.setText(s);
    }

    public void onLogOpen(){
        try {
            Desktop.getDesktop().open(new File(System.getProperty("user.dir") + "/.HUNT_LOG.txt"));
        } catch (IOException ioe){
            System.out.println("Unable to open log");
        }
    }


}

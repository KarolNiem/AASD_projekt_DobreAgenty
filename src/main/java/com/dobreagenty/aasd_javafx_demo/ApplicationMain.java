package com.dobreagenty.aasd_javafx_demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlhelloViewLoader = new FXMLLoader(ApplicationMain.class.getResource("/view/hello-view.fxml"));
        Scene sceneHelloView = new Scene(fxmlhelloViewLoader.load(), 800, 600);

        stage.setTitle("DobreAgentyApp");
        stage.setScene(sceneHelloView);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
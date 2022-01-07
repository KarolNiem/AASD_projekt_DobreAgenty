package com.dobreagenty.aasd_javafx_demo;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationMain extends Application {
    private static Stage mainStage;

    public static Stage getMainStage() {
        return ApplicationMain.mainStage;
    }

    private static void setMainStage(Stage stage){
        ApplicationMain.mainStage = stage;
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlhelloViewLoader = new FXMLLoader(ApplicationMain.class.getResource("/view/hello-view.fxml"));
        Scene sceneHelloView = new Scene(fxmlhelloViewLoader.load(), 800, 600);
        stage.setTitle("DobreAgentyApp");
        stage.setScene(sceneHelloView);
        stage.show();
        setMainStage(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}
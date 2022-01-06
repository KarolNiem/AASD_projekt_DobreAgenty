package com.dobreagenty.aasd_javafx_demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader fxmlLoader;

    public void switchToHelloView(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(ApplicationMain.class.getResource("/view/hello-view.fxml"));
        root = fxmlLoader.load();
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void switchToPersonalQuestionnaireView(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(ApplicationMain.class.getResource("/view/personal-questionnaire-view.fxml"));
        root = fxmlLoader.load();
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void switchToIdeaCreatorView(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(ApplicationMain.class.getResource("/view/idea-creator-view.fxml"));
        root = fxmlLoader.load();
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void switchToLoadingScreenView(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(ApplicationMain.class.getResource("/view/loading-screen-view.fxml"));
        root = fxmlLoader.load();
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void switchToIdeaEvaluationView(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(ApplicationMain.class.getResource("/view/idea-evaluation-view.fxml"));
        root = fxmlLoader.load();
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
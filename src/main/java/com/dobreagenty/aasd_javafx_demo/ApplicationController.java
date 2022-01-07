package com.dobreagenty.aasd_javafx_demo;

import com.dobreagenty.misc.DistrictEnum;
import com.dobreagenty.misc.OfferTypeEnum;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ApplicationController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader fxmlLoader;

    @FXML private TextField questionnaireName;
    @FXML private TextField questionnaireSurname;
    @FXML private TextField questionnaireMail;
    @FXML private TextField questionnairePhone;
    @FXML private Label questionnaireEmptyError;

    @FXML private TextField ideaName;
    @FXML private ComboBox<String> ideaType;
    @FXML private ComboBox<String> ideaDistrict;
    @FXML private Label ideaEmptyError;

    private static JSONObject ideaData;
    private static JSONObject customerData;

    public void initializeIdeaTypeComboBox(){
        ArrayList<String> typeList = new ArrayList<String>();
        for(var value : OfferTypeEnum.values()){
            typeList.add(value.toString());
        }
        ideaType.getItems().setAll(typeList);
    }

    public void initializeIdeaDistrictComboBox(){
        ArrayList<String> districtList = new ArrayList<String>();
        for(var value : DistrictEnum.values()){
            districtList.add(value.toString());
        }
        ideaDistrict.getItems().setAll(districtList);
    }

    public void handleQuestionnaireConfirmButton(ActionEvent event){
        if (!questionnaireName.getText().isEmpty() && !questionnaireSurname.getText().isEmpty() && !questionnaireMail.getText().isEmpty() && !questionnairePhone.getText().isEmpty()) {
            try{
                createCustomerData();
                switchToIdeaCreatorView(event);
            }
            catch(IOException e) {
                System.err.println(e);
                e.printStackTrace();
            }
        }
        else {
            questionnaireEmptyError.setText("Aby przejść dalej, wypełnij wszystkie pola.");
        }
    }

    public void handleIdeaConfirmButton(ActionEvent event) {
        if (!ideaName.getText().isEmpty() && ideaType.getValue() != null && ideaDistrict.getValue() != null) {
            try{
                createIdeaData();
                switchToLoadingScreenView(event);
            }
            catch(IOException e) {
                System.err.println(e);
                e.printStackTrace();
            }
        }
        else {
            ideaEmptyError.setText("Aby przejść dalej, wypełnij wszystkie pola.");
        }
    }

    public void createCustomerData(){
        customerData = new JSONObject();
        customerData.put("name",questionnaireName.getText());
        customerData.put("surname",questionnaireSurname.getText());
        customerData.put("email",questionnaireMail.getText());
        customerData.put("phoneNumber",questionnairePhone.getText());
    }

    public void createIdeaData() {
        ideaData = new JSONObject();
        ideaData.put("name", ideaName.getText());
        ideaData.put("type", ideaType.getSelectionModel().getSelectedIndex());
        ideaData.put("district", ideaDistrict.getSelectionModel().getSelectedIndex());
    }

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
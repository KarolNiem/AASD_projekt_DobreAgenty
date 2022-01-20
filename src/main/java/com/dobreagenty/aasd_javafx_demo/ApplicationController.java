package com.dobreagenty.aasd_javafx_demo;

import com.dobreagenty.AgentThread;
import com.dobreagenty.misc.DistrictEnum;
import com.dobreagenty.misc.OfferTypeEnum;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.text.DecimalFormat;

public class ApplicationController implements EvaluationListener {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader fxmlLoader;

    @FXML
    private TextField questionnaireName;
    @FXML
    private TextField questionnaireSurname;
    @FXML
    private TextField questionnaireMail;
    @FXML
    private TextField questionnairePhone;
    @FXML
    private Label questionnaireEmptyError;

    @FXML
    private TextField ideaName;
    @FXML
    private ComboBox<String> ideaType;
    @FXML
    private ComboBox<String> ideaDistrict;
    @FXML
    private Label ideaEmptyError;

    @FXML
    private Label globalEvaluationText;

    @FXML
    private ImageView globalEvaluationCircleNr1;
    @FXML
    private ImageView globalEvaluationCircleNr2;
    @FXML
    private ImageView globalEvaluationCircleNr3;
    @FXML
    private ImageView globalEvaluationCircleNr4;
    @FXML
    private ImageView globalEvaluationCircleNr5;

    @FXML
    private Label costEvaluationText;

    @FXML
    private ImageView costEvaluationCircleNr1;
    @FXML
    private ImageView costEvaluationCircleNr2;
    @FXML
    private ImageView costEvaluationCircleNr3;
    @FXML
    private ImageView costEvaluationCircleNr4;
    @FXML
    private ImageView costEvaluationCircleNr5;

    @FXML
    private Label ageEvaluationText;

    @FXML
    private ImageView ageEvaluationCircleNr1;
    @FXML
    private ImageView ageEvaluationCircleNr2;
    @FXML
    private ImageView ageEvaluationCircleNr3;
    @FXML
    private ImageView ageEvaluationCircleNr4;
    @FXML
    private ImageView ageEvaluationCircleNr5;

    @FXML
    private Label usabilityEvaluationText;

    @FXML
    private ImageView usabilityEvaluationCircleNr1;
    @FXML
    private ImageView usabilityEvaluationCircleNr2;
    @FXML
    private ImageView usabilityEvaluationCircleNr3;
    @FXML
    private ImageView usabilityEvaluationCircleNr4;
    @FXML
    private ImageView usabilityEvaluationCircleNr5;

    @FXML
    private final Image greyCircleImage = new Image(getClass().getResourceAsStream("/image/grey-circle.png"),
            50, 50, false, false);
    @FXML
    private final Image redCircleImage = new Image(getClass().getResourceAsStream("/image/red-circle.png"),
            50, 50, false, false);
    @FXML
    private final Image blueCircleImage = new Image(getClass().getResourceAsStream("/image/blue-circle.png"),
            50, 50, false, false);
    @FXML
    private final Image redHalfCircleImage = new Image(getClass().getResourceAsStream("/image/red-half-circle.png"),
            50, 50, false, false);
    @FXML
    private final Image blueHalfCircleImage = new Image(getClass().getResourceAsStream("/image/blue-half-circle.png"),
            50, 50, false, false);

    private static JSONObject ideaData;
    private static JSONObject customerData;
    private static double[] eval;

    @Override
    public void onEvent() {
        Platform.runLater(() -> {
            try {
                switchToIdeaEvaluationView();
            } catch (IOException e) {
                System.err.println(e);
                e.printStackTrace();
            }
        });
    }

    public void initializeIdeaTypeComboBox() {
        ArrayList<String> typeList = new ArrayList<>();
        for (var value : OfferTypeEnum.values()) {
            typeList.add(value.toString());
        }
        ideaType.getItems().setAll(typeList);
    }

    public void initializeIdeaDistrictComboBox() {
        ArrayList<String> districtList = new ArrayList<>();
        for (var value : DistrictEnum.values()) {
            if (value != DistrictEnum.Average) {
                districtList.add(value.toString());
            }
        }
        ideaDistrict.getItems().setAll(districtList);
    }

    public void handleQuestionnaireConfirmButton(ActionEvent event) {
        if (!questionnaireName.getText().isEmpty() && !questionnaireSurname.getText().isEmpty() && !questionnaireMail.getText().isEmpty() && !questionnairePhone.getText().isEmpty()) {
            try {
                createCustomerData();
                switchToIdeaCreatorView(event);
            } catch (IOException e) {
                System.err.println(e);
                e.printStackTrace();
            }
        } else {
            questionnaireEmptyError.setText("Aby przejść dalej, wypełnij wszystkie pola.");
        }
    }

    public void handleIdeaConfirmButton(ActionEvent event) {
        if (!ideaName.getText().isEmpty() && ideaType.getValue() != null && ideaDistrict.getValue() != null) {
            try {
                createIdeaData();
                switchToLoadingScreenView(event);

                AgentThread agentThread = new AgentThread(ideaData, customerData, this);
                agentThread.start();
            } catch (IOException e) {
                System.err.println(e);
                e.printStackTrace();
            }
        } else {
            ideaEmptyError.setText("Aby przejść dalej, wypełnij wszystkie pola.");
        }
    }

    public void createCustomerData() {
        customerData = new JSONObject();
        customerData.put("name", questionnaireName.getText());
        customerData.put("surname", questionnaireSurname.getText());
        customerData.put("email", questionnaireMail.getText());
        customerData.put("phoneNumber", questionnairePhone.getText());
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
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void switchToPersonalQuestionnaireView(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(ApplicationMain.class.getResource("/view/personal-questionnaire-view.fxml"));
        root = fxmlLoader.load();
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void switchToIdeaCreatorView(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(ApplicationMain.class.getResource("/view/idea-creator-view.fxml"));
        root = fxmlLoader.load();
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void switchToLoadingScreenView(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(ApplicationMain.class.getResource("/view/loading-screen-view.fxml"));
        root = fxmlLoader.load();
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void switchToIdeaEvaluationView() throws IOException {
        fxmlLoader = new FXMLLoader(ApplicationMain.class.getResource("/view/idea-evaluation-view.fxml"));
        root = fxmlLoader.load();
        scene = new Scene(root);
        stage = ApplicationMain.getMainStage();
        stage.setScene(scene);
        stage.show();
    }

    public void switchToEvaluationReportView() throws IOException {
        fxmlLoader = new FXMLLoader(ApplicationMain.class.getResource("/view/evaluation-report-view.fxml"));
        root = fxmlLoader.load();
        scene = new Scene(root);
        ApplicationController controller = fxmlLoader.getController();
        controller.colorGlobalEvaluationCircles(eval[0]);
        controller.writeGlobalEvaluationText();
        controller.colorCostEvaluationCircles(eval[1]);
        controller.writeCostEvaluationText();
        controller.colorAgeEvaluationCircles(eval[2]);
        controller.writeAgelEvaluationText();
        controller.colorUsabilityEvaluationCircles(eval[3]);
        controller.writeUsabilityEvaluationText();
        stage = ApplicationMain.getMainStage();
        stage.setScene(scene);
        stage.show();
    }

    public void handleEvaluationText(ActionEvent event) {
        eval = AgentThread.getEvaluationValue();
        DecimalFormat df = new DecimalFormat("#.###");
        if (globalEvaluationText != null && eval[0] != 0) {
            globalEvaluationText.setText("Ocena ogólna: " + df.format(eval[0]));
        }
        colorGlobalEvaluationCircles(eval[0]);
    }

    private double[] setColoredCircles(double evaluation) {
        double[] coloredCircles = new double[5];
        coloredCircles[0] = 0.5;
        if (evaluation >= 0.1) coloredCircles[0] = 1;
        if (evaluation >= 0.2) coloredCircles[1] = .5;
        if (evaluation >= 0.3) coloredCircles[1] = 1;
        if (evaluation >= 0.4) coloredCircles[2] = .5;
        if (evaluation >= 0.5) coloredCircles[2] = 1;
        if (evaluation >= 0.6) coloredCircles[3] = .5;
        if (evaluation >= 0.7) coloredCircles[3] = 1;
        if (evaluation >= 0.8) coloredCircles[4] = .5;
        if (evaluation >= 0.9) coloredCircles[4] = 1;
        return coloredCircles;
    }

    private void colorGlobalEvaluationCircles(double evaluation){
        double[] coloredCircles = setColoredCircles(evaluation);

        if (coloredCircles[0] == 0)
            globalEvaluationCircleNr1.setImage(greyCircleImage);
        else if (coloredCircles[0] == 0.5)
            globalEvaluationCircleNr1.setImage(redHalfCircleImage);
        else
            globalEvaluationCircleNr1.setImage(redCircleImage);

        if (coloredCircles[1] == 0)
            globalEvaluationCircleNr2.setImage(greyCircleImage);
        else if (coloredCircles[1] == 0.5)
            globalEvaluationCircleNr2.setImage(redHalfCircleImage);
        else
            globalEvaluationCircleNr2.setImage(redCircleImage);

        if (coloredCircles[2] == 0)
            globalEvaluationCircleNr3.setImage(greyCircleImage);
        else if (coloredCircles[2] == 0.5)
            globalEvaluationCircleNr3.setImage(redHalfCircleImage);
        else
            globalEvaluationCircleNr3.setImage(redCircleImage);

        if (coloredCircles[3] == 0)
            globalEvaluationCircleNr4.setImage(greyCircleImage);
        else if (coloredCircles[3] == 0.5)
            globalEvaluationCircleNr4.setImage(redHalfCircleImage);
        else
            globalEvaluationCircleNr4.setImage(redCircleImage);

        if (coloredCircles[4] == 0)
            globalEvaluationCircleNr5.setImage(greyCircleImage);
        else if (coloredCircles[4] == 0.5)
            globalEvaluationCircleNr5.setImage(redHalfCircleImage);
        else
            globalEvaluationCircleNr5.setImage(redCircleImage);
    }

    private void colorCostEvaluationCircles(double evaluation){
        double[] coloredCircles = setColoredCircles(evaluation);

        if (coloredCircles[0] == 0)
            costEvaluationCircleNr1.setImage(greyCircleImage);
        else if (coloredCircles[0] == 0.5)
            costEvaluationCircleNr1.setImage(blueHalfCircleImage);
        else
            costEvaluationCircleNr1.setImage(blueCircleImage);

        if (coloredCircles[1] == 0)
            costEvaluationCircleNr2.setImage(greyCircleImage);
        else if (coloredCircles[1] == 0.5)
            costEvaluationCircleNr2.setImage(blueHalfCircleImage);
        else
            costEvaluationCircleNr2.setImage(blueCircleImage);

        if (coloredCircles[2] == 0)
            costEvaluationCircleNr3.setImage(greyCircleImage);
        else if (coloredCircles[2] == 0.5)
            costEvaluationCircleNr3.setImage(blueHalfCircleImage);
        else
            costEvaluationCircleNr3.setImage(blueCircleImage);

        if (coloredCircles[3] == 0)
            costEvaluationCircleNr4.setImage(greyCircleImage);
        else if (coloredCircles[3] == 0.5)
            costEvaluationCircleNr4.setImage(blueHalfCircleImage);
        else
            costEvaluationCircleNr4.setImage(blueCircleImage);

        if (coloredCircles[4] == 0)
            costEvaluationCircleNr5.setImage(greyCircleImage);
        else if (coloredCircles[4] == 0.5)
            costEvaluationCircleNr5.setImage(blueHalfCircleImage);
        else
            costEvaluationCircleNr5.setImage(blueCircleImage);
    }

    private void colorAgeEvaluationCircles(double evaluation){
        double[] coloredCircles = setColoredCircles(evaluation);

        if (coloredCircles[0] == 0)
            ageEvaluationCircleNr1.setImage(greyCircleImage);
        else if (coloredCircles[0] == 0.5)
            ageEvaluationCircleNr1.setImage(blueHalfCircleImage);
        else
            ageEvaluationCircleNr1.setImage(blueCircleImage);

        if (coloredCircles[1] == 0)
            ageEvaluationCircleNr2.setImage(greyCircleImage);
        else if (coloredCircles[1] == 0.5)
            ageEvaluationCircleNr2.setImage(blueHalfCircleImage);
        else
            ageEvaluationCircleNr2.setImage(blueCircleImage);

        if (coloredCircles[2] == 0)
            ageEvaluationCircleNr3.setImage(greyCircleImage);
        else if (coloredCircles[2] == 0.5)
            ageEvaluationCircleNr3.setImage(blueHalfCircleImage);
        else
            ageEvaluationCircleNr3.setImage(blueCircleImage);

        if (coloredCircles[3] == 0)
            ageEvaluationCircleNr4.setImage(greyCircleImage);
        else if (coloredCircles[3] == 0.5)
            ageEvaluationCircleNr4.setImage(blueHalfCircleImage);
        else
            ageEvaluationCircleNr4.setImage(blueCircleImage);

        if (coloredCircles[4] == 0)
            ageEvaluationCircleNr5.setImage(greyCircleImage);
        else if (coloredCircles[4] == 0.5)
            ageEvaluationCircleNr5.setImage(blueHalfCircleImage);
        else
            ageEvaluationCircleNr5.setImage(blueCircleImage);
    }

    private void colorUsabilityEvaluationCircles(double evaluation){
        double[] coloredCircles = setColoredCircles(evaluation);

        if (coloredCircles[0] == 0)
            usabilityEvaluationCircleNr1.setImage(greyCircleImage);
        else if (coloredCircles[0] == 0.5)
            usabilityEvaluationCircleNr1.setImage(blueHalfCircleImage);
        else
            usabilityEvaluationCircleNr1.setImage(blueCircleImage);

        if (coloredCircles[1] == 0)
            usabilityEvaluationCircleNr2.setImage(greyCircleImage);
        else if (coloredCircles[1] == 0.5)
            usabilityEvaluationCircleNr2.setImage(blueHalfCircleImage);
        else
            usabilityEvaluationCircleNr2.setImage(blueCircleImage);

        if (coloredCircles[2] == 0)
            usabilityEvaluationCircleNr3.setImage(greyCircleImage);
        else if (coloredCircles[2] == 0.5)
            usabilityEvaluationCircleNr3.setImage(blueHalfCircleImage);
        else
            usabilityEvaluationCircleNr3.setImage(blueCircleImage);

        if (coloredCircles[3] == 0)
            usabilityEvaluationCircleNr4.setImage(greyCircleImage);
        else if (coloredCircles[3] == 0.5)
            usabilityEvaluationCircleNr4.setImage(blueHalfCircleImage);
        else
            usabilityEvaluationCircleNr4.setImage(blueCircleImage);

        if (coloredCircles[4] == 0)
            usabilityEvaluationCircleNr5.setImage(greyCircleImage);
        else if (coloredCircles[4] == 0.5)
            usabilityEvaluationCircleNr5.setImage(blueHalfCircleImage);
        else
            usabilityEvaluationCircleNr5.setImage(blueCircleImage);
    }

    public void writeGlobalEvaluationText() {
        DecimalFormat df = new DecimalFormat("#.###");
        if (globalEvaluationText != null && eval[0] != 0) {
            globalEvaluationText.setText("Ocena: " + df.format(eval[0]));
        }
    }

    public void writeCostEvaluationText() {
        DecimalFormat df = new DecimalFormat("#.###");
        if (costEvaluationText != null && eval[1] != 0) {
            costEvaluationText.setText("Ocena: " + df.format(eval[1]));
        }
    }

    public void writeAgelEvaluationText() {
        DecimalFormat df = new DecimalFormat("#.###");
        if (ageEvaluationText != null && eval[2] != 0) {
            ageEvaluationText.setText("Ocena: " + df.format(eval[2]));
        }
    }

    public void writeUsabilityEvaluationText() {
        DecimalFormat df = new DecimalFormat("#.###");
        if (usabilityEvaluationText != null && eval[3] != 0) {
            usabilityEvaluationText.setText("Ocena: " + df.format(eval[3]));
        }
    }
}

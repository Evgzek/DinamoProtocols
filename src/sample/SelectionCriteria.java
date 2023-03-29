package sample;

import java.io.IOException;
import java.net.URL;
import java.text.Collator;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SelectionCriteria {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button liveOn;

    @FXML
    private ImageView imageLogo;

    @FXML
    private RadioButton men;

    @FXML
    private RadioButton shootAndRun;

    @FXML
    private Button back;

    @FXML
    private RadioButton run;

    @FXML
    private ImageView imageBack;

    @FXML
    private RadioButton women;

    private ToggleGroup group;
    private ToggleGroup group1;
    public static String pol;
    public static String stage1;
    public static String vid;

    @FXML
    public void initialize() {
        group = new ToggleGroup();
        group1 = new ToggleGroup();
        men.setToggleGroup(group);
        women.setToggleGroup(group);
        shootAndRun.setToggleGroup(group1);
        run.setToggleGroup(group1);
    }

    @FXML
    public void back() throws IOException {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/sample.fxml"));
        Parent root = (Parent) loader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void liveOn() throws IOException {
        if (group.getSelectedToggle() != null && group1.getSelectedToggle() != null){
            RadioButton button = (RadioButton) group.getSelectedToggle();
            RadioButton button1 = (RadioButton) group1.getSelectedToggle();
            pol = button.getText();
            vid = button1.getText();
            String str1 = "Двоеборье";
            String str2 = button1.getText();
            Collator collator = Collator.getInstance(new Locale("ru", "RU"));
            collator.setStrength(Collator.PRIMARY);
            int result = collator.compare(str1, str2);
            if (result < 0){
                stage1 = "/sample/create.fxml";
            }else if (result == 0){
                stage1 = "/sample/createTwo.fxml";
            }else {
            }
            Stage stage = (Stage) liveOn.getScene().getWindow();
            stage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource(stage1));
            Parent root = (Parent) loader.load();
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        }else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Не все критерии выбраны!!!");
            alert.showAndWait();
        }



    }
}


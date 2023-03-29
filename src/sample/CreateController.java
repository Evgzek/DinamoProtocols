package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CreateController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView imageLogo;

    @FXML
    private Button newParticipants;

    @FXML
    private Button newListParticipants;

    @FXML
    private Button resultPaticipants;

    @FXML
    private Button back;

    @FXML
    private Button home;


    @FXML
    void initialize() {

    }

    @FXML
    public void setHome () throws IOException {
        Stage stage = (Stage) home.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/sample.fxml"));
        Parent root = (Parent) loader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void back(javafx.event.ActionEvent event) throws IOException {

        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/selectionCriteria.fxml"));
        Parent root = (Parent) loader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void createAction(javafx.event.ActionEvent event) throws IOException{
        Stage stage = (Stage) newParticipants.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/newParticipants.fxml"));
        Parent root = (Parent) loader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void createActionList(javafx.event.ActionEvent event) throws IOException{
        Stage stage = (Stage) newListParticipants.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/newListParticipants.fxml"));
        Parent root = (Parent) loader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void createActionResult(javafx.event.ActionEvent event) throws IOException{
        Stage stage = (Stage) resultPaticipants.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/resultParticipant.fxml"));
        Parent root = (Parent) loader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.show();
    }

}


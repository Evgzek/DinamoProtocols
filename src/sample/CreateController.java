package sample;

import java.awt.event.ActionEvent;
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
    void initialize() {

    }

    @FXML
    public void back(javafx.event.ActionEvent event) throws IOException {

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
        Stage stage = (Stage) newParticipants.getScene().getWindow();
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
        Stage stage = (Stage) newParticipants.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/resultParticipant.fxml"));
        Parent root = (Parent) loader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.show();
    }


}
//        newParticipants.setOnAction(event -> {
//            newParticipants.getScene().getWindow().hide();
//
//
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("/sample/newParticipants.fxml"));
//            try {
//                loader.load();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            Parent root = loader.getRoot();
//            Stage stage = new Stage();
//            stage.setScene(new Scene(root, 700, 400));
//            stage.setTitle("DinPro");
//            stage.showAndWait();
//        });


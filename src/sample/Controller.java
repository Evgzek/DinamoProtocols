package sample;

import com.itextpdf.text.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button preview;

    @FXML
    private Button deleteParticipants;

    @FXML
    private Button deleteTeams;

    @FXML
    private ImageView imageLogo;

    @FXML
    private Button create;

    @FXML
    private Label title;

    @FXML
    private Button teams_vk;

    @FXML
    void initialize() {
//        create.setOnAction(event -> {
//            create.getScene().getWindow().hide();
//
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("/sample/create.fxml"));
//
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

    }

    @FXML
    public void createProtocols(javafx.event.ActionEvent event) throws IOException{
        Stage stage = (Stage) create.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/selectionCriteria.fxml"));
        Parent root = (Parent) loader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void viewProtocols(javafx.event.ActionEvent event) throws IOException {
        Stage stage = (Stage) preview.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/viewProtocols.fxml"));
        Parent root = (Parent) loader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void deleteParticipans() throws ParseException, SQLException, ClassNotFoundException {
        DatabaseHandler db = new DatabaseHandler();
        db.test(1);
    }

    public void setDeleteTeams() throws ParseException, SQLException, ClassNotFoundException {
        DatabaseHandler db = new DatabaseHandler();
        db.test(2);
    }

    public void teamsVk() throws IOException{

        Stage stage = (Stage) teams_vk.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/newTeamVk.fxml"));
        Parent root = (Parent) loader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.show();
    }

}



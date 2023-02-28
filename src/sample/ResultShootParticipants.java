package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ResultShootParticipants {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView imageLogo;

    @FXML
    private TextField firstShoot;

    @FXML
    private TextField enterNumberParticipant;

    @FXML
    private Button chargeResultParticipant;

    @FXML
    private TextField secondShoot;

    @FXML
    private Button back;

    @FXML
    private Button loadResultParticipantProtocol;

    @FXML
    private ImageView imageBack;



    @FXML
    void initialize() {

    }

    @FXML
    public void back() throws IOException {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/resultParticipant.fxml"));
        Parent root = (Parent) loader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void setLoadResultParticipantProtocol() throws SQLException, ClassNotFoundException {
        if (enterNumberParticipant.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Не указан номер участника!");
            alert.showAndWait();
        }else if (firstShoot.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Не указано количество баллов по 1-ой стрельбе!");
            alert.showAndWait();
        }else if (secondShoot.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Не указано колличество баллов по 2-ой стрельбе!");
            alert.showAndWait();
        }else {
            DatabaseHandler db = new DatabaseHandler();
            double shoot = Integer.parseInt(firstShoot.getText()) + Integer.parseInt(secondShoot.getText());
            double number = Integer.parseInt(enterNumberParticipant.getText());
            db.addShootResultParticipant(shoot, number, Const.TWO_PARTICIPANTS_TABLE, Const.PARTICIPANTS_SHOOT);
            db.addShootResultPointsParticipant(shoot, Const.TWO_PARTICIPANTS_TABLE, number);
            enterNumberParticipant.clear();
            firstShoot.clear();
            secondShoot.clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Результат участника успешно добавлен");
            alert.showAndWait();
        }
    }


}


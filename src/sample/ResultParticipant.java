package sample;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class ResultParticipant {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView imageLogo;

    @FXML
    private TextField enterNumberParticipant;

    @FXML
    private Button chargeResultParticipant;

    @FXML
    private TextField resultTens;

    @FXML
    private TextField resultSeconds;

    @FXML
    private TextField resultMinute;

    @FXML
    private Button loadResultParticipantProtocol;

    @FXML
    void initialize() {
    }

    @FXML
    public void resultParticipant() throws SQLException, ClassNotFoundException {
        if (enterNumberParticipant.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Не указан номер участника!");
            alert.showAndWait();
        }else if (resultMinute.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Не колличество минут!");
            alert.showAndWait();
        }else if (resultSeconds.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Не колличество секунд!");
            alert.showAndWait();
        }
        DatabaseHandler db = new DatabaseHandler();
        double number = Integer.parseInt(enterNumberParticipant.getText());
        int j = Integer.parseInt(resultTens.getText());
        if (j < 5){
            j = 0;
        }else if (j < 10){
            j = 10;
        }
        db.addResultParticipant(Integer.parseInt(resultMinute.getText()), Integer.parseInt(resultSeconds.getText()),
                j, number);
        double h = Integer.parseInt(resultTens.getText());
        double time = (Integer.parseInt(resultMinute.getText()) * 60) + Integer.parseInt(resultSeconds.getText()) + h/100;
        System.out.println(time);
        db.addResultKefParticipant(number, time);
        enterNumberParticipant.clear();
        resultTens.clear();
        resultMinute.clear();
        resultSeconds.clear();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Результат участника успешно добавлен");
        alert.showAndWait();
    }

    @FXML
    public void chargeResultParticipant() throws SQLException, ClassNotFoundException{
        DatabaseHandler db = new DatabaseHandler();
        double number = Integer.parseInt(enterNumberParticipant.getText());
        int j = Integer.parseInt(resultTens.getText());
        if (j < 5){
            j = 0;
        }else if (j < 10){
            j = 10;
        }
        db.addResultParticipant(Integer.parseInt(resultMinute.getText()), Integer.parseInt(resultSeconds.getText()),
                j, number);
        double h = Integer.parseInt(resultTens.getText());
        double time = (Integer.parseInt(resultMinute.getText()) * 60) + Integer.parseInt(resultSeconds.getText()) + h/100;
        db.addResultKefParticipant(number, time);
        enterNumberParticipant.clear();
        resultTens.clear();
        resultMinute.clear();
        resultSeconds.clear();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Результат участника успешно изменен");
        alert.showAndWait();
    }
}


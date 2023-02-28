package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.Collator;
import java.util.Locale;
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
    private Button back;

    @FXML
    private Button shoot;

    @FXML
    void initialize() {
        Collator collator = Collator.getInstance(new Locale("ru", "RU"));
        collator.setStrength(Collator.PRIMARY);
        String str3 = "Кросс";
        String str4 = SelectionCriteria.vid;
        int result = collator.compare(str3, str4);
        if (result == 0){
            shoot.setVisible(false);
        }
    }

    @FXML
    public void back(javafx.event.ActionEvent event) throws IOException {

        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource(SelectionCriteria.stage1));
        Parent root = (Parent) loader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void setShoot(javafx.event.ActionEvent event) throws IOException {

        Stage stage = (Stage) shoot.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/resultShootParticipants.fxml"));
        Parent root = (Parent) loader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.show();
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
            alert.setContentText("Не указано колличество минут!");
            alert.showAndWait();
        }else if (resultSeconds.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Не указано колличество секунд!");
            alert.showAndWait();
        }else if (resultTens.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Не указано колличество сотых!");
            alert.showAndWait();
        }else {
            int e = 0;
            DatabaseHandler db = new DatabaseHandler();
            double number = Integer.parseInt(enterNumberParticipant.getText());
            int j = Integer.parseInt(resultTens.getText());
            Collator collator = Collator.getInstance(new Locale("ru", "RU"));
            collator.setStrength(Collator.PRIMARY);
            String str3 = "Двоеборье";
            String str4 = SelectionCriteria.vid;
            String table = "";
            int result = collator.compare(str3, str4);
            if (result < 0){
                table = Const.PARTICIPANTS_TABLE;
            }else if (result == 0){
                table = Const.TWO_PARTICIPANTS_TABLE;
                e = 1;
            }
            db.addResultParticipant(Integer.parseInt(resultMinute.getText()), Integer.parseInt(resultSeconds.getText()),
                    j, number, table, Const.PARTICIPANTS_RESULT);
            double h = Integer.parseInt(resultTens.getText());
            double time = (Integer.parseInt(resultMinute.getText()) * 60) + Integer.parseInt(resultSeconds.getText()) + h/100;
            System.out.println(time);
            if (e == 1){
                db.addRunResultPointsParticipant(time, table, Const.PARTICIPANTS_RESULT_POINTS, number);
            }
            db.addResultKefParticipant(number, time, h, table, e);
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
    }

    @FXML
    public void chargeResultParticipant() throws SQLException, ClassNotFoundException{
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
            alert.setContentText("Не указано колличество минут!");
            alert.showAndWait();
        }else if (resultSeconds.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Не указано колличество секунд!");
            alert.showAndWait();
        }else if (resultTens.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Не указано колличество сотых!");
            alert.showAndWait();
        }else{
            DatabaseHandler db = new DatabaseHandler();
            int e = 0;
            double number = Integer.parseInt(enterNumberParticipant.getText());
            int j = Integer.parseInt(resultTens.getText());
            Collator collator = Collator.getInstance(new Locale("ru", "RU"));
            collator.setStrength(Collator.PRIMARY);
            String str3 = "Двоеборье";
            String str4 = SelectionCriteria.vid;
            String table = "";
            int result = collator.compare(str3, str4);
            if (result < 0){
                table = Const.PARTICIPANTS_TABLE;
            }else if (result == 0){
                table = Const.TWO_PARTICIPANTS_TABLE;
                e = 1;
            }
            db.addResultParticipant(Integer.parseInt(resultMinute.getText()), Integer.parseInt(resultSeconds.getText()),
                    j, number, table, Const.PARTICIPANTS_RESULT);
            double h = Integer.parseInt(resultTens.getText());
            double time = (Integer.parseInt(resultMinute.getText()) * 60) + Integer.parseInt(resultSeconds.getText()) + h/100;
            System.out.println(time);
            if (e == 1){
                db.addRunResultPointsParticipant(time, table, Const.PARTICIPANTS_RESULT_POINTS, number);
            }
            db.addResultKefParticipant(number, time, h, table, e);
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


}


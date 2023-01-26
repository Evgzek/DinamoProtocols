package sample;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NewParticipants {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView imageLogo;

    @FXML
    private TextField create_team;

    @FXML
    private TextField create_year;

    @FXML
    private Button new_participants;

    @FXML
    private TextField create_number;

    @FXML
    private TextField create_name;

    @FXML
    private Button back;

    @FXML
    void initialize() {}

    @FXML
    public void back(javafx.event.ActionEvent event) throws IOException {

        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/create.fxml"));
        Parent root = (Parent) loader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void dbAction(javafx.event.ActionEvent event){

        if (create_team.getText().equals("") || create_name.getText().equals("") || create_number.getText().equals("") || create_year.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Не все поля для добавления участника заполнены!!!");
            alert.showAndWait();
        }
        else {
            DatabaseHandler dbHandler = new DatabaseHandler();
            int year = Integer.parseInt(create_year.getText());
            Calendar calendar = Calendar.getInstance();
            int now_year = calendar.get(Calendar.YEAR);
            String group = "";
            double kef = 0;
            int n = now_year - year;
            if (n < 30){
                group = Const.PARTICIPANTS_GROUP1;
                kef = Const.PARTICIPANTS_KEF1;
            }else if (n < 35){
                group = Const.PARTICIPANTS_GROUP2;
                kef = Const.PARTICIPANTS_KEF2;
            }else if (n < 40){
                group = Const.PARTICIPANTS_GROUP3;
                kef = Const.PARTICIPANTS_KEF3;
            }else if (n < 45){
                group = Const.PARTICIPANTS_GROUP4;
                kef = Const.PARTICIPANTS_KEF4;
            }else if (n < 50){
                group = Const.PARTICIPANTS_GROUP5;
                kef = Const.PARTICIPANTS_KEF5;
            }else {
                group = Const.PARTICIPANTS_GROUP6;
                kef = Const.PARTICIPANTS_KEF6;
            }
            dbHandler.newParticipants(Integer.parseInt(create_number.getText()), create_name.getText(), create_team.getText(), Integer.parseInt(create_year.getText()),
                    group, kef);
            create_number.clear();
            create_name.clear();
            create_year.clear();
            create_team.clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Участник успешно добавлен");
            alert.showAndWait();
        }

    }
}


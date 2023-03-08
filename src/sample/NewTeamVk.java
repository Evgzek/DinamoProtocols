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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NewTeamVk {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView imageLogo;

    @FXML
    private TextField newTeam;

    @FXML
    private Label title;

    @FXML
    private Button newTeamVk;

    @FXML
    private Button back;

    public static int proverka = 0;
    public static String team_vk = "";


    @FXML
    void initialize() {

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
    public void setNewTeam() throws SQLException, ClassNotFoundException {
        String team = "";
        if (newTeam.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Не введена команда!!!");
            alert.showAndWait();
        }else {
            team = newTeam.getText();
        }

        DatabaseHandler db = new DatabaseHandler();
        db.proverkaTeam(team);
        if (proverka == 1){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Эта команда уже участвует!!!");
            alert.showAndWait();
            newTeam.clear();
        }else {
            db.newTeamsVk(team);
            newTeam.clear();
        }

    }
}


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

public class ViewProtocols {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button teamsProtocols;

    @FXML
    private ImageView imageLogo;

    @FXML
    private Button individProcotols;


    @FXML
    void initialize() {

    }

    @FXML
    public void viewIndividProtocol (javafx.event.ActionEvent event) throws IOException {
        Stage stage = (Stage) individProcotols.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/viewIndividProtocols.fxml"));
        Parent root = (Parent) loader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    public void viewTeamsProtocol (javafx.event.ActionEvent event) throws IOException {
        Stage stage = (Stage) teamsProtocols.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/viewTeamsProtocols.fxml"));
        Parent root = (Parent) loader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.show();
    }
}


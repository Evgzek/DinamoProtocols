package sample;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ViewTeamsProtocols {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button teamsProtocols;

    @FXML
    private ImageView imageLogo;

    @FXML
    private Label performance;

    @FXML
    private MenuItem race_2;

    @FXML
    private MenuButton choice_race_teams;

    @FXML
    private MenuItem race_1;

    private int sorev = 0;

    @FXML
    private Button back;

    @FXML
    private Button home;

    @FXML
    private TextField data;

    @FXML
    public void setRace_1(){
        choice_race_teams.setText(race_1.getText());
        sorev = 1;//kroos
    }

    @FXML
    public void setRace_2(){
        choice_race_teams.setText(race_2.getText());
        sorev = 2;//two_borie
    }



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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/viewProtocols.fxml"));
        Parent root = (Parent) loader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void setTeamsProtocols() throws SQLException, ClassNotFoundException, IOException {
        String table = "";
        String f = "";
        String vid = "";
        int i = 0;
        if (sorev == 1){
            table = Const.NEW_PARTICIPANTS_TABLE;
            String dir = System.getProperty("user.dir");
            File filen = new File(dir + "/src/sample/xlsx/pr_teams_k.xlsx");
            f = filen.getAbsolutePath();
            i = 10;
            vid = "k";
        }else if (sorev == 2){
            table = Const.TWO_PARTICIPANTS_TABLE;
            String dir = System.getProperty("user.dir");
            File filen = new File(dir + "/src/sample/xlsx/pr_teams_d.xlsx");
            f = filen.getAbsolutePath();
            i = 12;
            vid = "d";
        }
        DatabaseHandler db = new DatabaseHandler();
        if (data.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Не введена дата!!!");
            alert.showAndWait();
        }else if (sorev == 0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Не указан вид забега, по которому нужен протокол!!!");
            alert.showAndWait();
        } else{
            db.teamProc(table, vid);

            String selectFile = "";
            Stage stage1 = new Stage();
            stage1.setTitle("AAA");
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel File", "*.xlsx"));
            File file = fileChooser.showSaveDialog(stage1);
            if (file != null){
                selectFile = file.getAbsolutePath();
                System.out.println(selectFile);
            }

            FileInputStream file1 = new FileInputStream(f);
            XSSFWorkbook wb = new XSSFWorkbook(file1);
            FileOutputStream out = new FileOutputStream(new File(selectFile));
            wb.write(out);
            file1.close();
            out.close();
            db.convertTeam(selectFile, i, vid, 3, data.getText());
            data.clear();
        }

    }
}


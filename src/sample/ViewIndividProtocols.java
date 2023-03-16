package sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ViewIndividProtocols {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button individProtocols;

    @FXML
    private ImageView imageLogo;

    @FXML
    private Label performance;

    @FXML
    private MenuButton what;

    @FXML
    private MenuItem choice_2;

    @FXML
    private MenuItem race_2;

    @FXML
    private MenuItem all_race;

    @FXML
    private MenuButton choice;

    @FXML
    private MenuButton choice_race;

    @FXML
    private MenuItem race_1;

    @FXML
    private MenuItem what_1;

    @FXML
    private MenuItem choice_1;

    @FXML
    private MenuItem what_2;

    @FXML
    private MenuItem what_3;

    @FXML
    private MenuItem what_4;

    @FXML
    private MenuItem what_5;

    @FXML
    private MenuItem what_6;

    private int zabeg = 0;
    private int male = 0;
    private int sorev = 0;

    @FXML
    private Button back;

    @FXML
    private Button home;

    @FXML
    private TextField startZabega;

    @FXML
    private TextField data;


    @FXML
    void initialize() {
        startZabega.setVisible(false);
    }

    @FXML
    public void setChoice_one(){
        choice.setText(choice_1.getText());
        male = 1;//men
    }

    @FXML
    public void setChoice_two(){
        choice.setText(choice_2.getText());
        male = 2;//women
    }

    @FXML
    public void setRace_1(){
        choice_race.setText(race_1.getText());
        sorev = 1;//kroos
    }

    @FXML
    public void setRace_2(){
        choice_race.setText(race_2.getText());
        sorev = 2;//two_borie
    }

    @FXML
    public void setWhat_1(){
        what.setText(what_1.getText());
        zabeg = 1;
        startZabega.setVisible(true);
    }

    @FXML
    public void setWhat_3(){
        what.setText(what_3.getText());
        zabeg = 3;
        startZabega.setVisible(true);
    }

    @FXML
    public void setWhat_4(){
        what.setText(what_4.getText());
        zabeg = 4;
        startZabega.setVisible(true);
    }

    @FXML
    public void setWhat_5(){
        what.setText(what_5.getText());
        zabeg = 5;
        startZabega.setVisible(true);
    }

    @FXML
    public void setWhat_6(){
        what.setText(what_6.getText());
        zabeg = 6;
        startZabega.setVisible(true);
    }

    @FXML
    public void setWhat_2(){
        what.setText(what_2.getText());
        zabeg = 2;
        startZabega.setVisible(true);
    }

    @FXML
    public void setAll_race(){
        what.setText(all_race.getText());
        zabeg = 10;
        startZabega.setVisible(false);
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
    public void setIndividProtocols() throws SQLException, ClassNotFoundException, IOException, InvalidFormatException {
        String selectFile = "";
        Stage stage1 = new Stage();
        stage1.setTitle("AAA");
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel File", "*.xlsx"));
        File file = fileChooser.showSaveDialog(stage1);
        String f = "";
        String l = "";
        int startZabega1 = 0;
        int data1 = 0;
        if (file != null){
            selectFile = file.getAbsolutePath();
            System.out.println(selectFile);
        }
        if (sorev == 1 && male == 1 && zabeg == 10){
            f = "src/sample/pr_m_k.xlsx";
            l = "M";
            data1 = 5;
        }else if (sorev == 2 && male == 1 && zabeg == 10){
            f = "src/sample/pr_m_d.xlsx";
            l = "M";
            data1 = 5;
        }else if (sorev == 1 && male == 2 && zabeg == 10){
            f = "src/sample/pr_w_k.xlsx";
            l = "W";
            data1 = 5;
        }else if (sorev == 2 && male == 2 & zabeg == 10){
            f = "src/sample/pr_w_d.xlsx";
            l = "W";
            data1 = 5;
        }else if (sorev == 1 && male == 1){
            f = "src/sample/pr_m_z_k.xlsx";
            l = "M";
            data1 = 5;
            startZabega1 = 8;
        }else if (sorev == 2 && male == 1){
            f = "src/sample/pr_m_z_d.xlsx";
            l = "M";
            data1 = 4;
            startZabega1 = 10;
        }else if (sorev == 1 && male == 2){
            f = "src/sample/pr_w_z_k.xlsx";
            l = "W";
            data1 = 5;
            startZabega1 = 8;
        }else if (sorev == 2 && male == 2){
            f = "src/sample/pr_w_z_d.xlsx";
            l = "W";
            data1 = 4;
            startZabega1 = 10;
        }

        FileInputStream file1 = new FileInputStream(f);
        XSSFWorkbook wb = new XSSFWorkbook(file1);
        FileOutputStream out = new FileOutputStream(new File(selectFile));
        wb.write(out);
        file1.close();
        out.close();

        DatabaseHandler db = new DatabaseHandler();
        int m = 0;
        if (sorev == 1){
            db.sortedPlace(Const.NEW_PARTICIPANTS_TABLE, m, l, zabeg);
            System.out.println("ALINA JOPA");
            db.convertExcel(selectFile, sorev, l, zabeg, data1, startZabega1, data.getText(), zabeg + " ЗАБЕГ НАЧАЛО В " + startZabega.getText());
        }else if (sorev == 2){
            m = 1;
            db.sortedPlace(Const.TWO_PARTICIPANTS_TABLE, m, l, zabeg);
            db.convertExcel(selectFile, sorev, l, zabeg, data1, startZabega1, data.getText(), zabeg + " ЗАБЕГ НАЧАЛО В " + startZabega.getText());
        }else System.out.println("false");
        data.clear();
        startZabega.clear();


    }
}



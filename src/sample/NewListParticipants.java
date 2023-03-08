package sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.Collator;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class NewListParticipants {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button selectList;

    @FXML
    private ImageView imageLogo;

    @FXML
    private Button loadList;

    @FXML
    private TextField selectFile;

    @FXML
    private TextField numberSheet;

    @FXML
    private Button back;

    @FXML
    private TextField createZabeg;

    @FXML
    private Button home;




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

        FXMLLoader loader = new FXMLLoader(getClass().getResource(SelectionCriteria.stage1));
        Parent root = (Parent) loader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void selectFile(ActionEvent event){
        Stage stage = new Stage();
        stage.setTitle("AAA");
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel File", "*.xlsx"));
        File file = fileChooser.showOpenDialog(stage);
        if (file != null){
            selectFile.setText(file.getAbsolutePath());
        }
    }

    @FXML
    public void loadList(ActionEvent event) throws IOException {
        String name_file = selectFile.getText();
        if (name_file.equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Не выбран список участников");
            alert.showAndWait();

        }else {
            FileInputStream file = new FileInputStream(name_file);
            XSSFWorkbook wb = new XSSFWorkbook(file);
            XSSFSheet sheet = wb.getSheetAt(Integer.parseInt(numberSheet.getText())-1);
            DatabaseHandler databaseHandler = new DatabaseHandler();
            int i = 0;
            int k = 0;
            while (k != 5){
                if (sheet.getRow(i) == null){
                    i++;
                }else if (sheet.getRow(i).getCell(0) == null){
                    i++;
                }else if (sheet.getRow(i).getCell(0).getCellTypeEnum() == CellType.NUMERIC){
                    k = 5;
                }else {
                    i++;
                }
            }
            String str1 = "Женщины";
            String str2 = SelectionCriteria.pol;
            String gender = "null3";
            Collator collator = Collator.getInstance(new Locale("ru", "RU"));
            collator.setStrength(Collator.PRIMARY);
            int result = collator.compare(str1, str2);
            if (result < 0){
                gender = "M";
            }else if (result == 0){
                gender = "W";
            }
            String str3 = "Двоеборье";
            String str4 = SelectionCriteria.vid;
            String table = "";
            int e = 0;
            result = collator.compare(str3, str4);
            if (result < 0){
                table = Const.PARTICIPANTS_TABLE;
                e = 1;
            }else if (result == 0){
                table = Const.TWO_PARTICIPANTS_TABLE;
                e = 2;
            }
            while (sheet.getRow(i) != null){
                if (sheet.getRow(i).getCell(2) != null && sheet.getRow(i).getCell(2).getCellTypeEnum() != CellType.BLANK && e == 1) {
                    databaseHandler.newListParticipants(sheet.getRow(i).getCell(1).getNumericCellValue(),
                            sheet.getRow(i).getCell(2).getStringCellValue(),
                            sheet.getRow(i).getCell(3).getStringCellValue(),
                            sheet.getRow(i).getCell(4).getNumericCellValue(),
                            sheet.getRow(i).getCell(5).getStringCellValue(),
                            sheet.getRow(i).getCell(6).getNumericCellValue(), gender, table, -1, Integer.parseInt(createZabeg.getText()));
                    i ++;
                }else if (sheet.getRow(i).getCell(3) != null && sheet.getRow(i).getCell(3).getCellTypeEnum() != CellType.BLANK && e == 2){
                    databaseHandler.newListParticipants(sheet.getRow(i).getCell(2).getNumericCellValue(),
                            sheet.getRow(i).getCell(3).getStringCellValue(),
                            sheet.getRow(i).getCell(4).getStringCellValue(),
                            sheet.getRow(i).getCell(5).getNumericCellValue(),
                            sheet.getRow(i).getCell(6).getStringCellValue(),
                            sheet.getRow(i).getCell(9).getNumericCellValue(), gender, table, sheet.getRow(i).getCell(1).getNumericCellValue(),
                            Integer.parseInt(createZabeg.getText()));
                    i ++;
                }else break;
            }
            file.close();
            numberSheet.clear();
            createZabeg.clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Список успешно добавлен");
            alert.showAndWait();
        }

    }
}


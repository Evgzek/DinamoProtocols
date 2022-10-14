package sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import com.mysql.cj.util.StringUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
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
    void initialize() {

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
            while (sheet.getRow(i) != null){
                if (sheet.getRow(i).getCell(2) != null && sheet.getRow(i).getCell(2).getCellTypeEnum() != CellType.BLANK) {
                    databaseHandler.newListParticipants(sheet.getRow(i).getCell(1).getNumericCellValue(),
                            sheet.getRow(i).getCell(2).getStringCellValue(),
                            sheet.getRow(i).getCell(3).getStringCellValue(),
                            sheet.getRow(i).getCell(4).getNumericCellValue(),
                            sheet.getRow(i).getCell(5).getStringCellValue(),
                            sheet.getRow(i).getCell(6).getNumericCellValue());
                    i ++;
                }else break;
            }
            file.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Список успешно добавлен");
            alert.showAndWait();
        }

    }
}


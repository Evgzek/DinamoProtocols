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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button preview;

    @FXML
    private Button settings;

    @FXML
    private ImageView imageLogo;

    @FXML
    private Button create;

    @FXML
    private Label title;



    @FXML
    void initialize() {
//        create.setOnAction(event -> {
//            create.getScene().getWindow().hide();
//
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("/sample/create.fxml"));
//
//            try {
//                loader.load();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            Parent root = loader.getRoot();
//            Stage stage = new Stage();
//            stage.setScene(new Scene(root, 700, 400));
//            stage.setTitle("DinPro");
//            stage.showAndWait();
//        });

    }

    @FXML
    public void createProtocols(javafx.event.ActionEvent event) throws IOException{
        Stage stage = (Stage) create.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/create.fxml"));
        Parent root = (Parent) loader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void test() throws SQLException, ClassNotFoundException, IOException {
        DatabaseHandler db = new DatabaseHandler();
//        db.sortedPlace();
//        FileChooser fileChooser = new FileChooser();
//        Stage stage = new Stage();
//        fileChooser.setTitle("Save");
//        fileChooser.setInitialFileName("save");
//        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Excel file", "*.xlsx"));
//        try {
//            File file = fileChooser.showSaveDialog(stage);
//            String s = file.getAbsolutePath();
//            Workbook wb_write = new XSSFWorkbook();
//            FileOutputStream outputStream = new FileOutputStream(s);
//            wb_write.write(outputStream);
//            outputStream.close();
//        }catch (Exception ex){
//
//        }
//        String selectFile = "";
//        Stage stage = new Stage();
//        stage.setTitle("AAA");
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel File", "*.xlsx"));
//        File file = fileChooser.showOpenDialog(stage);
//        if (file != null){
//            selectFile = file.getAbsolutePath();
//            System.out.println(selectFile);
//        }
        System.out.println("kkk");
        FileInputStream file1 = new FileInputStream("C:\\Users\\79991\\Desktop\\shablon.xlsx");
        System.out.println("g");
        XSSFWorkbook wb = new XSSFWorkbook(file1);
        System.out.println("good");
        for (int i = wb.getNumberOfSheets() - 1; i >= 0; i--){
            if(!wb.getSheetName(i).equals("КРОСС М итог"))
                wb.removeSheetAt(i);
        }
        FileOutputStream out = new FileOutputStream(new File("C:\\Users\\79991\\Desktop\\savetest1.xlsx"));
        wb.write(out);
        file1.close();
        out.close();
    }

}



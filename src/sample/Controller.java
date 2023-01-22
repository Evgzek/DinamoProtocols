package sample;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Iterator;
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
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
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

    public void testFunction (javafx.event.ActionEvent event) throws SQLException, ClassNotFoundException, ParseException, IOException, InvalidFormatException {
        DatabaseHandler db = new DatabaseHandler();
//        db.test();
//        db.sortedPlace();
//        db.convertExcel();
        try {
            String file = "C:\\Users\\79991\\Desktop\\tpr.xlsx";
            File new_file = new File(file);
            FileInputStream out = new FileInputStream(new_file);
            XSSFWorkbook wb = new XSSFWorkbook(out);
            XSSFSheet sheet = wb.getSheetAt(0);
            Row row = sheet.getRow(5);
            Cell cell = row.getCell(0);
            cell.setCellValue("5 сентября");
            cell = row.getCell(2);
            cell.setCellValue(2023);
            out.close();
            FileOutputStream write = new FileOutputStream(new_file);
            wb.write(write);
            write.close();
            System.out.println("good");
        } catch (Exception e) {
            e.printStackTrace();
        }
//        try {
//            String file = "C:\\Users\\79991\\Desktop\\savetest1.xlsx";
//            File new_file = new File(file);
//            FileInputStream out = new FileInputStream(new_file);
//            XSSFWorkbook wb = new XSSFWorkbook(out);
//            XSSFSheet sheet = wb.getSheetAt(0);
//            Row row = sheet.getRow(13);
//            Cell cell = row.getCell(6);
//            cell.setCellValue(0.92);
////            Cell cell = row.getCell(0);
////            cell.setCellValue("rr9");
////            System.out.println(row.getCell(0));
//            //13 1
//            out.close();
//            FileOutputStream write = new FileOutputStream(new_file);
//            wb.write(write);
//            write.close();
//            System.out.println("good");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    public void viewProtocols(javafx.event.ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        Stage stage = (Stage) preview.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/viewProtocols.fxml"));
        Parent root = (Parent) loader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.show();

//        DatabaseHandler db = new DatabaseHandler();
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
//        FileInputStream file1 = new FileInputStream("C:\\Users\\79991\\Desktop\\shablon.xlsx");
//        XSSFWorkbook wb = new XSSFWorkbook(file1);
//        for (int i = wb.getNumberOfSheets() - 1; i >= 0; i--){
//            if(!wb.getSheetName(i).equals("КРОСС М итог"))
//                wb.removeSheetAt(i);
//        }
//        FileOutputStream out = new FileOutputStream(new File("C:\\Users\\79991\\Desktop\\savetest1.xlsx"));
//        wb.write(out);
//        file1.close();
//        out.close();

//        FileOutputStream file3 = new FileOutputStream("C:\\Users\\79991\\Desktop\\hhh.xlsx");
//        XSSFWorkbook wb1 = new XSSFWorkbook();
//        XSSFSheet sheet = wb1.createSheet("eee");
//        XSSFRow row = sheet.createRow(0);
//        XSSFCell cell = row.createCell(0);
//        cell.setCellValue("asdas");
//        try {
//            int i = 0;
//            int k = 0;
//            FileInputStream file2 = new FileInputStream(new File("C:\\Users\\79991\\Desktop\\hhh.xlsx"));
//            XSSFWorkbook wb = new XSSFWorkbook(file2);
//            XSSFSheet sheet = wb.getSheetAt(0);
//            Iterator<Row> rowIterator = sheet.iterator();
//            while (rowIterator.hasNext()){
//                if(i == 1){
//                    break;
//                }
//                i++;
//                Row row = rowIterator.next();
//                Iterator<Cell> cellIterator = row.cellIterator();
//                while (cellIterator.hasNext()){
//                    if (k == 1){
//                        break;
//                    }
//                    k++;
//                    Cell cell = cellIterator.next();
//                    System.out.println(cell);
//                }
//            }
//            file2.close();
//        }catch (Exception e){
//            e.printStackTrace();
//        }

//        int i = 0;
//        int k = 0;
//        while (k != 5){
//            if (sheet.getRow(i) == null){
//                i++;
//            }else if (sheet.getRow(i).getCell(0) == null){
//                i++;
//            }else if (sheet.getRow(i).getCell(0).getCellTypeEnum() == CellType.NUMERIC){
//                k = 5;
//            }else {
//                i++;
//            }
//        }

//        Cell cell = row.getCell(0);
//        cell.setCellValue("sads");
//        if (cell.getCTCell().isSetT()) cell.getCTCell().unsetT();
//        if (cell.getCTCell().isSetV()) cell.getCTCell().unsetV();
//        wb1.write(file3);
//        file3.close();
//        file2.close();
        System.out.println("end");
    }

}



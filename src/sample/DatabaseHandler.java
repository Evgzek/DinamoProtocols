package sample;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;

public class DatabaseHandler extends Configs{
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    public void newParticipants (double number, String name, String team, double year, String group,
                                 double kef){
        String insert = "INSERT INTO " + Const.NEW_PARTICIPANTS_TABLE + "("+
                Const.PARTICIPANTS_NUMBER + ", " + Const.PARTICIPANTS_NAME + ", " + Const.PARTICIPANTS_TEAMS +
                ", " + Const.PARTICIPANTS_YEAR + ", " + Const.PARTICIPANTS_GROUP + ", " +
                Const.PARTICIPANTS_KEF + ")" + "VALUES(?,?,?,?,?,?)";


        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, String.valueOf(number));
            prSt.setString(2, name);
            prSt.setString(3, team);
            prSt.setString(4, String.valueOf(year));
            prSt.setString(5, group);
            prSt.setDouble(6, kef);
            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public void newListParticipants (double number, String name, String team, double year, String group,
                                 double kef){
        String insert = "INSERT INTO " + Const.NEW_PARTICIPANTS_TABLE + "("+
                Const.PARTICIPANTS_NUMBER + ", " + Const.PARTICIPANTS_NAME + ", " + Const.PARTICIPANTS_TEAMS +
                ", " + Const.PARTICIPANTS_YEAR + ", " + Const.PARTICIPANTS_GROUP + ", " +
                Const.PARTICIPANTS_KEF + ")" + "VALUES(?,?,?,?,?,?)";


        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, String.valueOf(number));
            prSt.setString(2, name);
            prSt.setString(3, team);
            prSt.setString(4, String.valueOf(year));
            prSt.setString(5, group);
            prSt.setDouble(6, kef);
            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void addResultParticipant(int minute, int seconds, int tens, double number) throws SQLException, ClassNotFoundException {
        String update = "UPDATE " + Const.NEW_PARTICIPANTS_TABLE + " SET " + Const.PARTICIPANTS_RESULT + " = '0:" +
                minute + ":" + seconds + "." + tens + "' " + "WHERE " + Const.PARTICIPANTS_NUMBER + " = '" + number + "';";
        getDbConnection().prepareStatement(update).executeUpdate();
    }

    public void addResultKefParticipant(double number, double time) throws SQLException, ClassNotFoundException {
        String select = "SELECT * FROM " + Const.NEW_PARTICIPANTS_TABLE;
        Statement statement = dbConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(select);
        int minute = 0;
        int seconds = 0;
        int tens = 0;
        while (resultSet.next()){
            double n = resultSet.getDouble(Const.PARTICIPANTS_NUMBER);
            double kef = resultSet.getDouble(Const.PARTICIPANTS_KEF);
            if (n == number){
                time = time * kef;
                minute = (int) (time/60);
                seconds = (int) (time - minute*60);
                double k = time%1;
                if (k<0.05){
                    tens = 0;
                }else if (k<0.1){
                    tens = 10;
                }else {
                    k = k*100;
                    tens = (int) k;
                }
                break;
            }
        }
        String update = "UPDATE " + Const.NEW_PARTICIPANTS_TABLE + " SET " + Const.PARTICIPANTS_RESULT_KEF + " = '0:" +
                minute + ":" + seconds + "." + tens + "' " + "WHERE "
                + Const.PARTICIPANTS_NUMBER + " = '" + number + "';";
        getDbConnection().prepareStatement(update).executeUpdate();
        String update_sec = "UPDATE " + Const.NEW_PARTICIPANTS_TABLE + " SET " + Const.PARTICIPANTS_SECONDS + " = " +
                time + " WHERE " + Const.PARTICIPANTS_NUMBER + " = " + number + ";";
        getDbConnection().prepareStatement(update_sec).executeUpdate();
    }

    public void sortedPlace() throws SQLException, ClassNotFoundException {
        int i = 0;
        double time_leader = 0;
        String select = "SELECT * FROM " + Const.NEW_PARTICIPANTS_TABLE + " ORDER BY " +
                Const.PARTICIPANTS_RESULT_KEF + " ASC;";
        Statement statement = getDbConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(select);
        while (resultSet.next()){
            time_leader = resultSet.getDouble(Const.PARTICIPANTS_SECONDS);
            System.out.println(time_leader);
            break;
        }
        resultSet = statement.executeQuery(select);
        while (resultSet.next()){
            i++;
            int number = resultSet.getInt("нагрудный_номер");
            String update = "UPDATE " + Const.NEW_PARTICIPANTS_TABLE + " SET " + Const.PARTICIPANTS_PLACE +
                    " = " + i + " WHERE " + Const.PARTICIPANTS_NUMBER + " = " + number + ";";
            getDbConnection().prepareStatement(update).executeUpdate();
            double time = resultSet.getDouble(Const.PARTICIPANTS_SECONDS);
            time = time - time_leader;
            int minute = 0;
            int seconds = 0;
            int tens = 0;
            minute = (int) (time/60);
            seconds = (int) (time - minute*60);
            double k = time%1;
            if (k<0.05){
                tens = 0;
            }else if (k<0.1){
                tens = 10;
            }else {
                k = k*100;
                tens = (int) k;
            }
            String update_raznica = "UPDATE " + Const.NEW_PARTICIPANTS_TABLE + " SET " + Const.PARTICIPANTS_RAZNICA + " = '0:" +
                    minute + ":" + seconds + "." + tens + "' " + "WHERE "
                    + Const.PARTICIPANTS_NUMBER + " = '" + number + "';";
            getDbConnection().prepareStatement(update_raznica).executeUpdate();
        }
    }

    public void runshoot(double time, double shoot) throws SQLException, ClassNotFoundException {
        String select = "SELECT * FROM " + Const.RUN_TABLE;
        Statement statement = getDbConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(select);
        while (resultSet.next()){
            if (time < resultSet.getDouble(Const.RUN_TIME_M)){
                System.out.println(resultSet.getDouble(Const.RUN_RESULT_M));
                break;
            }
        }
        select = "SELECT * FROM " + Const.SHOOTING_TABLE;
        resultSet = statement.executeQuery(select);
        while (resultSet.next()){
            if (shoot == resultSet.getDouble(Const.SHOOTING_RESULT)){
                System.out.println(resultSet.getDouble(Const.SHOOTING_POINTS));
                break;
            }
        }
    }


    public void test (double time_m, double points_m, double time_w, double points_w ) throws SQLException, ClassNotFoundException, ParseException {
        String insert = "INSERT INTO " + Const.RUN_TABLE + "("+ Const.RUN_TIME_M + ", " +
                Const.RUN_RESULT_M + ", " + Const.RUN_TIME_W + ", " + Const.RUN_RESULT_W + ")" + "VALUES(?,?,?,?)";
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
        System.out.println("s");
        System.out.println("d");
            preparedStatement.setDouble(1, time_m);
        System.out.println("f");
            preparedStatement.setDouble(2, points_m);
            preparedStatement.setDouble(3, time_w);
            preparedStatement.setDouble(4, points_w);
        System.out.println("g");
            preparedStatement.executeUpdate();
        System.out.println("good");
//        String select = "SELECT * FROM " + Const.NEW_PARTICIPANTS_TABLE + " ORDER BY " +
//                Const.PARTICIPANTS_RESULT_KEF + " ASC;";
//        String delete = "TRUNCATE TABLE " + Const.NEW_PARTICIPANTS_TABLE + ";";
//        Statement statement = getDbConnection().createStatement();
//        statement.executeUpdate(delete);
//        System.out.println("good");
//        ResultSet resultSet = statement.executeQuery(select);
//        while (resultSet.next()){
//            String time = resultSet.getString(Const.PARTICIPANTS_RESULT);
////            SimpleDateFormat dt = new SimpleDateFormat("00:mm:ss.SS");
////            Date date = dt.parse(time);
//            System.out.println(time);
//            break;
//        }

    }

    public void convertExcel () throws IOException, InvalidFormatException, SQLException, ClassNotFoundException {
//        Workbook wb = new XSSFWorkbook();
//        Sheet sheet = wb.createSheet("test");
//        FileOutputStream out = new FileOutputStream("C:/NewExcelFile.xlsx");
//        wb.write(out);
//        out.close();
//        JFileChooser fileChooser = new JFileChooser();
//        Stage stage = new Stage();
//        stage.setTitle("AAA");
        try {
            String file = "C:\\Users\\79991\\Desktop\\tpr.xlsx";
            File new_file = new File(file);
            FileInputStream out = new FileInputStream(new_file);
            XSSFWorkbook wb = new XSSFWorkbook(out);
            XSSFSheet sheet = wb.getSheetAt(0);
            String select = "SELECT * FROM " + Const.NEW_PARTICIPANTS_TABLE + " ORDER BY " +
                    Const.PARTICIPANTS_RESULT_KEF + " ASC;";
            Statement statement = getDbConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(select);
            int i = 0;
//            Row row = sheet.getRow(12);
//            Cell cell = row.getCell(3);
//            cell.setCellValue("КФК №7");
            while (resultSet.next()){
                Row row = sheet.getRow(12 + i);
                Cell cell = row.getCell(1);
                cell.setCellValue(resultSet.getInt(Const.PARTICIPANTS_NUMBER));
                cell = row.getCell(2);
                cell.setCellValue(resultSet.getString(Const.PARTICIPANTS_NAME));
                cell = row.getCell(3);
                cell.setCellValue(resultSet.getString(Const.PARTICIPANTS_TEAMS));
                cell = row.getCell(4);
                cell.setCellValue(resultSet.getInt(Const.PARTICIPANTS_YEAR));
                cell = row.getCell(5);
                cell.setCellValue(resultSet.getString(Const.PARTICIPANTS_GROUP));
                cell = row.getCell(6);
                cell.setCellValue(resultSet.getDouble(Const.PARTICIPANTS_KEF));
                cell = row.getCell(7);
                String time = resultSet.getString(Const.PARTICIPANTS_RESULT);
                System.out.println(time);
                cell.setCellValue(time);
                cell = row.getCell(8);
                time = resultSet.getString(Const.PARTICIPANTS_RESULT_KEF);
                System.out.println(time);
                cell.setCellValue(time);
                cell = row.getCell(9);
                cell.setCellValue(resultSet.getInt(Const.PARTICIPANTS_PLACE));
                cell = row.getCell(10);
                if (resultSet.getInt(Const.PARTICIPANTS_PLACE) == 1){
                    cell.setCellValue("00:00:00.0");
                }else {
                    time = resultSet.getString(Const.PARTICIPANTS_RAZNICA);
                    cell.setCellValue(time);
                }
                i++;
            }
            out.close();
            FileOutputStream write = new FileOutputStream(new_file);
            wb.write(write);
            write.close();
            System.out.println("good");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }



}

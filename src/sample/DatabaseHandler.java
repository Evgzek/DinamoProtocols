package sample;

import javafx.stage.Stage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.*;

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
        String updatesec = "UPDATE " + Const.NEW_PARTICIPANTS_TABLE + " SET " + Const.PARTICIPANTS_SECONDS + " = " +
                time + " WHERE " + Const.PARTICIPANTS_NUMBER + " = " + number + ";";
        getDbConnection().prepareStatement(updatesec).executeUpdate();
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

    public void convertExcel () throws IOException, InvalidFormatException {
//        Workbook wb = new XSSFWorkbook();
//        Sheet sheet = wb.createSheet("test");
//        FileOutputStream out = new FileOutputStream("C:/NewExcelFile.xlsx");
//        wb.write(out);
//        out.close();
        JFileChooser fileChooser = new JFileChooser();
        Stage stage = new Stage();
        stage.setTitle("AAA");

    }



}

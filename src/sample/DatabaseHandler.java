package sample;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.sql.*;
import java.text.Collator;
import java.text.ParseException;
import java.util.Locale;

public class DatabaseHandler extends Configs{
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    public void newParticipants (double number, String name, String team, double year, String group,
                                 double kef, String gender, String table, double target, double zabeg, int v_k) throws SQLException, ClassNotFoundException {

        String insert = "";
        if (target == -1){
            String select = "SELECT * FROM " + Const.NEW_PARTICIPANTS_TABLE;
            Statement statement = getDbConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(select);
            while (resultSet.next()){
                if (resultSet.getDouble(Const.PARTICIPANTS_NUMBER) == number){
                    String delete_t = "DELETE FROM " + Const.NEW_PARTICIPANTS_TABLE + " WHERE " + Const.PARTICIPANTS_NUMBER + " = " + number + ";";
                    getDbConnection().prepareStatement(delete_t).executeUpdate();
                }
            }
            insert = "INSERT INTO " + table + "("+
                    Const.PARTICIPANTS_NUMBER + ", " + Const.PARTICIPANTS_NAME + ", " + Const.PARTICIPANTS_TEAMS +
                    ", " + Const.PARTICIPANTS_YEAR + ", " + Const.PARTICIPANTS_GROUP + ", " +
                    Const.PARTICIPANTS_KEF + ", " + Const.PARTICIPANTS_GENDER + ", " + Const.PARTICIPANTS_ZABEG + ")" + "VALUES(?,?,?,?,?,?,?,?)";

            try {
                PreparedStatement prSt = getDbConnection().prepareStatement(insert);
                prSt.setString(1, String.valueOf(number));
                prSt.setString(2, name);
                prSt.setString(3, team);
                prSt.setString(4, String.valueOf(year));
                prSt.setString(5, group);
                prSt.setDouble(6, kef);
                prSt.setString(7, gender);
                prSt.setDouble(8, zabeg);
                prSt.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else {
            String select = "SELECT * FROM " + Const.NEW_PARTICIPANTS_TABLE;
            Statement statement = getDbConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(select);
            while (resultSet.next()){
                if (resultSet.getDouble(Const.PARTICIPANTS_NUMBER) == number){
                    String delete_t = "DELETE FROM " + Const.NEW_PARTICIPANTS_TABLE + " WHERE " + Const.PARTICIPANTS_NUMBER + " = " + number + ";";
                    getDbConnection().prepareStatement(delete_t).executeUpdate();
                }
            }
            insert = "INSERT INTO " + table + "("+ Const.PARTICIPANTS_TARGET + ", " +
                    Const.PARTICIPANTS_NUMBER + ", " + Const.PARTICIPANTS_NAME + ", " + Const.PARTICIPANTS_TEAMS +
                    ", " + Const.PARTICIPANTS_YEAR + ", " + Const.PARTICIPANTS_GROUP + ", " +
                    Const.PARTICIPANTS_KEF + ", " + Const.PARTICIPANTS_GENDER + ", " + Const.PARTICIPANTS_ZABEG + ")" + "VALUES(?,?,?,?,?,?,?,?,?)";

            try {
                PreparedStatement prSt = getDbConnection().prepareStatement(insert);
                prSt.setDouble(1, target);
                prSt.setString(2, String.valueOf(number));
                prSt.setString(3, name);
                prSt.setString(4, team);
                prSt.setString(5, String.valueOf(year));
                prSt.setString(6, group);
                prSt.setDouble(7, kef);
                prSt.setString(8, gender);
                prSt.setDouble(9, zabeg);
                prSt.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (v_k == 1){
            String update = "UPDATE " + table + " SET " + Const.V_K + " = 1 WHERE " + Const.PARTICIPANTS_NAME
                    + " = '" + name + "';";
            getDbConnection().prepareStatement(update).executeUpdate();
        }

    }


    public void newListParticipants (double number, String name, String team, double year, String group,
                                 double kef,String gender, String table, double target, double zabeg){
        String insert = "";
        if (target == -1){
            insert = "INSERT INTO " + table + "("+
                    Const.PARTICIPANTS_NUMBER + ", " + Const.PARTICIPANTS_NAME + ", " + Const.PARTICIPANTS_TEAMS +
                    ", " + Const.PARTICIPANTS_YEAR + ", " + Const.PARTICIPANTS_GROUP + ", " +
                    Const.PARTICIPANTS_KEF + ", " + Const.PARTICIPANTS_GENDER + ", " + Const.PARTICIPANTS_ZABEG + ")" + "VALUES(?,?,?,?,?,?,?,?)";

            try {
                PreparedStatement prSt = getDbConnection().prepareStatement(insert);
                prSt.setString(1, String.valueOf(number));
                prSt.setString(2, name);
                prSt.setString(3, team);
                prSt.setString(4, String.valueOf(year));
                prSt.setString(5, group);
                prSt.setDouble(6, kef);
                prSt.setString(7, gender);
                prSt.setDouble(8, zabeg);
                prSt.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else {
            insert = "INSERT INTO " + table + "("+ Const.PARTICIPANTS_TARGET + ", " +
                    Const.PARTICIPANTS_NUMBER + ", " + Const.PARTICIPANTS_NAME + ", " + Const.PARTICIPANTS_TEAMS +
                    ", " + Const.PARTICIPANTS_YEAR + ", " + Const.PARTICIPANTS_GROUP + ", " +
                    Const.PARTICIPANTS_KEF + ", " + Const.PARTICIPANTS_GENDER + ", " + Const.PARTICIPANTS_ZABEG + ")" + "VALUES(?,?,?,?,?,?,?,?,?)";

            try {
                PreparedStatement prSt = getDbConnection().prepareStatement(insert);
                prSt.setDouble(1, target);
                prSt.setString(2, String.valueOf(number));
                prSt.setString(3, name);
                prSt.setString(4, team);
                prSt.setString(5, String.valueOf(year));
                prSt.setString(6, group);
                prSt.setDouble(7, kef);
                prSt.setString(8, gender);
                prSt.setDouble(9, zabeg);
                prSt.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    public void addResultParticipant(int minute, int seconds, int tens, double number, String table, String cell) throws SQLException, ClassNotFoundException {
        String t = "";
        String update = "";
        if (tens < 10){
            t = "0" + tens;
            update = "UPDATE " + table + " SET " + cell + " = '0:" +
                    minute + ":" + seconds + "." + t + "' " + "WHERE " + Const.PARTICIPANTS_NUMBER + " = '" + number + "';";
        }else {
            update = "UPDATE " + table + " SET " + cell + " = '0:" +
                    minute + ":" + seconds + "." + tens + "' " + "WHERE " + Const.PARTICIPANTS_NUMBER + " = '" + number + "';";
        }

        getDbConnection().prepareStatement(update).executeUpdate();
    }

    public void addShootResultParticipant(double shoot, double number, String table, String cell) throws SQLException, ClassNotFoundException {
        String update = "UPDATE " + table + " SET " + cell + " = '" + shoot + "' WHERE " + Const.PARTICIPANTS_NUMBER + " = '" +
                number + "';";
        getDbConnection().prepareStatement(update).executeUpdate();
        DatabaseHandler db = new DatabaseHandler();
    }

    public void addShootResultPointsParticipant(double shoot, String table, double number) throws SQLException, ClassNotFoundException {
        DatabaseHandler db = new DatabaseHandler();
        String select = "SELECT * FROM " + Const.SHOOTING_TABLE;
        Statement statement = getDbConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(select);
        double points = 0;
        while (resultSet.next()){
            if (shoot == resultSet.getDouble(Const.SHOOTING_RESULT)){
                points = resultSet.getDouble(Const.SHOOTING_POINTS);
                break;
            }
        }
        String update_shoot = "UPDATE " + table + " SET " + Const.PARTICIPANTS_SHOOT_POINTS + " = '" + points + "' " +
                "WHERE " + Const.PARTICIPANTS_NUMBER + " = " + number + ";";
        getDbConnection().prepareStatement(update_shoot).executeUpdate();
        db.addAllPoints(number);
    }

    public void addRunResultPointsParticipant(double time, String table, String cel, double number) throws SQLException, ClassNotFoundException{
        String select = "SELECT * FROM " + Const.TWO_PARTICIPANTS_TABLE;
        Statement statement = getDbConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(select);
        String cell = "";
        double points = 0;
        while (resultSet.next()){
            if (number == resultSet.getDouble(Const.PARTICIPANTS_NUMBER)){
                if (resultSet.getString(Const.PARTICIPANTS_GENDER).equals("M")){
                    cell = "m";
                }else{
                    cell = "w";
                }
                break;
            }
        }
        select = "SELECT * FROM " + Const.RUN_TABLE;
        resultSet = statement.executeQuery(select);
        System.out.println(cell);
        while (resultSet.next()){
            if (time <= resultSet.getDouble("time_" + cell)){
                points = resultSet.getDouble("result_" + cell);
                break;
            }
        }
        String update_run = "UPDATE " + table + " SET " + cel + " = '" + points + "' " +
                "WHERE " + Const.PARTICIPANTS_NUMBER + " = " + number + ";";
        getDbConnection().prepareStatement(update_run).executeUpdate();
        DatabaseHandler db = new DatabaseHandler();
        db.addAllPoints(number);
    }

    public void addAllPoints(double number) throws SQLException, ClassNotFoundException {
        String select = "SELECT * FROM " + Const.TWO_PARTICIPANTS_TABLE;
        Statement statement = getDbConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(select);
        double points = 0;
        double points_kef = 0;
        String discharge = "";
        while (resultSet.next()){
            if (number == resultSet.getDouble(Const.PARTICIPANTS_NUMBER)){
                points = resultSet.getDouble(Const.PARTICIPANTS_RESULT_POINTS) + resultSet.getDouble(Const.PARTICIPANTS_SHOOT_POINTS);
                System.out.println(points);
                points_kef = resultSet.getDouble(Const.PARTICIPANTS_RESULT_KEF_POINTS) + resultSet.getDouble(Const.PARTICIPANTS_SHOOT_POINTS);
                System.out.println(points_kef);
                if (points > 2099 && resultSet.getDouble(Const.PARTICIPANTS_SHOOT_POINTS) > 699 && resultSet.getDouble(Const.PARTICIPANTS_RESULT_POINTS) > 699){
                    discharge = "МС";
                }else if (points > 1799 && resultSet.getDouble(Const.PARTICIPANTS_SHOOT_POINTS) > 599 && resultSet.getDouble(Const.PARTICIPANTS_RESULT_POINTS) > 599){
                    discharge = "КМС";
                }else if (points > 1599){
                    discharge = "1";
                }else if (points > 1399){
                    discharge = "2";
                }else if (points > 1199){
                    discharge = "3";
                }else discharge = "б/р";
                break;
            }
        }

        String update_all_points = "UPDATE " + Const.TWO_PARTICIPANTS_TABLE + " SET " + Const.PARTICIPANTS_SUM_POINTS +
                " = '" + points + "' " + "WHERE " + Const.PARTICIPANTS_NUMBER + " = " + number + ";";
        getDbConnection().prepareStatement(update_all_points).executeUpdate();
        String update_all_points_kef = "UPDATE " + Const.TWO_PARTICIPANTS_TABLE + " SET " + Const.PARTICIPANTS_SUM_POINTS_KEF +
                " = '" + points_kef + "' " + "WHERE " + Const.PARTICIPANTS_NUMBER + " = " + number + ";";
        getDbConnection().prepareStatement(update_all_points_kef).executeUpdate();
        String update_discharge = "UPDATE " + Const.TWO_PARTICIPANTS_TABLE + " SET " + Const.PARTICIPANTS_DISCHARGE +
                " = '" + discharge + "' " + "WHERE " + Const.PARTICIPANTS_NUMBER + " = " + number + ";";
        getDbConnection().prepareStatement(update_discharge).executeUpdate();
     }

    public void addResultKefParticipant(double number, double time, double t, String table, int l) throws SQLException, ClassNotFoundException
    {
        String select = "SELECT * FROM " + table;
        Statement statement = dbConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(select);
        DatabaseHandler db = new DatabaseHandler();
        int minute = 0;
        int seconds = 0;
        int tens = 0;
        String sot = "";
        while (resultSet.next()){
            double n = resultSet.getDouble(Const.PARTICIPANTS_NUMBER);
            double kef = resultSet.getDouble(Const.PARTICIPANTS_KEF);
            if (n == number){
                time = time - (t/100);
                time = time * kef;
                if (l == 1){
                    db.addRunResultPointsParticipant(time, table, Const.PARTICIPANTS_RESULT_KEF_POINTS, number);
                }
                double e = (int) time;
                e = time - e;
                t = t/100;
                t = t * kef;
                double f = t + e;
                f = f * 100;
                tens = (int) f;
                if (tens < 10){
                    sot = "0" + tens;
                }else sot = "" + tens;
                time = time + t;
                minute = (int) (time/60);
                seconds = (int) (time - minute*60);
                break;
            }
        }
        String update = "UPDATE " + table + " SET " + Const.PARTICIPANTS_RESULT_KEF + " = '0:" +
                minute + ":" + seconds + "." + sot + "' " + "WHERE "
                + Const.PARTICIPANTS_NUMBER + " = '" + number + "';";
        getDbConnection().prepareStatement(update).executeUpdate();
        String update_sec = "UPDATE " + table + " SET " + Const.PARTICIPANTS_SECONDS + " = " +
                time + " WHERE " + Const.PARTICIPANTS_NUMBER + " = " + number + ";";
        getDbConnection().prepareStatement(update_sec).executeUpdate();
    }

    public void sortedPlace(String table, int m, String l, int zabeg) throws SQLException, ClassNotFoundException {
        if (m == 0){
            int i = 0;
            String sot;
            double time_leader = 0;
            String select = "";
            if (zabeg == 10){
                select = "SELECT * FROM " + Const.NEW_PARTICIPANTS_TABLE + " WHERE " + Const.PARTICIPANTS_RESULT + " IS NOT NULL AND " +
                        Const.PARTICIPANTS_GENDER + " = '" + l + "' ORDER BY " + Const.PARTICIPANTS_RESULT_KEF + " ASC;";
            }else {
                select = "SELECT * FROM " + Const.NEW_PARTICIPANTS_TABLE + " WHERE " + Const.PARTICIPANTS_RESULT + " IS NOT NULL AND " +
                        Const.PARTICIPANTS_GENDER + " = '" + l + "' AND " + Const.PARTICIPANTS_ZABEG + " = " + zabeg +
                        " ORDER BY " + Const.PARTICIPANTS_RESULT_KEF + " ASC;";
            }
            Statement statement = getDbConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(select);
            while (resultSet.next()){
                time_leader = resultSet.getDouble(Const.PARTICIPANTS_SECONDS);
                break;
            }
            resultSet = statement.executeQuery(select);
            while (resultSet.next()){
                System.out.println(resultSet.getString(Const.PARTICIPANTS_NAME));
                System.out.println(i);
                i++;
                int number = resultSet.getInt(Const.PARTICIPANTS_NUMBER);
                String update = "UPDATE " + table + " SET " + Const.PARTICIPANTS_PLACE +
                        " = " + i + " WHERE " + Const.PARTICIPANTS_NUMBER + " = " + number + ";";
                getDbConnection().prepareStatement(update).executeUpdate();
                System.out.println(resultSet.getString(Const.PARTICIPANTS_PLACE));
                double time = resultSet.getDouble(Const.PARTICIPANTS_SECONDS);
                time = time - time_leader;
                int minute = 0;
                int seconds = 0;
                int tens = 0;
                double e = (int) time;
                e = time - e;
                double f = e;
                f = f * 100;
                tens = (int) f;
                if (tens < 10){
                    sot = "0" + tens;
                }else sot = "" + tens;
                minute = (int) (time/60);
                seconds = (int) (time - minute*60);
                String update_raznica = "UPDATE " + Const.NEW_PARTICIPANTS_TABLE + " SET " + Const.PARTICIPANTS_RAZNICA + " = '0:" +
                        minute + ":" + seconds + "." + sot + "' " + "WHERE "
                        + Const.PARTICIPANTS_NUMBER + " = '" + number + "';";
                getDbConnection().prepareStatement(update_raznica).executeUpdate();
                System.out.println(resultSet.getString(Const.PARTICIPANTS_PLACE));
        }
        }else if (m == 1){
            int i = 0;
            String select = "";
            if (zabeg == 10){
                select = "SELECT * FROM " + Const.TWO_PARTICIPANTS_TABLE + " WHERE " + Const.PARTICIPANTS_RESULT + " IS NOT NULL AND " +
                        Const.PARTICIPANTS_GENDER + " = '" + l + "' ORDER BY " + Const.PARTICIPANTS_SUM_POINTS_KEF + " DESC;";
            }else {
                select = "SELECT * FROM " + Const.TWO_PARTICIPANTS_TABLE + " WHERE " + Const.PARTICIPANTS_RESULT + " IS NOT NULL AND " +
                        Const.PARTICIPANTS_GENDER + " = '" + l + "' AND " + Const.PARTICIPANTS_ZABEG + " = " + zabeg +
                        " ORDER BY " + Const.PARTICIPANTS_SUM_POINTS_KEF + " DESC;";
            }
            Statement statement = getDbConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(select);
            while (resultSet.next()){
                        i++;
                        int number = resultSet.getInt("нагрудный_номер");
                        String update = "UPDATE " + table + " SET " + Const.PARTICIPANTS_PLACE +
                                " = " + i + " WHERE " + Const.PARTICIPANTS_NUMBER + " = " + number + ";";
                        getDbConnection().prepareStatement(update).executeUpdate();

            }
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


    public void test ( ) throws SQLException, ClassNotFoundException, ParseException {
//        String insert = "INSERT INTO " + Const.RUN_TABLE + "("+ Const.RUN_TIME_M + ", " +
//                Const.RUN_RESULT_M + ", " + Const.RUN_TIME_W + ", " + Const.RUN_RESULT_W + ")" + "VALUES(?,?,?,?)";
//            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
//        System.out.println("s");
//        System.out.println("d");
//            preparedStatement.setDouble(1, time_m);
//        System.out.println("f");
//            preparedStatement.setDouble(2, points_m);
//            preparedStatement.setDouble(3, time_w);
//            preparedStatement.setDouble(4, points_w);
//        System.out.println("g");
//            preparedStatement.executeUpdate();
//        System.out.println("good");
//        String select = "SELECT * FROM " + Const.NEW_PARTICIPANTS_TABLE + " ORDER BY " +
//                Const.PARTICIPANTS_RESULT_KEF + " ASC;";
//        String delete = "TRUNCATE TABLE " + Const.NEW_PARTICIPANTS_TABLE + ";";
//        Statement statement = getDbConnection().createStatement();
//        statement.executeUpdate(delete);
//        System.out.println("good");
//        delete = "TRUNCATE TABLE " + Const.TWO_PARTICIPANTS_TABLE + ";";
//        Statement statement3 = getDbConnection().createStatement();
//        statement3.executeUpdate(delete);
        String select = "SELECT * FROM " + Const.TEAMS_TABLE;
        Statement statement1 = getDbConnection().createStatement();
        ResultSet resultSet = statement1.executeQuery(select);
        while (resultSet.next()){
//            if (resultSet.getInt("id") > 9){
//                System.out.println("rrr");
//                break;
//            }else System.out.println(resultSet.getInt("id"));
            String update = "UPDATE " + Const.TEAMS_TABLE + " SET " + Const.W_1 + "d = null WHERE " + Const.TEAMS_TEAM
                    + " = '" + resultSet.getString(Const.TEAMS_TEAM) + "';";
            Statement statement2 = getDbConnection().createStatement();
            statement2.executeUpdate(update);
            update = "UPDATE " + Const.TEAMS_TABLE + " SET " + Const.W_2 + "d = null WHERE " + Const.TEAMS_TEAM
                    + " = '" + resultSet.getString(Const.TEAMS_TEAM) + "';";
            statement2 = getDbConnection().createStatement();
            statement2.executeUpdate(update);
            update = "UPDATE " + Const.TEAMS_TABLE + " SET " + Const.M_1 + "d = null WHERE " + Const.TEAMS_TEAM
                    + " = '" + resultSet.getString(Const.TEAMS_TEAM) + "';";
            statement2 = getDbConnection().createStatement();
            statement2.executeUpdate(update);
            update = "UPDATE " + Const.TEAMS_TABLE + " SET " + Const.M_2 + "d = null WHERE " + Const.TEAMS_TEAM
                    + " = '" + resultSet.getString(Const.TEAMS_TEAM) + "';";
            statement2 = getDbConnection().createStatement();
            statement2.executeUpdate(update);
            update = "UPDATE " + Const.TEAMS_TABLE + " SET " + Const.M_3 + "d = null WHERE " + Const.TEAMS_TEAM
                    + " = '" + resultSet.getString(Const.TEAMS_TEAM) + "';";
            statement2 = getDbConnection().createStatement();
            statement2.executeUpdate(update);
            update = "UPDATE " + Const.TEAMS_TABLE + " SET " + Const.M_4 + "d = null WHERE " + Const.TEAMS_TEAM
                    + " = '" + resultSet.getString(Const.TEAMS_TEAM) + "';";
            statement2 = getDbConnection().createStatement();
            statement2.executeUpdate(update);
            update = "UPDATE " + Const.TEAMS_TABLE + " SET " + Const.TEAMS_PLACE + "d = null WHERE " + Const.TEAMS_TEAM
                    + " = '" + resultSet.getString(Const.TEAMS_TEAM) + "';";
            statement2 = getDbConnection().createStatement();
            statement2.executeUpdate(update);
            update = "UPDATE " + Const.TEAMS_TABLE + " SET " + Const.TEAMS_SUM_PLACE + "d = null WHERE " + Const.TEAMS_TEAM
                    + " = '" + resultSet.getString(Const.TEAMS_TEAM) + "';";
            statement2 = getDbConnection().createStatement();
            statement2.executeUpdate(update);
            update = "UPDATE " + Const.TEAMS_TABLE + " SET " + Const.W_1 + "k = null WHERE " + Const.TEAMS_TEAM
                    + " = '" + resultSet.getString(Const.TEAMS_TEAM) + "';";
            statement2 = getDbConnection().createStatement();
            statement2.executeUpdate(update);
            update = "UPDATE " + Const.TEAMS_TABLE + " SET " + Const.W_2 + "k = null WHERE " + Const.TEAMS_TEAM
                    + " = '" + resultSet.getString(Const.TEAMS_TEAM) + "';";
            statement2 = getDbConnection().createStatement();
            statement2.executeUpdate(update);
            update = "UPDATE " + Const.TEAMS_TABLE + " SET " + Const.M_1 + "k = null WHERE " + Const.TEAMS_TEAM
                    + " = '" + resultSet.getString(Const.TEAMS_TEAM) + "';";
            statement2 = getDbConnection().createStatement();
            statement2.executeUpdate(update);
            update = "UPDATE " + Const.TEAMS_TABLE + " SET " + Const.M_2 + "k = null WHERE " + Const.TEAMS_TEAM
                    + " = '" + resultSet.getString(Const.TEAMS_TEAM) + "';";
            statement2 = getDbConnection().createStatement();
            statement2.executeUpdate(update);
            update = "UPDATE " + Const.TEAMS_TABLE + " SET " + Const.M_3 + "k = null WHERE " + Const.TEAMS_TEAM
                    + " = '" + resultSet.getString(Const.TEAMS_TEAM) + "';";
            statement2 = getDbConnection().createStatement();
            statement2.executeUpdate(update);
            update = "UPDATE " + Const.TEAMS_TABLE + " SET " + Const.M_4 + "k = null WHERE " + Const.TEAMS_TEAM
                    + " = '" + resultSet.getString(Const.TEAMS_TEAM) + "';";
            statement2 = getDbConnection().createStatement();
            statement2.executeUpdate(update);
            update = "UPDATE " + Const.TEAMS_TABLE + " SET " + Const.TEAMS_PLACE + "k = null WHERE " + Const.TEAMS_TEAM
                    + " = '" + resultSet.getString(Const.TEAMS_TEAM) + "';";
            statement2 = getDbConnection().createStatement();
            statement2.executeUpdate(update);
            update = "UPDATE " + Const.TEAMS_TABLE + " SET " + Const.TEAMS_SUM_PLACE + "k = null WHERE " + Const.TEAMS_TEAM
                    + " = '" + resultSet.getString(Const.TEAMS_TEAM) + "';";
            statement2 = getDbConnection().createStatement();
            statement2.executeUpdate(update);
        }
    }

    public void convertExcel (String f, int g, String l, double zabeg) throws IOException, InvalidFormatException, SQLException, ClassNotFoundException {
        try {
            String time = "";
            System.out.println(f);
            String select = "";
            String select2 = "";
            int i = 0;
            if (zabeg == 10 && g == 1){
                i = 12;
                select = "SELECT * FROM " + Const.NEW_PARTICIPANTS_TABLE + " WHERE " + Const.PARTICIPANTS_RESULT + " IS NOT NULL AND " +
                        Const.PARTICIPANTS_GENDER + " = '" + l + "' ORDER BY " + Const.PARTICIPANTS_RESULT_KEF + " ASC;";
                select2 = "SELECT * FROM " + Const.NEW_PARTICIPANTS_TABLE + " WHERE " + Const.PARTICIPANTS_RESULT + " IS NULL AND " +
                        Const.PARTICIPANTS_GENDER + " = '" + l + "' ORDER BY " + Const.PARTICIPANTS_RESULT_KEF + " ASC;";

            }else if (zabeg == 10 && g == 2){
                i = 16;
                select = "SELECT * FROM " + Const.TWO_PARTICIPANTS_TABLE + " WHERE " + Const.PARTICIPANTS_RESULT + " IS NOT NULL AND " +
                        Const.PARTICIPANTS_GENDER + " = '" + l + "' ORDER BY " + Const.PARTICIPANTS_SUM_POINTS_KEF + " DESC;";
                select2 = "SELECT * FROM " + Const.TWO_PARTICIPANTS_TABLE + " WHERE " + Const.PARTICIPANTS_RESULT + " IS NULL AND " +
                        Const.PARTICIPANTS_GENDER + " = '" + l + "' ORDER BY " + Const.PARTICIPANTS_SUM_POINTS_KEF + " DESC;";
            }else if (g == 1){
                i = 12;
                select = "SELECT * FROM " + Const.NEW_PARTICIPANTS_TABLE + " WHERE " + Const.PARTICIPANTS_GENDER + " = '" + l +
                        "' AND " + Const.PARTICIPANTS_ZABEG +
                        " = " + zabeg + " AND " + Const.PARTICIPANTS_RESULT + " IS NOT NULL " +
                        " ORDER BY " + Const.PARTICIPANTS_RESULT_KEF + " ASC;";
                select2 = "SELECT * FROM " + Const.NEW_PARTICIPANTS_TABLE + " WHERE " + Const.PARTICIPANTS_GENDER + " = '" + l +
                        "' AND " + Const.PARTICIPANTS_ZABEG +
                        " = " + zabeg + " AND " + Const.PARTICIPANTS_RESULT + " IS NULL " +
                        " ORDER BY " + Const.PARTICIPANTS_RESULT_KEF + " ASC;";
            }else if (g == 2){
                i = 15;
                select = "SELECT * FROM " + Const.TWO_PARTICIPANTS_TABLE + " WHERE " + Const.PARTICIPANTS_GENDER + " = '" + l + "' AND "
                        + Const.PARTICIPANTS_ZABEG + " = " + zabeg + " AND " + Const.PARTICIPANTS_RESULT + " IS NOT NULL " + " ORDER BY " +
                        Const.PARTICIPANTS_SUM_POINTS_KEF + " DESC;";
                select2 = "SELECT * FROM " + Const.TWO_PARTICIPANTS_TABLE + " WHERE " + Const.PARTICIPANTS_GENDER + " = '" + l + "' AND "
                        + Const.PARTICIPANTS_ZABEG + " = " + zabeg + " AND " + Const.PARTICIPANTS_RESULT + " IS NULL " + " ORDER BY " +
                        Const.PARTICIPANTS_SUM_POINTS_KEF + " DESC;";
            }
            File new_file = new File(f);
            FileInputStream out = new FileInputStream(new_file);
            XSSFWorkbook wb = new XSSFWorkbook(out);
            XSSFSheet sheet = wb.getSheetAt(0);
            String v_k = "SELECT * FROM " + Const.TEAMS_TABLE + " WHERE id > 9;";
            Statement statement3 = getDbConnection().createStatement();
            ResultSet resultSet3 = statement3.executeQuery(v_k);
            int place_k = 1;
            int place_d = 1;
            int sos = 0;
            if (g == 1){
                Statement statement = getDbConnection().createStatement();
                ResultSet resultSet = statement.executeQuery(select);
                String str1 = "M";
                Collator collator = Collator.getInstance(new Locale("ru", "RU"));
                collator.setStrength(Collator.PRIMARY);
                int result = collator.compare(str1, l);
                while (resultSet.next()){
                    resultSet3 = statement3.executeQuery(v_k);
                    Row row = sheet.getRow(i);
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
                    if (resultSet.getString(Const.PARTICIPANTS_RESULT) == null){
                        cell.setCellValue("00:00:00.00");
                    }else {
                        time = resultSet.getString(Const.PARTICIPANTS_RESULT);
                        cell.setCellValue(time);
                    }
                    cell = row.getCell(8);
                    if (resultSet.getString(Const.PARTICIPANTS_RESULT) == null){
                        cell.setCellValue("00:00:00.00");
                    }else {
                        time = resultSet.getString(Const.PARTICIPANTS_RESULT_KEF);
                        cell.setCellValue(time);
                    }
                    cell = row.getCell(9);
                    while (resultSet3.next()){
                        String teamddd = resultSet.getString(Const.PARTICIPANTS_TEAMS);
                        String str2 = teamddd;
                        Collator collator2 = Collator.getInstance(new Locale("ru", "RU"));
                        collator2.setStrength(Collator.PRIMARY);
                        int result2 = collator2.compare(str2, resultSet3.getString(Const.TEAMS_TEAM));
                        int result4 = collator2.compare(teamddd, "ДВВКУ");
                        if (result2 == 0 || result4 == 0 || resultSet.getInt(Const.V_K) == 1){
                            cell.setCellValue("в/к");
                            sos = 1;
                        }
                    }
                    if (resultSet.getString(Const.PARTICIPANTS_RESULT) == null){
                        cell.setCellValue("DNS");
                    }else if (sos == 0){
                        cell.setCellValue(place_k);
                        String update = "UPDATE " + Const.NEW_PARTICIPANTS_TABLE + " SET " + Const.PARTICIPANTS_PLACE +
                                " = " + place_k + " WHERE " + Const.PARTICIPANTS_NUMBER + " = " + resultSet.getInt(Const.PARTICIPANTS_NUMBER) +
                                ";";
                        getDbConnection().prepareStatement(update).executeUpdate();
                    }

                    cell = row.getCell(10);
                    if (resultSet.getString(Const.PARTICIPANTS_RESULT) == null){
                        cell.setCellValue("DNS");
                    }else if (resultSet.getInt(Const.PARTICIPANTS_PLACE) == 1){
                        cell.setCellValue("00:00:00.00");
                    }else {
                        time = resultSet.getString(Const.PARTICIPANTS_RAZNICA);
                        cell.setCellValue(time);
                    }

                    i++;
                    if (sos == 0){
                        place_k++;
                    }else sos = 0;
                }
            }else if (g == 2){
                Statement statement = getDbConnection().createStatement();
                ResultSet resultSet = statement.executeQuery(select);
                while (resultSet.next()){
                    resultSet3 = statement3.executeQuery(v_k);
                    Row row = sheet.getRow(i);
                    Cell cell = row.getCell(1);
                    cell.setCellValue(resultSet.getInt(Const.PARTICIPANTS_TARGET));
                    cell = row.getCell(2);
                    cell.setCellValue(resultSet.getInt(Const.PARTICIPANTS_NUMBER));
                    cell = row.getCell(3);
                    cell.setCellValue(resultSet.getString(Const.PARTICIPANTS_NAME));
                    cell = row.getCell(4);
                    cell.setCellValue(resultSet.getString(Const.PARTICIPANTS_TEAMS));
                    cell = row.getCell(5);
                    cell.setCellValue(resultSet.getInt(Const.PARTICIPANTS_YEAR));
                    cell = row.getCell(6);
                    cell.setCellValue(resultSet.getString(Const.PARTICIPANTS_GROUP));
                    cell = row.getCell(7);
                    if (resultSet.getString(Const.PARTICIPANTS_RESULT) == null){
                        cell.setCellValue("00:00:00.00");
                    }else {
                        cell.setCellValue(resultSet.getString(Const.PARTICIPANTS_RESULT));
                    }
                    cell = row.getCell(8);
                    if (resultSet.getString(Const.PARTICIPANTS_RESULT) == null){
                        cell.setCellValue(0);
                    }else {
                        cell.setCellValue(resultSet.getInt(Const.PARTICIPANTS_RESULT_POINTS));
                    }
                    cell = row.getCell(9);
                    cell.setCellValue(resultSet.getDouble(Const.PARTICIPANTS_KEF));
                    cell = row.getCell(10);
                    if (resultSet.getString(Const.PARTICIPANTS_RESULT) == null){
                        cell.setCellValue("00:00:00.00");
                    }else {
                        cell.setCellValue(resultSet.getString(Const.PARTICIPANTS_RESULT_KEF));
                    }
                    cell = row.getCell(11);
                    if (resultSet.getString(Const.PARTICIPANTS_RESULT) == null){
                        cell.setCellValue(0);
                    }else {
                        cell.setCellValue(resultSet.getInt(Const.PARTICIPANTS_RESULT_KEF_POINTS));
                    }
                    cell = row.getCell(12);
                    if (resultSet.getString(Const.PARTICIPANTS_RESULT) == null){
                        cell.setCellValue(0);
                    }else {
                        cell.setCellValue(resultSet.getInt(Const.PARTICIPANTS_SHOOT));
                    }
                    cell = row.getCell(13);
                    if (resultSet.getString(Const.PARTICIPANTS_RESULT) == null){
                        cell.setCellValue(0);
                    }else {
                        cell.setCellValue(resultSet.getInt(Const.PARTICIPANTS_SHOOT_POINTS));
                    }
                    cell = row.getCell(14);
                    if (resultSet.getString(Const.PARTICIPANTS_RESULT) == null){
                        cell.setCellValue(0);
                    }else {
                        cell.setCellValue(resultSet.getInt(Const.PARTICIPANTS_SUM_POINTS));
                    }
                    cell = row.getCell(15);
                    if (resultSet.getString(Const.PARTICIPANTS_RESULT) == null){
                        cell.setCellValue("б/р");
                    }else {
                        cell.setCellValue(resultSet.getString(Const.PARTICIPANTS_DISCHARGE));
                    }
                    cell = row.getCell(16);
                    if (resultSet.getString(Const.PARTICIPANTS_RESULT) == null){
                        cell.setCellValue(0);
                    }else {
                        cell.setCellValue(resultSet.getInt(Const.PARTICIPANTS_SUM_POINTS_KEF));
                    }
                    cell = row.getCell(17);
                    while (resultSet3.next()){
                        String teamddd = resultSet.getString(Const.PARTICIPANTS_TEAMS);
                        String str1 = teamddd;
                        Collator collator = Collator.getInstance(new Locale("ru", "RU"));
                        collator.setStrength(Collator.PRIMARY);
                        int result2 = collator.compare(str1, resultSet3.getString(Const.TEAMS_TEAM));
                        int result4 = collator.compare(teamddd, "ДВВКУ");
                        if (result2 == 0 || result4 == 0 || resultSet.getInt(Const.V_K) == 1){
                            cell.setCellValue("в/к");
                            sos = 1;
                        }
                    }
                    if (resultSet.getString(Const.PARTICIPANTS_RESULT) == null){
                        cell.setCellValue("DNS");
                    }else if (sos == 0){
                        cell.setCellValue(place_d);
                        String update = "UPDATE " + Const.NEW_PARTICIPANTS_TABLE + " SET " + Const.PARTICIPANTS_PLACE +
                                " = " + place_d + " WHERE " + Const.PARTICIPANTS_NUMBER + " = " + resultSet.getInt(Const.PARTICIPANTS_NUMBER) +
                                ";";
                        getDbConnection().prepareStatement(update).executeUpdate();
                    }
                    i++;
                    if (sos == 0){
                        place_d++;
                    }else sos = 0;
                }
            }
            if (g == 1){
                Statement statement4 = getDbConnection().createStatement();
                ResultSet resultSet4 = statement4.executeQuery(select2);
                while (resultSet4.next()){
                    resultSet3 = statement3.executeQuery(v_k);
                    Row row = sheet.getRow(i);
                    Cell cell = row.getCell(1);
                    cell.setCellValue(resultSet4.getInt(Const.PARTICIPANTS_NUMBER));
                    cell = row.getCell(2);
                    cell.setCellValue(resultSet4.getString(Const.PARTICIPANTS_NAME));
                    cell = row.getCell(3);
                    cell.setCellValue(resultSet4.getString(Const.PARTICIPANTS_TEAMS));
                    cell = row.getCell(4);
                    cell.setCellValue(resultSet4.getInt(Const.PARTICIPANTS_YEAR));
                    cell = row.getCell(5);
                    cell.setCellValue(resultSet4.getString(Const.PARTICIPANTS_GROUP));
                    cell = row.getCell(6);
                    cell.setCellValue(resultSet4.getDouble(Const.PARTICIPANTS_KEF));
                    cell = row.getCell(7);
                    if (resultSet4.getString(Const.PARTICIPANTS_RESULT) == null){
                        cell.setCellValue("00:00:00.00");
                    }else {
                        time = resultSet4.getString(Const.PARTICIPANTS_RESULT);
                        cell.setCellValue(time);
                    }
                    cell = row.getCell(8);
                    if (resultSet4.getString(Const.PARTICIPANTS_RESULT) == null){
                        cell.setCellValue("00:00:00.00");
                    }else {
                        time = resultSet4.getString(Const.PARTICIPANTS_RESULT_KEF);
                        cell.setCellValue(time);
                    }
                    cell = row.getCell(9);
                    while (resultSet3.next()){
                        String teamddd = resultSet4.getString(Const.PARTICIPANTS_TEAMS);
                        String str1 = teamddd;
                        Collator collator = Collator.getInstance(new Locale("ru", "RU"));
                        collator.setStrength(Collator.PRIMARY);
                        int result2 = collator.compare(str1, resultSet3.getString(Const.TEAMS_TEAM));
                        int result4 = collator.compare(teamddd, "ДВВКУ");
                        if (result2 == 0 || result4 == 0 || resultSet4.getInt(Const.V_K) == 1){
                            cell.setCellValue("в/к");
                            sos = 1;
                        }
                    }
                    if (resultSet4.getString(Const.PARTICIPANTS_RESULT) == null){
                        cell.setCellValue("DNS");
                    }else if (sos == 0){
                        cell.setCellValue(place_k);
                        String update = "UPDATE " + Const.NEW_PARTICIPANTS_TABLE + " SET " + Const.PARTICIPANTS_PLACE +
                                " = " + place_k + " WHERE " + Const.PARTICIPANTS_NUMBER + " = " + resultSet4.getInt(Const.PARTICIPANTS_NUMBER) +
                                ";";
                        getDbConnection().prepareStatement(update).executeUpdate();
                    }

                    cell = row.getCell(10);
                    if (resultSet4.getString(Const.PARTICIPANTS_RESULT) == null){
                        cell.setCellValue("DNS");
                    }else if (resultSet4.getInt(Const.PARTICIPANTS_PLACE) == 1){
                        cell.setCellValue("00:00:00.00");
                    }else {
                        time = resultSet4.getString(Const.PARTICIPANTS_RAZNICA);
                        cell.setCellValue(time);
                    }

                    i++;
                    if (sos == 0){
                        place_k++;
                    }else sos = 0;
                }

            }else if (g == 2){
                Statement statement4 = getDbConnection().createStatement();
                ResultSet resultSet4 = statement4.executeQuery(select2);
                while (resultSet4.next()){
                    resultSet3 = statement3.executeQuery(v_k);
                    Row row = sheet.getRow(i);
                    Cell cell = row.getCell(1);
                    cell.setCellValue(resultSet4.getInt(Const.PARTICIPANTS_TARGET));
                    cell = row.getCell(2);
                    cell.setCellValue(resultSet4.getInt(Const.PARTICIPANTS_NUMBER));
                    cell = row.getCell(3);
                    cell.setCellValue(resultSet4.getString(Const.PARTICIPANTS_NAME));
                    cell = row.getCell(4);
                    cell.setCellValue(resultSet4.getString(Const.PARTICIPANTS_TEAMS));
                    cell = row.getCell(5);
                    cell.setCellValue(resultSet4.getInt(Const.PARTICIPANTS_YEAR));
                    cell = row.getCell(6);
                    cell.setCellValue(resultSet4.getString(Const.PARTICIPANTS_GROUP));
                    cell = row.getCell(7);
                    if (resultSet4.getString(Const.PARTICIPANTS_RESULT) == null){
                        cell.setCellValue("00:00:00.00");
                    }else {
                        cell.setCellValue(resultSet4.getString(Const.PARTICIPANTS_RESULT));
                    }
                    cell = row.getCell(8);
                    if (resultSet4.getString(Const.PARTICIPANTS_RESULT) == null){
                        cell.setCellValue("0");
                    }else {
                        cell.setCellValue(resultSet4.getInt(Const.PARTICIPANTS_RESULT_POINTS));
                    }
                    cell = row.getCell(9);
                    cell.setCellValue(resultSet4.getDouble(Const.PARTICIPANTS_KEF));
                    cell = row.getCell(10);
                    if (resultSet4.getString(Const.PARTICIPANTS_RESULT) == null){
                        cell.setCellValue("00:00:00.00");
                    }else {
                        cell.setCellValue(resultSet4.getString(Const.PARTICIPANTS_RESULT_KEF));
                    }
                    cell = row.getCell(11);
                    if (resultSet4.getString(Const.PARTICIPANTS_RESULT) == null){
                        cell.setCellValue("0");
                    }else {
                        cell.setCellValue(resultSet4.getInt(Const.PARTICIPANTS_RESULT_KEF_POINTS));
                    }
                    cell = row.getCell(12);
                    if (resultSet4.getString(Const.PARTICIPANTS_RESULT) == null){
                        cell.setCellValue("0");
                    }else {
                        cell.setCellValue(resultSet4.getInt(Const.PARTICIPANTS_SHOOT));
                    }
                    cell = row.getCell(13);
                    if (resultSet4.getString(Const.PARTICIPANTS_RESULT) == null){
                        cell.setCellValue("0");
                    }else {
                        cell.setCellValue(resultSet4.getInt(Const.PARTICIPANTS_SHOOT_POINTS));
                    }
                    cell = row.getCell(14);
                    if (resultSet4.getString(Const.PARTICIPANTS_RESULT) == null){
                        cell.setCellValue("0");
                    }else {
                        cell.setCellValue(resultSet4.getInt(Const.PARTICIPANTS_SUM_POINTS));
                    }
                    cell = row.getCell(15);
                    if (resultSet4.getString(Const.PARTICIPANTS_RESULT) == null){
                        cell.setCellValue("б/р");
                    }else {
                        cell.setCellValue(resultSet4.getInt(Const.PARTICIPANTS_DISCHARGE));
                    }
                    cell = row.getCell(16);
                    if (resultSet4.getString(Const.PARTICIPANTS_RESULT) == null){
                        cell.setCellValue("0");
                    }else {
                        cell.setCellValue(resultSet4.getInt(Const.PARTICIPANTS_SUM_POINTS_KEF));
                    }
                    cell = row.getCell(17);
                    while (resultSet3.next()){
                        String teamddd = resultSet4.getString(Const.PARTICIPANTS_TEAMS);
                        String str1 = teamddd;
                        Collator collator = Collator.getInstance(new Locale("ru", "RU"));
                        collator.setStrength(Collator.PRIMARY);
                        int result2 = collator.compare(str1, resultSet3.getString(Const.TEAMS_TEAM));
                        int result4 = collator.compare(teamddd, "ДВВКУ");
                        if (result2 == 0 || result4 == 0 || resultSet4.getInt(Const.V_K) == 1){
                            cell.setCellValue("в/к");
                            sos = 1;
                        }
                    }
                    if (resultSet4.getString(Const.PARTICIPANTS_RESULT) == null){
                        cell.setCellValue("DNS");
                    }else if (sos == 0){
                        cell.setCellValue(place_d);
                        String update = "UPDATE " + Const.NEW_PARTICIPANTS_TABLE + " SET " + Const.PARTICIPANTS_PLACE +
                                " = " + place_d + " WHERE " + Const.PARTICIPANTS_NUMBER + " = " + resultSet4.getInt(Const.PARTICIPANTS_NUMBER) +
                                ";";
                        getDbConnection().prepareStatement(update).executeUpdate();
                    }
                    i++;
                    if (sos == 0){
                        place_d++;
                    }else sos = 0;
                }
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

    public void teamProc(String table, String vid) throws SQLException, ClassNotFoundException {
        String teams = "SELECT * FROM " + Const.TEAMS_TABLE;
        Statement statement = getDbConnection().createStatement();
        Statement statement1 = getDbConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(teams);
        int i = 0;
        int j = 0;
        double sum = 0;
        String count = "";
        String team = "";
        int h = 0;
        while (resultSet.next()) {
            i = 0;
            j = 0;
            sum = 0;
            if (resultSet.getInt("id") > 9) {
                team = resultSet.getString(Const.TEAMS_TEAM);
                count = "SELECT * FROM " + table + " WHERE " + Const.PARTICIPANTS_TEAMS + " LIKE '" + team + "%' ORDER BY "
                        + Const.PARTICIPANTS_PLACE + ";";
            } else {
                team = resultSet.getString(Const.TEAMS_TEAM);
                count = "SELECT * FROM " + table + " WHERE " + Const.PARTICIPANTS_TEAMS + " LIKE 'КФК № " + team + " %' ORDER BY "
                        + Const.PARTICIPANTS_PLACE + ";";
            }
            ResultSet resultSet1 = statement1.executeQuery(count);
            while (resultSet1.next()) {
                if (resultSet1.getString(Const.PARTICIPANTS_RESULT) == null){

                }else if (resultSet1.getString(Const.PARTICIPANTS_GENDER).equals("M")) {
                    j++;
                    if ((j == 4 && i == 2) || (j == 5 && i == 1)) {
                        break;
                    } else if (j < 5) {
                        String t_m = "m_" + j + "_" + vid;
                        String update_m = "UPDATE " + Const.TEAMS_TABLE + " SET " + t_m + " = " +
                                resultSet1.getDouble(Const.PARTICIPANTS_PLACE) + " WHERE " + Const.TEAMS_TEAM +
                                " = '" + team + "';";
                        getDbConnection().prepareStatement(update_m).executeUpdate();
                        sum = (sum + resultSet1.getDouble(Const.PARTICIPANTS_PLACE));
                    } else j = 4;

                } else if (resultSet1.getString(Const.PARTICIPANTS_GENDER).equals("W")) {
                    i++;
                    if ((i == 3 && j == 3) || (i == 2 && j == 4)) {
                        break;
                    } else if (i < 3) {
                        String t_w = "w_" + i + "_" + vid;
                        String update_m = "UPDATE " + Const.TEAMS_TABLE + " SET " + t_w + " = " +
                                resultSet1.getDouble(Const.PARTICIPANTS_PLACE) + " WHERE " + Const.TEAMS_TEAM +
                                " = '" + team + "';";
                        getDbConnection().prepareStatement(update_m).executeUpdate();
                        sum = (sum + resultSet1.getDouble(Const.PARTICIPANTS_PLACE));
                    } else i = 2;

                }
                String update_sum = "UPDATE " + Const.TEAMS_TABLE + " SET " + Const.TEAMS_SUM_PLACE + vid + " = " +
                        sum + " WHERE " + Const.TEAMS_TEAM +
                        " = '" + team + "';";
                getDbConnection().prepareStatement(update_sum).executeUpdate();
            }
        }
            String select_2 = "SELECT * FROM " + Const.NEW_PARTICIPANTS_TABLE + " WHERE " + Const.PARTICIPANTS_GENDER + " = 'W';";
            String select_1 = "SELECT * FROM " + Const.TEAMS_TABLE;
            Statement statement2 = getDbConnection().createStatement();
            ResultSet resultSet2 = statement2.executeQuery(select_2);
            int place_w_k = 0;
            while (resultSet2.next()){
                place_w_k++;
            }
            int place_m_k = 0;
            String select_3 = "SELECT * FROM " + Const.NEW_PARTICIPANTS_TABLE + " WHERE " + Const.PARTICIPANTS_GENDER + " = 'M';";
            resultSet2 = statement2.executeQuery(select_3);
            while (resultSet2.next()){
                place_m_k++;
            }
            int place_w_d = 0;
            String select_4 = "SELECT * FROM " + Const.TWO_PARTICIPANTS_TABLE + " WHERE " + Const.PARTICIPANTS_GENDER + " = 'W';";
            resultSet2 = statement2.executeQuery(select_4);
            while (resultSet2.next()){
                place_w_d++;
            }
            int place_m_d = 0;
            String select_5 = "SELECT * FROM " + Const.TWO_PARTICIPANTS_TABLE + " WHERE " + Const.PARTICIPANTS_GENDER + " = 'M';";
            resultSet2 = statement2.executeQuery(select_5);
            while (resultSet2.next()){
                place_m_d++;

            }
            int q = place_m_d;
            int w = place_w_d;
            int e = place_m_k;
            int r = place_w_k;
            resultSet2 = statement2.executeQuery(select_1);
            while (resultSet2.next()){
                place_m_d = q;
                place_w_d = w;
                place_m_k = e;
                place_w_k = r;
                if (vid.equals("k")){
                    if (resultSet2.getDouble(Const.W_1 + "k") == 0){
                        place_w_k = place_w_k + 1;
                        String up = "UPDATE " + Const.TEAMS_TABLE + " SET " + Const.W_1 + "k = " + place_w_k +
                                " WHERE id = " + resultSet2.getInt("id") + ";";
                        getDbConnection().prepareStatement(up).executeUpdate();
                    }
                    if (resultSet2.getDouble(Const.M_1 + "k") == 0){
                        place_m_k = place_m_k + 1;
                        String up = "UPDATE " + Const.TEAMS_TABLE + " SET " + Const.M_1 + "k = " + place_m_k +
                                " WHERE id = " + resultSet2.getInt("id") + ";";
                        getDbConnection().prepareStatement(up).executeUpdate();
                    }
                    if (resultSet2.getDouble(Const.M_2 + "k") == 0){
                        place_m_k = place_m_k + 1;
                        String up = "UPDATE " + Const.TEAMS_TABLE + " SET " + Const.M_2 + "k = " + place_m_k +
                                " WHERE id = " + resultSet2.getInt("id") + ";";
                        getDbConnection().prepareStatement(up).executeUpdate();
                    }
                    if (resultSet2.getDouble(Const.M_3 + "k") == 0){
                        place_m_k = place_m_k + 1;
                        String up = "UPDATE " + Const.TEAMS_TABLE + " SET " + Const.M_3 + "k = " + place_m_k +
                                " WHERE id = " + resultSet2.getInt("id") + ";";
                        getDbConnection().prepareStatement(up).executeUpdate();
                    }
                    if (resultSet2.getDouble(Const.M_4 + "k") == 0 && resultSet2.getDouble(Const.W_2 + "k") == 0){
                        place_m_k = place_m_k + 1;
                        String up = "UPDATE " + Const.TEAMS_TABLE + " SET " + Const.M_4 + "k = " + place_m_k +
                                " WHERE id = " + resultSet2.getInt("id") + ";";
                        getDbConnection().prepareStatement(up).executeUpdate();
                    }
                }else if (vid.equals("d")){
                    if (resultSet2.getDouble(Const.W_1 + "d") == 0){
                        place_w_d = place_w_d + 1;
                        String up = "UPDATE " + Const.TEAMS_TABLE + " SET " + Const.W_1 + "d = " + place_w_d +
                                " WHERE id = " + resultSet2.getInt("id") + ";";
                        getDbConnection().prepareStatement(up).executeUpdate();
                    }
                    if (resultSet2.getDouble(Const.M_1 + "d") == 0){
                        place_m_d = place_m_d + 1;
                        String up = "UPDATE " + Const.TEAMS_TABLE + " SET " + Const.M_1 + "d = " + place_m_d +
                                " WHERE id = " + resultSet2.getInt("id") + ";";
                        getDbConnection().prepareStatement(up).executeUpdate();
                    }
                    if (resultSet2.getDouble(Const.M_2 + "d") == 0){
                        place_m_d = place_m_d + 1;
                        String up = "UPDATE " + Const.TEAMS_TABLE + " SET " + Const.M_2 + "d = " + place_m_d +
                                " WHERE id = " + resultSet2.getInt("id") + ";";
                        getDbConnection().prepareStatement(up).executeUpdate();
                    }
                    if (resultSet2.getDouble(Const.M_3 + "d") == 0){
                        place_m_d = place_m_d + 1;
                        String up = "UPDATE " + Const.TEAMS_TABLE + " SET " + Const.M_3 + "d = " + place_m_d +
                                " WHERE id = " + resultSet2.getInt("id") + ";";
                        getDbConnection().prepareStatement(up).executeUpdate();
                    }
                    if (resultSet2.getDouble(Const.M_4 + "d") == 0 && resultSet2.getDouble(Const.W_2 + "d") == 0){
                        place_m_d = place_m_d + 1;
                        String up = "UPDATE " + Const.TEAMS_TABLE + " SET " + Const.M_4 + "d = " + place_m_d +
                                " WHERE id = " + resultSet2.getInt("id") + ";";
                        getDbConnection().prepareStatement(up).executeUpdate();
                    }
                }
            }
            if (vid.equals("k")){
                String select5 = "SELECT * FROM " + Const.TEAMS_TABLE;
                Statement statement5 = getDbConnection().createStatement();
                ResultSet resultSet5 = statement5.executeQuery(select5);
                Collator collator = Collator.getInstance(new Locale("ru", "RU"));
                collator.setStrength(Collator.PRIMARY);
                while (resultSet5.next()){
                    int w1 = resultSet5.getInt(Const.W_1 + "k");
                    int w2 = resultSet5.getInt(Const.W_2 + "k");
                    int m1 = resultSet5.getInt(Const.M_1 + "k");
                    int m2 = resultSet5.getInt(Const.M_2 + "k");
                    int m3 = resultSet5.getInt(Const.M_3 + "k");
                    int m4 = resultSet5.getInt(Const.M_4 + "k");
                    int sum_k = w1 + w2 + m1 + m2 + m3 + m4;
                    String update_sum = "UPDATE " + Const.TEAMS_TABLE + " SET " + Const.TEAMS_SUM_PLACE + "k" + " = " +
                            sum_k + " WHERE " + Const.TEAMS_TEAM +
                            " = '" + resultSet5.getString(Const.TEAMS_TEAM) + "';";
                    getDbConnection().prepareStatement(update_sum).executeUpdate();
                }
            }else if (vid.equals("d")){
                String select5 = "SELECT * FROM " + Const.TEAMS_TABLE;
                Statement statement5 = getDbConnection().createStatement();
                ResultSet resultSet5 = statement5.executeQuery(select5);
                Collator collator = Collator.getInstance(new Locale("ru", "RU"));
                collator.setStrength(Collator.PRIMARY);
                while (resultSet5.next()){
                    int w1 = resultSet5.getInt(Const.W_1 + "d");
                    int w2 = resultSet5.getInt(Const.W_2 + "d");
                    int m1 = resultSet5.getInt(Const.M_1 + "d");
                    int m2 = resultSet5.getInt(Const.M_2 + "d");
                    int m3 = resultSet5.getInt(Const.M_3 + "d");
                    int m4 = resultSet5.getInt(Const.M_4 + "d");
                    int sum_d = w1 + w2 + m1 + m2 + m3 + m4;
                    String update_sum = "UPDATE " + Const.TEAMS_TABLE + " SET " + Const.TEAMS_SUM_PLACE + "d" + " = " +
                            sum_d + " WHERE " + Const.TEAMS_TEAM +
                            " = '" + resultSet5.getString(Const.TEAMS_TEAM) + "';";
                    getDbConnection().prepareStatement(update_sum).executeUpdate();
                }
            }
    }

    public void convertTeam(String f, int i, String vid) throws SQLException, ClassNotFoundException, IOException {
        String select = "SELECT * FROM " + Const.TEAMS_TABLE + " ORDER BY " +
                Const.TEAMS_SUM_PLACE + vid + " ASC;";
        int place = 0;
        Statement statement = getDbConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(select);
        while (resultSet.next()){
            String team = resultSet.getString(Const.TEAMS_TEAM);
            if (resultSet.getInt("id") > 9){
                String update = "UPDATE " + Const.TEAMS_TABLE + " SET " + Const.TEAMS_PLACE + vid + " = " +
                        "'в/к'" + " WHERE " + Const.TEAMS_TEAM +
                        " = '" + team + "';";
                getDbConnection().prepareStatement(update).executeUpdate();
            }else {
                place++;
                String update = "UPDATE " + Const.TEAMS_TABLE + " SET " + Const.TEAMS_PLACE + vid + " = " +
                        place + " WHERE " + Const.TEAMS_TEAM +
                        " = '" + team + "';";
                getDbConnection().prepareStatement(update).executeUpdate();
            }

        }
        File new_file = new File(f);
        FileInputStream out = new FileInputStream(new_file);
        XSSFWorkbook wb = new XSSFWorkbook(out);
        XSSFSheet sheet = wb.getSheetAt(0);
        select = "SELECT * FROM " + Const.TEAMS_TABLE + " ORDER BY " +
                Const.TEAMS_SUM_PLACE + vid + " ASC;";
        int place_t = 1;
        int sos = 0;
        resultSet = statement.executeQuery(select);
        while (resultSet.next()){
            Row row = sheet.getRow(i);
            Cell cell = row.getCell(1);
            if (resultSet.getInt("id") > 9 ){
                cell.setCellValue(resultSet.getString(Const.TEAMS_TEAM));
            }else {
                cell.setCellValue("КФК № " + resultSet.getString(Const.TEAMS_TEAM));
            }
            cell = row.getCell(2);
            cell.setCellValue(resultSet.getInt(Const.W_1 + vid));
            cell = row.getCell(3);
            cell.setCellValue(resultSet.getInt(Const.W_2 + vid));
            cell = row.getCell(4);
            cell.setCellValue(resultSet.getInt(Const.M_1 + vid));
            cell = row.getCell(5);
            cell.setCellValue(resultSet.getInt(Const.M_2 + vid));
            cell = row.getCell(6);
            cell.setCellValue(resultSet.getInt(Const.M_3 + vid));
            cell = row.getCell(7);
            cell.setCellValue(resultSet.getInt(Const.M_4 + vid));
            cell = row.getCell(8);
            cell.setCellValue(resultSet.getInt(Const.TEAMS_SUM_PLACE + vid));
            cell = row.getCell(9);
            cell.setCellValue(resultSet.getString(Const.TEAMS_PLACE + vid));
            i++;
        }
        out.close();
        FileOutputStream write = new FileOutputStream(new_file);
        wb.write(write);
        write.close();
        System.out.println("good");

    }

    public void newTeamsVk(String team) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + Const.TEAMS_TABLE + "("+ Const.TEAMS_TEAM + ")" + " VALUES(?)";

            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, team);
            prSt.executeUpdate();
    }

    public void proverkaTeam(String team) throws SQLException, ClassNotFoundException {
        String select = "SELECT * FROM " + Const.TEAMS_TABLE;
        Statement statement = getDbConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(select);
        while (resultSet.next()){
            if (resultSet.getString(Const.TEAMS_TEAM).equals(team)){
                NewTeamVk.proverka = 1;
                break;
            }
        }
    }


    public void tyu() throws SQLException, ClassNotFoundException {
        String s = "SELECT * FROM " + Const.TEAMS_TABLE +" WHERE id = 1;";
        Statement statement = getDbConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(s);
        while (resultSet.next()){
            System.out.println(resultSet.getDouble(Const.W_1 + "k"));
            if (resultSet.getDouble(Const.W_1 + "k") == 0 ){
                System.out.println("good");
            }
        }
    }

}

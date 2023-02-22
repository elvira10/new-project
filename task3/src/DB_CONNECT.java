import java.sql.*;
import javax.security.auth.login.LoginException;import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DB_CONNECT {
    private static final String DB_Username="postgres";
    private static final String DB_Password="0000";
    private static final String DB_URL="jdbc:postgresql://localhost:5432/postgres";
    public String hash(String jojo) throws LoginException, NoSuchAlgorithmException{
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] bytes = md5.digest(jojo.getBytes());    StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02X", b));
        }
        String mer = builder.toString();
        mer = mer.toLowerCase();
        System.out.println(mer);
        return mer;
    }
    public Connection DB_Connect(){
        Connection connection = null;
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_Username, DB_Password);
            if(connection != null){
                assert true;
            }
            else{
                System.err.println("Connection is failed.");
            }
        } catch (Exception a){
            System.out.print(a);
        }
        return connection;
    }

    public boolean Check_Login_DB(String s) throws SQLException{
        Connection connection = DB_Connect();
        Statement statement = connection.createStatement();
        String sqlQuery = "Select login FROM table_1";
        ResultSet result = statement.executeQuery(sqlQuery);
        boolean flag = false;
        while(result.next()){
            String login = result.getString("login");
            if((s.equals(login))){
                flag = true;
                return true;
            }
        }
        if(!flag){
            return false;
        }
        result.close();
        return false;
    }
    public boolean Check_IIN_DB(String s) throws SQLException{
        Connection connection = DB_Connect();
        Statement statement = connection.createStatement();
        String sqlQuery = "Select iin FROM table_1";
        ResultSet result = statement.executeQuery(sqlQuery);
        boolean flag = false;
        while(result.next()){
            String iin = result.getString("iin");
            if((s.equals(iin))){
                flag = true;
                return true;
            }
        }
        if(!flag){
            return false;
        }
        result.close();
        return false;
    }
    public boolean Check_Password_DB(String s) throws SQLException {
        Connection connection = DB_Connect();
        Statement statement = connection.createStatement();
        String sqlQuery = "Select password FROM table_1";
        ResultSet result = statement.executeQuery(sqlQuery);
        boolean flag = false;
        while (result.next()) {
            String password = result.getString("password");
            if ((s.equals(password))) {
                flag = true;
                return true;
            }
        }
        if (!flag) {
            return false;
        }
        result.close();
        return false;
    }
}
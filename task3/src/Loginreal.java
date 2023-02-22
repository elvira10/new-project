import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Loginreal {
    private String login;
    Loginreal(String login) {
        this.login = login;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public boolean makeLogin() throws IOException, SQLException {
        Logger Log = Logger.getLogger(Main.class.getName());
        Handler filehandler = new FileHandler("%h/yJava.log");
        Log.setLevel(Level.INFO);
        Log.setUseParentHandlers(false);
        Log.addHandler(filehandler);
        DB_CONNECT db = new DB_CONNECT();
        db.DB_Connect();
        while (true) {
            if(db.Check_Login_DB(this.login)) {
                System.out.printf("Correct");
                Log.info("Correct login");
                return true;
            }
            if (db.Check_Login_DB(this.login)) {
                System.out.printf("Correct");
                Log.info("Correct iin");
                return true;
            } else {
                System.out.println("Not authorised");
                Log.info("User use incorrect login");
                return false;
            }
        }
    }
}
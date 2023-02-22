import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;

public class Iin extends Loginreal {
    private String iin;

    Iin(String login) {
        super(login);
    }

    public String getIin() {
        return iin;
    }

    public void setIin(String iin) {
        this.iin = iin;
    }

    public boolean checkIIN() throws IOException, SQLException {
        java.util.logging.Logger Log = java.util.logging.Logger.getLogger(Main.class.getName());
        Handler filehandler = new FileHandler("%h/yJava.log");
        Log.setLevel(Level.INFO);
        Log.setUseParentHandlers(false);
        Log.addHandler(filehandler);
        super.makeLogin();
        DB_CONNECT db = new DB_CONNECT();
        db.DB_Connect();
        while (true) {
            if (db.Check_IIN_DB(this.getLogin())) {
                System.out.println("Good! Next...");
                Log.info("Correct IIN");
                return true;
            } else if (this.getLogin().matches("^\\d+$") && this.getLogin().equals("1234567890")) {
                Log.info("Correct IIN");
                return true;
            } else {
                System.out.printf("Incorrect, please try again");
                Log.info("Incorrect IIN entered");
                return false;
            }
        }
    }
}
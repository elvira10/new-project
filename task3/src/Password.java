import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.*;

public class Password {
    private String password;
    private Scanner sc;
    Password(String password) {
        this.password = password;
        sc = new Scanner(System.in);
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean checkAuthorize() throws IOException, SQLException, LoginException, NoSuchAlgorithmException {
        java.util.logging.Logger Log = java.util.logging.Logger.getLogger(Main.class.getName());
        Handler filehandler = new FileHandler("%h/yJava.log");
        Log.setLevel(Level.INFO);
        Log.setUseParentHandlers(false);
        Log.addHandler(filehandler);
        DB_CONNECT db = new DB_CONNECT();
        db.DB_Connect();
        System.out.print("Enter password: ");
        this.password = sc.nextLine();
        this.password = db.hash(this.password);
        Log.info("enter password");
        if (db.Check_Password_DB(this.password)) {

            System.out.println("Correct password");
            Log.info("User entered correct password");
        } else {
            while (true) {
                System.out.println("Incorrect password. Please use at least 6 symbols including numbers.");
                password = sc.nextLine();
                Log.info("user enter");
                if (db.Check_Password_DB(this.password)) {
                    System.out.println("Correct password");
                    Log.info("Password is correct");
                    return true;
                } else if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]+$") || password.length() < 6) {
                    System.out.println("Incorrect password. Please use at least 6 symbols including numbers.");
                    Log.info("Password is not correct");
                } else {
                    setPassword(password);
                    System.out.println("Incorrect password, please try again.");
                    Log.info(" Incorrect password ");
                }
            }
        }
        sc.close();
        return true;
    }
}
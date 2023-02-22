import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    private int choose;
    Menu(int c) {
        this.choose = c;
    }
    public int getChoose() {
        return choose;
    }
    public void setChoose(int choose) {
        this.choose = choose;
    }
    public void makeChoose() throws IOException, SQLException, LoginException, NoSuchAlgorithmException {
        int attempt = 0;
        String login;
        Scanner sc = new Scanner(System.in);
        if (this.choose == 1) {
            while (attempt < 5) {
                login = sc.nextLine();
                Iin login2 = new Iin(login);
                boolean isLOGINED = login2.checkIIN();
                if (isLOGINED) {
                    break;
                }
                attempt++;
            }
        }
        if (this.choose == 2) {
            login = sc.nextLine();
            while (attempt < 5) {
                login = sc.nextLine();
                Loginreal login3 = new Loginreal(login);
                if (login3.makeLogin()) {
                    break;
                }
                attempt++;
            }
        }
        if (attempt == 5) {
            System.out.println("Please try later");
        }
        String password = sc.nextLine();
        Password login1 = new Password(password);
        boolean isAuthorized = login1.checkAuthorize();
    }
}
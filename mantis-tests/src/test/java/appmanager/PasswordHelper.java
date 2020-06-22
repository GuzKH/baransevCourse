package appmanager;

import model.MailMessage;
import org.apache.http.client.methods.HttpPost;
import org.openqa.selenium.By;

import javax.mail.MessagingException;
import java.util.List;

public class PasswordHelper extends HelperBase {
    public PasswordHelper(ApplicationManager app) {
        super(app);
    }

    public void resetPassword() {
        click(By.cssSelector("input[value='Reset Password']"));
    }

    public void chooseTheLastUser() {
        click(By.xpath("(//a[contains(@href,'manage_user_edit_page')])[last()]"));
    }

    public void goToManageUsers() {
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
    }


    public void chooseUser() {
        goToManageUsers();
        chooseTheLastUser();
    }

    public void changePassword() throws MessagingException {
        //given
        String user = "user1592778356636";
        String password = "password";
        String email = "user1592778356636@localhost.localdomain";

        //when
       // HttpPost post = new HttpPost(app.getProperty("web.baseUrl") + "/manage_user_page.php"); // post request
        loginAsAdmin();
        chooseUser();
        resetPassword();

        app.james().createUser(user, password);
        List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
        app.mail().findConfirmationLink(mailMessages, email);
    }

    public void loginAsAdmin() {
        type(By.name("username"), "administrator");
        click(By.cssSelector("input[value='Login']"));
        type(By.id("password"), "root");
        click(By.cssSelector("input[value='Login']"));
    }

}

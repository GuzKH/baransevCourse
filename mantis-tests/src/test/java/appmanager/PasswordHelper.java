package appmanager;

import model.MailMessage;
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

    public void goToLoginPage() {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    }

    public void createUser(String user, String password, String email) throws MessagingException {
        //Given
        app.james().createUser(user, password);

        //When
        app.registration().start(user, email);
        String confirmationLink = getConfirmationLink(user, password, email);
        app.registration().finish(confirmationLink, password);

        app.james().drainEmail(user, password);
    }

    public String getConfirmationLink(String user, String password, String email) throws MessagingException {
        List<MailMessage> mailMessages = app.james()
                .waitForMail(user, password, 60000);
        return app.mail().findConfirmationLink(mailMessages, email);
    }


    public void chooseUser() {
        goToManageUsers();
        chooseTheLastUser();
    }

//    public void changePassword() throws MessagingException {
//        //given
//        long now = System.currentTimeMillis();
//        String user = String.format("user%d", now);
//        String oldPassword = "password";
//        String email = String.format("user%d@localhost", now);
//
//        app.passwordHelper().createUser(user, oldPassword, email);
//
//        String newPassword = "passwordNew";
//        //when
//        // HttpPost post = new HttpPost(app.getProperty("web.baseUrl") + "/manage_user_page.php"); // post request
//        loginAsAdmin();
//        chooseUser();
//        resetPassword();
//
//        String confirmationLink = getConfirmationLink(user, oldPassword, email);
//        app.registration().finish(confirmationLink, newPassword);
//    }

    public void loginAsAdmin() {
        type(By.name("username"), "administrator");
        click(By.cssSelector("input[value='Login']"));
        type(By.id("password"), "root");
        click(By.cssSelector("input[value='Login']"));
    }

//    public void deleteUser(){
//        goToLoginPage();
//        loginAsAdmin();
//    }

}

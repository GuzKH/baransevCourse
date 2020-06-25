package tests;

import model.UserData;
import model.Users;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;

public class ChangingPasswordTest extends TestBase {


    @Test
    public void testChangePassword() throws IOException, MessagingException {
        //Given
//        long now = System.currentTimeMillis();
//        app.passwordHelper().createUser(user, password, email);
        String newPassword = "passwordNew";
        Users users = app.dbHelper().users();
        UserData commonUser = users.stream()
                .filter(dbUser ->
                        !dbUser.getUsername().equals("administrator") && !dbUser.getUsername().equals("administrator4")
                        && !dbUser.getEmail().contains("localdomain")
                )
                .findFirst().get();

        String user = commonUser.getUsername();
        String password = "password";
        String email = commonUser.getEmail();
        int userId = commonUser.getId();

        app.james().drainEmail(user, password);

        //When
        app.passwordHelper().goToLoginPage();
        app.passwordHelper().loginAsAdmin();
        app.passwordHelper().chooseUser(userId);
        app.passwordHelper().resetPassword();

        String confirmationLink = app.passwordHelper().getConfirmationLink(user, password, email);
        app.registration().finish(confirmationLink, newPassword);

        //Then
        // Assert.assertTrue(app.newSession().login(user,password));
        Assert.assertTrue(app.newSession().login(user, newPassword));

//        app.james().deleteUser();
    }


}

package tests;

import appmanager.MailHelper;
import model.MailMessage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public class RegistrationTests extends TestBase{

  //  @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @Test
    public void testRegistration() throws IOException, MessagingException {
        //Given
        long now = System.currentTimeMillis();
        String user = String.format("user%d", now);
        String password = "password";

        String email = String.format("user%d@localhost", now);
        app.james().createUser(user, password);

        //When
        app.registration().start(user, email);
        //List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
        String confirmationLink = app.mail().findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, password);

        //Then
        Assert.assertTrue(app.newSession().login(user,password));
    }

 //   @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}

package appmanager;

import org.openqa.selenium.WebDriver;

public class RegistrationHelper {
    private ApplicationManager app;
    private WebDriver wd;

    public RegistrationHelper(ApplicationManager app) {
        this.app = app;
        wd = app.getDriver();
    }

    public void start(String username, String email){
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    }
}

package fourthTask.appmanager;

import fourthTask.model.ContactData;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertTrue;

public class ContactHelper {
    public boolean acceptNextAlert = true;
    private WebDriver wd;

    public ContactHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void submitContactCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), lastName());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomeNumber());
        type(By.name("email"), contactData.getEmail());
    }

    public void type(By locator, String text) {
        click(locator);
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
    }

    @NotNull
    public String lastName() {
        return "test3*";
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }

    public void acceptContactDeletion() {
        assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
    }

    public void deleteConatct() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void selectContact() {
        click(By.id("4"));
    }

    public String closeAlertAndGetItsText() {
        try {
            Alert alert = wd.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}

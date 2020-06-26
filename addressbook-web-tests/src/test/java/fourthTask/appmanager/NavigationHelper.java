package fourthTask.appmanager;

import fourthTask.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void groupPage() {
        if (isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Group")
                && isElementPresent(By.name("new"))) {
            return;
        }
        click(By.linkText("groups"));
    }

    public void homePage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home page"));
    }

    public void homePageFromGroup() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home"));
    }

    public void homeFromGroup() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("group page \"test1\""));
    }

    public void homeFromGroup(GroupData group) {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("group page \"" + group.getName() + "\""));
    }
}

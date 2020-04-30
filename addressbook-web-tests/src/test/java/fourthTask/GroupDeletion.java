package fourthTask;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import org.openqa.selenium.*;

import java.util.concurrent.TimeUnit;

public class GroupDeletion extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    goToGroupPage();
//    wd.findElement(By.xpath("//div[@id='content']/form/span")).click();
//    wd.findElement(By.xpath("//form[@action='/addressbook/group.php']")).click();
    selectGroup();
    deleteGroup();
    backToGroupPage();
  }

}

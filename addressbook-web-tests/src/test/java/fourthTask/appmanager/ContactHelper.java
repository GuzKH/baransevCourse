package fourthTask.appmanager;

import fourthTask.model.ContactData;
import fourthTask.model.Contacts;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class ContactHelper extends HelperBase {
    public boolean acceptNextAlert = true;

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
      //  attach(By.name("photo"), contactData.getPhoto());
        type(By.name("address"), contactData.getAddress());
//        type(By.name("home"), contactData.getHomePhone());
//        type(By.name("mobile"), contactData.getMobilePhone());
//        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("email"), contactData.getEmail());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void create(ContactData contact, boolean b) {
        initContactCreation();
        fillContactForm(contact, true);
        submitContactCreation();
        contactCache = null;
    }

    public void modify(ContactData contact) {
        selectById(contact.getId());
        edit();
        fillContactForm(contact, false);
        updateContactEdition();
        contactCache = null;
    }


    public void delete(ContactData сontact) {
        selectById(сontact.getId());
        delete();
        acceptContactDeletion();
        acceptNextAlert = true;
        contactCache = null;
    }

    public void type(By locator, String text) {
        click(locator);
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
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

    public void delete() {
        click(By.xpath("//input[@value='Delete']"));
    }


    public void select(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectById(int id) {
        wd.findElement(By.cssSelector("input[value = '" + id + "']")).click();
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

    public void edit() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void updateContactEdition() {
        click(By.name("update"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

//    public Set<ContactData> all() {
//        Set<ContactData> contacts = new HashSet<ContactData>();
//        List<WebElement> rows = wd.findElements(By.name("entry"));
//
//        for (WebElement row : rows) {
//            //String name = row.getText();
//
//            List<WebElement> cells = row.findElements(By.tagName("td"));
//            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
//            String lastName = cells.get(1).getText();
//            String firstName = cells.get(2).getText();
//            String address = cells.get(3).getText();
//            String email = cells.get(4).getText();
//            String[] phones = cells.get(5).getText().split("\n");
//
//            contacts.add( new ContactData()
//                    .withId(id)
//                    .withFirstName(firstName)
//                    .withLastName(lastName)
//                    .withAddress(address)
//                    .withEmail(email)
//                    .withHomePhone(phones[0])
//                    .withMobilePhone(phones[1])
//                    .withWorkPhone(phones[2]));
//        }
//        return contacts;
//    }


    private Contacts contactCache = null;

    public Contacts all() {

        //cache
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();

        //
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            String address = cells.get(3).getText();
            String email = cells.get(4).getText();
            String allPhones = cells.get(5).getText();

            contactCache.add(new ContactData()
                    .withId(id)
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withAddress(address)
                    .withEmail(email)
                    .withAllPhones(allPhones));
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String homePhone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
        String workPhone = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return new ContactData()
                .withId(contact.getId())
                .withLastName(lastName)
                .withFirstName(firstName)
                .withHomePhone(homePhone)
                .withMobilePhone(mobilePhone)
                .withWorkPhone(workPhone);

    }

    private void initContactModificationById(int id) {
        //short variables
//        wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
//        wd.findElement(By.xpath(String.format("//tr[./input[@value='%s']]/td[8]/a", id))).click();
//        wd.findElement(By.cssSelector(String.format("a[href = 'edit.php?id=%s']",id))).click();

        //long path
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value = '%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cell = row.findElements(By.tagName("td"));
        cell.get(7).findElement(By.tagName("a")).click();

    }
}

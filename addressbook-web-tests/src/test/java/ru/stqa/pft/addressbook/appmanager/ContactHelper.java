package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactHelper extends BaseHelper {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    attach(By.name("photo"), contactData.getPhoto());
    type(By.name("address"), contactData.getAddress());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("phone2"), contactData.getSecondPhone());
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void goToHome() {
    click(By.linkText("home"));
  }

  private void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void submitContactDeletion() {
    wd.switchTo().alert().accept();
  }

  private void initContactModificationById(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void create(ContactData contact) {
    initContactCreation();
    fillContactForm(contact);
    submitContactCreation();
    contactCache = null;
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    submitContactDeletion();
    contactCache = null;
    goToHome();
  }

  public void modify(ContactData contact) {
    initContactModificationById(contact.getId());
    fillContactForm(contact);
    submitContactModification();
    contactCache = null;
    returnToHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//td/input"));
  }

  public int count() {
    return wd.findElements(By.xpath("//img[@alt='Edit']")).size();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if(contactCache != null){
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@name = 'entry']"));
    for(WebElement element : elements){
      List<WebElement> cells = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
      String firstname = cells.get(2).getText();
      String lastname = cells.get(1).getText();
      String address = cells.get(3).getText();
      String allEmails = cells.get(4).getText();
      String allPhones = cells.get(5).getText();
      ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withAddress(address).withAllEmails(allEmails).withAllPhones(allPhones);
      contactCache.add(contact);
    }
    return new Contacts(contactCache);
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getText();
    String emailFirst = wd.findElement(By.name("email")).getAttribute("value");
    String emailSecond = wd.findElement(By.name("email2")).getAttribute("value");
    String emailThird = wd.findElement(By.name("email3")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String secondPhone = wd.findElement(By.name("phone2")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withAddress(address).withEmail(emailFirst).withEmailSecond(emailSecond).withEmailThird(emailThird)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withSecondPhone(secondPhone);
  }

  public void addContactInGroup(ContactData contact, GroupData group) {
    selectContactById(contact.getId());
    new Select(wd.findElement(By.name("to_group"))).selectByValue(String.valueOf(group.getId()));
    click(By.name("add"));
    contactCache = null;
  }

  public void selectGroupToDel(int id) {
    new Select(wd.findElement(By.name("group"))).selectByValue(String.valueOf(id));
  }

  public void removeContactFromGroup(ContactData contact, int id) {
    selectGroupToDel(id);
    selectContactById(contact.getId());
    click(By.name("remove"));
  }
}


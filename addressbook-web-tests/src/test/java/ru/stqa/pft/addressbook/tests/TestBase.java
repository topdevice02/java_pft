package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;


public class TestBase {

  Logger logger = LoggerFactory.getLogger(TestBase.class);

  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite (alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();
  }

  @BeforeMethod
  public void logTestStart(Method m, Object[] p){
    logger.info("Start test " + m.getName() + " with parameters " + Arrays.asList(p));
  }

  @AfterMethod (alwaysRun = true)
  public void logTestStop(Method m){
    logger.info("Stop test " + m.getName());
  }

  public void verifyGroupListInUI() {
    if(Boolean.getBoolean("vefiryUI")){
      Groups dbGroups = app.db().groups();
      Groups uiGroups = app.group().all();
      assertThat(uiGroups, CoreMatchers.equalTo(dbGroups.stream().map((g) -> new GroupData().withId(g.getId())
              .withName(g.getName())).collect(Collectors.toSet())));
    }
  }

  public void verifyContactListInUI() {
    if(Boolean.getBoolean("vefiryUI")){
      Contacts dbContacts = app.db().contacts();
      Contacts uiContacts = app.contact().all();
      assertThat(uiContacts, CoreMatchers.equalTo(dbContacts.stream().map((g) -> new ContactData().withId(g.getId())
              .withFirstname(g.getFirstname()).withLastname(g.getLastname())
              .withMobilePhone(g.getMobilePhone()).withEmail(g.getEmail())).collect(Collectors.toSet())));
    }
  }
}

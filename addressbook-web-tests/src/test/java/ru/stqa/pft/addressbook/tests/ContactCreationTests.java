package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.initContactCreation();
    app.fillContactForm(new ContactData("Ivan", "Ivanov", "+79175552358", "Ivanov02@gmail.com"));
    app.submitContactCreation();
    app.returnToHomePage();
  }

}

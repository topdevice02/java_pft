package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    initContactCreation();
    fillContactForm(new ContactData("Ivan", "Ivanov", "+79175552358", "Ivanov02@gmail.com"));
    submitContactCreation();
    returnToHomePage();
  }

}

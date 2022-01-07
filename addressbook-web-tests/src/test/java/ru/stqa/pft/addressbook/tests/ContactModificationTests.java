package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    if(app.db().contacts().size() == 0){
      File photo = new File("src/test/resources/java.png");
      app.contact().create(new ContactData()
              .withFirstname("Ivan").withLastname("Ivanov").withPhoto(photo).withMobilePhone("+79175552358").withEmail("Ivanov02@gmail.com")
              .withNickname("testNickname").withAddress("testAddress").withSecondPhone("911"));
    }
  }

  @Test
  public void testContactModification(){
    File photo = new File("src/test/resources/js.png");
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId())
            .withFirstname("Ivan").withLastname("Ivanov").withPhoto(photo).withMobilePhone("+79175552358").withEmail("Ivanov02@gmail.com")
            .withNickname("testNickname").withAddress("testAddress").withSecondPhone("911");
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
  }
}

package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactFromAGroupTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      File photo = new File("src/test/resources/java.png");
      app.contact().create(new ContactData()
              .withFirstname("Ivan").withLastname("Ivanov").withPhoto(photo).withMobilePhone("+79175552358").withEmail("Ivanov02@gmail.com")
              .withNickname("testNickname").withAddress("testAddress").withSecondPhone("911"));
    } else if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("testgroup"));
    }
  }

  @Test
  public void testDeleteContactFromAGroup() {
    ContactData contact = selectContact();
    GroupData groupForDel = selectedGroup(contact);
    Groups before = contact.getGroups();
    app.contact().goToHome();
    app.contact().selectGroupToDel(groupForDel.getId());
    app.contact().removeContactFromGroup(contact, groupForDel.getId());
    ContactData contactsAfter = selectContactById(contact);
    Groups after = contactsAfter.getGroups();
    assertThat(after, equalTo(before.withOut(groupForDel)));
  }

  private ContactData selectContactById(ContactData contact) {
    Contacts contactById = app.db().contacts();
    return contactById.iterator().next().withId(contact.getId());
  }

  private GroupData selectedGroup(ContactData deleteContact) {
    ContactData contact = selectContactById(deleteContact);
    Groups deletedContact = contact.getGroups();
    return deletedContact.iterator().next();
  }

  private ContactData selectContact() {
    app.contact().goToHome();
    Contacts contacts = app.db().contacts();
    for (ContactData contact : contacts) {
      if (contact.getGroups().size() > 0) {
        return contact;
      }
    }
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("testgroup"));
    }
    app.contact().goToHome();
    ContactData addContact = app.db().contacts().iterator().next();
    app.contact().addContactInGroup(addContact, app.db().groups().iterator().next());
    return addContact;
  }
}



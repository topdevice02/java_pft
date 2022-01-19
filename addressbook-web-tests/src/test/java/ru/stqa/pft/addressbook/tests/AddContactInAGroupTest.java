package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactInAGroupTest extends TestBase {

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
  public void testAddContactInAGroup() {
    app.contact().goToHome();
    ContactData contact = selectedContact();
    Groups before = contact.getGroups();
    GroupData groupToAdd = selectedGroup(contact);
    app.contact().addContactInGroup(contact, groupToAdd);
    Groups after = app.db().getContactFromDb(contact.getId()).getGroups();
    assertThat(after, equalTo(before.withAdded(groupToAdd)));
  }

  public ContactData selectedContact() {
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    for (ContactData contact : contacts) {
     if (contact.getGroups().size() < groups.size()) {
        return contact;
      }
    }
    app.goTo().groupPage();
    app.group().create(new GroupData().withName("testgroup"));
    app.contact().goToHome();
    return contacts.iterator().next();
  }

  public GroupData selectedGroup(ContactData contact) {
    Groups all = app.db().groups();
    Collection<GroupData> freeGroups = new HashSet<GroupData>(all);
    freeGroups.removeAll(contact.getGroups());
    return freeGroups.iterator().next();
  }
}

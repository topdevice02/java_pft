package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase{

  @BeforeMethod
  public void startMailServer(){
    app.mail().start();
  }

  @Test
  public void testResetPassword() throws MessagingException, IOException {
    long now = System.currentTimeMillis();
    Users users = app.db().users();
    UserData selectUser = users.iterator().next();
    String username = selectUser.getUsername();
    String email = selectUser.getEmail();
    String newPass = String.format("pass%s", now);
    app.registration().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    app.registration().resetPassword(username);
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = app.registration().findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, newPass);
    assertTrue(app.newSession().login(username, newPass));
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer(){
    app.mail().stop();
  }
}

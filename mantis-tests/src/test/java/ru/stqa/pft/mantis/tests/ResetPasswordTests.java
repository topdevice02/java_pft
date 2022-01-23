package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

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
    app.registration().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    app.registration().resetPassword("user1642850236395");
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = app.registration().findConfirmationLink(mailMessages, "user1642850236395@localhost.localdomain");
    app.registration().finish(confirmationLink, "pass123");
    assertTrue(app.newSession().login("user1642850236395", "pass123"));
  }

//  public String findConfirmationLink(List<MailMessage> mailMessages, String email) {
//    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
//    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
//    return regex.getText(mailMessage.text);
//  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer(){
    app.mail().stop();
  }
}

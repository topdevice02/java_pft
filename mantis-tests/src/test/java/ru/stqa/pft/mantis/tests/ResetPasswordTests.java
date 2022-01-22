package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

public class ResetPasswordTests extends TestBase{

  @Test
  public void testResetPassword(){
    app.registration().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword "));
  }
}

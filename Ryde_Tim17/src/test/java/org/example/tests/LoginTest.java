package org.example.tests;

import org.example.pages.HomePage;
import org.testng.annotations.Test;

public class LoginTest extends TestBase{

    @Test
    public void  goToLogin() throws InterruptedException {
        HomePage page = new HomePage(driver);
        page.goToLoginPage();
    }
}

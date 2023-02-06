package org.example.tests;

import org.example.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;



public class LoginTest extends TestBase{


    private final String EMAIL_ADMIN = "mika@mikic.com";
    private final String EMAIL_DRIVER = "neca@perovic.com";

    private final String EMAIL_PASSENGER = "kosta@perovic.com";

    private final String WRONG_EMAIL = "pogresan@mail";

    private final String PASSWORD = "Pasword123";

    private final String SHORT_PASSWORD = "pass";




    @Test(testName = "Login as Admin")
    public void  loginAsAdmin(){
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.homeIsOpened());
        homePage.goToLoginPage();
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.loginIsOpened());
        loginPage.login(EMAIL_ADMIN, PASSWORD);
        AdminPage adminPage = new AdminPage(driver);
        Assert.assertTrue(adminPage.adminIsOpened());
        adminPage.logoutAdmin();
        Assert.assertTrue(loginPage.loginIsOpened());
    }
    @Test(testName = "Login as Driver")
    public void  loginAsDriver(){
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.loginIsOpened());
        loginPage.login(EMAIL_DRIVER, PASSWORD);
        DriverPage driverPage = new DriverPage(driver);
        Assert.assertTrue(driverPage.DriverIsOpened());
        driverPage.logoutDriver();
        Assert.assertTrue(loginPage.loginIsOpened());
    }
    @Test(testName = "Login as Passenger")
    public void  loginAsPassenger(){
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.loginIsOpened());
        loginPage.login(EMAIL_PASSENGER, PASSWORD);
        PassengerPage passengerPage = new PassengerPage(driver);
        Assert.assertTrue(passengerPage.passengerIsOpened());
        passengerPage.logoutPassenger();
        Assert.assertTrue(loginPage.loginIsOpened());
    }
    @Test(testName = "Login with wrong credentials")
    public void  loginWithWrongCredentialsShowTextCheck(){
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.loginIsOpened());
        loginPage.login(WRONG_EMAIL, PASSWORD);
        Assert.assertTrue(loginPage.loginIsOpened());
        Assert.assertTrue(loginPage.wrongCredentialsText());
    }
    @Test(testName = "Login with wrong credentials2")
    public void  loginWithWrongCredentialsInvalidFormCheck(){
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.loginIsOpened());
        loginPage.login(WRONG_EMAIL, SHORT_PASSWORD);
        Assert.assertTrue(loginPage.loginIsOpened());
        Assert.assertTrue(loginPage.ErrorlistIsEmpty());
    }
}

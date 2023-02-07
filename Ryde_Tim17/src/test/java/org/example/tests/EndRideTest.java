package org.example.tests;

import org.example.pages.DriverPage;
import org.example.pages.LoginPage;
import org.example.pages.PassengerPage;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


public class EndRideTest extends TestBase{
    private final String EMAIL_DRIVER = "pera@perovic.com";
    private final String EMAIL_DRIVER2 = "neca@perovic.com";
    private final String EMAIL_PASSENGER = "kosta@perovic.com";
    private final String EMAIL_PASSENGER2 = "mirkovicka01@gmail.com";
    private final String PASSWORD = "Pasword123";
    private final String PANIC_REASON = "Passenger is having a heart attack!";
    private LoginPage loginPage;
    private DriverPage driverPage;
    private PassengerPage passengerPage;


    @BeforeSuite
    public void initializePages() {
        driverPage = new DriverPage(driver);
        passengerPage = new PassengerPage(driver);
    }

    @Test
    public void endRide() {
        loginPage = new LoginPage(driver);
        loginPage.login(EMAIL_DRIVER, PASSWORD);
        Assert.assertTrue(driverPage.DriverIsOpened());
        Assert.assertTrue(driverPage.hasActiveRide());

        driverPage.endRide();
        Assert.assertTrue(driverPage.hasNoActiveRide());
        driverPage.logoutDriver();
        loginPage.login(EMAIL_PASSENGER, PASSWORD);
        Assert.assertTrue(passengerPage.canCreateRide());
        passengerPage.logoutPassenger();
    }

    @Test
    public void noActiveRide() {
        loginPage = new LoginPage(driver);
        loginPage.login(EMAIL_DRIVER, PASSWORD);
        Assert.assertTrue(driverPage.DriverIsOpened());
        Assert.assertTrue(driverPage.hasNoActiveRide());
        driverPage.logoutDriver();

    }

    @Test
    public void panicRide() {
        loginPage = new LoginPage(driver);
        loginPage.loginIsOpened();
        loginPage.login(EMAIL_DRIVER2, PASSWORD);
        Assert.assertTrue(driverPage.DriverIsOpened());
        Assert.assertTrue(driverPage.hasActiveRide());

        driverPage.panic(PANIC_REASON);
        Assert.assertTrue(driverPage.hasNoActiveRide());
        driverPage.logoutDriver();
        loginPage.login(EMAIL_PASSENGER2, PASSWORD);
        Assert.assertTrue(passengerPage.passengerIsOpened());
        Assert.assertTrue(passengerPage.canCreateRide());
        passengerPage.logoutPassenger();
    }
}

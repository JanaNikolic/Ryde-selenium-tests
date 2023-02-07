package org.example.tests;


import org.example.pages.DriverPage;
import org.example.pages.HomePage;
import org.example.pages.LoginPage;
import org.example.pages.PassengerPage;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateRideTest extends TestBase {

    private final String EMAIL = "kosta@perovic.com";

    private final String PASSWORD = "Pasword123";

    private final String DEPARTURE = "Bulevar Oslobodjenja 15, Novi Sad";

    private final String DESTINATION = "Danila Medakovica 34, Novi Sad";

    private final String VEHICLE_TYPE = "STANDARD";

    private final boolean BABY_TRANSPORT = false;

    private final boolean PET_TRANSPORT = true;

    private final String PASSENGER = "mirkovicka01@gmail.com";

    private List<String> passengers = new ArrayList<>();

    private final String EMAIL_DRIVER = "zika@peric.com";


    @Test(testName = "Schedule ride")
    public void shouldCreateRideInOneHour() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.homeIsOpened());
        homePage.goToLoginPage();
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.loginIsOpened());
        loginPage.login(EMAIL, PASSWORD);

        PassengerPage passengerPage = new PassengerPage(driver);
        Assert.assertTrue(passengerPage.passengerIsOpened());
        passengerPage.createRide(DEPARTURE, DESTINATION, VEHICLE_TYPE, BABY_TRANSPORT, PET_TRANSPORT, null, true);
        Assert.assertTrue(passengerPage.scheduledRide());
        driver.navigate().refresh();
    }

    @Test(testName = "Create ride no available drivers", dependsOnMethods = "shouldCreateRideInOneHour")
    public void shouldNotCreateRideWithNoAvailableDrivers() {
        PassengerPage passengerPage = new PassengerPage(driver);
        Assert.assertTrue(passengerPage.passengerIsOpened());
        passengerPage.createRide(DEPARTURE, DESTINATION, VEHICLE_TYPE, BABY_TRANSPORT, PET_TRANSPORT, null, false);
        Assert.assertTrue(passengerPage.noAvailableDrivers());
        passengerPage.logoutPassenger();
    }

    @Test(testName = "Create ride", dependsOnMethods = "shouldNotCreateRideWithNoAvailableDrivers")
    public void shouldCreateRide() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.loginIsOpened());
        loginPage.login(EMAIL_DRIVER, PASSWORD);

        DriverPage driverPage = new DriverPage(driver);
        Assert.assertTrue(driverPage.DriverIsOpened());
        driverPage.startShift();

        driverPage.logoutDriver();
        Assert.assertTrue(loginPage.loginIsOpened());

        loginPage.login(PASSENGER, PASSWORD);

        passengers.add(PASSENGER);

        PassengerPage passengerPage = new PassengerPage(driver);
        Assert.assertTrue(passengerPage.passengerIsOpened());
        passengerPage.createRide(DEPARTURE, DESTINATION, VEHICLE_TYPE, BABY_TRANSPORT, PET_TRANSPORT, passengers, false);
        Assert.assertTrue(passengerPage.passengersAdded(passengers.size()));
        Assert.assertTrue(passengerPage.createRide());

    }

    @AfterSuite
    public void quitDriver() {
        driver.quit();
    }

}

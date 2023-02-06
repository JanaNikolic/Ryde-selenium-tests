package org.example.tests;


import org.example.pages.HomePage;
import org.example.pages.LoginPage;
import org.example.pages.PassengerPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateRideTest extends TestBase {

    private final String EMAIL = "kosta@perovic.com";

    private final String PASSWORD = "Pasword123";

    private final String DEPARTURE = "Bulevar Oslobodjenja 15, Novi Sad";

    private final String DESTINATION = "Danila Medakovica 34, Novi Sad";

    private final String VEHICLE_TYPE = "STANDARD";

    private final boolean BABY_TRANSPORT = false;

    private final boolean PET_TRANSPORT = true;

    @Test(testName = "Create ride")
    public void createRide() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.homeIsOpened());
        homePage.goToLoginPage();
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.loginIsOpened());
        loginPage.login(EMAIL, PASSWORD);

        PassengerPage passengerPage = new PassengerPage(driver);
        Assert.assertTrue(passengerPage.passengerIsOpened());
        passengerPage.createRide(DEPARTURE, DESTINATION, VEHICLE_TYPE, BABY_TRANSPORT, PET_TRANSPORT, null);
        Assert.assertTrue(passengerPage.noAvailableDrivers());
    }

}

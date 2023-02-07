package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class PassengerPage {
    WebDriver driver;

    @FindBy(css = "h1")
    private WebElement getRydeText;

    @FindBy(id = "getRideBtn")
    WebElement getRideBtn;

    @FindBy(id = "logoutButton")
    WebElement logoutButton;

    @FindBy(id = "departureInput")
    WebElement departureInput;

    @FindBy(id = "destinationInput")
    WebElement destinationInput;

    @FindBy(css = ".vehicletype-cc.standard")
    WebElement standardCarBtn;

    @FindBy(css = ".vehicletype-cc.luxury")
    WebElement luxuryCarBtn;

    @FindBy(css = ".vehicletype-cc.van")
    WebElement vanCarBtn;

    @FindBy(id = "mat-mdc-checkbox-1-input")
    WebElement petsCheckbox;

    @FindBy(id = "mat-mdc-checkbox-2-input")
    WebElement babiesCheckbox;

    @FindBy(id = "addBtn")
    WebElement addBtn;

    @FindBy(id = "addPassenger")
    WebElement addPassenger;

    @FindBy(id = "addFriend")
    WebElement addFriend;

    @FindBy(xpath = ".//p[text()='Searching for available driver...']")
    WebElement dialog;

    @FindBy(id = "mat-input-3")
    WebElement timePicker;


    public PassengerPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean passengerIsOpened() {
        return (new WebDriverWait(driver, Duration.ofSeconds(10)))
                .until(ExpectedConditions.textToBePresentInElement(getRydeText, "Get A Ryde"));
    }

    public boolean canCreateRide() {
        return (new WebDriverWait(driver, Duration.ofSeconds(10)))
                .until(ExpectedConditions.elementToBeClickable(getRideBtn)).isDisplayed();
    }
    public void logoutPassenger(){
        logoutButton.click();
    }

    public void createRide(String departure, String destination, String vehicleType, boolean baby, boolean pet, List<String> passengers, boolean scheduled) {
        departureInput.clear();
        departureInput.sendKeys(departure);

        destinationInput.clear();
        destinationInput.sendKeys(destination);

        if (baby && !babiesCheckbox.isSelected()) {
            babiesCheckbox.click();
        }

        if (pet && !petsCheckbox.isSelected()) {
            petsCheckbox.click();
        }

        switch (vehicleType) {
            case "STANDARD":
                standardCarBtn.click();
                break;
            case "LUXURY":
                luxuryCarBtn.click();
                break;
            case "VAN":
                vanCarBtn.click();
                break;
        }

        if (passengers != null) {
            addPassengers(passengers);
        }

        if (scheduled) {
            scheduleRide();
        }

        getRideBtn.click();

    }

    private void scheduleRide() {
        int hour = LocalDateTime.now().getHour() + 1;
        timePicker.click();

        driver.findElement(By.xpath(".//span[@class='mdc-button__label' and text()=' " + hour + " ']")).click();
        driver.findElement(By.xpath(".//span[@class='mdc-button__label' and text()='OK ']")).click();

    }

    public boolean noAvailableDrivers() {
        return (new WebDriverWait(driver, Duration.ofSeconds(15)))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//simple-snack-bar/div[text()=' No available drivers!\n']"))).isDisplayed();
    }
    public boolean scheduledRide() {
        return (new WebDriverWait(driver, Duration.ofSeconds(15)))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//simple-snack-bar/div[text()=' Ride sucessfuly ordered!\n']"))).isDisplayed();
    }

    public boolean createRide() {
        return (new WebDriverWait(driver, Duration.ofSeconds(15)))
                .until(ExpectedConditions.visibilityOf(dialog)).isDisplayed();
    }

    public boolean passengersAdded(int numOfPassengers) {
        return (new WebDriverWait(driver, Duration.ofSeconds(3)))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(".//div[@id='friends']/span"))).size() == numOfPassengers;
    }

    public void addPassengers(List<String> passengers) {
        addBtn.click();
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("addPassenger")));
        for (String passenger: passengers) {
            addPassenger.clear();
            addPassenger.sendKeys(passenger);
            addFriend.click();
        }
    }

}

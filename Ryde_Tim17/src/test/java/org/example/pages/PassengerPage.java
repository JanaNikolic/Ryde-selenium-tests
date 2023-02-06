package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PassengerPage {
    WebDriver driver;

    @FindBy(css = "h1")
    private WebElement getRydeText;

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

    @FindBy(id = "getRideBtn")
    WebElement getRideBtn;

    @FindBy(xpath = ".//div[text()='No available drivers!']")
    WebElement snackBar;

    public PassengerPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean passengerIsOpened() {
        return (new WebDriverWait(driver, Duration.ofSeconds(10)))
                .until(ExpectedConditions.textToBePresentInElement(getRydeText, "Get A Ryde"));
    }
    public void logoutPassenger(){
        logoutButton.click();
    }

    public void createRide(String departure, String destination, String vehicleType, boolean baby, boolean pet, List<String> passengers) {
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

        //TODO add passengers and scheduled time

        getRideBtn.click();

    }

    public boolean noAvailableDrivers() {
        return (new WebDriverWait(driver, Duration.ofSeconds(10)))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//simple-snack-bar"))).isDisplayed();
    }

}

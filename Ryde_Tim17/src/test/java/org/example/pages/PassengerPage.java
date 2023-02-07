package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PassengerPage {
    WebDriver driver;

    @FindBy(css = "h1")
    private WebElement getRydeText;

    @FindBy(id = "getRideBtn")
    WebElement getRideBtn;

    @FindBy(id = "logoutButton")
    WebElement logoutButton;

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
}

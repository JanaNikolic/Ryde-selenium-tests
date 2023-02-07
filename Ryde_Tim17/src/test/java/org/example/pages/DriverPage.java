package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DriverPage {
    WebDriver driver;

    @FindBy(id = "toggle-button")
    WebElement toggleButton;
    @FindBy(id = "logoutButton")
    WebElement logoutButton;

    @FindBy(id = "panic")
    WebElement panicButton;

    @FindBy(id = "end")
    WebElement endButton;

    @FindBy(id = "reason")
    WebElement panicInput;

    @FindBy(id = "send-panic-button")
    WebElement submitButton;

    public DriverPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean DriverIsOpened() {
        return (new WebDriverWait(driver, Duration.ofSeconds(10)))
                .until(ExpectedConditions.elementToBeClickable(toggleButton)).isDisplayed();
    }
    public void logoutDriver(){
        logoutButton.click();
    }

    public void startShift() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(toggleButton)).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeToBe(toggleButton, "aria-checked", "true"));
    }

    public void endRide() {
        endButton.click();
    }

    public void clickPanic() {
        panicButton.click();
    }

    public void insertPanicMessage(String reason) {
        panicInput.clear();
        panicInput.sendKeys(reason);
    }

    public void submitPanic() {
        submitButton.click();
    }

    public void panic(String reason) {
        clickPanic();
        insertPanicMessage(reason);
        submitPanic();
    }

    public boolean hasActiveRide() {
        return (new WebDriverWait(driver, Duration.ofSeconds(10)))
                .until(ExpectedConditions.elementToBeClickable(panicButton)).isDisplayed();
    }

    public boolean hasNoActiveRide() {
        return (new WebDriverWait(driver, Duration.ofSeconds(10)))
                .until(ExpectedConditions.invisibilityOf(panicButton));
    }
}

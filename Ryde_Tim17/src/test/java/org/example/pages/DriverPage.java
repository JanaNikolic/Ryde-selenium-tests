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
}

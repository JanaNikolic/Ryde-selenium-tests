package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AdminPage {
    WebDriver driver;

    @FindBy(id = "updateBtn")
    WebElement updateButton;
    @FindBy(id = "logoutButton")
    WebElement logoutButton;


    public AdminPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

public boolean adminIsOpened() {
    return (new WebDriverWait(driver, Duration.ofSeconds(10)))
            .until(ExpectedConditions.textToBePresentInElement(updateButton, "Driver updates"));
}
    public void logoutAdmin(){
        logoutButton.click();
    }

}

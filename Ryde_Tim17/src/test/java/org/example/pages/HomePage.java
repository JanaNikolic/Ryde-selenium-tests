package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class HomePage {

  WebDriver driver;
  String url = "http://localhost:4200";
  @FindBy(how = How.LINK_TEXT, using="LOGIN")
  WebElement loginBtn;
  @FindBy(id = "getStarted")
  WebElement getStartedBtn;

  public HomePage(WebDriver driver){
    this.driver = driver;
    driver.get(url);
    PageFactory.initElements(driver, this);
  }
  public void goToLoginPage() {
    new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(loginBtn)).click();
  }
  public boolean homeIsOpened(){
    return (new WebDriverWait(driver, Duration.ofSeconds(10)))
            .until(ExpectedConditions.textToBePresentInElement(getStartedBtn, "GET STARTED"));
  }
}

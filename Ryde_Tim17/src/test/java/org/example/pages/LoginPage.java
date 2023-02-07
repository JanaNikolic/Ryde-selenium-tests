package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LoginPage {
    WebDriver driver;
    @FindBy(id = "Email")
    WebElement emailInput;
    @FindBy(id = "password")
    WebElement passwordInput;
    @FindBy(id = "loginBtn")
    WebElement loginBtn;
    @FindBy(id = "forgotPass")
    WebElement forgotPassButton;
    @FindBy(css = ".ng-invalid")
    List<WebElement> invalidDivs;
    @FindBy(id = "login-warning")
    WebElement wrongCredentialsText;




    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void insertEmail(String email){
        emailInput.clear();
        emailInput.sendKeys(email);
    }
    private void insertPassword(String password){
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }
    private void clickLoginButton(){
        loginBtn.click();
    }
    public void login(String email, String password){
        insertEmail(email);
        insertPassword(password);
        loginBtn.click();
    }
    public boolean loginIsOpened() {
        return (new WebDriverWait(driver, Duration.ofSeconds(10)))
                .until(ExpectedConditions.textToBePresentInElement(forgotPassButton, "Forgot Password?"));
    }

    public boolean wrongCredentialsText(){
        return (new WebDriverWait(driver, Duration.ofSeconds(1)))
                .until(ExpectedConditions.textToBePresentInElement(wrongCredentialsText, "Login credentials are not correct!"));
    }
    public boolean ErrorlistIsEmpty() {
        return !invalidDivs.isEmpty();
    }



}

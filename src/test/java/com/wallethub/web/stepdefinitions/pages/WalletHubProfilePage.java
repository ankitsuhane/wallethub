package com.wallethub.web.stepdefinitions.pages;

import com.wallethub.web.Base;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Lazy
@Component
@Scope("cucumber-glue")
public class WalletHubProfilePage extends Base {

    @Value("${walletUserName}")
    private String walletUserName;

    @Value("${walletPassword}")
    private String walletPassword;

    @Autowired
    WalletHubReviewSubmissionPage walletHubReviewSubmissionPage;

    @FindBy(xpath= "//span[text()=' Your Review']")
    private WebElement yourReview;

    @FindBy(xpath= "//a[text()='Login']")
    private WebElement loginLink;

    @FindBy(css= "input[name='em']")
    private WebElement userName;

    @FindBy(css= "input[placeholder='Password']")
    private WebElement password;

    @FindBy(xpath= "//span[text()='Login']")
    private WebElement loginButton;

    @FindBy(css= "h1")
    private WebElement pageTitle;

    public void verifyReview() {
        PageFactory.initElements(webDriverFactory.getWebDriver(), this);
        login();
        Assert.assertTrue(yourReview.isDisplayed());
    }

    private void login() {
        waitForVisibilityOfElement(loginLink);
        loginLink.click();
        userName.sendKeys(walletUserName);
        password.sendKeys(walletPassword);
        loginButton.click();
        WebDriverWait wait = new WebDriverWait (webDriverFactory.getWebDriver(), 20);
        wait.until(ExpectedConditions.titleContains("Test Insurance Company"));
    }
}
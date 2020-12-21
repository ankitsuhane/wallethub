package com.wallethub.web.stepdefinitions.pages;

import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.wallethub.web.Base;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.util.List;
import java.util.Locale;

@Lazy
@Component
@Scope("cucumber-glue")
public class WalletHubReviewSubmissionPage extends Base {

    @Value("${walletinsuranceUI}")
    private String walletInsuranceUI;

    @FindAll(@FindBy(css = ".rv.review-action.ng-enter-element>.rvs-svg>.rating-box-wrapper>svg[viewbox='0 0 34 32']"))
    private List<WebElement> ratingStartHover;

    @FindAll(@FindBy(css = ".rv.review-action.ng-enter-element>.rvs-svg>.rating-box-wrapper>svg[viewbox='0 0 34 32']:nth-child(4)>g>path"))
    private List<WebElement> verifyratingStars;

    @FindBy(css = "div[class='dropdown second']>span[class='dropdown-placeholder']")
    private WebElement productDropdown;

    @FindBy(xpath = "//li[text()='Health Insurance']")
    private WebElement productDropdownHealthInsurance;

    @FindBy(css = ".android>textarea[class='textarea wrev-user-input validate']")
    private WebElement writeYourReviewTextBox;

//    @FindBy(css = "div[class='sbn-action semi-bold-font btn fixed-w-c tall']")
    @FindBy(xpath ="//div[text()='Submit']")
    private WebElement submitButton;

    @FindBy(css = ".btn.rvc-continue-btn")
    private WebElement continueButton;
    public String publicRandomString;

    public void enterReviewPage()  {
        webDriverFactory.getWebDriver().get(walletInsuranceUI);
        PageFactory.initElements(webDriverFactory.getWebDriver(), this);
    }

    public void submitStars(Integer starNumber) throws InterruptedException {
        verifyStarLitUp(starNumber);
        submitDetails();
    }

    private void submitDetails() {
        waitForVisibilityOfElement(productDropdown);
        productDropdown.click();
        waitForElementToBeClickable(productDropdownHealthInsurance);
        productDropdownHealthInsurance.click();
        writeYourReviewTextBox.sendKeys(randomString());
        Actions action = new Actions(webDriverFactory.getWebDriver());
        action.moveToElement(submitButton).click(submitButton).build().perform();
    }

    private void verifyStarLitUp(Integer starNumber) {
        Actions action = new Actions(webDriverFactory.getWebDriver());
        action.moveToElement(ratingStartHover.get(starNumber-1)).pause(Duration.ofSeconds(2)).build().perform();

        if(verifyratingStars.size()==2){
            action.moveToElement(ratingStartHover.get(starNumber-1)).doubleClick().build().perform();
        }
        else
            Assert.fail();
    }

    public String randomString() {
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());
        return fakeValuesService.regexify("[a-z ]{200}");
    }
}

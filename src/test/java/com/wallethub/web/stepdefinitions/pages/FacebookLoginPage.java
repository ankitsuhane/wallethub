package com.wallethub.web.stepdefinitions.pages;

import net.bytebuddy.asm.Advice;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.wallethub.web.Base;

@Lazy
@Component
@Scope("cucumber-glue")
public class FacebookLoginPage extends Base {

    @Value("${facebookUI}")
    private String facebookUI;

    @Value("${facebookUserName}")
    private String facebookUserName;

    @Value("${facebookPassword}")
    private String facebookPassword;

    @FindBy(css = "#email")
    private  WebElement username;

    @FindBy(css = "#pass")
    private  WebElement password;

    @FindBy(css = "#u_0_h")
    private  WebElement submitButton;

    @FindBy(css = ".m9osqain.a5q79mjw.jm1wdb64.k4urcfbm>.a8c37x1j.ni8dbmo4.stjgntxs.l9j0dhe7")
    private  WebElement wallMainTextBox;

    @FindBy(css="div#mount_0_0>div>div>div>div:nth-of-type(4)>div>div>div>div>div:nth-of-type(2)>div>div>div>form>div>div>div>div>div:nth-of-type(2)>div>div>div>div>div>div>div>div>div:nth-of-type(2)>div>div>div>div")
    //@FindBy(xpath = "//div[@data-editor='ltn5']//div[1]")
    private  WebElement wallTextBox;

    @FindBy(css=".hop8lmos.rl04r1d5")
    private  WebElement test;

    @FindBy(xpath = "//div[contains(@class,'k4urcfbm dati1w0a hv4rvrfc i1fnvgqd j83agx80 rq0escxv bp9cbjyn discj3wi')]//div//div//div[1]")
    public WebElement wallTextBoxPostButton;

    //@FindBy(css = ".hu5pjgll.lzf7d6o1.sp_dmsEYXsz1mq_2x.sx_f6e82b")
    @FindBy(xpath="(//div[@role='button']//i)[3]")
    public WebElement accountDropdownButton;

    @FindBy(xpath = "//span[text()='Log Out']")
    private WebElement logout;



    public void login()  {
        PageFactory.initElements(webDriverFactory.getWebDriver(), this);
        webDriverFactory.getWebDriver().get(facebookUI);
        username.clear();
        username.sendKeys(facebookUserName);
        password.clear();
        password.sendKeys(facebookPassword);
        password.sendKeys(Keys.ENTER);
    }

    public void postMessage() throws InterruptedException{
        wallMainTextBox.click();
        WebDriverWait wait = new WebDriverWait (webDriverFactory.getWebDriver(), 20);
        wait.until(ExpectedConditions.elementToBeClickable(wallTextBox));
        wallTextBox.click();
        wallTextBox.sendKeys("Hello World");
        wallTextBoxPostButton.click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(accountDropdownButton));
        accountDropdownButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(logout));
        logout.click();
    }
}

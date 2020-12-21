package com.wallethub.web.stepdefinitions;

import cucumber.api.java8.En;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import com.wallethub.web.stepdefinitions.pages.FacebookLoginPage;

public class FacebookLoginStepDef implements En {

    @Lazy
    @Autowired
    FacebookLoginPage facebookLoginPage;

    public FacebookLoginStepDef() {
        Given("I want to click login to facebook", () -> {
            facebookLoginPage.login();
        });

        Then("Post a status message {string} and logout", (String string) -> {
            facebookLoginPage.postMessage();
        });
    }
}

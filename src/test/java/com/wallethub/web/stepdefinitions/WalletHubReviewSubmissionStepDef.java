package com.wallethub.web.stepdefinitions;

import com.wallethub.web.stepdefinitions.pages.WalletHubProfilePage;
import com.wallethub.web.stepdefinitions.pages.WalletHubReviewSubmissionPage;
import cucumber.api.java8.En;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public class WalletHubReviewSubmissionStepDef implements En {

    @Lazy
    @Autowired
    WalletHubReviewSubmissionPage walletHubReviewSubmissionPage;

    @Lazy
    @Autowired
    WalletHubProfilePage walletHubProfilePage;

    public WalletHubReviewSubmissionStepDef(){

        Given("User landing to the test insurance_company", () -> {
            walletHubReviewSubmissionPage.enterReviewPage();

        });

        When("Submit the {int} star rating for a Health Insurance product", (Integer starNumber)  -> {
            walletHubReviewSubmissionPage.submitStars(starNumber);

        });

        Then("check it on the review feed page", () -> {
            walletHubProfilePage.verifyReview();

        });


    }
}

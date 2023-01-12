package com.miro.tinw;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import java.util.UUID;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SignupPositiveTest {
    SignupSelectors selectors = new SignupSelectors();
    SignupData data = new SignupData();

    static Playwright playwright;
    static Browser browser;

    BrowserContext context;
    Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
    }

    @AfterAll
    static void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        context.close();
    }

    @Test
    @DisplayName("Go to signup screen using header button")
    void tapHeaderSignupButton() {
        page.navigate(data.urlIndex);
        page.locator(selectors.buttonSignupHeader).click();
        assertThat(page.locator(selectors.headerSignupNow)).isVisible();
    }

    @Test
    @DisplayName("Go to signup screen using main index page button")
    void tapBodySignupButton() {
        page.navigate(data.urlIndex);
        page.locator(selectors.buttonSignupBody).click();
        assertThat(page.locator(selectors.headerSignupNow)).isVisible();
    }

    @Test
    @DisplayName("Go to signup screen by filling in email on the index page")
    void tapBodySignupButtonWithEmail() {
        // Use generated email (with '+' symbol in it) to fail the test:
        // signup screen is opened instead of the name screen, pre-filled email is shown with " " instead of "+"
        String email = String.format(data.userEmail, UUID.randomUUID());

        page.navigate(data.urlIndex);
        page.locator(selectors.inputEmailIndex).fill(data.registeredEmail);
        //  page.locator(selectors.inputEmailIndex).fill(email);
        page.locator(selectors.buttonSignupBody).click();
        assertThat(page.locator(selectors.inputName)).isVisible();
    }

    @Test
    @DisplayName("Go to signup screen using bottom banner")
    void tapBottomSignupButton() {
        page.navigate(data.urlIndex);
        page.locator(selectors.buttonSignupBottom).click();
        assertThat(page.locator(selectors.headerSignupNow)).isVisible();
    }

    @Test
    @DisplayName("Successfully pass signup by email with valid input parameters")
    void positiveSignupFlow() {
        String password = UUID.randomUUID().toString();
        String email = String.format(data.userEmail, UUID.randomUUID());

        page.navigate(data.urlSignup);

        page.locator(selectors.inputEmail).fill(email);
        page.locator(selectors.buttonContinueEmail).click();

        page.locator(selectors.inputName).fill(data.userName);
        page.locator(selectors.buttonContinueName).click();

        page.locator(selectors.inputPassword).fill(password);
        page.locator(selectors.checkboxTerms).click(new Locator.ClickOptions().setForce(true));
        page.locator(selectors.buttonContinuePassword).click();

        assertThat(page.locator(selectors.descriptionCheckEmail)).containsText(email);
    }

    @Test
    @DisplayName("Check that weak password is accepted for signup")
    void checkPasswordWeak() {
        String email = String.format(data.userEmail, UUID.randomUUID());

        page.navigate(data.urlSignup);
        page.locator(selectors.inputEmail).fill(email);
        page.locator(selectors.buttonContinueEmail).click();
        page.locator(selectors.inputName).fill(data.userName);
        page.locator(selectors.buttonContinueName).click();
        page.locator(selectors.checkboxTerms).click(new Locator.ClickOptions().setForce(true));

        page.locator(selectors.inputPassword).fill(data.weakPassword);
        assertThat(page.locator(selectors.hintPasswordWeak)).isVisible();

        String textDefault = page.locator(selectors.hintPasswordWeak).getAttribute(selectors.attributeTextWeak);
        String textShown = page.locator(selectors.hintPasswordWeak).allTextContents().get(0);

        Assertions.assertEquals(textDefault, textShown);

        page.locator(selectors.buttonContinuePassword).click();

        assertThat(page.locator(selectors.descriptionCheckEmail)).isVisible();
    }

/*
    not covered: oauth signup

    haven't checked all empty inputs

    additional option to split tests was by the screen, like in classical Page Object,
    yet the coverage size is small enough to leave it in a single domain: KISS
*/

/*
    bonus UI bug: tap back from check email screen to see both placeholder and text in the email input
*/
}

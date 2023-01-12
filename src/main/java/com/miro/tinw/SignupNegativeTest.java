package com.miro.tinw;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import java.util.UUID;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SignupNegativeTest {
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
    void checkEmailValidationIndex() {
        page.navigate(data.urlIndex);

        page.locator(selectors.inputEmailIndex).fill(data.invalidEmail);
        page.locator(selectors.buttonSignupBody).click();

        assertThat(page.locator(selectors.errorInvalidEmailIndex)).isVisible();
    }


    @Test
    @DisplayName("Invalid email on signup screen shows errors")
    void checkEmailValidationSignup() {
        page.navigate(data.urlSignup);

        page.locator(selectors.inputEmail).fill(data.invalidEmail);

        assertThat(page.locator(selectors.errorInvalidEmailAuto)).isVisible();

        page.locator(selectors.buttonContinueEmail).click();

        assertThat(page.locator(selectors.errorInvalidEmailContinue)).isVisible();
    }

    @Test
    @DisplayName("Occupied email is not accepted for signup")
    void checkEmailOccupiedSignup() {
        String password = UUID.randomUUID().toString();

        page.navigate(data.urlSignup);

        page.locator(selectors.inputEmail).fill(data.registeredEmail);

        page.locator(selectors.buttonContinueEmail).click();
        page.locator(selectors.inputName).fill(data.userName);
        page.locator(selectors.buttonContinueName).click();
        page.locator(selectors.inputPassword).fill(password);
        page.locator(selectors.checkboxTerms).click(new Locator.ClickOptions().setForce(true));
        page.locator(selectors.buttonContinuePassword).click();

        // why don't you show this error on the zero step
        assertThat(page.locator(selectors.messageErrorEmailRegistered)).isVisible();
    }

    @Test
    @DisplayName("Clicking 'continue' with empty name shows error")
    void checkNameInputEmpty() {
        page.navigate(data.urlSignup);
        page.locator(selectors.inputEmail).fill(data.userEmail);
        page.locator(selectors.buttonContinueEmail).click();

        page.locator(selectors.buttonContinueName).click();

        assertThat(page.locator(selectors.messageErrorEmptyName)).isVisible();
    }

    @Test
    @DisplayName("Short password is not accepted for signup")
    void checkPasswordShort() {
        String email = String.format(data.userEmail, UUID.randomUUID());

        page.navigate(data.urlSignup);
        page.locator(selectors.inputEmail).fill(email);
        page.locator(selectors.buttonContinueEmail).click();
        page.locator(selectors.inputName).fill(data.userName);
        page.locator(selectors.buttonContinueName).click();

        page.locator(selectors.inputPassword).fill(data.shortPassword);
        assertThat(page.locator(selectors.hintPasswordShort)).isVisible();

        String textDefault = page.locator(selectors.hintPasswordShort).getAttribute(selectors.attributeTextDefault);
        String textShown = page.locator(selectors.hintPasswordShort).allTextContents().get(0);

        Assertions.assertEquals(textDefault, textShown);

        page.locator(selectors.buttonContinuePassword).click();
        assertThat(page.locator(selectors.hintPasswordShort)).isVisible();
    }

    @Test
    @DisplayName("Clicking 'continue' with empty Terms' checkbox shows error")
    void checkTermsNotChecked() {
        String password = UUID.randomUUID().toString();

        page.navigate(data.urlSignup);
        page.locator(selectors.inputEmail).fill(data.registeredEmail);
        page.locator(selectors.buttonContinueEmail).click();
        page.locator(selectors.inputName).fill(data.userName);
        page.locator(selectors.buttonContinueName).click();
        page.locator(selectors.inputPassword).fill(password);

        page.locator(selectors.buttonContinuePassword).click();

        assertThat(page.locator(selectors.messageErrorAcceptTerms)).isVisible();
    }
}

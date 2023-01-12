package com.miro.tinw;

public class SignupSelectors {
    String inputEmailIndex = "input[name='input-cta-email']";
    String errorInvalidEmailIndex = "div[class='Box-sc-g760pt-0 Input__Error-sc-1ui4jav-4 cfGQXL']";


    String buttonSignupHeader = "a[class='Button__ButtonBase-sc-15mxbna-2 Button-sc-15mxbna-3 CtaNavigation__Button-sc-1j2vaed-2 fmsCPW iWnnzZ js__event-link']";
    String buttonSignupBody = "a[class='Button__ButtonBase-sc-15mxbna-2 Button-sc-15mxbna-3 fmsCPW gDonwf js__event-link']";
    String buttonSignupBottom = "span[class='com-el-button__text']";


    String headerSignupNow = "div[class='ssp-step ssp-step-0 ssp-step-show'] > h1[class='signup__title-form signup__title-form--sign-up ab-signup-usa--title']";
    String inputEmail = "input[name='signup[email]']";
    String buttonContinueEmail = "button[data-testid='mr-form-signup-btn-start-1']";

    String messageErrorEmailRegistered = "label[id='emailError']:text('Sorry, this email is already registered')";
    //  String messageErrorEmail = "label[id='emailError']:text('Enter a valid email address.')";

    // Not that easy if you want to check invalid email message -- there are two of them,
    // and they have the same text and three levels of DOM. Are you serious?
    // There have to be a more slick way to check these elements' visibility and not rely on having "hidden" class
    // in the selector, but it will take more time i do not want to spend now.
    String errorInvalidEmailAuto = "div[id='custom-email-error'] > div[class='signup__error-wrap-login js-signup__error-wrap-login']";
    String errorInvalidEmailContinue = "div[class='signup__input-wrap ssp-step ssp-step-0 ssp-step-show'] > div[class='signup__error-wrap-login js-signup__error-wrap-login']";


    String inputName = "input[name='signup[name]']";
    String messageErrorEmptyName = "label[id='nameError']";
    String buttonContinueName = "button[data-testid='mr-form-signup-btn-start-2']";


    String inputPassword = "input[name='signup[password]']";
    String buttonContinuePassword = "button[data-testid='mr-form-signup-btn-start-3']";
    String attributeTextDefault = "data-text-default";
    String attributeTextWeak = "data-text-weak";
    String hintPasswordShort = "div[class='signup__input-hint-text']";
    String hintPasswordWeak = "div[class='signup__input-hint-text signup__input-hint-text--color-soso']";

    String messageErrorAcceptTerms = "label[id='termsError']";
    String checkboxTerms = "input[id='signup-terms']";


    String descriptionCheckEmail = "div[class='signup__subtitle-form']";
}

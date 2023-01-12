package com.miro.tinw;

public class SignupData {
    String urlIndex = "https://miro.com/";
    String urlSignup = "https://miro.com/signup/";

    // use it, if you do not want me to know how the review is going (:
    String reviewEmail = "qa.review+%@miro.com.";

    String userEmail = "tinwelen+%s@gmail.com";
    String registeredEmail = "tinwelen@gmail.com";
    String invalidEmail = "a";

    String userName = "Complex Test Name";
    String userNameInjection = "<script>alert('Injected!');</script>";

    String shortPassword = "alpha";
    String weakPassword = "alphabeta";
}

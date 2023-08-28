# Miro assessment

## Description:
Sign up flow automation for https://miro.com/. 

## Not covered:
* OAuth signup (need test accounts to go through the whole flow)
* Every single input validation (only the most illustrative examples)

## Tech:
Playwright + Java + jUnit + maven.
Tests are expected to run manually from IDE (Intellij Idea in particular), runnable files are SignupNegativeTests.java and SignupPositiveTests.java

## Notes:
* Additional option to split tests was by the screen, like in classical Page Object, yet i thought that the coverage is small enough to leave it in a single domain. KISS!
* There are some comments in the test files, mainly regarding minor bugs and html structure peculiarities.
* Dependencies might be outdated, since the assessment is not continuously supported.


## Bonus!
UI bug: tap back from final 'Check email' screen to see both placeholder and text in the email input.

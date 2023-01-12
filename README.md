# Miro assessment

## Used:
Playwright + Java + jUnit + mvn

## Not covered:
* OAuth signup (need test accounts to go through whole flow)
* Every single validation (only the most illustrative examples)

## Notes:
* Additional option to split tests was by the screen, like in classical Page Object, yet i thought that the coverage is small enough to leave it in a single domain. KISS!
* There are some comments in the test files, mainly regarding minor bugs and html structure peculiarities.


## Bonus!
UI bug: tap back from final 'Check email' screen to see both placeholder and text in the email input.

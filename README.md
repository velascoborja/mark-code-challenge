# Digital Surgery Android Tech Test

We would like you to spend a few hours on the following technical task - create an Android application with two screens, some API requests and a bit of database storing.

### Procedure list page
This should be the home page of the app and it should show a list of procedures cards/cells each with:
* A thumbnail image
* The procedure title
* The phase count.
* A favourite button toggle
* Clicking on one of the procedure items should show the user a bottom sheet dismissable view, containing all the detailed information of the procedure.
  * Card image at the top
  * A favourite button toggle
  * The title of the procedure
  * Total duration in minutes
  * Creation date in dd/mm/yyyy format
  * A horizontal list of phases of the procedure, each containing:
    * Thumbnail image
    * Phase name     

### Favourite procedures page
Each time a user taps on the favourite button toggle, that procedure has to be stored in a local database. This favourite procedures list, will display just the marked as favourite procedures (if any).
 * Procedures must be tappable too so that the user is able to see the procedure details view
 * Each time a procedure's favourite state changes, the user should be able to see a Snackbar notifying the result to the user

## Instructions:
- This is your fork of the project. Feel free to push to the main branch.
- Upon completion, push to main and email us to let us know you have finished.
- Do not include build folders or IDE specific files.

## Endpoints
- The procedures list will be available at `https://staging.touchsurgery.com/api/v3/procedures` 
- Procedure details are available at `https://staging.touchsurgery.com/api/v3/procedures/$PROCEDURE_ID`

For example `https://staging.touchsurgery.com/api/v3/procedures/procedure-TSC_CemCup`

## Considerations
* Be written as production-ready code. We would like you to build it in the same way as if you were going to publish to the store.
* If possible, use the latest stable version for Android Studio.
* Consider explaning the solution and why certain things are included and others are left out.

## Automated tests:
Please make sure that you write your code in a testable way and that you include some unit tests. TDD is a tool that we use when we feel it is appropriate. We want to respect your time and do not expect 100% coverage, but would some examples of representative unit testing. The same goes for UI tests.

## Technologies & techniques to use:
You will be joining an existing team with existing technology. We are interested in gauging how well you use those technologies, so if we do not see them addressed here we will discuss them in the interviews. These technologies include, but are not limited to:
Dependency injection with Dagger/Hilt, Room, Coroutines, Compose, Retrofit, UI testing with Espresso, MVVM, and unit testing.

In the Touch Surgery app, all modern development is done with Coroutines and Jetpack Compose, however we still have plenty of RxJava and Android Views / XML based layouts. Use whichever you think will give you the cleanest results given your ability.

We do not expect you to be able to come in on day one and do everything straight away, but familiarity with these technologies helps significantly, especially for senior candidates.
We are interested in how you architect mobile applications so pay attention to which design pattern to use and be prepared to answer questions about why you chose the pattern you did.

A good architecture would be highly appreciated.

## Bonus:
* Include a way to search/filter procedures in the list
* Capability to work offline

# Challenger's notes:
This project is an Android app that interacts with the TouchSurgery API. 

# Author
Mark Edison R. Cua

## Features
- Procedure List: View a list of procedures fetched from the TouchSurgery API.
- Procedure Details: View detailed information of a selected procedure.
- Favourite Procedures: Store and display favourite procedures in a local database.
- Search Procedures: Filter procedures by title.
- Filter Procedures: Filter procedures by title and duration .
- Offline Capability: Store procedures in a local database for offline viewing.

Technical Overview

## Architecture
The project uses MVVM (Model-View-ViewModel) architecture with the following layers:

- Data Layer: Handles API calls and local database operations using Room.
- Presentation Layer: Composables and ViewModels powered by Jetpack Compose.
- 
## Libraries Used
- Jetpack Compose: Modern UI toolkit for building native Android apps.
- Hilt: Dependency injection for managing app-wide dependencies.
- Retrofit: HTTP client for API requests.
- Room: Local database for caching API data.
- Kotlin Coroutines: Asynchronous programming and flows.
- Timber: Logging library.
- Jacoco: Code coverage library.
- Detekt: Static code analysis tool.

## Testing
- Unit tests: Test the ViewModel and Repository classes.
- Instrumented tests: Test the procedures list screen.

## Code Coverage
- Jacoco: Code coverage report is generated after running the tests.
- The code coverage report is located at `app/build/reports/jacoco/jacocoTestReport/html/index.html`.
- To run the tests and generate the code coverage report, run the following command:
```Android Studio terminal
./gradlew testDebugUnitTest
./gradlew jacocoTestReport
```

## Static Code Analysis
- Detekt: Static code analysis tool is used to enforce code quality.
- To run the static code analysis, run the following command:
```Android Studio terminal
./gradlew detekt
```
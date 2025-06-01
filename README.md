Ahmed Fathi Rest Assured API Automation

This project automates the JSONPlaceholder API endpoints (POST /posts, GET /posts, GET /posts/{id}) using Java, Maven, Rest Assured, TestNG, and Allure reports. It includes JSON test data and follows the Page Object Model (POM) design pattern for clean and maintainable code.

Project Overview

The project tests the JSONPlaceholder API with the following scenarios:





Create Post: Creates a post with valid and invalid data, verifies 201 status and response body.



Retrieve All Posts: Fetches all posts, verifies 200 status and 100 posts.



Retrieve Specific Post (ID 20): Fetches post with ID 20, verifies 200 status, id=20, userId=2, and specific title.



Error Handling: Attempts to fetch an invalid post (ID 9999), verifies 404 status and empty response body.

Technologies Used





Java: Programming language.



Maven: Build tool and dependency management.



Rest Assured: API testing framework.



TestNG: Testing framework.



Allure: Reporting tool for detailed test results.



Jackson: JSON processing for test data.



JSON Test Data: Stored in src/test/resources/testdata/postData.json.



POM Pattern: API logic encapsulated in PostApi class.

Project Structure

ahmed-fathi-rest-assured/
├── pom.xml
├── src/
│   ├── test/
│   │   ├── java/
│   │   │   ├── base/               # Empty (reserved for future base classes)
│   │   │   ├── apis/               # API logic (POM)
│   │   │   │   └── PostApi.java
│   │   │   ├── pojo/               # Data models
│   │   │   │   └── Post.java
│   │   │   ├── utils/              # Utility classes
│   │   │   │   └── JsonReader.java
│   │   │   ├── tests/              # Test cases
│   │   │   │   └── PostApiTests.java
│   │   ├── resources/
│   │   │   ├── testdata/           # JSON test data
│   │   │   │   └── postData.json
│   │   │   └── testng.xml          # TestNG configuration
├── screenshots/                    # Allure report screenshots
└── README.md

Setup Instructions





Clone the Repository:

git clone https://github.com/your-username/ahmed-fathi-rest-assured.git
cd ahmed-fathi-rest-assured



Install Dependencies:





Ensure Maven is installed (mvn --version).



Run:

mvn clean install



Run Tests:





Execute all tests:

mvn clean test



Generate Allure Report:





Install Allure CLI:





Windows: choco install allure or download from Allure releases.



Run:

mvn allure:serve



The report opens in a browser, showing test details and response bodies.



To save the report:

mvn allure:report

Find it in target/site/allure-maven-plugin.

Test Details

The project includes 5 tests in PostApiTests.java:





Create Post (Valid): Uses validPost from postData.json, expects 201, verifies response body.



Create Post (Invalid): Uses invalidPost from postData.json, expects 201, verifies response body.



Retrieve All Posts: Expects 200, 100 posts.



Retrieve Post ID 20: Expects 200, id=20, userId=2, title "doloribus ad provident suscipit at".



Invalid Post ID (9999): Expects 404, empty body {}.

Allure Report

The Allure report provides detailed results, including:





Epic: API Testing



Feature: JSONPlaceholder Post API Tests



Stories: Create Post, Create Invalid Post, Retrieve All Posts, Retrieve Specific Post, Error Handling



Attachments: Request/response bodies for each test

Screenshots are in the screenshots/ folder.

Sample Test Data

postData.json:

{
  "validPost": {
    "title": "foo",
    "body": "bar",
    "userId": 1
  },
  "invalidPost": {
    "title": "",
    "body": "",
    "userId": -1
  }
}

Notes





The JSONPlaceholder API is a mock API for testing.



The base package is empty but reserved for future extensions (e.g., BaseTest).



If the task intended /users instead of /posts, please clarify.

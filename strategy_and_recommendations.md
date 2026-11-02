# Strategy & Recommendations

### Why you selected these 2 tests for automation over other candidates ?
While APIs check the underlying data, UI tests focus purely on what the customer sees. Selected the complete positive flow (from picking a bet to getting a receipt) to protect the most critical parts of the app:

* **UI Test : Sports Bet app UI and Bet Slip Validation on selecting an odd**
  * **Why:** Critical and most frequently used flow ,also it covers validation  of most of UI elements usage
* **Placing the Bet & Final Receipt Validation**
  * **Why:** This is the core revenue engine. Validating the final receipt on screen proves that the app successfully placed the bet  
* **API Test – Covers all endpoints exposed , validating the most frequently used flow for each of it**

### What you intentionally left as manual only and why ?
* **TC-5-02: Error Modal Resilience and Re bet Recovery Flow** – Network interruptions are inconsistent and must be simulated manually for testing purposes.

### Recommendations:

#### Run Tests Automatically (CI/CD Pipeline)
Stop running tests manually on your laptop. Move them to an automatic system.
* **The Goal:** Every time a developer updates code, the tests run by themselves in the cloud.
* **How to do it:** Link your framework to a tool like GitHub Actions or Jenkins.

#### Use APIs to Set Up Test Data Fast
Do not waste time using the browser UI to create users, deposit money, or find sports games before a test.
* **The Goal:** Use fast backend API calls to set up the data, and use Selenium only for the final UI validation.
* **How to do it:** Use a Java API tool inside your @Before hooks to instantly create a ready-to-use account with money in it.
* **Why it matters:** It stops your UI tests from breaking due to slow page loads or irrelevant setup steps.

#### Match Tests Perfectly with Business Rules
Betting apps have complex rules (like changing odds or account blocks). Clear specifications prevent broken tests.
* **The Goal:** Write clear Gherkin feature files with your Product Owner before writing code.
* **How to do it:** Use Jira tags (like @Jira-123) directly on top of your Cucumber scenarios.
* **Why it matters:** It connects your automation framework directly to your project goals, making it obvious to everyone exactly what features are covered.

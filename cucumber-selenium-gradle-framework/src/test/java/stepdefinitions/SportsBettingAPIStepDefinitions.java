package stepdefinitions;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SportsBettingAPIStepDefinitions{

    private RequestSpecification request;

    private Response response;

    /**
     * Configures the global target environment URL and initializes the request instance.
     *
     * @param baseUri The target root domain URL
     */
    @Given("the API base URI is {string}")
    public void setBaseUri(String baseUri) {
        RestAssured.baseURI = baseUri;
        request = given();
    }

    /**
     * Appends the Content-Type header ensuring outbound request payloads are processed as JSON text.
     */
    @Given("the content type header is set to JSON")
    public void setContentType() {
        request.contentType("application/json");
    }

    /**
     * Appends required operational metadata and user identification tokens to the request headers.
     *
     * @param userID The custom system authentication token belonging to the testing candidate
     */
    @And("the header is set {string}")
    public void setHeaders(String userID) {
        request.header("accept", "*/*")
                .header("x-user-id", userID);
    }

    /**
     * Executes a synchronous HTTP GET request targeting the specified system resource path.
     *
     * @param endpoint The local route suffix appended to the base domain (e.g., "/api/v1/bets")
     */
    @When("a GET request is sent to {string}")
    public void sendGetRequest(String endpoint) {
        response = request.when().get(endpoint);
    }

    /**
     * Asserts that the numeric HTTP response status code evaluates to the expected validation value.
     *
     * @param expectedStatus The expected HTTP status numerical flag (e.g., 200, 201, 404)
     */
    @Then("the response status code should be {int}")
    public void verifyStatusCode(int expectedStatus) {
        response.then().statusCode(expectedStatus);
    }

    /**
     * Reads a local template JSON document, normalizes format whitespaces, and verifies
     * the actual API response body structure strictly matches the string literal sequence.
     *
     * @param filePath Relative system directory path pointer beginning inside 'src/test/resources/'
     * @throws IOException If the resource data pathway cannot be resolved or parsed into standard text
     */
    @And("I validate the response body matches json content {string}")
    public void verifyResponseBodyMatchesJsonFile(String filePath) throws IOException {
        String fullPath = "src/test/resources/" + filePath;
        String expectedJson = new String(Files.readAllBytes(Paths.get(fullPath)));
        String actualJson = response.getBody().asString();
        String cleanExpected = expectedJson.replaceAll("\\s+", "");
        String cleanActual = actualJson.replaceAll("\\s+", "");
        Assert.assertEquals("The response body content did not match the expected JSON file.", cleanExpected, cleanActual);
    }

    /**
     * Executes a standard HTTP POST transaction containing zero body content parameters.
     *
     * @param endPoint The destination network endpoint sequence path
     */
    @When("a POST request is sent to {string}")
    public void postRequestTo(String endPoint) {
        response = request.when().post(endPoint);
    }

    /**
     * Executes an HTTP POST transaction packing a specialized request data payload.
     *
     * @param endpoint The destination network endpoint sequence path
     * @param requestBody The literal string formatted JSON payload parsed directly via DocStrings
     */
    @When("a POST request is sent to {string} with body:")
    public void sendPostRequest(String endpoint, String requestBody) {
        response = request.body(requestBody).when().post(endpoint);
    }

    /**
     * Traverses the JSON tree framework to isolate a single designated query string coordinate
     * path value and confirms it exactly matches an anticipated text parameter.
     *
     * @param jsonPath The targeted dynamic JSON key tracking string context reference path
     * @param expectedValue The expected alphanumeric text state needed to satisfy the assertion rules
     */
    @Then("the response body field {string} should match {string}")
    public void verifyResponseBodyField(String jsonPath, String expectedValue) {
        response.then()
                .body(jsonPath, equalTo(expectedValue));
    }

    @And("the response body contains {string}")
    public void theResponseBodyContains(String containsText) {
        response.then().toString().contains(containsText);
    }
}
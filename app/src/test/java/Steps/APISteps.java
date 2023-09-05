package Steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
public class APISteps {

  //* para configurar los detalles de la solicitud
  private static RequestSpecification request;

  //* Una variable para guardar la respuesta de la solicitud
  private Response response;

  //* para poder validar y extraer los datos de la respuesta (json)
  private ValidatableResponse json;

  @Given("^Envio una peticion Get a la URI (.+)$")
  public void sendGetRequest(String URI) {
    request = given().baseUri(URI).contentType(ContentType.JSON);
  }

  @Then("^Recibo un codigo de estado (\\d+)$")
  public void expectedStatusCode(int expectedStatusCode) {
    response = request.when().get("https://jsonplaceholder.typicode.com/posts");
    json = response.then().statusCode(expectedStatusCode);
  }
}

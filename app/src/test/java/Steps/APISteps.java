package Steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.util.List;
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

  //* Método para verificar el número de elementos de una respuesta
  @Then("^I validate there are (\\d+) items on the (.+) endpoint$")
  public void validateSize(int expectedSize, String endpoint) {

    response = request.when()
               .get(endpoint);

    //* vamos a extraer de la respuesta
    List<String> jsonResponse = response.jsonPath().getList("$");
    int actualSize = jsonResponse.size();

    //* Afirmar que el tamaño esperado es igual al tamaño real
    // * el valor esperado y el valor real recibido del request
    assertEquals(expectedSize, actualSize);

  }

  //* Método para verificar un valor especifico en la respuesta
  @Then("^Valido que hay un valor: (.+) en la respuesta en el endpoint (.+)$")
  public void validateValue(String expectedValue, String endpoint) {
    response = request.when().get(endpoint);
    List<String> jsonResponse = response.jsonPath().getList("username");
    assertTrue("El valor " + expectedValue + " no se encuentra en la lista", jsonResponse.contains(expectedValue));
  }

}

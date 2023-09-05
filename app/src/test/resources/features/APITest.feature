Feature: Ejemplo de Request
  @API
  Scenario: Prueba Get al endpoint
    Given Envio una peticion Get a la URI https://jsonplaceholder.typicode.com/posts
    Then Recibo un codigo de estado 200
Feature: Ejemplo de Request

  Scenario: Prueba Get al endpoint
    Given Envio una peticion Get a la URI https://jsonplaceholder.typicode.com/posts
    Then Recibo un codigo de estado 200

 
  Scenario: Validar la cantidad de entradas en la respuesta
    Given Envio una peticion Get a la URI https://jsonplaceholder.typicode.com
    Then I validate there are 100 items on the /albums endpoint

  @API
  Scenario: Validar que un elemento está en la respuesta
     Given Envio una peticion Get a la URI https://jsonplaceholder.typicode.com
     Then Valido que hay un valor: Kamren en la respuesta en el endpoint /users
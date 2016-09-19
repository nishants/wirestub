# Miraje
- A wrapper over wiremock.
- Designed to work in standalone mode only.
- Adds powerful scripting and templating system for wiremock.
- Allows pure behavior based stubbing without writing java code.
- Adds support for 
    - templating engine for json and xml.
    - before blocks to execute instruction before serving resoponse.
    - use request body, headers, cookies, query params in templates.
    - xpath support xml request body.
    - configuration by environment.
    - set session values in before blocks. 
    - read session values in templates.
- This wrapper doesn't change any existing behavior of wiremock, so a wiremock standalone jar can be safely replaced by miraje standalone jar.

# Philosophy : Stubbing over Mocking 
- This extension tries to push developers away from mocking external services instead of stubbing them.
- A stub has a pre-determined behavior and tests are written based on stub's behavior.
- Mocking on the other hand creates expected behavior just before executing a test.
- Hence stubbing facilitates easier reproduction of issues and keeps test data in human readable form (json/xml files).
- Also the test data itself can be validated or migrated into databases if needed.
- Miraze is desigend to reflect back user requests based on response content.
- Though it also allows session variables, but sessions must be avoided to keep stubs stateless.
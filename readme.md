# Wirestub : wiremock in stub style
- Designed to operate in standalone and stubbing mode only.
- Adds powerful scripting and templating system for wiremock.
- Allows behavioral stubbing without writing java code.
- Adds following feature to wiremock : 
    - templating engine for json and xml.
    - before script to execute instruction before serving response.
    - accessing request body, headers, cookies, query params in templates.
    - xpath support for xml request body.
    - configuration by environment.
    - setting session values in before scripts. 
    - accessing session values in templates.
- This wrapper doesn't change any existing behavior of wiremock, so a wiremock standalone jar can be safely replaced by miraze standalone jar.
- Because it behaves just like wiremock, so it can be used along with other wrappers (like service_mock) and extensions.
- It can be controlled with the wiremock's http api.
- It support all the runtime parameters of wiremock, along with adding some of its own.

# Philosophy : Stubbing over Mocking 
- This extension tries to push developers away from mocking external services instead of stubbing them.
- A stub has a pre-determined behavior and tests are written based on stub's behavior.
- Mocking on the other hand creates expected behavior just before executing a test.
- Hence stubbing facilitates easier reproduction of issues and keeps test data in human readable form (json/xml files).
- Also the test data itself can be validated or migrated into databases if needed.
- Miraze is designed to reflect back user response based on incoming request.
- Though it also allows session variables, but sessions must be avoided to keep stubs stateless.
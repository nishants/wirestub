# Setup
- install maven
- install/update to java 8
- git clone repo
- mvn clean compile

* not tested on java versions lower than 8, might work as long as a ScriptEngine is available (java 6+)

# Clean and Run Tests
- mvn clean verify

# Adding specs 
- add specification file in test/resources/specs
- update specs in JeysonTest.java
- clean and verify

# How it works
- the src/reources/jeyson.js is the js script which implements the templating engine and provides js env for expressions in templates.
- The CompileParam class is used to link the the callbacks from java environment to nashorn.
- the jeyson.js script itself uses fixtures to map nashorn js objects to java objects

- the script is loaded when a new Jeyson instance is created (hence takes template path in constructor)
- on creation Jeyson objec load the js script, passing a CompileParam object to script
- for every call to compile the Jeyson object invokes the scripts compile function
- script's compile function evaluates result and transforms using fixture to nashorn-java compatible objects
- the result is received as a Map object, corresponding to a json

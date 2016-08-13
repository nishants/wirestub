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
- the src/resources/jeyson.js is the js script which implements the template engine and provides js env for expressions in templates.

- The CompileParam class is used to link the callbacks from java environment to nashorn.

- the jeyson.js script itself uses fixtures to map nashorn js objects to java objects

- the script loads up on creating a Jeyson instance (it takes template path in constructor, to calculate the relative path of included json temlpates)

- on creation Jeyson object loads the js script, passing a CompileParam object to script

- for every call to compile the Jeyson object invokes the scripts compile function

- script's compile function evaluates result and transforms using fixture to nashorn-java compatible objects

- the result is a Map object that represents a json tree.

function jeyson(config){
    return {
        compile: function(scope, template){
            return config.getTemplate("hello/hello.json");
        }
    };
}
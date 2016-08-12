function jeyson(config){
    return {
        compile: function(scope, template, config){
            return config.getTemplate("hello/hello.json");
        }
    };
}
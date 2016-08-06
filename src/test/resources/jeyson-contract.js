function jeyson(config){
    return {
        compile: function(){
            return config.getTemplate("hello/hello.json");
        }
    };
}
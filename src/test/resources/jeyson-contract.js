config = undefined;

function init(_config){
    config = _config;
}

function compile(scope, template){
    return config.getTemplate("hello/hello.json");
}

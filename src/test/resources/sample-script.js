function returnArgument(arg){
    return arg.getMessage();
}

function evalSnippet(arg){
    return eval(arg.getMessage());
}

callsMade = 0;
function callsCount(){
    return {count: ++callsMade};
}
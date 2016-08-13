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



var error = function error(params){
      var message =  "<error> :  <expression> for <scope>"
          .replace("<expression>", params.expression)
          .replace("<scope>", jeysonConfig.stringify(params.scope))
          .replace("<error>", params.error);
      return {
        message: message
      };
    },
    execute = function (scope, expression) {
      var contextScript = "";
      for (var field in scope) {
        contextScript += ("var <field> = scope.<field>;".replace("<field>", field).replace("<field>", field));
      }
      scope.execute = function () {
        var escapeExpression = expression.replace(new RegExp("\'", 'g'), "\\'");
        try {
          var sript = contextScript + "eval('<expression>');".replace("<expression>", escapeExpression);
          return eval(sript);
        }catch(err){
          return error({scope: this, expression: expression, error: err}).message;
        }
      };
      return scope.execute();
    };

function executeScope(params){
  return execute(params.scope,params.expression);
}
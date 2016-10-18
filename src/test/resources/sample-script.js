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

var
    isArrayList = function (objet) {
      return objet.getClass().getName().equals("java.util.ArrayList");
    },
    unwrapList = function (list) {
      var arrayed = [];
      list.forEach(function (value) {
        arrayed.push(value);
      })
      return arrayed;
    },
    fixObject = function (object) {
      for (var field in object) {
        if (isArrayList(object[field])) {
          object[field] = unwrapList(object[field]);
        }
      }
      return object;
    },
    unwrap = function (param) {
      return fixObject({val: param}).val;
    };

function insertInArray(array, val, config){
  var jsArray = unwrap(array);
  jsArray.push(4);

  var list = new java.util.ArrayList();
  for(var i = 0 ; i < jsArray.length; i++){
    list.add(jsArray[i]);
  }
  return list;
}

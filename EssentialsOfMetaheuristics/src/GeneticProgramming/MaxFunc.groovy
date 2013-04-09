package GeneticProgramming

class MaxFunc {
    def arity = 2
    def expr = {arg0, arg1 -> 'Math.max(' + listOfArgs[0].toString()  + ',' + listOfArgs[1].toString() + ')'}
    
}

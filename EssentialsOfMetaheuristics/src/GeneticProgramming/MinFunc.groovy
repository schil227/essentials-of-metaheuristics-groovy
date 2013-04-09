package GeneticProgramming

class MinFunc {
    def arity = 2
    def expr = {arg0, arg1 -> 'Math.min(' + listOfArgs[0].toString()  + ',' + listOfArgs[1].toString() + ')'}
    

}

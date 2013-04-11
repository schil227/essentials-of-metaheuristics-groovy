package GeneticProgramming

class MinFunc {
    def arity = 2
    def expr = {arg0, arg1 -> 'Math.min(' + arg0.toString()  + ',' + arg1.toString() + ')'}
    

}

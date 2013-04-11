package GeneticProgramming

class MaxFunc {
    def arity = 2
    def expr = {arg0, arg1 -> 'Math.max(' + arg0.toString()  + ',' + arg1.toString() + ')'}
    
}

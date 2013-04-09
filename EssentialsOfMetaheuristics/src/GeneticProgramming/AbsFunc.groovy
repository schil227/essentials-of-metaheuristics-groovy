package GeneticProgramming

class AbsFunc {
    def arity = 1
    
    def expr = {arg -> return 'Math.abs('+arg.toString()+')'}
}

package GeneticProgramming

class SinFunc {
    def arity = 1
    def expr = {arg -> return 'Math.sin('+arg.toString()+')'}
}

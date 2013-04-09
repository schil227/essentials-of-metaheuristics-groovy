package GeneticProgramming

class Subtract {
    def arity = 2
    def expr = {arg0, arg1 -> '(' + arg0.toString() + ') - (' +  arg1.toString() + ')'}
}
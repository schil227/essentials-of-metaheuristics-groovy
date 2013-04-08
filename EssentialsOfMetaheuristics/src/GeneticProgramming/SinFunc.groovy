package GeneticProgramming

class SinFunc {
    def arity = 1
    
    public String toString(listOfArgs){
        'Math.sin(' + listOfArgs[0].toString() + ')'
    }

}

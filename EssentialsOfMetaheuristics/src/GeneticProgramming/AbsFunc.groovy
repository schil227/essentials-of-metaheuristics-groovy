package GeneticProgramming

class AbsFunc {
    def arity = 1
    
    public String toString(listOfArgs){
        'Math.abs(' + listOfArgs[0] + ')'
    }

}

package GeneticProgramming

class MinFunc {
    def arity = 2
    
    public String toString(listOfArgs){
        'Math.min(' + listOfArgs[0].toString()  + ',' + listOfArgs[1].toString() + ')'
    }
}

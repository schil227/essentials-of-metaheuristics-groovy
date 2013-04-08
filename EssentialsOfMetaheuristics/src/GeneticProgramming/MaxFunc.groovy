package GeneticProgramming

class MaxFunc {
    def arity = 2
    
    public String toString(listOfArgs){
        'Math.max(' + listOfArgs[0].toString()  + ',' + listOfArgs[1].toString() + ')'
    }
}

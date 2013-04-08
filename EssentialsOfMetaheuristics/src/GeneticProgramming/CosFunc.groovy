package GeneticProgramming

class CosFunc {
    def arity = 1
    
    public String toString(listOfArgs){
        'Math.cos(' + listOfArgs[0].toString() + ')'
    }

}

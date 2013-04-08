package GeneticProgramming

class Divide {
	def arity = 2
	
	public String toString(listOfArgs){
		if(listOfArgs[1] == 0 || listOfArgs[1] == -0){
			return '1'
		} else {
			return '(' + listOfArgs[0].toString() + ') / (' +  listOfArgs[1].toString() + ')'
		}
	}
}

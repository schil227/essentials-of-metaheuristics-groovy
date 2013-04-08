package GeneticProgramming

class Subtract {
	
	def arity = 2

	public String toString(listOfArgs){
		return '(' + listOfArgs[0].toString() + ') - (' +  listOfArgs[1].toString() + ')'
	}
}

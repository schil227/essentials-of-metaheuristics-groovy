package GeneticProgramming

class Subtract {
	
	def arity = 2

	def eval(listOfArgs){
		return listOfArgs[0] - listOfArgs[1]
	}
}

package GeneticProgramming

class Divide {
	def arity = 2
	
	def eval(arg1, arg2){
		if(arg2 == 0){
			return 1
		} else {
			return arg1 / arg2
		}
	}
}

package GeneticProgramming

class TreeGP {
	
	
	
	def generateRandomTree(setOfFunctions, setOfTerminals, maxDepth, method){
		def expr
		if(maxDepth == 0 || (method == "grow" && rand() < (setOfTerminals/(setOfTerminals + setOfFunctions) ) )){
			expr = chooseRandomElement(setOfTerminals)
		}else{
			def func = chooseRandomElement(setOfFunctions)
			listOfArgs = []
			for(i in 1..arity(func)){
				listOfArgs.add(generateRandomTree(setOfFunctions, setOfTerminals, maxDepth-1, method))
			}
			expr = [func, listOfArgs]
		}
		return expr
		
	}
	
}

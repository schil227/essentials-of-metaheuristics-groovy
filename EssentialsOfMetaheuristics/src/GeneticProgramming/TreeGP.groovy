package GeneticProgramming

class TreeGP {
	
	Random rand = new Random()
	
	def chooseRandomElement(alist){
		return alist.get(rand.nextInt(alist.size -1))
		
	}
	
	
	
	
	
	def generateRandomTree(setOfFunctions, setOfTerminals, maxDepth, method){
		def expr
		if(maxDepth == 0 || (method == "grow" && rand() < (setOfTerminals/(setOfTerminals + setOfFunctions) ) )){
			expr = new TerminalNode(chooseRandomElement(setOfTerminals))
		}else{
			def func = chooseRandomElement(setOfFunctions)
			def listOfArgs = [] 
			for(i in 1..func.arity){
				listOfArgs.add(generateRandomTree(setOfFunctions, setOfTerminals, maxDepth-1, method))
			}
			expr = new FunctionNode(func, listOfArgs)
		}
		return expr
	}
	
	static main(args) {
		def setOfFunctions = [new Add(), new Subtract(), new Multiply(), new Divide()]
		def x,y,z
		def setOfTerminals = [1,2,3,4,5, x, y, z]
		//def tree = generateRandomTree(setOfFunctions, setOfTerminals, 2, "grow")
		println(setOfFunctions.get(0).arity)
	}
	
}

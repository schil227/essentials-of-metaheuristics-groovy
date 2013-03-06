package GeneticProgramming

class TreeGP {
	
	static Random rand = new Random()
	
	static def chooseRandomElement(alist){
		return alist.get(rand.nextInt(alist.size -1))
		
	}
	
	static def generateRandomTree(setOfFunctions, setOfTerminals, maxDepth, method){
		def expr
		//println("before if")
		if(maxDepth == 0 || (method == "grow" && rand.nextInt() < (setOfTerminals.size()/(setOfTerminals.size() + setOfFunctions.size()) ) )){
			//println("making a terminalNode")
			expr = new TerminalNode(chooseRandomElement(setOfTerminals))
			//println("made a terminalNode")
		}else{
		   // println("defining func")
			def func = chooseRandomElement(setOfFunctions)
			//println("defined func")
			def listOfArgs = [] 
			//println("defined listOfArgs")
			for(i in 1..func.arity){
			//	println("In the For loop!")
				listOfArgs.add(generateRandomTree(setOfFunctions, setOfTerminals, maxDepth-1, method))
			}
			expr = new FunctionNode(func, listOfArgs)
		}
		//println("returning node")
		return expr
	}
	
	public static main(args) {
		def setOfFunctions = [new Add(), new Subtract(), new Multiply(), new Divide()]
		//def x,y,z
		def setOfTerminals = [1,2,3,4,5]
		
		println("calling randElement")
		chooseRandomElement(setOfTerminals)
		println("Calling the method")
		def tree = generateRandomTree(setOfFunctions, setOfTerminals, 2, "grow")
		println("tree " + tree.eval())
		println(setOfFunctions.get(0).arity)
	}
	
}

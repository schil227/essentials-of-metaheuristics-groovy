package GeneticProgramming

class TreeGP {
	
	static Random rand = new Random()
	
	static def chooseRandomElement(alist){
		return alist.get(rand.nextInt(alist.size -1))
		
	}
	
	static def generateRandomTree(setOfFunctions, setOfTerminals, maxDepth, method){
		def expr
		if(maxDepth == 0 || (method == "grow" && (rand.nextInt(10)/10.0) < (setOfTerminals.size()/(setOfTerminals.size() + setOfFunctions.size()) ) )){
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
		def setOfFunctions = [new Add(), new Subtract(), new Multiply(), new Divide(), new Add(), new Subtract(), new Multiply(), new Divide()]
		def setOfTerminals = [1,2,3,4,5,'x','y','z']
		
		println("calling randElement")
		chooseRandomElement(setOfTerminals)
		println("Calling the method")
		def tree = generateRandomTree(setOfFunctions, setOfTerminals, 5, "grow")
		def ran = rand.nextInt(10)
		println("tree " + tree.eval(['x': 2, 'y':4, 'z': ran]))
		println(setOfFunctions.get(0).arity)
		
	}
	
}

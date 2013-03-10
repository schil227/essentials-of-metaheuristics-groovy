package GeneticProgramming

class TreeGP {

	static Random rand = new Random()

	static def chooseRandomElement(alist){
		return alist.get(rand.nextInt(alist.size -1))
	}

	static def generateRandomTree(setOfFunctions, setOfTerminals, maxDepth, method){
		return generateRandomTreeRecur(setOfFunctions, setOfTerminals, maxDepth, method, 0, 0).getAt(0)
	}


	static def generateRandomTreeRecur(setOfFunctions, setOfTerminals, maxDepth, method, depthSize, oldDepthSize){
		def expr
		if(maxDepth == 0 || (method == "grow" && (rand.nextInt(10)/10.0) < (setOfTerminals.size()/(setOfTerminals.size() + setOfFunctions.size()) ) )){
			//println("making a terminalNode")
			depthSize += 1
			if(depthSize > oldDepthSize){
				oldDepthSize = depthSize
			}
			//println("after the depth check")
			expr = new TerminalNode(chooseRandomElement(setOfTerminals), oldDepthSize)
			//println("made a terminalNode")
		}else{
			//println("defining func")
			def func = chooseRandomElement(setOfFunctions)
			//	println("defined func")
			def listOfArgs = []
			//	println("defined listOfArgs")
			for(i in 1..func.arity){
				//		println("In the For loop!")
				def genRandTreeOutcome = generateRandomTreeRecur(setOfFunctions, setOfTerminals, maxDepth-1, method, depthSize + 1, oldDepthSize)
				listOfArgs.add(genRandTreeOutcome.get(0))
				if((genRandTreeOutcome.get(1)) > oldDepthSize){
					oldDepthSize = genRandTreeOutcome.get(1)
				}
			}
			expr = new FunctionNode(func, listOfArgs, oldDepthSize)
		}
		//println("returning node")
		//		if(expr.class == TerminalNode && oldDepthSize != 1){ //not the initial node
		//			return [expr, oldDepthSize]
		//		}
		return [expr, oldDepthSize]
	}


	static def chooseRandomNode(listOfChildren){
		def funcNodeList = []
		def termNodeList = []

		for(node in listOfChildren){
			if(node.class == TerminalNode){
				termNodeList.add(node)
			} else {
				funcNodeList.add(node)
			}

			if(funcNodeList.isEmpty){
				return chooseRandomElement(termNodeList)
			} else if(termNodeList.isEmpty){
				return chooseRandomElement(funcNodeList)
			} else {
				if(rand.nextInt(10) == 0){
					return chooseRandomElement(termNodeList)
				} else {
					return chooseRandomElement(funcNodeList)
				}
			}
		}
	}

	static def crossoverTrees(firstTree, secondTree){
		def node = secondTree
		def treeDepth = node.depth - 1
		def nodeNotFound = true
		while(nodeNotFound){
			if(node.class == TerminalNode || (rand.nextInt(treeDepth) == 0)){
				nodeNotFound = false
			} else {
				node = chooseRandomNode(node.listOfChildren)
			}
		}
	}

		public static main(args) {
			def setOfFunctions = [
				new Add(),
				new Subtract(),
				new Multiply(),
				new Divide(),
				new Add(),
				new Subtract(),
				new Multiply(),
				new Divide()
			]
			def setOfTerminals = [1, 2, 3, 4, 5, 'x', 'y', 'z']

			println("calling randElement")
			chooseRandomElement(setOfTerminals)
			println("Calling the method")
			def tree = generateRandomTree(setOfFunctions, setOfTerminals, 5, "grow")
			println("the depth of the tree is " + tree.depth )
			def ran = rand.nextInt(10)
			println("tree " + tree.eval(['x': 2, 'y':4, 'z': ran]))
			println(setOfFunctions.get(0).arity)
		}
	}

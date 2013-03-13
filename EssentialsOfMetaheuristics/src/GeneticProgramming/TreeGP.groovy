package GeneticProgramming

import java.util.prefs.AbstractPreferences.NodeAddedEvent;

class TreeGP {

	static Random rand = new Random()

	static def chooseRandomElement(alist){
		return alist.get(rand.nextInt(alist.size))
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
		}

		println("functionNodeList is " + funcNodeList)
		println("functionNodeList's class is " + funcNodeList.class)
		if(funcNodeList.isEmpty()){
			println("funcNodeList is empty")
			return chooseRandomElement(termNodeList)
		} else if(termNodeList.isEmpty()){
			println("termNodeList is empty")
			return chooseRandomElement(funcNodeList)
		} else {
			println("Gonna randomly choose a list!")
			if(rand.nextInt(10) == 0){
				println("Chose the termNodeList of size " + termNodeList.size)
				return chooseRandomElement(termNodeList)
			} else {
				println("Chose the funcNodeList of size " + funcNodeList.size)
				return chooseRandomElement(funcNodeList)
			}
		}

	}

	static def copyTree(node){

		if(node instanceof TerminalNode){
			return node
		}else{
			def newNode = node.clone()
			newNode.listOfChildren = []
			for(childNode in node.listOfChildren){
				newNode.listOfChildren.add(copyTree(childNode))
			}
			return newNode
		}
	}

	static def findReplaceNode(toNode, parentNode, depth, fromNode){
		if(toNode instanceof TerminalNode || (rand.nextInt(depth) == 0)){
			if(parentNode instanceof DummyNode){
				println("replacing the first node in the tree")
				parentNode.child = fromNode
			}else{
				println("replacing " + toNode + " with " + fromNode)
				parentNode.listOfChildren.remove(toNode)
				parentNode.listOfChildren.add(fromNode)
				toNode = fromNode
			}
		}else{
			findReplaceNode(chooseRandomNode(toNode.listOfChildren), toNode,depth,fromNode)
		}
	}

	static def calculateDepth(node,depth, oldDepth){
		if(node instanceof TerminalNode){
			depth += 1
			if(depth > oldDepth){
				oldDepth = depth
			}
			return oldDepth
		}else{
			for(child in node.listOfChildren){
				def childDepth = calculateDepth(child,depth + 1,oldDepth)
				if(childDepth > oldDepth){
					oldDepth = childDepth
				}
				child
			}
			return oldDepth
		}
	}

	static def crossoverTrees(firstTree, secondTree){
		def node = secondTree
		def treeDepth = node.depth
		def nodeNotFound = true
		while(nodeNotFound){
			def tmpRand = rand.nextInt(treeDepth)
			if(node instanceof TerminalNode || (tmpRand == 0)){
				println("the tmpRand = " + tmpRand)
				println("The tree depth is: " + treeDepth)
				println("We found the spot!")
				nodeNotFound = false
			} else {
				println("we're going deeper!")
				node = chooseRandomNode(node.listOfChildren)
			}
		}
		nodeNotFound = true
		def toNode = copyTree(firstTree)
		def dummyNode = new DummyNode(null)
		findReplaceNode(toNode, dummyNode, toNode.depth - 1,node)
		if(dummyNode.child != null){
			toNode = dummyNode.child
		}

		toNode.depth = calculateDepth(toNode,0,0)
		println("toNode's max depth is " + toNode.depth)
		return toNode
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
		println("Calling tree1")
		def tree = generateRandomTree(setOfFunctions, setOfTerminals, 2, "growd")
		println("tree 1 evaluates to : " + tree.eval(['x': 2, 'y':4, 'z': 5]))
		println()
		println("Calling tree2")
		def tree2 = generateRandomTree(setOfFunctions, setOfTerminals, 2, "growd")
		println("tree 2 evaluates to : " + tree2.eval(['x': 2, 'y':4, 'z': 5]))
		println()
		println("Calling tree3")
		def tree3 = crossoverTrees(tree, tree2)
		println()
		println("tree 3 evaluates to : " + tree3.eval(['x': 2, 'y':4, 'z': 5]))

	}
}

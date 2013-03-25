package GeneticProgramming

class FunctionNode implements Cloneable {
	def function
	def listOfChildren
	def depth
	
	FunctionNode(afunction, alistOfChildren, theDepth){
		function = afunction 
		listOfChildren = alistOfChildren
		depth = theDepth
	}
	
	def eval(hashMap){
		println("the hashMap is " + hashMap)
		println(function)
		println("the function depth is " + depth)
		def tmpList = listOfChildren.collect {node -> node.eval(hashMap)}
		return function.eval(tmpList)
	}
}

package GeneticProgramming

class FunctionNode {
	def function
	def listOfChildren
	def depth
	
	FunctionNode(afunction, alistOfChildren, theDepth){
		function = afunction 
		listOfChildren = alistOfChildren
		depth = theDepth
	}
	
	def eval(hashMap){
		println(function)
		println("the function depth is " + depth)
		def tmpList = listOfChildren.collect {node -> node.eval(hashMap)}
		return function.eval(tmpList)
	}
}
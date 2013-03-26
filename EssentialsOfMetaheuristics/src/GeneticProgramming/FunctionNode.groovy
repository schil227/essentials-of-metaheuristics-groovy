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
	
	def eval(hashMap,outputInfo){
		if(outputInfo){
			println(function)
		}
	
	//	println("the hashMap is " + hashMap)
	//	
		def tmpList = listOfChildren.collect {node -> node.eval(hashMap, outputInfo)}
		return function.eval(tmpList)
	}
}

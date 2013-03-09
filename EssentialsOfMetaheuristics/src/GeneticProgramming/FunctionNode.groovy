package GeneticProgramming

class FunctionNode {
	def afunction
	def alistOfChildren
	
	FunctionNode(function, listOfChildren){
		afunction = function 
		alistOfChildren = listOfChildren
	}
	
	def eval(hashMap){
		println(afunction)
		def tmpList = alistOfChildren.collect {node -> node.eval(hashMap)}
		return afunction.eval(tmpList)
	}
}

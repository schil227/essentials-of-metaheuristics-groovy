package GeneticProgramming

class FunctionNode {
	def afunction
	def alistOfChildren
	
	FunctionNode(function, listOfChildren){
		afunction = function 
		alistOfChildren = listOfChildren
	}
	
	def eval(){
		def tmpList = alistOfChildren.collect {node -> node.eval()}
		return afunction.eval(tmpList)
	}
}

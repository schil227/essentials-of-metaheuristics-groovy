package GeneticProgramming

class TerminalNode {
	def terminal
	def depth
	TerminalNode(aTerminal, theDepth){
		terminal = aTerminal
		depth = theDepth
		
	}

	def eval(hashMap, outputInfo){
		if(outputInfo){
			println("the terminal node's value is " + terminal)
		}
		
	//	println("the depth is " + depth)
		if(terminal.class == String){
			return hashMap[terminal]
		}else{
			return terminal
		}
	}
    
       public String toString(){
           return terminal.toString()
       } 
}

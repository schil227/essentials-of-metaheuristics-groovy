package GeneticProgramming

class TerminalNode {
	def terminal
	def depth
	TerminalNode(aTerminal, theDepth){
		terminal = aTerminal
		depth = theDepth
		
	}

	def eval(hashMap){
		println("the terminal node's value is " + terminal)
		println("the depth is " + depth)
		if(terminal.class == String){
			return hashMap[terminal]
		}else{
			return terminal
		}
	}
}
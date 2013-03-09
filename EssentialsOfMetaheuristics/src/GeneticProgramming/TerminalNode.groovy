package GeneticProgramming

class TerminalNode {
	def terminal

	TerminalNode(aTerminal){
		terminal = aTerminal
	}

	def eval(hashMap){
		println("the terminal node's value is " + terminal)
		println("the terminal node's class is " + terminal.class)
		if(terminal.class == String){
			return hashMap[terminal]
		}else{
			return terminal
		}
	}
}

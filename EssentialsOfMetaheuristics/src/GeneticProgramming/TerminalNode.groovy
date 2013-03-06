package GeneticProgramming

class TerminalNode {
  def terminal
  
  TerminalNode(aTerminal){
	  terminal = aTerminal
  }
  
  def eval(){
	  return terminal
  }
}

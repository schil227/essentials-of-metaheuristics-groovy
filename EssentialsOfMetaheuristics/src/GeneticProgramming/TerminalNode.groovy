package GeneticProgramming

class TerminalNode {
  def terminal
  
  TerminalNode(aTerminal){
	  terminal = aTerminal
  }
  
  def eval(){
	  println("the terminal node's value is " + terminal)
	  println("the terminal node's class is " + terminal.class)
	  return terminal
  }
}

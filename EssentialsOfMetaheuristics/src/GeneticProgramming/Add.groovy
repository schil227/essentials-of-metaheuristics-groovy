package GeneticProgramming

class Add {
  def arity = 2
  
  def eval(listOfArgs){
	  return listOfArgs[0] + listOfArgs[1]
  }
}

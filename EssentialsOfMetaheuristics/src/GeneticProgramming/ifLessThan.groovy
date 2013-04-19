package GeneticProgramming

class ifLessThan {
  def arity = 4 
  def expr = {arg0, arg1, arg2, arg3 -> return '(('+ arg0.toString() + '<'+ arg1.toString() +') ?' + arg2.toString() +':'+ arg3.toString()+')'}

}

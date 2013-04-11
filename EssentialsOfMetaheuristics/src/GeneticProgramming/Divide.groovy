package GeneticProgramming

class Divide {
	def arity = 2
        def expr = {arg0, arg1 -> 
            if(arg1 == 0)
              {'1'}
            else
              {'(' + arg0.toString() + ') / (' +  arg1.toString() + ')'} 
           }
    
}

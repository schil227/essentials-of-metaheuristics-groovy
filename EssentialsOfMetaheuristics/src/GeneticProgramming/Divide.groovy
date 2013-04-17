package GeneticProgramming

class Divide {
	def arity = 2
        def expr = {arg0, arg1 -> 
              '((' + arg1.toString() + ' != 0 ) ? ((' + arg0.toString() + ') / (' +  arg1.toString() + ')) : 1)'
           } //( (asdf) != 0 : ((ajkl)/(asdf)), 1)
    
}

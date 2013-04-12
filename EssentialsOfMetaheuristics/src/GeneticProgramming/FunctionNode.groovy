package GeneticProgramming

class FunctionNode implements Cloneable {
    def function
    def listOfChildren
    def depth

    FunctionNode(afunction, alistOfChildren, theDepth){
        function = afunction
        listOfChildren = alistOfChildren
        depth = theDepth
    }

    def eval(hashMap,outputInfo){
        if(outputInfo){
            println(function)
        }

        //	println("the hashMap is " + hashMap)
        //
        def tmpList = listOfChildren.collect {node -> node.eval(hashMap, outputInfo)}
        return function.eval(tmpList)
    }

    public String toString(){
        if(function.arity==1){
            return function.expr(listOfChildren[0])
        }
        else if(function.arity==2){
            return function.expr(listOfChildren[0],listOfChildren[1])
        }
        else if(function.arity==4){
            return function.expr(listOfChildren[0], listOfChildren[1], listOfChildren[2], listOfChildren[3])
        }
    }
}

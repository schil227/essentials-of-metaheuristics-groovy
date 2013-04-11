package GeneticProgramming;

import static org.junit.Assert.*
import spock.lang.Specification

class FunctionNodeSpec extends Specification{

    def "Test toString Output"(){
        given:
        def myTree = new FunctionNode(new Add(), [
            new TerminalNode(1, 2),
            new TerminalNode(3, 2)], 1)
        when:
        def myString = myTree.toString()
        
        then:
        myString  == '(1) + (3)'
        println myString
    }
    
    def "Test random tree output"(){
        given:
        def treeGp = new TreeGP()
        def setOfFunctions = [
                        new Add(),
                        new Subtract(),
                        new Multiply(),
                        new Divide(),
                        new Add(),
                        new Subtract(),
                        new Multiply(),
                        new Divide(),
                        new AbsFunc(),
                        new CosFunc(),
                        new MaxFunc(),
                        new MinFunc(),
                        new SinFunc(),
                        new Divide(),
                        new AbsFunc(),
                        new CosFunc(),
                        new MaxFunc(),
                        new MinFunc(),
                        new SinFunc()
                ]
        def setOfTerminals = [1, -1, 2, -2, 'e.getDistance()', 'getX()', 'getY()', 'e.getVelocity()']
        def randoTree = treeGp.generateRandomTree(setOfFunctions, setOfTerminals, 3, "growd")
        
        when:
        def myString = ''
        myString += randoTree.toString()
        println(myString)
        
        then:
        assert myString != ''
    }
}


package populationMethods
import GeneticProgramming.*
import applications.BattleRunner
import applications.RobotBuilder

class CopyOfMuPlusLambdaESTreeGP {
    Integer numParents = 2
    Integer numChildren = 6
    Integer numTweaks = 1
    Integer individualCount = 0
    def randomParent = new Random()
    def treeGP

    public HashMap defineHashMap(arr){
        individualCount++
        
        return [
                    'id' : individualCount,
                    'enemy_energy' : arr[0].toString(),
                    'my_energy' :  arr[1].toString(),
                    'angle_diff' :  arr[2].toString(),
                    'distance' :  arr[3].toString()
                ]
    }
    def maximize(treeGP){
        this.treeGP = treeGP
        def individualArr = []
        def setOfFunctions = [
            new Add(),
            new Subtract(),
            new Multiply(),
            new Divide(),
            new Add(),
            new Subtract(),
            new Multiply(),
            new Divide(),
//            new AbsFunc(),
//            new CosFunc(),
//            new MaxFunc(),
//            new MinFunc(),
//            new SinFunc(),
//            new Divide(),
//            new AbsFunc(),
//            new CosFunc(),
//            new MaxFunc(),
//            new MinFunc(),
//            new SinFunc(),
//            new ifGreaterThan(),
//            new ifGreaterThan()
        ]
        def setOfTerminals = [
            1,
            -1,
            2,
            -2,
 //           'e.getDistance()',
 //           'getX()',
 //           'getY()',
 //           'e.getVelocity()'
        ]

        println("making the initial individuals")
        def tmpNode
        numChildren.times {
           tmpNode = [
                    [
                        treeGP.generateRandomTree(setOfFunctions, setOfTerminals, 5, "growf"),
                        treeGP.generateRandomTree(setOfFunctions, setOfTerminals, 5, "growf"),
                        treeGP.generateRandomTree(setOfFunctions, setOfTerminals, 5, "growf"),
                        treeGP.generateRandomTree(setOfFunctions, setOfTerminals, 5, "growf")
                    ],
                    [:],
                    0]
            println("gonna def the hashmap")
            tmpNode[1] = defineHashMap(tmpNode[0])
//            println("the hashmap be lookin like " + defineHashMap(tmpNode[0]))
//            println("hashmaps class be lookin like " + tmpNode[1].class)
            individualArr.add(tmpNode)
        }
        
        println("finding the best")

        def best = individualArr[0][0]
        def robotBuilder = new RobotBuilder("templates/HawkOnFireOS.template")
        def battleRunner = new BattleRunner("templates/battle.template")
        println("building the jar files")
        individualArr.each{ robot ->
            println("the class is " + robot[1].class)
            println("gonna make the jar file")
            robotBuilder.buildJarFile(robot[1])
            println("gonna build the battle file")
            battleRunner.buildBattleFile(robot[1].id)
        }
        
        println("running the battle!")
        individualArr[0][2] = battleRunner.runBattle(individualArr[0][1].id)
        println("settin the bestQuality!")
        def bestQuality = individualArr[0][2]

        while(!treeGP.terminate(best, bestQuality)) {
            for (individual in individualArr) {
                individual[2] = battleRunner.runBattle(individual[1].id)
                if (individual[2]> bestQuality) {
                    best = individual
                    bestQuality =  individual[2]
                }
            }

            individualArr = individualArr.sort{it[2]}[0..<numParents]//.reverse()[0..<numParents]

            for (i in 0..<numParents) {
                //				println("the parents evaluated to : " + treeGP.evaluateTree(individualArr.get(i), problemParings,['x']))
                for (j in 0..<(numChildren / numParents)) {
                    def treeToTweak =[[], [], 0]
                    4.times{
                        treeToTweak[0].add(treeGP.crossoverTrees(individualArr.get(randomParent.nextInt(numParents)), individualArr.get(randomParent.nextInt(numParents))))
                    }
                    treeToTweak[1] = defineHashMap(treeToTweak[0])
                    individualArr.add(treeToTweak)    
                    
                }
            }
            
            individualArr.each{ robot ->
                robotBuilder.buildJarFile(robot[1][0])
                battleRunner.buildBattleFile(robot[1][0].id)
            }
        }
        return best
    }

    String toString() {
        "MuPlusLambdaES_" + numParents + "_" + numChildren + "_" + numTweaks
    }
}

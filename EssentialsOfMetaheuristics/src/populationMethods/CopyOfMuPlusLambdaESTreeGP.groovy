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
            new SinFunc(),
            new ifGreaterThan(),
            new ifGreaterThan()
        ]
        def setOfTerminals = [
            1,
            -1,
            2,
            -2,
            'e.getDistance()',
            'getX()',
            'getY()',
            'e.getVelocity()'
        ]

        numChildren.times {
            individualArr.add(
                [treeGP.generateRandomTree(setOfFunctions, setOfTerminals, 5, "growf"),
                    treeGP.generateRandomTree(setOfFunctions, setOfTerminals, 5, "growf"),
                    treeGP.generateRandomTree(setOfFunctions, setOfTerminals, 5, "growf"),
                    treeGP.generateRandomTree(setOfFunctions, setOfTerminals, 5, "growf")
                    ],
                    [])
            individualArr[1] =
                    [
                        'id' : individualCount,
                        'enemy_energy' : individualArr[0][0].toString(),
                        'my_energy' :  individualArr[0][1].toString(),
                        'angle_diff' :  individualArr[0][2].toString(),
                        'distance' :  individualArr[0][3].toString()
                    ]
        }


        def best = individualArr[0][0]
        def robotBuilder = new RobotBuilder("templates/HawkOnFireOS.template")
        def battleRunner = new BattleRunner("templates/battle.template")
        
        individualArr.each{ robot -> robotBuilder.buildJarFile(robot[1][0])}        
        individualArr.each{ robot -> battleRunner.buildBattleFile(robot[1][0].id)}
        
        def bestQuality = battleRunner.runBattle(individualArr[0][1].id)
        

        while(!treeGP.terminate(best, bestQuality)) {
            for (individual in individualArr) {
                if (treeGP.evaluateTree(individual,problemParings,['x']) < bestQuality) {
                    best = individual
                    bestQuality =  treeGP.evaluateTree(best, problemParings,['x'])
                }
            }

            individualArr = individualArr.sort{treeGP.evaluateTree(it,problemParings,['x'])}[0..<numParents]//.reverse()[0..<numParents]

            for (i in 0..<numParents) {
                //				println("the parents evaluated to : " + treeGP.evaluateTree(individualArr.get(i), problemParings,['x']))
                for (j in 0..<(numChildren / numParents)) {
                    def treeToTweak = treeGP.crossoverTrees(individualArr.get(randomParent.nextInt(numParents)), individualArr.get(randomParent.nextInt(numParents)))
                    individualArr.add(treeToTweak)
                }
            }
        }
        return best
    }

    String toString() {
        "MuPlusLambdaES_" + numParents + "_" + numChildren + "_" + numTweaks
    }
}

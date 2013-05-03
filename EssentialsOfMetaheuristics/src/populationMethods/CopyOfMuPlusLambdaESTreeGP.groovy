package populationMethods
import java.lang.annotation.Target;

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
			//'point_value' : arr[0].toString(),
			'extra_angle' :  arr[0].toString()
//			'enemy_energy' : arr[1].toString(),
//			'angle_diff' :  arr[2].toString(),
//			'distance' :  arr[3].toString()
		]
	}
	def maximize(treeGP){
		this.treeGP = treeGP
		def individualArr = []
		def setOfFunctions = [
			new Add(),
			new SinFunc(),
			new Subtract(),
			new ifGreaterThan(),
			new ifLessThan(),
			new Multiply(),
			new MinFunc(),
			new Divide(),
			new MaxFunc(),
			new AbsFunc(),
			new CosFunc(),
			new Add(),
			new SinFunc(),
			new Subtract(),
			new ifGreaterThan(),
			new ifLessThan(),
			new Multiply(),
			new MinFunc(),
			new Divide(),
			new MaxFunc(),
			new AbsFunc(),
			new CosFunc(),
			new Add(),
			new SinFunc(),
			new Subtract(),
			new ifGreaterThan(),
			new ifLessThan(),
			new Multiply(),
			new MinFunc(),
			new Divide(),
			new MaxFunc(),
			new AbsFunc(),
			new CosFunc(),
			new Add(),
			new SinFunc(),
			new Subtract(),
			new ifGreaterThan(),
			new ifLessThan(),
			new Multiply(),
			new MinFunc(),
			new Divide(),
			new MaxFunc(),
			new AbsFunc(),
			new CosFunc()
		]
		def setOfTerminals = [
			0,
			randomParent.nextDouble()*10,
			randomParent.nextGaussian(),
			2*randomParent.nextDouble()-1,
			'distanceToTarget',
                        'distanceToTarget',
                        'myEnergy',
                        'myEnergy',
                        'getGunTurnRemaining()',
                        'getGunTurnRemaining()',
                        'calcAngle(target.pos, myPos)',
                        'calcAngle(target.pos, myPos)',
                        'target.velocity',
                        'target.velocity',
                        'target.energy',
                        'target.energy',
                        'target.bearing',
                        'target.bearing',
			0,
			randomParent.nextDouble()*10,
			randomParent.nextGaussian(),
			2*randomParent.nextDouble()-1
			
		]

		println("making the initial individuals")
		def tmpNode
		numChildren.times {
			tmpNode = [
				[
					treeGP.generateRandomTree(setOfFunctions, setOfTerminals, 4, "growf"),
//					treeGP.generateRandomTree(setOfFunctions, setOfTerminals, 4, "growf"),
//					treeGP.generateRandomTree(setOfFunctions, setOfTerminals, 4, "growf"),
//					treeGP.generateRandomTree(setOfFunctions, setOfTerminals, 4, "growf"),
					//treeGP.generateRandomTree(setOfFunctions, setOfTerminals, 5, "growf")
				],
				[:],
				0
			]
			//            println("gonna def the hashmap")
			tmpNode[1] = defineHashMap(tmpNode[0])
			//            println("the hashmap be lookin like " + defineHashMap(tmpNode[0]))
			//            println("hashmaps class be lookin like " + tmpNode[1].class)
			individualArr.add(tmpNode)
		}

		//println("finding the best")

		def best = individualArr[0][0]
		def bestId = 0
		def robotBuilder = new RobotBuilder("templates/HawkOnFireOS.template")
		def battleRunner = new BattleRunner("templates/battle.template")
		//println("building the jar files")
		individualArr.each{ robot ->
			//println("the class is " + robot[1].class)
			//println("gonna make the jar file")
			robotBuilder.buildJarFile(robot[1])
			//println("gonna build the battle file")
			//battleRunner.buildBattleFile(robot[1].id)
		}

		//println("running the battle!")
		//battleRunner.buildBattleFile(individualArr[0][1].id)
		//individualArr[0][2] = battleRunner.runBattle(individualArr[0][1].id)
		//println("settin the bestQuality!")
		def bestQuality = 0//individualArr[0][2]


		while(!treeGP.terminate(best, bestQuality)) {
			println("Current Best is robot ${bestId} with score ${bestQuality}" )
			for (individual in individualArr) {
				battleRunner.buildBattleFile(individual[1].id)
				individual[2] = battleRunner.runBattle(individual[1].id)
				println("The score for "+individual[1].id + " was: " + individual[2])
				if (individual[2]> bestQuality) {
					best = individual
					bestId = individual[1].id
					bestQuality =  individual[2]
				}
			}
			//println("ran the battles, gonna sort the dudes. Best quality is "+best)
			individualArr = individualArr.sort{it[2]}.reverse()[0..<numParents]
			//println("finished sorting them booya.")// individualArr[0][2]= "+individualArr[0][2]+" and should be: "+bestQuality)
			for (i in 0..<numParents) {
				//				println("the parents evaluated to : " + treeGP.evaluateTree(individualArr.get(i), problemParings,['x']))
				for (j in 0..<(numChildren / numParents)) {
					def treeToTweak =[[], [:], 0]
					1.times{
						treeToTweak[0].add(treeGP.crossoverTrees(individualArr.get(randomParent.nextInt(numParents))[0][0], individualArr.get(randomParent.nextInt(numParents))[0][0]))
					}
					//treeToTweak[1] = defineHashMap(treeToTweak[0])
					individualArr.add(treeToTweak)
				}
			}

			individualArr.each{individual ->
				//println("the id before defHashMap: " + individual[1].id)
				individual[1] = defineHashMap(individual[0])
				//println("the id after defHashMap: " + individual[1].id)
				robotBuilder.buildJarFile(individual[1])
			}
		}

		return best
	}

	String toString() {
		"MuPlusLambdaES_" + numParents + "_" + numChildren + "_" + numTweaks
	}
}

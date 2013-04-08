package populationMethods
import GeneticProgramming.*

class CopyOfMuPlusLambdaESTreeGP {
	Integer numParents = 2
	Integer numChildren = 6
	Integer numTweaks = 1
	def randomParent = new Random()
	def treeGP
	//def problemParings = [0: 20, 0.5 : 37.5, 1: 50, 1.5: 57.5, 2 : 60, 2.5 : 57.5, 3 : 50, 3.5 : 37.5, 4 : 20, 4.5 : -02.5, 5: -30]
	def problemParings = [0: 2, 0.5 : 3.75, 1: 5.0, 1.5: 5.75, 2 : 6.0, 2.5 : 5.75, 3 : 5.0, 3.5 : 3.75, 4 : 2.0, 4.5 : -0.25, 5: -3]
//	def problemParings =[0: 0.0,
//					1:08.414709848078965,
//					2:09.092974268256817,
//					3:01.411200080598672,
//					4:-07.568024953079282,
//					5:-09.589242746631385,
//					6:-02.7941549819892586,
//					7:06.569865987187891,
//					8:09.893582466233818,
//					9:04.121184852417566,
//					10:-05.440211108893698]
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
                        new SinFunc()
		]
		def setOfTerminals = [1, -1, 2, -2, 'e.getDistance()', 'getX()', 'getY()', 'e.getVelocity()', ]

		numChildren.times {
			individualArr.add(treeGP.generateRandomTree(setOfFunctions, setOfTerminals, 5, "growf"))
		}


		def best = individualArr[0]
		def bestQuality = treeGP.evaluateTree(best, problemParings,['x'])

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

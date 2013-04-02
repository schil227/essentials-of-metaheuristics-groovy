package populationMethods
import GeneticProgramming.*

class CopyOfMuPlusLambdaESTreeGP {
	Integer numParents = 2
	Integer numChildren = 6
	Integer numTweaks = 1
	def randomParent = new Random()
	def treeGP
	//def problemParings = [0: 20, 0.5 : 37.5, 1: 50, 1.5: 57.5, 2 : 60, 2.5 : 57.5, 3 : 50, 3.5 : 37.5, 4 : 20, 4.5 : -02.5, 5: -30]
	def problemPairings = [0: 2, 0.5 : 3.75, 1: 5.0, 1.5: 5.75, 2 : 6.0, 2.5 : 5.75, 3 : 5.0, 3.5 : 3.75, 4 : 2.0, 4.5 : -0.25, 5: -3]
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
			new Divide()
		]
		def setOfTerminals = [1, -1, 2, -2, 'x', 'x', 'x']

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

package populationMethods
import GeneticProgramming.*

class CopyOfMuPlusLambdaESTreeGP {
	Integer numParents = 2
	Integer numChildren = 6
	Integer numTweaks = 1
	def randomParent = new Random()
	def treeGP = new TreeGP()
	def problemParings = [0: 2, 0.5 : 3.75, 1: 5, 1.5: 5.75, 2 : 6, 2.5 : 5.75, 3 : 5, 3.5 : 3.75, 4 : 2, 4.5 : -0.25, 5: -3]
	def maximize(){
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
		def setOfTerminals = [1, 2, 3, 4, 5, 'x']

		numChildren.times {
			individualArr.add(treeGP.generateRandomTree(setOfFunctions, setOfTerminals, 10, "growd"))
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

			individualArr = individualArr.sort{treeGP.evaluateTree(it,problemParings,['x'])}.reverse()[0..<numParents]

			for (i in 0..<numParents) {
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

package populationMethods
import problems.OnesMax

class MuPlusLambdaES {
	Integer numParents = 2
	Integer numChildren = 6
	Integer numTweaks = 1
	def maximize(problem){
		def individualArr = []

		numChildren.times {
			individualArr.add(problem.random())
		}


		def best = individualArr[0]
		def bestQuality = problem.quality(best)

		while(!problem.terminate(best, bestQuality)) {
			for (individual in individualArr) {
				if (problem.quality(individual) > bestQuality) {
					best = individual
					bestQuality = problem.quality(best)
				}
			}

                        individualArr = individualArr.sort{problem.quality(it)}.reverse()[0..<numParents]
                        
			for (i in 0..<numParents) {
				for (j in 0..<(numChildren / numParents)) {
					def problemToTweak = problem.copy(individualArr.get(i))
					numTweaks.times {
						problemToTweak = problem.tweak(problemToTweak)
					}
					individualArr.add(problemToTweak)
				}
			}

		}
		return best
	}

	String toString() {
		"MuPlusLambdaES_" + numParents + "_" + numChildren + "_" + numTweaks
	}
}

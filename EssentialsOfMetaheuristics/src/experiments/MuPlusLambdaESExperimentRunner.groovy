package experiments

import GeneticProgramming.TreeGP
import populationMethods.CopyOfMuPlusLambdaESTreeGP
import populationMethods.MuPlusLambdaES
import problems.HIFF
import problems.LeadingOnes
import problems.LeadingOnesBlocks
import problems.OnesMax
import problems.Trap
import singleStateMethods.HillClimber
import singleStateMethods.SteepestAscentHillClimber
import singleStateMethods.SteepestAscentHillClimberWithReplacement

class MuPlusLambdaESExperimentRunner {
	
	static runExperiment(searchers, problems, numRuns = 30) {
		println("starting runs")
		for (p in problems) {
			for (s in searchers) {
				for (i in 0..<numRuns) {
					p.evalCount = 0
					def result = s.maximize(p)
					// the problem pairings model the function -x^2 + 4x + 2
					 //= [0: 2, 0.5 : 3.75, 1: 5.0, 1.5: 5.75, 2 : 6.0, 2.5 : 5.75, 3 : 5.0, 3.5 : 3.75, 4 : 2.0, 4.5 : -0.25, 5: -3]
					def problemPairings =[0: 0.0,
					1:08.414709848078965,
					2:09.092974268256817,
					3:01.411200080598672,
					4:-07.568024953079282,
					5:-09.589242746631385,
					6:-02.7941549819892586,
					7:06.569865987187891,
					8:09.893582466233818,
					9:04.121184852417566,
					10:-05.440211108893698]
					def variables = ['x']
//					p.toggleOutputCompare()
//					p.evaluateTree(result, problemPairings,variables)
//					p.toggleOutputCompare()
					println "${s.toString()}\t${p.toString()}\t${p.evaluateTree(result, problemPairings,variables )}"
				}
			}
		}
	}

	static main(args) {
		def searchers = [
			new CopyOfMuPlusLambdaESTreeGP(numParents : 30, numChildren : 60),
			//			new MuPlusLambdaES(numParents : 2, numChildren : 6, numTweaks: 2),
			//			new MuPlusLambdaES(numParents : 2, numChildren : 6, numTweaks: 3),
			//			new MuPlusLambdaES(numParents : 2, numChildren : 6, numTweaks: 4),
			//			new MuPlusLambdaES(numParents : 2, numChildren : 6, numTweaks: 5),
			//			new MuPlusLambdaES(numParents : 2, numChildren : 6, numTweaks: 6),
		]
		def problems = [
			new TreeGP(maxIterations : 50000)
			//new OnesMax(numBits : 1000, maxIterations : 1000),
			//new OnesMax(numBits : 100, maxIterations : 1000),
			//new OnesMax(numBits : 1000, maxIterations : 1000),
		]
		// It would be nice to collect the total time here and include it in the
		// output.
		runExperiment(searchers, problems)
	}
}

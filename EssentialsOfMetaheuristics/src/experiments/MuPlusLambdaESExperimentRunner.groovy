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
	
	static runExperiment(searchers, problems, numRuns = 10) {
		println("starting runs")
		for (p in problems) {
			for (s in searchers) {
				for (i in 0..<numRuns) {
					p.evalCount = 0
					def result = s.maximize(p)
					println "${s.toString()}\t${p.toString()}\t${result[2]}"
				}
			}
		}
	}

	static main(args) {
		def searchers = [
			new CopyOfMuPlusLambdaESTreeGP(numParents : 4, numChildren : 12),

		]
		def problems = [
			new TreeGP(maxIterations : 1000)

		]
		// It would be nice to collect the total time here and include it in the
		// output.
		runExperiment(searchers, problems)
	}
}

package applications

import GeneticProgramming.AbsFunc
import GeneticProgramming.Add
import GeneticProgramming.CosFunc
import GeneticProgramming.Divide
import GeneticProgramming.MaxFunc
import GeneticProgramming.MinFunc
import GeneticProgramming.Multiply
import GeneticProgramming.SinFunc
import GeneticProgramming.Subtract
/*
 * enemy_energy : the coefficient for the enemy's energy
 * my_energy : the coefficient for our energy
 * angle_diff : the coefficient for the different in angles between us and the point and then and the point
 * distance : the coefficient for the distance between the point and the enemy
 */

class RoboCodeProblem {
    Integer individualCount = 0
    Integer evalCount = 0
    Random random = new Random()
    public static final STDEV = 10
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
    def setOfTerminals = [
        1,
        -1,
        2,
        -2,
        'getX()',
        'getY()',
        'getEnergy()',
        'getHeading()',
        'getBattlefieldHeight()',
        'getBattlefieldWidth()',
        'e.getEnergy()',
        'e.getDistance()',
        'e.getVelocity()',
        'e.getBearing()',
        'e.getHeading()',       
    ]
    def random() {
        ++individualCount
        [ 'id' : individualCount,
                    'enemy_energy' : random.nextGaussian()*STDEV,
                    'my_energy' : random.nextGaussian()*STDEV,
                    'angle_diff' : random.nextGaussian()*STDEV,
                    'distance' : random.nextGaussian()*STDEV ]
    }

    def copy(individual) {
        return individual.clone()
    }

    def tweak(individual) {
        return individual
    }

    def quality(individual) {
        ++evalCount
        return 0
    }

    def terminate(bestIndividual, bestQuality) {
        evalCount > 100
    }
}

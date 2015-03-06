package bi.ria.camp.scorer.rf


import _root_.bi.ria.camp.scorer.helper.UnitSparkSpec
import bi.ria.camp.scorer.{Evaluator, DataLoader}
import bi.ria.camp.scorer.tree_ensemble.RandomForestScorer

import org.scalatest._


/**
 * Created by domingos on 1/29/15.
 */
class RandomForestScorerSpec extends UnitSparkSpec with Matchers {


  "The RandomForest classifier" should "process the Adult UCI dataset with accuracy" in {

    // the following parameters have been optimized to the given input

    val scorer = new RandomForestScorer(sc, 2, 100, 15)

    val (training, testing) = new DataLoader(sc).loadLibSVMFile("src/test/resources/libsvmInput1.txt")

    scorer.train(training)

    val score = new Evaluator().evaluateAndCalculateAUC(scorer.evaluate(testing))

    // due to the non-deterministic nature of the Random Forest its impossible to guarantee deterministic results.
    // hopefully the follow will hold for any practical purpose :)

    assert(score >= 0.8 * 0.85)

  }

    "The RandomForest classifier" should "process the gene selection dataset with accuracy" in {

      // the following parameters have been optimized to the given input
      val scorer = new RandomForestScorer(sc, 2, 150, 15)

      val (training, testing) = new DataLoader(sc).loadLibSVMFile("src/test/resources/libsvmInput3.txt")

      scorer.train(training)

      val score = new Evaluator().evaluateAndCalculateAUC(scorer.evaluate(testing))

      // due to the non-deterministic nature of the Random Forest its impossible to guarantee deterministic results.
      // hopefully the follow will hold for any practical purpose :)

      assert(score >= 0.8 * 0.85)
  }

}
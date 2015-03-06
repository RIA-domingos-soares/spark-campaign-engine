package bi.ria.camp.scorer.svm

import _root_.bi.ria.camp.scorer.helper.UnitSparkSpec
import bi.ria.camp.scorer.{Evaluator, DataLoader}
import org.scalatest._

/**
 * Created by domingos on 1/29/15.
 */
class SVMScorerSpec extends UnitSparkSpec with Matchers {


  "The SVM classifier" should "process the Adult UCI dataset with accuracy" in {

    val scorer = new SVMScorer(sc, 200, 0.2)

    val (training, testing) = new DataLoader(sc).splitLoadLibSVMFile("src/test/resources/libsvmInput1.txt", 0.7)

    scorer.train(training)

    val score = new Evaluator().evaluateAndCalculateAUC(scorer.evaluate(testing))

    assert(score >= 0.65 * 0.85)

  }

  "The SVM classifier" should "process the gene selection dataset with accuracy" in {

    val scorer = new SVMScorer(sc, 200, 0.2)

    val (training, testing) = new DataLoader(sc).splitLoadLibSVMFile("src/test/resources/libsvmInput5.txt", 0.7)

    scorer.train(training)

    val score = new Evaluator().evaluateAndCalculateAUC(scorer.evaluate(testing))

    assert(score >= 0.65 * 0.85)
  }

}
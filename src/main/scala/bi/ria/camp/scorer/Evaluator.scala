package bi.ria.camp.scorer

import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.rdd.RDD

/**
 * Created by domingos on 3/6/15.
 */
class Evaluator {

  def evaluateAndCalculateAUC(predictedData: RDD[(Double, Double)]): Double = {

    new BinaryClassificationMetrics(predictedData).areaUnderROC()

  }

}

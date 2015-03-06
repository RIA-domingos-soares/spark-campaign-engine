package bi.ria.camp.scorer.svm

/**
 * Created by domingos on 2/25/15.
 */

import bi.ria.camp.scorer.classification.svm.ClassificationScorer
import org.apache.spark.SparkContext
import org.apache.spark.mllib.classification.{ClassificationModel, SVMModel, SVMWithSGD}
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics
import org.apache.spark.mllib.optimization.L1Updater
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.rdd.RDD

class SVMScorer(sparkContext: SparkContext, numIterations: Int, regularization: Double) extends ClassificationScorer {

  override var model: ClassificationModel = _

  def train(trainingData: RDD[LabeledPoint]) = {

    val svm = new SVMWithSGD()

    svm.optimizer.
      setNumIterations(numIterations).
      setRegParam(regularization).
      setUpdater(new L1Updater)

     model = svm.run(trainingData)
  }

  def evaluate(testData: RDD[LabeledPoint]): RDD[(Double, Double)] = {

    model.asInstanceOf[SVMModel].clearThreshold()

    testData.map {
      point => val prediction = model.predict(point.features)
        (point.label, prediction)
    }

  }

}

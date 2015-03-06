package bi.ria.camp.scorer.tree_ensemble

/**
 * Created by domingos on 2/25/15.
 */

import org.apache.spark.SparkContext
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.tree.RandomForest
import org.apache.spark.mllib.tree.model.RandomForestModel
import org.apache.spark.rdd.RDD

class RandomForestScorer(sparkContext: SparkContext, numClasses: Int, numTrees: Int, maxDepth: Int) extends TreeEnsembleScorer {

  type T = RandomForestModel

  override var model: RandomForestModel = _

  private val categoricalFeaturesInfo = Map[Int, Int]()

  private val featureSubsetStrategy = "auto"

  private val impurity = "gini"

  private val maxBins = 32

  def train(trainingData: RDD[LabeledPoint]) {

    model = RandomForest.trainClassifier(trainingData, numClasses, categoricalFeaturesInfo,
      numTrees, featureSubsetStrategy, impurity, maxDepth, maxBins)
  }

  def evaluate(testData: RDD[LabeledPoint]): RDD[(Double, Double)] = {

    testData.map {

      point => val prediction = model.predict(point.features)
        (point.label, prediction)

    }

  }



}

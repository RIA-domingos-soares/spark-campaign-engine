package bi.ria.camp.scorer.tree_ensemble

/**
 * Created by domingos on 2/25/15.
 */

import org.apache.spark.SparkContext
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.tree.{GradientBoostedTrees, RandomForest}
import org.apache.spark.mllib.tree.configuration.BoostingStrategy
import org.apache.spark.mllib.tree.model.{GradientBoostedTreesModel, RandomForestModel}
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.tree.configuration.Strategy


// TODO finish the current implementation. work in progress
class GradientBoostedTreesScorer(sparkContext: SparkContext, numClasses: Int, numIterations: Int, maxDepth: Int) extends TreeEnsembleScorer {

   type T = GradientBoostedTreesModel

  override var model: GradientBoostedTreesModel = _

/*
  val boostingStrategy = BoostingStrategy.defaultParams("Classification")

  boostingStrategy.numIterations = 3

  boostingStrategy.treeStrategy.numClassesForClassification = 2

  boostingStrategy.treeStrategy.maxDepth = 5

  boostingStrategy.treeStrategy.categoricalFeaturesInfo = Map[Int, Int]()



  val boostingStrategy = BoostingStrategy
    .defaultParams("Classification")
    .setNumIterations(numIterations)
*/


   def train(trainingData: RDD[LabeledPoint]) {

     //model = GradientBoostedTrees.train(trainingData, boostingStrategy)

   }

   def evaluate(testData: RDD[LabeledPoint]): RDD[(Double, Double)] = {

     testData.map {

       point => val prediction = model.predict(point.features)
         (point.label, prediction)

     }

   }



 }

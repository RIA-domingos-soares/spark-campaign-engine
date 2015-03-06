package bi.ria.camp.scorer

import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.rdd.RDD


/**
  * Created by domingos on 3/5/15.
  */
abstract class Scorer extends Serializable {
   type T
   var model : T

   def train(trainingData: RDD[LabeledPoint])

   def evaluate(testData: RDD[LabeledPoint]): RDD[(Double, Double)]


 }

package bi.ria.camp.scorer

import org.apache.spark.SparkContext
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.rdd.RDD

/**
 * Created by domingos on 3/5/15.
 */
class DataLoader(sparkContext: SparkContext) {

  def loadLibSVMFile(datasetPath: String): RDD[LabeledPoint] = {

    return MLUtils.loadLibSVMFile(sparkContext, datasetPath)

  }

  def splitLoadLibSVMFile(datasetPath: String, trainingFraction: Double): (RDD[LabeledPoint], RDD[LabeledPoint]) = {

    if (trainingFraction < 0.0 || trainingFraction > 1.0) {

      throw new IllegalArgumentException("Training dataset size must be a natural fraction of 1.0")
    }

    val splits = loadLibSVMFile(datasetPath: String).randomSplit(Array(trainingFraction, 1.0 - trainingFraction))

    return (splits(0), splits(1))

  }

}

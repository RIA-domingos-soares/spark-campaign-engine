package bi.ria.camp.scorer

import org.apache.spark.SparkContext
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.rdd.RDD

/**
 * Created by domingos on 3/5/15.
 */
class DataLoader(sparkContext: SparkContext) {

  def loadLibSVMFile(datasetPath: String) : (RDD[LabeledPoint], RDD[LabeledPoint]) = {

    val data = MLUtils.loadLibSVMFile(sparkContext, datasetPath)

    val splits = data.randomSplit(Array(0.7, 0.3))

    return (splits(0), splits(1))


  }

}

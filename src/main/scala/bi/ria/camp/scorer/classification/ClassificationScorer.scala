package bi.ria.camp.scorer.classification.svm

import bi.ria.camp.scorer.Scorer
import org.apache.spark.mllib.classification.{ ClassificationModel}

/**
 * Created by domingos on 3/5/15.
 */
abstract class ClassificationScorer extends Scorer {

  type T = ClassificationModel

}

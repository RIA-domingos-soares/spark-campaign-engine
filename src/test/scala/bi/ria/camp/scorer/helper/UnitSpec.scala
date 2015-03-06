package bi.ria.camp.scorer.helper

import _root_.io.netty.util.internal.logging.{InternalLoggerFactory, Slf4JLoggerFactory}
import org.apache.spark._
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, Suite, _}

abstract class UnitSpec extends FlatSpec with Matchers

abstract class UnitSparkSpec extends FlatSpec with Matchers with LocalSparkContext

/** Manages a local `sc` {@link SparkContext} variable, correctly stopping it after each test. */
trait LocalSparkContext extends BeforeAndAfterEach with BeforeAndAfterAll { self: Suite =>

  @transient var sc: SparkContext = _

  override def beforeAll() {
    sc = new SparkContext( "local", "test" )
    InternalLoggerFactory.setDefaultFactory( new Slf4JLoggerFactory() )
    super.beforeAll()
  }

  override def afterAll() {
    resetSparkContext()
    super.afterEach()
  }

  def resetSparkContext() = {
    LocalSparkContext.stop( sc )
    sc = null
  }
}

object LocalSparkContext {
  def stop( sc: SparkContext ) {
    if ( sc != null ) {
      sc.stop()
    }
    // To avoid Akka rebinding to the same port, since it doesn't unbind immediately on shutdown
    System.clearProperty( "spark.driver.port" )
  }

  /** Runs `f` by passing in `sc` and ensures that `sc` is stopped. */
  def withSpark[T]( sc: SparkContext )( f: SparkContext => T ) = {
    try {
      f( sc )
    } finally {
      stop( sc )
    }
  }

}

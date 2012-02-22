import org.scalatest.FunSuite
import scala.util.Random

class RunSuite extends FunSuite {
  test ("This actually runs?") {}

  test ("Basic test") {
    assert(Searcher.search((0 to 20).toArray, 3, scala.math.Ordering.Int) match {
      case Some(3) => true
      case _ => false
    })
  }

  test ("More testing") {
    assert(Searcher.search((0 to 23).toArray, 6, scala.math.Ordering.Int) match {
      case Some(6) => true
      case _ => false
    })
  }

  test ("Another weird test") {
    assert(Searcher.search((0 to 29).toArray, 8, scala.math.Ordering.Int) match {
      case Some(8) => true
      case _ => false
    })
  }

  test ("Fuzzing") {
    val rand = new Random()
    for (x <- 0 until 20) {
      val x = rand.nextInt(20) + 20
      val matchee = rand.nextInt(x)
      assert(Searcher.search((0 to x).toArray, matchee, scala.math.Ordering.Int) match {
        case Some(matchee) => true
        case _ => false
      })
    }
  }

}

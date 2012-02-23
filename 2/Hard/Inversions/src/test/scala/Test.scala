import org.scalatest.FunSuite
import scala.util.Random
import scala.util.Sorting
import org.naka.Inversions.Sorter

class Test extends FunSuite{
  test ("Does this work??") {}

  test("This actually eats sorts?") {
    val sortee = new Array[Int](40)
    for (x <- 20 until 0 by -1) {
      sortee(x * 2 - 1) = x
      sortee(x * 2 - 2) = x
    }
    val tmp = Sorter.inversions(sortee, scala.math.Ordering.Int)
    assert(tmp == 0)
  }

  test("This actually works?") {
    val sortee = new Array[Int](40)
    for (x <- 0 until 20) {
      sortee((20 - x) * 2 - 1) = x
      sortee((20 - x) * 2 - 2) = x
    }
    val tmp = Sorter.inversions(sortee, scala.math.Ordering.Int)
    assert(tmp == 760)
  }

  test("All inversions") {
    val sortee = new Array[Int](40)
    for (x <- 0 until 40) {
      sortee(39 - x) = x
    }
    val tmp = Sorter.inversions(sortee, scala.math.Ordering.Int)
    assert(tmp == 780)
  }

  /*  It would be nice to have a fuzzing test for this, not sure how it would work. */

}

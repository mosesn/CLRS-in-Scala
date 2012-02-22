import org.scalatest.FunSuite
import scala.collection.mutable.Stack
import scala.util.Sorting
import scala.util.Random

class RunSuite extends FunSuite {
  test("This actually runs?") {}

  test("This actually eats traversables?") {
    new ISSeq[Int](0)
  }

  test("This actually eats values?") {
    val sortee = new ISSeq[Int](20)
    for (x <- 0 until 20) {
      sortee += x
    }
    val tmp = sortee.insertionSort(scala.math.Ordering.Int)
    assert(sortee sameElements tmp)
  }

  test("This actually eats sorts?") {
    val sortee = new ISSeq[Int](20)
    for (x <- 20 until 0 by -1) {
      sortee += x
      sortee += x
    }
    val tmp = sortee.insertionSort(scala.math.Ordering.Int)
    assert(sortee.reverse sameElements tmp)
  }

  test("fuzzing test") {
    for (x <- 0 until 100) {
      val rand = new Random()
      val sortee = new ISSeq[Int](20)
      val array = new Array[Int](20)
      for (x <- 0 until 20) {
        val tmp = rand.nextInt(100)
        array(x) = tmp
        sortee += tmp
      }
      Sorting.quickSort(array)
      assert(array sameElements sortee.insertionSort(scala.math.Ordering.Int))
    }
  }

}

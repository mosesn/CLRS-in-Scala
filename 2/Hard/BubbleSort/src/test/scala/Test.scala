import org.scalatest.FunSuite
import scala.util.Random
import scala.util.Sorting

class Test extends FunSuite{
  test ("Does this work??") {}

  test("This actually eats sorts?") {
    val sortee = new Array[Int](40)
    for (x <- 20 until 0 by -1) {
      sortee(x * 2 - 1) = x
      sortee(x * 2 - 2) = x
    }
    val tmp = Sorter.sort(sortee.toList, scala.math.Ordering.Int)
    Sorting.quickSort(sortee)
    assert(sortee.toList sameElements tmp)
  }

  test("fuzzing test") {
    val rand = new Random()
    for (x <- 0 until 100) {
      val array = new Array[Int](20)
      for (x <- 0 until 20) {
        array(x) = rand.nextInt(100)
      }

      val arr = array.clone
      Sorting.quickSort(arr)
      assert(arr.toList sameElements Sorter.sort(array.toList, scala.math.Ordering.Int))
    }
  }

}

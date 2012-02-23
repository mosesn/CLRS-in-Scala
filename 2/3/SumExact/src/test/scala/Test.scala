import org.scalatest.FunSuite
import scala.util.Random

class Test extends FunSuite {
  test("does this actually run?") {}

  test("Very simple array.") {
    val arr = Array(3, 7)
    val value = 10
    assert(SumExact.isThereExactSum(arr, value))
    assert(SumExact.getIndices(arr, value).get == Tuple2(0, 1))
  }

  test("Fuzzing") {
    val rand = new Random()
    for (iter <- 0 until 1000) {
      val arr = new Array[Int](100)
      for (x <- 0 until 100) {
        arr(x) = rand.nextInt(1000)
      }

      val first = rand.nextInt(100)
      val second = differentRandom(rand, first)
      val value = arr(first) + arr(second)

      assert(SumExact.isThereExactSum(arr, value))
      val results = SumExact.getIndices(arr, value).get
      assert(value == arr(results._1) + arr(results._2))
    }
  }

  private[this] def differentRandom(rand :Random, diff: Int): Int = {
    var x = diff
    while (x == diff) {
      x = rand.nextInt(100)
    }
    x
  }


}

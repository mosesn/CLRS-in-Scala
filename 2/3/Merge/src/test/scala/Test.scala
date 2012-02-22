import org.scalatest.FunSuite

class Test extends FunSuite {
  test("Does this actually work?") {}

  test("Merge very basic arrays.") {
    val arr = Merger.merge(Array(1, 3, 5), Array(2, 4, 6), scala.math.Ordering.Int)
    assert(arr sameElements Array(1, 2, 3, 4, 5, 6))
  }

  test("Fuzzing") {
    import scala.util.Random

    for (i <- 0 to 1000) {
      val rand = new Random
      var max = 0
      var counter = 0
      var fPtr = 0
      var sPtr = 0
      val j = rand.nextInt(100) + 10
      val k = rand.nextInt(100) + 10
      val first = new Array[Int](j)
      val second = new Array[Int](k)
      val compare = new Array[Int](j + k)
      while (fPtr < j && sPtr < k) {
        if (rand.nextBoolean()) {
          max = max + rand.nextInt(2)
          compare(counter) = max
          first(fPtr) = max
          fPtr += 1
        }
        else {
          max = max + rand.nextInt(2)
          compare(counter) = max
          second(sPtr) = max
          sPtr += 1
        }
        counter += 1
      }

      if (fPtr  < j) {
        while (fPtr < j) {
          compare(counter) = max
          first(fPtr) = max
          fPtr += 1
          counter += 1
        }
      }
      else {
        while (sPtr < k) {
          compare(counter) = max
          second(sPtr) = max
          sPtr += 1
          counter += 1
        }
      }
      val arr = Merger.merge(first, second, scala.math.Ordering.Int)
      assert(compare sameElements arr)
    }
  }
}

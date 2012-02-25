import org.scalatest.Spec
import org.bruteforce.BruteForce
import scala.util.Random

class MaximizerSpec  extends Spec {
  describe("maximum subarray finder") {
    it("should run, at least."){}

    it("should work on this very simple array."){
      assert(Maximizer.subarray(Array(10, 3, 2)) === Tuple2(0, 2))
    }

    it("should work when I fuzz it.") {
      val rand = new Random()
      for (x <- 0 until 1000) {
        val arr = new Array[Int](rand.nextInt(80) + 20)
        for (index <- 0 until arr.length){
          arr(index) = rand.nextInt(200) - 100
        }
        val first = Maximizer.subarray(arr)
        val second = BruteForce.subarray(arr)
        assert(arr(first._1) - arr(first._2) === arr(second._1) - arr(second._2))
      }
    }
  }
}

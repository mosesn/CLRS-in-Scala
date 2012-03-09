import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec
import scala.util.Random

class ShufflerSpec extends Spec with ShouldMatchers {
  describe("Shuffler") {
    describe("shuffle") {
      it("should at least run"){}

      it("should take an array of some length and give back an array of the same length") {
        val rand = new Random()
        val len = rand.nextInt(1000 + 1)
        val arr = new Array[Int](len)
        for (x <- 0 until len) {
          arr(x) = rand.nextInt(10000)
        }
        Shuffler.shuffle(arr).toSet should be (arr.toSet)
      }
    }

  }
}

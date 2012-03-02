import org.scalatest.Spec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import scalala.tensor.dense.DenseMatrix
import scala.util.Random

@RunWith(classOf[JUnitRunner])
class MatrixSpec extends Spec {
  describe("Matrix multiplier") {
    it("should do nothing properly.") ()
    it("should multiply two easy matrices together.") ({
      val x = DenseMatrix((1, 1), (2, 3))
      val y = DenseMatrix((7, 3), (-3, 6))
      assert(Multiplier.SquareMatrices(x, y) === x * y)
    })
    it("should be able to handle fuzzing multiplications.") ({
      val rand = new Random()
      for (i <- 0 until 1000) {
        val size = rand.nextInt(20)
        val (first, second) = (DenseMatrix.randi(100, size, size), DenseMatrix.randi(100, size, size))
        assert(Multiplier.SquareMatrices(first, second) === first * second)
      }
      
    })
  }
}

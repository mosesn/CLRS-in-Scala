import org.scalatest.Spec
import scala.math.pow
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import scalala.tensor.dense.DenseMatrix
import scala.util.Random

@RunWith(classOf[JUnitRunner])
class MatrixSpec extends Spec {
  describe("SquareNaiveMatrix") {
    it("should do nothing properly.") ()
    it("should multiply two easy matrices together.") ({
      val x = DenseMatrix((1, 1), (2, 3))
      val y = DenseMatrix((7, 3), (-3, 6))
      assert(Multiplier.squareMatrices(x, y) === x * y)
    })
    
    it("should be able to handle fuzzing multiplications for powers of two.") ({
      val rand = new Random()
      for (i <- 0 until 1000) {
        val size = rand.nextInt(2) + 3
        val powSize = pow(2, size).toInt
        val (first, second) = (DenseMatrix.randi(100, powSize, powSize),
        	                   DenseMatrix.randi(100, powSize, powSize))
        assert(Multiplier.squareMatrices(first, second) === first * second)
      }
      
    })
    it("should be able to handle fuzzing multiplications.") ({
      val rand = new Random()
      for (i <- 0 until 1000) {
        val size = rand.nextInt(20) + 1
        val (first, second) = (DenseMatrix.randi(25, size, size),
        	                   DenseMatrix.randi(25, size, size))
        assert(Multiplier.squareMatrices(first, second) === first * second)
      }
      
    })
  }
}

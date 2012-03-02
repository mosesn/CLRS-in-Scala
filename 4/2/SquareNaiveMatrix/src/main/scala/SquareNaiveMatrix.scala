import scalala.tensor.dense.DenseMatrix
import scalala.tensor.::

object Multiplier {
  def SquareMatrices(first: DenseMatrix[Int], second: DenseMatrix[Int]) : DenseMatrix[Int] = {
    if (!validMatrices(first, second))
      throw new IllegalArgumentException("Arguments are not square matrices of the same size.")
    val size = first.numRows
    val ret = DenseMatrix.zeros[Int](size, size)
    for (x <- 0 until size) {
      for (y <- 0 until size) {
        ret(x, y) = first(x, ::) * second(::, y) 
      }
    }
    ret
  }
  
  private def validMatrices(first: DenseMatrix[Int], second: DenseMatrix[Int]): Boolean = {
    first.isSquare && second.isSquare && second.numRows == first.numRows
  }
}
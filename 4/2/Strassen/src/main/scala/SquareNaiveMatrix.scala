import scalala.tensor.dense.DenseMatrix
import scalala.tensor.::
import scalala.tensor.mutable.Matrix


object Multiplier {
  def squareMatrices(first: Matrix[Int], second: Matrix[Int]) : Matrix[Int] = {
    if (!validMatrices(first, second)) 
      throw new IllegalArgumentException("Arguments are not two square matrices of the same size.")
    if (first.numCols == 1) {
      first * second
    }
    else {
      val size = if ((first.numCols & 1) == 0) first.numCols else first.numCols - 1
//      val size = first.numCols
      val half = size / 2
      val ret = DenseMatrix.zeros[Int](first.numCols, first.numCols)
      val f11 = first(0 until half, 0 until half)
      val f12 = first(0 until half, half until size)
      val f21 = first(half until size, 0 until half)
      val f22 = first(half until size, half until size)    
      val s11 = second(0 until half, 0 until half)
      val s12 = second(0 until half, half until size)
      val s21 = second(half until size, 0 until half)
      val s22 = second(half until size, half until size)    
      
      val r1 = s12 - s22
      val r2 = f11 + f12
      val r3 = f21 + f22
      val r4 = s21 - s11
      val r5 = f11 + f22
      val r6 = s11 + s22
      val r7 = f12 - f22
      val r8 = s21 + s22
      val r9 = f11 - f21
      val r10 = s11 + s12
      
      val p1 = squareMatrices(f11, r1)
      val p2 = squareMatrices(r2, s22)
      val p3 = squareMatrices(r3, s11)
      val p4 = squareMatrices(f22, r4)
      val p5 = squareMatrices(r5, r6)
      val p6 = squareMatrices(r7, r8)
      val p7 = squareMatrices(r9, r10)
      
      ret(0 until half, 0 until half) := p5 + p4 - p2 + p6
      ret(0 until half, half until size) := p1 + p2
      ret(half until size, 0 until half) := p3 + p4
      ret(half until size, half until size) := p5 + p1 - p3 - p7
      
      if (size < first.numCols) {
        ret(0 until size, 0 until size) := first(0 until size, size) * second(size, 0 until size) +
                                           ret(0 until size, 0 until size)
        for (i <- 0 until size) {
          ret(size, i) = first(size, ::) * second(::, i)
          ret(i, size) = first(i, ::) * second(::, size)
        }
        ret(size, size) = first(size, ::) * second(::, size)
      }
      ret
    }
  }
  
  private[this] def validMatrices(first: Matrix[Int], second: Matrix[Int]): Boolean = {
    first.isSquare && second.isSquare && second.numRows == first.numRows
  }
}
object Polynomials {
  def naiveComputer(coeffs: Array[Int], variable: Int) : Int = {
    (0 /: (0 until coeffs.length).toList) ((x: Int, y: Int) =>
      coeffs(y) * power(variable, y) + x
    )
  }

  private[this] def power(variable: Int, exp: Int) : Int = {
    exp match {
      case 0 => 1
      case _ => variable * power(variable, exp - 1)
    }
  }

  def hornersComputer(coeffs: Array[Int], variable: Int) : Int = {
    coeffs reduceRight ((x, y) => x + variable * y)
  }
}

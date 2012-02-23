import org.scalatest.FunSuite
import scala.util.Random

class Test extends FunSuite {
  test("Does this even work") {}

  test("Very simple test") {
    val first = Polynomials.naiveComputer(Array(3, 2), 2)
    val second = Polynomials.hornersComputer(Array(3, 2), 2)
    assert(first == 7)
    assert(second == 7)
  }

  test("Fuzzing") {
    val rand = new Random()
    for (i <- 0 until 1000) {
      val arr = new Array[Int](3)
      val value = rand.nextInt(4) + 1

      var sum = 0
      for (i <- 0 until 3) {
        arr(i) = rand.nextInt(20)
        sum += power(value, i) * arr(i)
      }

      val first = Polynomials.naiveComputer(arr, value)
      val second = Polynomials.hornersComputer(arr, value)
      assert(first == sum)
      assert(second == sum)
    }
  }

  private[this] def power(variable: Int, exp: Int) : Int = {
    exp match {
      case 0 => 1
      case _ => variable * power(variable, exp - 1)
    }
  }

}

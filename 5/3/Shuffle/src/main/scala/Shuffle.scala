import scala.util.Random

object Shuffler {
  def shuffle[T](arr: Array[T]): Array[T] = {
    val rand = new Random()
    val ret = arr.clone()
    for (x <- 0 until ret.length) {
      val tmp = ret(x)
      val y = rand.nextInt(x + 1)
      ret(x) = ret(y)
      ret(y) = tmp
    }
    ret
  }
}

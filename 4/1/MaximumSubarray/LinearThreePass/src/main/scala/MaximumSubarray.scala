
/**
 * Maximizer maximizes.
 */

object Maximizer {

  /**
   * Finds the maximal subarray.
   *
   * Finds the biggest difference between two elements in the array
   * where the second one comes after the first.
   */
  def subarray(arr: Array[Int]) : Tuple2[Int, Int] = {
    val length = arr.length
    val mins = new Array[Int](length)
    val maxes = new Array[Int](length)
    var max = 0
    var min = arr.length - 1
    for (x <- (length - 1) to 0 by -1) {
      if (arr(x) < arr(min)) {
        min = x
      }
      mins(x) = min
    }

    for (x <- 0 until length) {
      if (arr(x) > arr(max)) {
        max = x
      }
      maxes(x) = max
    }

    (maxes zip mins) reduce {(x, y) =>
      if (arr(x._1) - arr(x._2) < arr(y._1) - arr(y._2)) {
        y
      }
      else {
        x
      }
                           }
  }
}

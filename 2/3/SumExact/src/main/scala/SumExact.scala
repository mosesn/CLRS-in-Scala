import org.naka.MergeSort.Sorter

object SumExact {
  def isThereExactSum(arr: Array[Int], value: Int): Boolean = getIndices(arr, value).isDefined

  def getIndices(arr: Array[Int], value: Int): Option[Tuple2[Int, Int]] = {
    val sortedArray = Sorter.sort(arr, scala.math.Ordering.Int)
    var second = arr.length - 1
    for (first <- 0 to second) {
      while (sortedArray(second) + sortedArray(first) > value) {
        second -= 1
      }
      if (sortedArray(second) + sortedArray(first) == value && first != second) {
        return Some(findDifferent(arr, sortedArray(first), sortedArray(second)))
      }
    }
    None
  }

  private[this] def findDifferent(arr: Array[Int],
                                  first: Int,
                                  second: Int) : Tuple2[Int, Int] = {
    var fPtr = -1
    var sPtr = -1
    for (x <- 0 until arr.length) {
      if (fPtr == -1 && arr(x) == first) {
        fPtr = x
      }
      if (sPtr == -1 && arr(x) == second && fPtr != x) {
        sPtr = x
      }
    }
    Tuple2(fPtr, sPtr)
  }
}

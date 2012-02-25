object Maximizer {
  def subarray(arr: Array[Int]) : Tuple2[Int, Int] = {
    var maxTup = Tuple2(0, 0)
    var max = 0

    for (x <- 0 until arr.length) {
      if (arr(x) > arr(max)) {
        max = x
      }
      if (evalTuple(arr, maxTup) < evalPair(arr, max, x)) {
        maxTup = Tuple2(max, x)
      }
    }
    maxTup
  }

  private[this] def evalPair(arr: Array[Int], first: Int, second: Int): Int ={
    arr(first) - arr(second)
  }

  private[this] def evalTuple(arr: Array[Int], tup: Tuple2[Int, Int]): Int ={
    evalPair(arr, tup._1, tup._2)
  }
}

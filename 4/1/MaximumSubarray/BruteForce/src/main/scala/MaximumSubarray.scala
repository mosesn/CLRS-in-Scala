package org.bruteforce

object BruteForce {
  def subarray(arr: Array[Int]) : Tuple2[Int, Int] = {
    var maxTuple = Tuple2(0, 0)
    for (max <- 0 until arr.length - 1) {
      for (min <- max + 1 until arr.length) {
        if (evalPair(arr, max, min) > evalTuple(arr, maxTuple)) {
          maxTuple = Tuple2(max, min)
        }
      }
    }
    maxTuple
  }

  private[this] def evalTuple(arr: Array[Int], tup: Tuple2[Int, Int]): Int = {
    evalPair(arr, tup._1, tup._2)
  }

  private[this] def evalPair(arr: Array[Int], first: Int, second: Int): Int = {
    arr(first) - arr(second)
  }
}

object Maximizer {
  def subarray(arr: Array[Int]): Tuple2[Int, Int] = {
    subarrayPointers(arr, 0, arr.length)._1
  }

  private[this] def subarrayPointers(arr: Array[Int], first: Int, second: Int): Tuple2[Tuple2[Int, Int], Tuple2[Int, Int]] = {
    if (first + 1 == second) {
      Tuple2(Tuple2(first, first), Tuple2(first, first))
    }
    else {
      mergeTuples(arr,
                  subarrayPointers(arr, first, (first + second) / 2),
                  subarrayPointers(arr, (first + second) / 2, second))
    }
  }

  private[this] def evalPair(arr: Array[Int], first: Int, second: Int): Int = {
    arr(first) - arr(second)
  }

  private[this] def evalTuple(arr: Array[Int], tup: Tuple2[Int, Int]): Int = {
    evalPair(arr, tup._1, tup._2)
  }

  private[this] def mergeTuples(arr: Array[Int],
                                first: Tuple2[Tuple2[Int, Int], Tuple2[Int, Int]],
                                second: Tuple2[Tuple2[Int, Int], Tuple2[Int, Int]]):
  Tuple2[Tuple2[Int, Int], Tuple2[Int, Int]] = {
    Tuple2(argMax(arr, argMax(arr, first._1, second._1), Tuple2(first._2._2, second._2._1)),
           Tuple2(uniArgMin(arr, first._2._1, second._2._1), uniArgMax(arr, first._2._2, second._2._2)))
  }

  private[this] def uniArgMin(arr: Array[Int], first: Int, second: Int) : Int = {
    if (arr(first) < arr(second)) {
      first
    }
    else {
      second
    }
  }

  private[this] def uniArgMax(arr: Array[Int], first: Int, second: Int) : Int = {
    if (arr(first) > arr(second)) {
      first
    }
    else {
      second
    }
  }

  private[this] def argMax(arr: Array[Int],
                           first: Tuple2[Int, Int],
                           second: Tuple2[Int, Int]): Tuple2[Int, Int] = {
    if (evalTuple(arr, first) > evalTuple(arr, second)) {
      first
    }
    else {
      second
    }
  }

}

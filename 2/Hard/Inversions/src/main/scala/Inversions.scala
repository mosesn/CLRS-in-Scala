package org.naka.Inversions {
  object Sorter {
    def inversions[A] (arr: Array[A], order: Ordering[A]) (implicit manifest: Manifest[A]): Int = {
      mergeSort(arr, order, 0, arr.length)._2
    }

    private[this] def mergeSort[A] (arr: Array[A], order: Ordering[A],
                                    begin: Int, end: Int) (implicit manifest: Manifest[A]): Tuple2[Array[A], Int] = {
      if (begin == end - 1) {
        return Tuple2(Array(arr(begin)), 0)
      }
      val first = mergeSort(arr, order, begin, (begin + end) / 2)
      val second = mergeSort(arr, order, (begin + end) / 2, end)
      val result = merge(first._1, second._1, order)
      Tuple2(result._1, result._2 + second._2 + first._2)
    }

    private[this] def merge[A] (first: Array[A], second: Array[A],
                                order: Ordering[A])(implicit manifest:
                                                    Manifest[A]) : Tuple2[Array[A], Int] = {
                                  val output = new Array[A](first.length + second.length)
                                  mergeMutate(first, second, order, MergeState(0, 0, 0, 0, 0), output)
                                }

    private[this] def mergeMutate[A] (first: Array[A], second: Array[A], order: Ordering[A],
                                      curState: MergeState[A], output: Array[A]): Tuple2[Array[A], Int] = {
      if (curState.oPtr == output.length) {
        Tuple2(output, curState.sumInv)
      }
      else {
        if (curState.fPtr < first.length && curState.sPtr < second.length) {
          if (order.lteq(first(curState.fPtr), second(curState.sPtr))) {
            nextFirst(first, second, order, curState, output)
          }
          else {
            nextSecond(first, second, order, curState, output)
          }
        }
          else {
            if (curState.fPtr < first.length) {
              nextFirst(first, second, order, curState, output)
            }
            else {
              nextSecond(first, second, order, curState, output)
            }
          }
      }
    }

    private[this] def nextSecond[A](first: Array[A], second: Array[A], order: Ordering[A],
                                    curState: MergeState[A], output: Array[A]): Tuple2[Array[A], Int] = {
      output(curState.oPtr) = second(curState.sPtr)
      mergeMutate(first, second, order, curState.second(), output)
    }

    private[this] def nextFirst[A](first: Array[A], second: Array[A], order: Ordering[A],
                                   curState: MergeState[A], output: Array[A]): Tuple2[Array[A], Int] = {
      output(curState.oPtr) = first(curState.fPtr)
      mergeMutate(first, second, order, curState.first(), output)
    }

    private[this] case class MergeState[A](fPtr: Int, sPtr: Int, oPtr: Int, inv: Int, sumInv: Int) {
      def first() : MergeState[A] = MergeState(fPtr + 1, sPtr, oPtr + 1, inv, sumInv + inv)
      def second() : MergeState[A] = MergeState(fPtr, sPtr + 1, oPtr + 1, inv + 1, sumInv)
    }
  }
}

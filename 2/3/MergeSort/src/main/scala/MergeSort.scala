package org.naka.MergeSort {
  object Sorter {
    def sort[A] (arr: Array[A], order: Ordering[A]) (implicit manifest: Manifest[A]): Array[A] = {
      mergeSort(arr, order, 0, arr.length)
    }

    private[this] def mergeSort[A] (arr: Array[A], order: Ordering[A],
                                    begin: Int, end: Int) (implicit manifest: Manifest[A]): Array[A] = {
      if (begin == end - 1) {
        return Array(arr(begin))
      }
      merge(mergeSort(arr, order, begin, (begin + end) / 2),
            mergeSort(arr, order, (begin + end) / 2, end), order)
    }

    private[this] def merge[A] (first: Array[A], second: Array[A],
                                order: Ordering[A])(implicit manifest:
                                                    Manifest[A]) : Array[A] = {
                                  val output = new Array[A](first.length + second.length)
                                  mergeMutate(first, second, order, MergeState(0, 0, 0), output)
                                }

    private[this] def mergeMutate[A] (first: Array[A], second: Array[A], order: Ordering[A],
                                      curState: MergeState[A], output: Array[A]): Array[A] = {
      if (curState.oPtr == output.length) {
        output
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
                                    curState: MergeState[A], output: Array[A]): Array[A] = {
      output(curState.oPtr) = second(curState.sPtr)
      mergeMutate(first, second, order, curState.second(), output)
    }

    private[this] def nextFirst[A](first: Array[A], second: Array[A], order: Ordering[A],
                                   curState: MergeState[A], output: Array[A]): Array[A] = {
      output(curState.oPtr) = first(curState.fPtr)
      mergeMutate(first, second, order, curState.first(), output)
    }

    private[this] case class MergeState[A](fPtr: Int, sPtr: Int, oPtr: Int) {
      def first() : MergeState[A] = MergeState(fPtr + 1, sPtr, oPtr + 1)
      def second() : MergeState[A] = MergeState(fPtr, sPtr + 1, oPtr + 1)
    }
  }
}

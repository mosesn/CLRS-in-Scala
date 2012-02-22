object Searcher {
  def search[T](arr: Array[T], searchee: T, order: Ordering[T]): Option[Int] = {
    searchByIndex(arr, searchee, 0, arr.length, order)
  }

  private[this] def searchByIndex[T](arr: Array[T], searchee: T, start: Int,
                                     end: Int, order: Ordering[T]): Option[Int] = {
    if (start == end) {
      return None
    }

    val mid = (end + start) / 2

    if (order.gt(arr(mid), searchee)) {
      searchByIndex(arr, searchee, start, mid, order)
    }
    else if (order.lt(arr(mid), searchee)) {
      searchByIndex(arr, searchee, mid + 1, end, order)
    }
    else {
      Some(mid)
    }
  }
}

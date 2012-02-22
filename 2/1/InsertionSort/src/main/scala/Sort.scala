import scala.collection.mutable.ArrayBuffer
import scala.util.Random

trait InsertionSorter[U] extends Iterable[U] {
  def insertionSort(order: Ordering[U]): Iterable[U] = insertionSort(Nil, this, order)

  private def insertionSort(curSorted: Iterable[U],
                            unSorted: Iterable[U],
                          order: Ordering[U]) :Iterable[U] = {
    if (!unSorted.isEmpty) {
      insertionSort(insert(unSorted.head, curSorted, order), unSorted.tail, order)
    }
    else {
      curSorted
    }
  }

  private def insert(newElt: U,
                     sorted: Iterable[U],
                     order: Ordering[U]): Iterable[U] = {
    val tup = sorted.span(order.lteq(_, newElt))
    tup._1 ++ List(newElt) ++ tup._2
  }
}

class ISSeq[U](size: Int) extends IndexedSeq[U] with InsertionSorter[U] {
  var inner = new ArrayBuffer[U](size)

  def apply(pos: Int): U = {
    inner(pos)
  }

  def +=(elt: U) = (inner = inner += elt)

  def update(pos: Int, elt: U) = inner.update(pos, elt)

  def length = inner.length
}

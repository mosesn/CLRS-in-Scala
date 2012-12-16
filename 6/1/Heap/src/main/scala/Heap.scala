import scala.collection.mutable
import scala.annotation.tailrec

case class Heap[A : Ordering](underlying: IndexedSeq[A]) extends Iterable[A] {
  import Heap.heapy

  override def iterator: Iterator[A] = underlying.iterator

  lazy val max: A = underlying.head

  lazy val extractMax: (A, Heap[A]) = (max, Heap(
    (underlying.last +: underlying.tail.init).maxHeapify(0)
  ))

  def +(elt: A): Heap[A] = Heap(underlying.insert(elt))

  def increaseKey(old: A, cur: A): Heap[A] = Heap(underlying.updateKey(old, cur))

  def heapSort: IndexedSeq[A] = underlying.heapSort(underlying.size - 1)

  override def newBuilder: mutable.Builder[A, Heap[A]] = Heap.builder
}

object Heap {
  private[Heap] case class HeapySeq[A : Ordering](seq: IndexedSeq[A]) {
    val o: Ordering[A] = implicitly[Ordering[A]]

    def heapSort(idx: Int): IndexedSeq[A] =
      if (idx == 0) seq else (swap(0, idx).maxHeapify(0)).heapSort(idx - 1)

    def updateKey(old: A, cur: A): IndexedSeq[A] = {
      require(o.lteq(old, cur))
      val pos = seq.indexOf(old)
      seq.updated(pos, cur).increasedKey(pos)
    }

    def insert(item: A): IndexedSeq[A] = (seq :+ item).increasedKey(seq.size)

    private[HeapySeq] def increasedKey(pos: Int): IndexedSeq[A] = {
      def parentPos(pos: Int): Int = pos / 2

      def parent(pos: Int): A = seq(parentPos(pos))

      if (pos == 0)
        seq
      else
        if (o.lteq(parent(pos), seq(pos)))
          swap(parentPos(pos), pos).increasedKey(parentPos(pos))
        else
          seq
    }

    private[this] def swap(first: Int, second: Int) =
      seq.updated(second, seq(first)).updated(first, seq(second))

    def maxHeapify(pos: Int): IndexedSeq[A] = {
      def get(pos: Int): Option[A] = if (pos <= seq.size) Some(seq(pos)) else None

      def left(pos: Int): Option[A] = get(leftPos(pos))

      def leftPos(pos: Int): Int = pos * 2

      def leftMaxHeapify(pos: Int): IndexedSeq[A] = {
        def swapLeft(pos: Int): IndexedSeq[A] = swap(pos, leftPos(pos))
        swapLeft(pos).maxHeapify(leftPos(pos))
      }

      def right(pos: Int): Option[A] = get(rightPos(pos))

      def rightPos(pos: Int): Int = pos * 2 + 1

      def rightMaxHeapify(pos: Int): IndexedSeq[A] = {
        def swapRight(pos: Int): IndexedSeq[A] = swap(pos, rightPos(pos))
        swapRight(pos).maxHeapify(rightPos(pos))
      }

      (left(pos), right(pos)) match {
        case (Some(left), Some(right)) => {
          if (o.lt(seq(pos), o.max(left, right)))
            if (o.lt(left, right))
              rightMaxHeapify(pos)
            else
              leftMaxHeapify(pos)
          else
            seq
        }
        case (None, Some(right)) =>
          throw new IllegalStateException("Can't have a right child without a left one")
        case (Some(left), None) => {
          if (o.lt(seq(pos), left))
            leftMaxHeapify(pos)
          else
            seq
        }
        case (None, None) => seq
      }
    }
  }

  implicit def heapy[A : Ordering](seq: IndexedSeq[A]): HeapySeq[A] = HeapySeq(seq)

  def builder[A : Ordering]: mutable.Builder[A, Heap[A]] = new mutable.Builder[A, Heap[A]] {
    val buf = mutable.Buffer[A]()

    override def +=(elt: A): this.type = {
      buf += elt
      this
    }

    override def result(): Heap[A] = {
      val idxSeq: IndexedSeq[A] = buf.toIndexedSeq
      Heap((idxSeq.size/2 to 0 by -1).foldRight(idxSeq)((cur, acc) =>
        acc.maxHeapify(cur)).toIndexedSeq)
    }

    override def clear() {
      buf.clear()
      this
    }
  }
}

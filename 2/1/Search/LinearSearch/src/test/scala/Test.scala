import org.scalatest.FunSuite

class RunSuite extends FunSuite {
  test ("This actually runs?") {}

  test ("Basic test") {
    assert(Searcher.search((0 to 20).toArray, 3) match {
      case Some(3) => true
      case _ => false
    })
  }

  test ("Works with non-integers") {
    val array  = ((0 to 20) zip (20 to 0 by -1)).toArray
    assert(Searcher.search(array, Tuple2[Int, Int](2, 18)) match {
      case Some(2) => true
      case _ => false
    })
  }


}

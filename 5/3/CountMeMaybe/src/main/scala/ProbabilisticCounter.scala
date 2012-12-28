import scala.util.Random

case class ProbabilisticCounter(counter: Int) {
  require(counter >= 0)

  lazy val incremented: ProbabilisticCounter = copy(counter = increment(counter))

  lazy val decremented: ProbabilisticCounter = copy(counter = decrement(counter))

  lazy val doubled: ProbabilisticCounter = copy(counter = counter + 1)

  lazy val halved: ProbabilisticCounter = copy(counter = counter - 1)

  private[this] def increment(counter: Int): Int =
    counter + (if (Random.nextDouble() > (1.0 / math.pow(2, counter))) 0 else 1)

  private[this] def decrement(counter: Int): Int =
    counter + (if (Random.nextDouble() > (1.0 / math.pow(2, counter))) 0 else -1)

  lazy val value: BigInt = BigInt(2).pow(counter)
}
package salesman_problem.logic
import scala.collection.mutable
import scala.util.Random

private[logic] case class Individual[T](
    val rawItems: Seq[T], evalFunction: EvaluationFunction[T]) {
  val score = evalFunction.score(this)

  def size: Int = rawItems.size
  def apply(ind: Int): T = rawItems(ind)

  // swap
  def mutation(pos1: Int, pos2: Int): Individual[T] = Individual(
    (0 until size)
      .map(i =>
        if(i < pos1 || i >= pos2) i
        else pos2 - i + pos1 - 1)
      .map(i => rawItems(i)),
      evalFunction
  )

  def mutation: Individual[T] = {
    val len = size
    val pos1 = Random.nextInt(len)
    val pos2 = Random.nextInt(len)
    mutation(pos1, pos2)
  }
}

private[logic] object Individual {
  def crossOverOne[T](
      parent1: Individual[T], parent2: Individual[T], fromLeft: Int, untilRight: Int):
      Individual[T] = {
      val len = parent1.size
      var parentIndex = fromLeft
      val rawChild = mutable.Map[Int, T]()

      (fromLeft until len + fromLeft).foreach(_i => {
        val i = _i % len
        if (i >= fromLeft && i < untilRight) {
          rawChild(i) = parent2.rawItems(i)
        }
        else {
          while(rawChild.values.toSeq.contains(parent1(parentIndex))) {
            parentIndex = (parentIndex + 1) % len
          }
          rawChild(i) = parent1(parentIndex)
        }
        parentIndex = (parentIndex + 1) % len
      })

      Individual(
        for(i <- 0 until len) yield rawChild(i),
          parent1.evalFunction
      )
    }

  def crossOver[T](
      parent1: Individual[T], parent2: Individual[T], fromLeft: Int, untilRight: Int):
      (Individual[T], Individual[T]) =
      (
        crossOverOne[T](parent1, parent2, fromLeft, untilRight),
        crossOverOne[T](parent2, parent1, fromLeft, untilRight),
      )

  def crossOver[T](
      parent1: Individual[T], parent2: Individual[T]):
      (Individual[T], Individual[T]) = {
    val len = parent1.size
    val fromLeft = Random.nextInt(len)
    val untilRight = Random.nextInt(len)
    (
      crossOverOne[T](parent1, parent2, fromLeft, untilRight),
      crossOverOne[T](parent2, parent1, fromLeft, untilRight),
    )
  }
}

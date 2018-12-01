package salesman_problem.logic
import scala.collection.mutable

case class Individual[T](val rawItems: Seq[T]) {
  def size: Int = rawItems.size
  def apply(ind: Int): T = rawItems(ind)
}

object Individual {
  private[logic] def crossOverOne[T](
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
        for(i <- 0 until len) yield rawChild(i)
      )
    }
}

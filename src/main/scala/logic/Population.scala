package salesman_problem.logic

import scala.util.Random

case class Population[T](initIndividual: Individual[T]) {
  val size = 100
  private val individuals = for(i <- (0 until size)) yield
    Individual(initIndividual.rawItems.sortBy(_ => Random.nextInt))
}

package salesman_problem.logic

import scala.util.Random

case class Population[T](initIndividual: Individual[T]) {
  val size = 100
  val numberOfEliteSelection = 10

  private var individuals = for(i <- (0 until size)) yield
    Individual(initIndividual.rawItems.sortBy(_ => Random.nextInt))

  private def eliteSelection: Seq[Individual[T]] = individuals.take(numberOfEliteSelection)
}

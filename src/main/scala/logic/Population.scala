package salesman_problem.logic

import scala.util.Random

case class Population[T](initIndividual: Individual[T], evalFunction: EvaluationFunction[T]) {
  val size = 100
  val numberOfEliteSelection = 10
  val numberOfSelectedBySelection = 10

  private var individuals = for(i <- (0 until size)) yield
    Individual(initIndividual.rawItems.sortBy(_ => Random.nextInt))

  private def eliteSelection: Seq[Individual[T]] = individuals.take(numberOfEliteSelection)

  private def selectNicer: Individual[T] =
    Random.shuffle((0 until initIndividual.size).toList).take(10)
      .map(i => individuals(i))
      .minBy(x => evalFunction.score(x.rawItems))
}

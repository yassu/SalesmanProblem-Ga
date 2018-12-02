package salesman_problem.logic

import scala.util.Random

case class Population[T](initIndividual: Individual[T], evalFunction: EvaluationFunction[T]) {
  val size = 100
  val numberOfSelectedBySelection = 10
  val numberOfEliteSelection = 10
  val numberOfMutation = 10
  val numberOfCrossingOver = (size - numberOfEliteSelection - numberOfMutation) / 2

  private var individuals = (for(i <- (0 until size)) yield
    Individual(initIndividual.rawItems.sortBy(_ => Random.nextInt)))
    .sortBy(x => evalFunction.score(x.rawItems))

  private def eliteSelection: Seq[Individual[T]] = individuals.take(numberOfEliteSelection)
  private def mutation: Seq[Individual[T]] = {
    (0 until numberOfMutation).map(i => {
      val parent = selectNicer
      parent.mutation
    })
  }
  private def crossOver: Seq[Individual[T]] =
    (0 until numberOfCrossingOver)
    .map(i => {
      val parent1 = selectNicer
      val parent2 = selectNicer
      val (child1, child2) = Individual.crossOver(parent1, parent2)
      Seq(child1, child2)
    })
    .flatMap(x => x)

  private def selectNicer: Individual[T] =
    Random.shuffle((0 until initIndividual.size).toList).take(10)
      .map(i => individuals(i))
      .minBy(x => evalFunction.score(x.rawItems))
}

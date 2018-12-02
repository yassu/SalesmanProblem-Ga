package salesman_problem.logic

import scala.util.Random

case class Population[T](initRawIndividual: Seq[T], evalFunction: EvaluationFunction[T]) {
  val initIndividual = Individual[T](initRawIndividual, evalFunction)
  val size = 100
  val numberOfSelectedBySelection = 10
  val numberOfEliteSelection = 10
  val numberOfMutation = 10
  val numberOfCrossingOver = (size - numberOfEliteSelection - numberOfMutation) / 2
  private var rawEvolvedCount: Int = 0

  private[logic] var individuals: Seq[Individual[T]] = (for(i <- (0 until size)) yield
    Individual(Random.shuffle(initIndividual.rawItems), evalFunction))
    .sortBy(x => x.score)

  lazy val bestIndividualSeq: Seq[T] = individuals.head.rawItems

  private[logic] def evolveOne(): Unit = {
    individuals = (eliteSelection ++: mutation ++: crossOver)
      .sortBy(x => x.score)
    rawEvolvedCount += 1
  }

  def evolve(count: Int): Unit =
    (0 until count).foreach(j => {
      evolveOne()
    })

  def evolvedCount: Int = rawEvolvedCount

  private def eliteSelection: Seq[Individual[T]] = individuals.take(numberOfEliteSelection)

  private def mutation: Seq[Individual[T]] =
    (0 until numberOfMutation).map(i => {
      val parent = selectNicer
      parent.mutation
    })

  private def crossOver: Seq[Individual[T]] =
    (0 until numberOfCrossingOver)
    .map(i => {
      val parent1 = selectNicer
      val parent2 = selectNicer
      val (child1, child2) = Individual.crossOver(parent1, parent2)
      Seq(child1, child2)
    })
    .flatMap(x => x).toSeq

  private def selectNicer: Individual[T] = {
    Random.shuffle((0 until initIndividual.size).toList).take(10)
      .map(i => individuals(i))
      .minBy(x => x.score)
  }
}

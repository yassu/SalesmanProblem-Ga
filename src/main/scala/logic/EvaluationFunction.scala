package salesman_problem.logic

trait EvaluationFunction[T] {
  def score(individual: Seq[T]): Double
  def score(individual: Individual[T]): Double =
    score(individual.rawItems)
}

private[logic] object TestEvalFunc extends EvaluationFunction[Int] {
  def score(individual: Seq[Int]): Double =
    (0 until individual.size - 1)
    .map(i => (individual(i) - individual(i + 1)).abs)
    .sum
}

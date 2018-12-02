package salesman_problem.logic

trait EvaluationFunction[T] {
  def score(individual: Seq[T]): Double
}

private[logic] object TestEvalFunc extends EvaluationFunction[Int] {
  def score(individual: Seq[Int]): Double =
    (0 until individual.size - 1)
    .map(i => (individual(i) - individual(i + 1)).abs)
    .sum
}

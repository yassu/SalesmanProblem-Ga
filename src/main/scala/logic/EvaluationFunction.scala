package salesman_problem.logic

trait EvaluationFunction[T] {
  def score[T](individual: Seq[T]): Double
}

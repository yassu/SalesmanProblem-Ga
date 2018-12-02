package salesman_problem

import scala.math

private[salesman_problem] object SalesmanEvaluationFunction extends logic.EvaluationFunction[(Int, Int)] {
  def score(points: Seq[(Int, Int)]): Double =
    (0 until points.size - 1)
    .map(i =>
      math.sqrt(
        (points(i)._1 - points(i + 1)._1) * (points(i)._1 - points(i + 1)._1) +
        (points(i)._2 - points(i + 1)._2) * (points(i)._2 - points(i + 1)._2))
    )
    .sum
}

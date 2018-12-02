package salesman_problem

import org.scalatest.FunSpec

class SalesmanEvaluationFunctionSpec extends FunSpec {
  private val PRECISION: Double = 0.0001

  describe("About score") {
    it("If 0 points") {
      assert(SalesmanEvaluationFunction.score(Seq[(Int, Int)]()) == 0)
    }

    it("Conplex test") {
      val points = Seq((1, 0), (4, -4), (9, 8))
      val score = SalesmanEvaluationFunction.score(points)
      val logicalScore = 18
      assert(score > logicalScore - PRECISION && score < logicalScore + PRECISION)
    }
  }
}

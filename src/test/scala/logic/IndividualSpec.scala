package salesman_problem.logic

import org.scalatest.FunSpec

class IndividualSpec extends FunSpec {
  describe("About Init") {
    it("Able to Init") {
      Seq(1, 2, 3)
      Individual[Int](Seq(1, 2, 3))
    }
  }
}

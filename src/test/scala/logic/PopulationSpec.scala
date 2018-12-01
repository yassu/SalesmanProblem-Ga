package salesman_problem.logic

import org.scalatest.FunSpec

class PopulationSpec extends FunSpec {
  describe("About Init") {
    it("Able to Init") {
      val individual = Individual[Int](Seq(8, 9, 10))
      Population(individual)
    }

    it("size") {
      val individual = Individual[Int](Seq(1, 2, 3))
      val population = Population(individual)
      assert(population.size == 100)
    }
  }
}


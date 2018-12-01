package salesman_problem.logic

import org.scalatest.FunSpec

class IndividualSpec extends FunSpec {
  describe("About Init") {
    it("Able to Init") {
      Seq(1, 2, 3)
      Individual[Int](Seq(1, 2, 3))
    }
  }

  describe("About CrossOverOne") {
    it("If parent1 == parent2 => child1 = parent1, child2 = parent2") {
      val parent1 = Individual[Int](Seq(1, 2, 3, 4, 5, 6))
      val parent2 = Individual[Int](Seq(1, 2, 3, 4, 5, 6))
      val child = Individual.crossOverOne[Int](parent1, parent2, 2, 4)
      assert(parent1 == child)
    }
    it("complex test") {
      val parent1 = Individual[Int](Seq(2, 3, 1, 5, 4))
      val parent2 = Individual[Int](Seq(2, 4, 3, 5, 1))
      val child = Individual.crossOverOne[Int](parent1, parent2, 2, 4)
      assert(child == Individual[Int](Seq(2, 1, 3, 5, 4)))
    }
  }

  describe("About CrossOver with fromLeft and untilRight parameters") {
    it("If parent1 == parent2 => child1 = parent1, child2 = parent2") {
      val parent1 = Individual[Int](Seq(1, 2, 3, 4, 5, 6))
      val parent2 = Individual[Int](Seq(1, 2, 3, 4, 5, 6))
      val (child1, child2) = Individual.crossOver[Int](parent1, parent2, 2, 4)
      assert(parent1 == child1 && parent2 == child2)
    }

    it("complex test") {
      val parent1 = Individual[Int](Seq(2, 3, 1, 5, 4))
      val parent2 = Individual[Int](Seq(2, 4, 3, 5, 1))
      val (child1, child2) = Individual.crossOver[Int](parent1, parent2, 2, 4)
      assert(child1 == Individual[Int](Seq(2, 1, 3, 5, 4)))
      assert(child2 == Individual[Int](Seq(4, 3, 1, 5, 2)))
    }
  }
}

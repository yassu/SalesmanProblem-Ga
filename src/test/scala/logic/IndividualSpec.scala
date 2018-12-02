package salesman_problem.logic

import org.scalatest.FunSpec

class IndividualSpec extends FunSpec {
  describe("About Init") {
    it("Able to Init") {
      Seq(1, 2, 3)
      Individual[Int](Seq(1, 2, 3), TestEvalFunc)
    }
  }

  describe("mutation") {
    it("with pos1 and pos2 parameter") {
      val individual = Individual[Int](Seq(0, 1, 2, 3, 4, 5, 6, 7, 8), TestEvalFunc)
      assert(individual.mutation(3, 7) == Individual[Int](Seq(0, 1, 2, 6, 5, 4, 3, 7, 8), TestEvalFunc))
    }

    it("with pos1 and pos2 parameter2") {
      val individual = Individual[Int](Seq(0, 1, 2, 3, 4, 5, 6, 7, 8), TestEvalFunc)
      assert(individual.mutation(2, 7) == Individual[Int](Seq(0, 1, 6, 5, 4, 3, 2, 7, 8), TestEvalFunc))
    }

    it("without parameter") {
      val individual = Individual[Int](Seq(1, 2, 3, 4, 5, 6), TestEvalFunc)
      assert(individual.mutation.rawItems.toSet == Set(1, 2, 3, 4, 5, 6))
    }
  }

  describe("About CrossOverOne") {
    it("If parent1 == parent2 => child1 = parent1, child2 = parent2") {
      val parent1 = Individual[Int](Seq(1, 2, 3, 4, 5, 6), TestEvalFunc)
      val parent2 = Individual[Int](Seq(1, 2, 3, 4, 5, 6), TestEvalFunc)
      val child = Individual.crossOverOne[Int](parent1, parent2, 2, 4)
      assert(parent1 == child)
    }
    it("complex test") {
      val parent1 = Individual[Int](Seq(2, 3, 1, 5, 4), TestEvalFunc)
      val parent2 = Individual[Int](Seq(2, 4, 3, 5, 1), TestEvalFunc)
      val child = Individual.crossOverOne[Int](parent1, parent2, 2, 4)
      assert(child == Individual[Int](Seq(2, 1, 3, 5, 4), TestEvalFunc))
    }
  }

  describe("About CrossOver with fromLeft and untilRight parameters") {
    it("If parent1 == parent2 => child1 = parent1, child2 = parent2") {
      val parent1 = Individual[Int](Seq(1, 2, 3, 4, 5, 6), TestEvalFunc)
      val parent2 = Individual[Int](Seq(1, 2, 3, 4, 5, 6), TestEvalFunc)
      val (child1, child2) = Individual.crossOver[Int](parent1, parent2, 2, 4)
      assert(parent1 == child1 && parent2 == child2)
    }

    it("complex test") {
      val parent1 = Individual[Int](Seq(2, 3, 1, 5, 4), TestEvalFunc)
      val parent2 = Individual[Int](Seq(2, 4, 3, 5, 1), TestEvalFunc)
      val (child1, child2) = Individual.crossOver[Int](parent1, parent2, 2, 4)
      assert(child1 == Individual[Int](Seq(2, 1, 3, 5, 4), TestEvalFunc))
      assert(child2 == Individual[Int](Seq(4, 3, 1, 5, 2), TestEvalFunc))
    }
  }

  describe("About CrossOver without fromLeft and untilRight parameters") {
    it("If parent1 == parent2 => child1 = parent1, child2 = parent2") {
      val parent1 = Individual[Int](Seq(1, 2, 3, 4, 5, 6), TestEvalFunc)
      val parent2 = Individual[Int](Seq(1, 2, 3, 4, 5, 6), TestEvalFunc)
      val (child1, child2) = Individual.crossOver[Int](parent1, parent2)
      assert(parent1 == child1 && parent2 == child2)
    }

    it("complex test") {
      val parent1 = Individual[Int](Seq(2, 3, 1, 5, 4), TestEvalFunc)
      val parent2 = Individual[Int](Seq(2, 4, 3, 5, 1), TestEvalFunc)
      val (child1, child2) = Individual.crossOver[Int](parent1, parent2)
      assert(child1.rawItems.toSet == Set(2, 1, 3, 5, 4))
      assert(child2.rawItems.toSet == Set(2, 1, 3, 5, 4))
    }
  }
}

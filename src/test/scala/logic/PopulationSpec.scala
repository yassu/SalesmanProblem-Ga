package salesman_problem.logic

import org.scalatest.FunSpec

class PopulationSpec extends FunSpec {
  describe("About Init") {
    it("Able to Init") {
      val individual = Individual[Int](Seq(8, 9, 10))
      Population(individual, TestEvalFunc)
    }

    it("size") {
      val individual = Individual[Int](Seq(1, 2, 3))
      val population = Population(individual, TestEvalFunc)
      assert(population.size == 100)
    }
  }

  describe("EvolveOne") {
    val individual = Individual[Int]((0 until 10).toSeq)
    val population = Population(individual, TestEvalFunc)

    it("size test") {
      population.evolveOne()
      assert(population.individuals.size == population.size)
    }

    it("change test") {
      val oldIndividuals = population.individuals
      population.evolveOne()
      val individuals = population.individuals
      assert(oldIndividuals != individuals)
    }

    it("order test") {
      (0 until 100).foreach(_ => {
          population.evolveOne()
          val individuals = population.individuals
          val firstScore = TestEvalFunc.score(population.individuals.head)
          val minScore = TestEvalFunc.score(
            population.individuals.minBy(x => TestEvalFunc.score(x)))
          assert(firstScore == minScore)
        }
      )
    }
  }
}


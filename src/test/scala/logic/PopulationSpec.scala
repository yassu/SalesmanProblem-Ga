package salesman_problem.logic

import org.scalatest.FunSpec

class PopulationSpec extends FunSpec {
  describe("About Init") {
    it("Able to Init") {
      val rawIndividual = Seq(8, 9, 10)
      Population(rawIndividual, TestEvalFunc)
    }

    it("size") {
      val rawIndividual = Seq(1, 2, 3)
      val population = Population(rawIndividual, TestEvalFunc)
      assert(population.size == 100)
    }
  }

  describe("EvolveOne") {
    val rawIndividual = (0 until 10).toSeq
    val population = Population(rawIndividual, TestEvalFunc)

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


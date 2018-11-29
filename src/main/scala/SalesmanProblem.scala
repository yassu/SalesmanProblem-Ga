package salesman_problem

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.control.{Spinner, Button, Label}
import scalafx.scene.Scene
import scalafx.scene.layout.{HBox, VBox}
// import scalafx.scene.paint.Color._   // 他の色を使うときに入れる
import scalafx.scene.canvas._
import scala.util.Random

object SalesmanProblem extends JFXApp {
  private val CANVAS_SIZE = 600
  private val canvas = new Canvas(CANVAS_SIZE, CANVAS_SIZE)
  private val gc = canvas.graphicsContext2D
  private var points = Seq[(Int, Int)]()

  val mainBox = new HBox {
    padding = Insets(20)
    gc.strokePolyline(Seq(
      (0, 0),
      (CANVAS_SIZE, CANVAS_SIZE),
      (0, CANVAS_SIZE),
      (CANVAS_SIZE, 0),
      (0, 0),
    ))

    children = Seq(
      canvas,
    )
  }

  val configBox = new HBox {
    val numberOfSamplesButton = new Button("Number Of Points:") {disable=false}
    val numberOfSamplesSpinner = new Spinner[Int](10, 100, 30, 5)
    numberOfSamplesSpinner.maxWidth = 70
    val numberOfSampleButton = new Button {
      text="Init"
      onAction = () => {
        gc.clearRect(0, 0, CANVAS_SIZE, CANVAS_SIZE)
        // TODO: 数値にできない場合どうするか考える
        val n = numberOfSamplesSpinner.value.value
        points = (0 until n).map(n => (Random.nextInt(CANVAS_SIZE), Random.nextInt(CANVAS_SIZE)))
          .toSeq
        points.foreach(p => gc.fillOval(p._1, p._2, 10, 10))
      }
    }

    val countOfEvalButton = new Button("Count Of Eval") {disable=false}
    val countOfEvalSpinner = new Spinner[Int](1000, 10000, 1000, 1000)
    countOfEvalSpinner.maxWidth = 90
    val runButton = new Button {
      text="Run"
      onAction = () => {
        println("Run Button is pressed.")
      }
    }
    val reRunButton = new Button {
      text="ReRun"
      onAction = () => {
        println("ReRun Button is pressed.")
      }
    }

    padding = Insets(20, 20, 0, 20)
    children = Seq(
      numberOfSamplesButton,
      numberOfSamplesSpinner,
      numberOfSampleButton,

      countOfEvalButton,
      countOfEvalSpinner,
      runButton,
      reRunButton,
    )
  }

  stage = new PrimaryStage {
    title = "SalesmanProblem"
    scene = new Scene {
      resizable=false
      content = new VBox {
        children=Seq(
          configBox,
          mainBox,
        )
      }
    }
  }
}


package salesman_problem

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.control.{Spinner, Button, Label}
import scalafx.scene.Scene
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.{HBox, VBox}
import scalafx.event.{ActionEvent}
import scalafx.scene.paint.Color._
import scalafx.scene.canvas._
import scalafx.scene.paint.{Stops, LinearGradient}
import scalafx.scene.text.Text
import scala.util.Random
import scalafx.scene.input.{KeyCodeCombination, KeyCode, KeyCombination}

object ScalaFXHelloWorld extends JFXApp {
  private val CANVAS_SIZE = 600
  private val canvas = new Canvas(CANVAS_SIZE, CANVAS_SIZE)
  private val gc = canvas.graphicsContext2D
  private var points = Seq[(Int, Int)]()

  val mainBox = new HBox {
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
    val numberOfSamplesLabel = new Label("Number Of Points:")
    val numberOfSamplesSpinner = new Spinner[Int](10, 100, 30, 5)
    numberOfSamplesSpinner.maxWidth = 100
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
    children = Seq(
      numberOfSamplesSpinner,
      numberOfSampleButton
    )
  }

  stage = new PrimaryStage {
    title = "Canvas"
    scene = new Scene {
      content = new VBox {
        children=Seq(
          configBox,
          // menuBar,
          mainBox,
        )
      }
    }
  }
}


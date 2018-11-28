package salesman_problem

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.control.{MenuBar, Menu, MenuItem, RadioMenuItem, ToggleGroup}
import scalafx.scene.Scene
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.{HBox, VBox}
import scalafx.event.{ActionEvent}
import scalafx.scene.paint.Color._
import scalafx.scene.canvas._
import scalafx.scene.paint.{Stops, LinearGradient}
import scalafx.scene.text.Text
import scalafx.scene.input.{KeyCodeCombination, KeyCode, KeyCombination}

object ScalaFXHelloWorld extends JFXApp {
  val canvas = new Canvas(600, 600)
  val gc = canvas.graphicsContext2D
  val mainBox = new HBox {
    gc.strokePolyline(Seq(
      (0, 0),
      (600, 600),
      (0, 600),
      (600, 0),
      (0, 0),
    ))

    children = Seq(
      canvas,
    )
  }

  val menuBar = {
    val _menuBar = new MenuBar

    // ラジオボタン
    val numberMenu = new Menu("Radio")
    val numberMenuItems =
      Seq(100, 500, 1000, 1500, 2000)
      .map(n => new RadioMenuItem(n.toString))
      .toList
    val group = new ToggleGroup
    group.toggles = numberMenuItems
    numberMenu.items = numberMenuItems
    // radioMenuが更新されたら gcを更新
    numberMenu.onAction = (e: ActionEvent) => {
      gc.clearRect(0, 0, 300, 300)
    }

    _menuBar.menus = List(numberMenu)
    _menuBar
  }


  stage = new PrimaryStage {
    title = "Canvas"
    scene = new Scene {
      content = new VBox {
        children=Seq(
          menuBar,
          mainBox,
        )
      }
    }
  }
}


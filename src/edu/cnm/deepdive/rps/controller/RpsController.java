package edu.cnm.deepdive.rps.controller;


import edu.cnm.deepdive.rps.model.Arena;
import edu.cnm.deepdive.rps.view.TerrainView;
import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;

public class RpsController {

  @FXML
  private Button reset;
  @FXML
  private ToggleButton toggleRun;
  @FXML
  private TerrainView terrainView;

  private Arena arena;

  @FXML
  private void initialize() {
    arena = new Arena.Builder()
        .setNumBreeds((byte) 5)
        .setArenaSize(30)
        .build();

    terrainView.setArena(arena);
    arena.init();
    terrainView.draw();

    AnimationTimer animationTimer = new AnimationTimer() {
      @Override
      public void handle(long now) {
        arena.advance(10);
        terrainView.draw();
      }
    };
    SimpleBooleanProperty toggled = new SimpleBooleanProperty();

    toggleRun.setOnAction(event -> {
      if (!toggled.getValue()) {
        animationTimer.start();
      } else {
        animationTimer.stop();
      }
      toggled.set(!toggled.get());
    });
  }


}

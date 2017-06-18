package cs3500.music.controller.commands;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ChrisRisley on 6/18/17.
 */
public class MouseHandler implements MouseListener {
  Map<Integer, Runnable> mouseClickedMap = new HashMap<>();
  Map<Integer, Runnable> mousePressedMap = new HashMap<>();
  Map<Integer, Runnable> mouseReleasedMap = new HashMap<>();
  Map<Integer, Runnable> mouseEnteredMap = new HashMap<>();
  Map<Integer, Runnable> mouseExitedMap = new HashMap<>();

  public void setMouseClickedMap(Map<Integer, Runnable> mouseClickedMap) {
    this.mouseClickedMap = mouseClickedMap;
  }

  public void setMousePressedMap(Map<Integer, Runnable> mousePressedMap) {
    this.mousePressedMap = mousePressedMap;
  }

  public void setMouseReleasedMap(Map<Integer, Runnable> mouseReleasedMap) {
    this.mouseReleasedMap = mouseReleasedMap;
  }

  public void setMouseEnteredMap(Map<Integer, Runnable> mouseEnteredMap) {
    this.mouseEnteredMap = mouseEnteredMap;
  }

  public void setMouseExitedMap(Map<Integer, Runnable> mouseExitedMap) {
    this.mouseExitedMap = mouseExitedMap;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (this.mouseClickedMap.containsKey(e)) {
      mouseClickedMap.get(e).run();
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {
    if (this.mousePressedMap.containsKey(e)) {
      mousePressedMap.get(e).run();
    }

  }

  @Override
  public void mouseReleased(MouseEvent e) {
    if (this.mouseReleasedMap.containsKey(e)) {
      mouseReleasedMap.get(e).run();
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    if (this.mouseEnteredMap.containsKey(e)) {
      mouseEnteredMap.get(e).run();
    }
  }

  @Override
  public void mouseExited(MouseEvent e) {
    if (this.mouseExitedMap.containsKey(e)) {
      mouseExitedMap.get(e).run();
    }
  }
}

package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

import cs3500.music.view.textview.GuiView;

/**
 * Created by ChrisRisley on 6/23/17.
 */
public class ControllerCapo extends Controller {

  /**
   * Builds the controller given an IMusicOperation
   *
   * @param kh Represents the keyboard handler to set it to.
   * @param mh Represents the mouse handler to set it to.
   */
  public ControllerCapo(KeyboardHandler kh, MouseListener mh) {
    super(kh, mh);
  }

  public void setView(GuiView view) {
    super.setView(view);
    super.mouseSetup();
  }

  @Override
  protected void keyBoardSetup() {
    super.keyBoardSetup();
    HashMap<Integer, Runnable> x = new HashMap<>();
    x.put(KeyEvent.VK_SHIFT, () -> view.addRepeat());
    super.kbd.addCommand(x);

    view.addKeyListener(kbd);
    view.resetFocus();
  }
}

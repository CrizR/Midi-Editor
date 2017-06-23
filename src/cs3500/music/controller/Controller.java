package cs3500.music.controller;


import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

import cs3500.music.model.IMusicOperations;
import cs3500.music.view.textview.GuiView;


/**
 * Initializes the keyboard and mouse commands and then adds the keyboard and mouse listeners
 * to the set view.
 * Modified: June 22, Changed the controller to account for the key and mouse listeners.
 */
public class Controller {
  private IMusicOperations op;
  private GuiView view;
  private KeyboardHandler kbd;
  private MouseListener mh;

  /**
   * Builds the controller given an IMusicOperation
   *
   * @param op Represents the operations to set it to.
   * @param kh Represents the keyboard handler to set it to.
   * @param mh Represents the mouse handler to set it to.
   */
  public Controller(IMusicOperations op, KeyboardHandler kh, MouseListener mh) {
    this.op = op;
    this.kbd = kh;
    this.mh = mh;
  }

  /**
   * Sets the view of the controller.
   *
   * @param view Represents the view to set.
   * @return the controller itself.
   */
  public void setView(GuiView view) {
    this.view = view;
    keyBoardSetup();
    mouseSetup();
  }

  //sets up the mouse handler and adds it to the view
  private void mouseSetup() {
    view.addMouseListener(mh);
    view.resetFocus();
  }

  //Sets upt the keyboard and the commands and adds it to the view
  private void keyBoardSetup() {
    Map<Character, Runnable> keyTypes = new HashMap<>();
    Map<Integer, Runnable> keyPresses = new HashMap<>();
    Map<Integer, Runnable> keyReleases = new HashMap<>();

    keyPresses.put(KeyEvent.VK_LEFT, () -> view.prevBeat());
    keyPresses.put(KeyEvent.VK_RIGHT, () -> view.nextBeat());
    keyPresses.put(KeyEvent.VK_SPACE, () -> view.togglePlay());
    keyPresses.put(KeyEvent.VK_HOME, () -> view.toBeginning());
    keyPresses.put(KeyEvent.VK_END, () -> view.toEnd());

    kbd.setKeyTypedMap(keyTypes);
    kbd.setKeyPressedMap(keyPresses);
    kbd.setKeyReleasedMap(keyReleases);

    view.addKeyListener(kbd);
    view.resetFocus();
  }
}

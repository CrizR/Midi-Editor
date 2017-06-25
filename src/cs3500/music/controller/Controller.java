package cs3500.music.controller;


import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

import cs3500.music.view.textview.GuiView;


/**
 * Initializes the keyboard and mouse commands and then adds the keyboard and mouse listeners
 * to the set view.
 * Modified: June 22, Changed the controller to account for the key and mouse listeners.
 * Modified: June 25, Added commands to change tempo, to add repeats, and to toggle practice mode.
 */
public class Controller {
  protected GuiView view;
  protected KeyboardHandler kbd;
  protected MouseListener mh;

  /**
   * Builds the controller given an IMusicOperation
   *
   * @param kh Represents the keyboard handler to set it to.
   * @param mh Represents the mouse handler to set it to.
   */
  public Controller(KeyboardHandler kh, MouseListener mh) {
    this.kbd = kh;
    this.mh = mh;
  }

  /**
   * Sets the view of the controller.
   *
   * @param view Represents the view to set.
   */
  public void setView(GuiView view) {
    this.view = view;
    keyBoardSetup();
    mouseSetup();
  }

  //sets up the mouse handler and adds it to the view

  /**
   * TODO: JAVADOC
   */
  protected void mouseSetup() {
    view.addMouseListener(mh);
    view.resetFocus();
  }

  //Sets upt the keyboard and the commands and adds it to the view
  protected void keyBoardSetup() {
    Map<Character, Runnable> keyTypes = new HashMap<>();
    Map<Integer, Runnable> keyPresses = new HashMap<>();
    Map<Integer, Runnable> keyReleases = new HashMap<>();

    keyPresses.put(KeyEvent.VK_LEFT, () -> view.prevBeat());
    keyPresses.put(KeyEvent.VK_RIGHT, () -> view.nextBeat());
    keyPresses.put(KeyEvent.VK_SPACE, () -> view.togglePlay());
    keyPresses.put(KeyEvent.VK_HOME, () -> view.toBeginning());
    keyPresses.put(KeyEvent.VK_END, () -> view.toEnd());
    keyPresses.put(KeyEvent.VK_CLOSE_BRACKET, () -> view.increaseTempo());
    keyPresses.put(KeyEvent.VK_OPEN_BRACKET, () -> view.decreaseTempo());
    keyPresses.put(KeyEvent.VK_P, () -> view.togglePractice());
    keyPresses.put(KeyEvent.VK_E, () -> view.addBeginRepeat());
    keyPresses.put(KeyEvent.VK_R, () -> view.addEndRepeat());
    keyPresses.put(KeyEvent.VK_B, () -> view.addDoubleRepeat());

    kbd.setKeyTypedMap(keyTypes);
    kbd.setKeyPressedMap(keyPresses);
    kbd.setKeyReleasedMap(keyReleases);

    view.addKeyListener(kbd);
    view.resetFocus();
  }
}

package cs3500.music.view;

import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.sound.midi.MetaEventListener;

import cs3500.music.model.IMusicOperations;

/**
 * IView class that represents the methods to be used by all of the editors views.
 * //TODO: MODIFIED
 */
public interface IView {


  /**
   * Make the view visible. This is usually called
   * after the view is constructed
   */
  void initialize();

  //TODO: JAVADOC
  void prevBeat();

  //TODO: JAVADOC
  void nextBeat();

  void toEnd();

  void toBeginning();


  void togglePlay();

  /**
   * Reset the focus on the appropriate part of the view that has the keyboard listener attached
   * to it, so that keyboard events will still flow through.
   */
  void resetFocus();

  /**
   * Signal the view to draw itself.
   */
  void refresh();


  /**
   * this is to force the view to have a method to set up the keyboard.
   * The name has been chosen deliberately. This is the same method signature to
   * add a key listener in Java Swing.
   *
   * Thus our Swing-based implementation of this interface will already have such a
   * method.
   */
  void addKeyListener(KeyListener listener);


  void addMouseListener(MouseListener listener);

  void addNote(MouseEvent e);
}
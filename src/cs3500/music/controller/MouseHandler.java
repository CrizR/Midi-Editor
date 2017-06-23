package cs3500.music.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import cs3500.music.view.textview.GuiView;

/**
 * Handles the mouse events that occur in the view. There's currently only one action
 * that is meant to occur in a Mouse Event and that is to add a note to the editor.
 */
public class MouseHandler implements MouseListener {
  private final GuiView view;


  /**
   * Constructs the MouseHandler by initializing the IMusicOperations and IView.
   *
   * @param view Represents the View.
   */
  public MouseHandler(GuiView view) {
    this.view = view;
  }

  /**
   * If a mouse if clicked addNote at the location to the model.
   */
  @Override
  public void mouseClicked(MouseEvent e) {
    view.addNote(e);
  }

  @Override
  public void mousePressed(MouseEvent e) {
    //Do Nothing
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    //Do Nothing
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    //Do Nothing
  }

  @Override
  public void mouseExited(MouseEvent e) {
    //Do Nothing
  }
}

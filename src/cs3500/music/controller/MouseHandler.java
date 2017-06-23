package cs3500.music.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import cs3500.music.model.IMusicOperations;
import cs3500.music.view.textview.GuiView;

/**
 * Handles the mouse events that occur in the view. There's currently only one action
 * that is meant to occur in a Mouse Event and that is to add a note to the editor.
 */
public class MouseHandler implements MouseListener {
  IMusicOperations op;
  GuiView view;


  /**
   * Constructs the MouseHandler by initializing the IMusicOperations and IView.
   *
   * @param op   Represents the IMusicOperations.
   * @param view Represents the View.
   */
  public MouseHandler(IMusicOperations op, GuiView view) {
    this.op = op;
    this.view = view;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    view.addNote(e);
  }

  @Override
  public void mousePressed(MouseEvent e) {
  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }
}

package cs3500.music.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import cs3500.music.mechanics.Note;
import cs3500.music.model.IMusicOperations;
import cs3500.music.view.GuiView;
import cs3500.music.view.IView;
import cs3500.music.view.graphicsview.GuiViewFrame;
import cs3500.music.view.graphicsview.PianoPanel;

/**
 * Created by ChrisRisley on 6/18/17.
 */
public class MouseHandler implements MouseListener {
  IMusicOperations op;
  GuiView view;


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

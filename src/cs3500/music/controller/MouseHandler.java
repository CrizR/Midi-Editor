package cs3500.music.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import cs3500.music.model.IMusicOperations;
import cs3500.music.view.graphicsview.GuiViewFrame;
import cs3500.music.view.textview.GuiView;

/**
 * Handles the mouse events that occur in the view. There's currently only one action
 * that is meant to occur in a Mouse Event and that is to add a note to the editor.
 */
public class MouseHandler implements MouseListener {
  private final GuiView view;
  private javax.swing.Timer timer;
  private int time;
  /**
   * Constructs the MouseHandler by initializing the IMusicOperations and IView.
   *
   * @param view Represents the View.
   */
  public MouseHandler(GuiView view, IMusicOperations op) {
    this.time = 0;
    this.view = view;
    timer = new Timer(op.getTempo() / 1000, e -> {
      view.nextBeat();
      time++;
    });
  }

  /**
   * If a mouse if clicked addNote at the location to the model.
   */
  @Override
  public void mouseClicked(MouseEvent e) {
    //TODO Nothing
  }

  @Override
  public void mousePressed(MouseEvent e) {
    timer.start();
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    timer.stop();
    System.out.println(time);
    view.addNote(e, time);
    time = 0;

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

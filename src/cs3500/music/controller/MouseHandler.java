package cs3500.music.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import cs3500.music.model.IMusicOperations;
import cs3500.music.view.textview.GuiView;

/**
 * Handles the mouse events that occur in the view. There's currently only one action
 * that is meant to occur in a Mouse Event and that is to add a note to the editor.
 */
public class MouseHandler implements MouseListener {
  private final GuiView view;
  private javax.swing.Timer timer;
  private int time;
  private int tempo;
  private IMusicOperations op;

  /**
   * Constructs the MouseHandler by initializing the IMusicOperations and IView.
   *
   * @param view Represents the View.
   */
  public MouseHandler(GuiView view, IMusicOperations op) {
    this.op = op;
    this.time = 1;
    this.view = view;
  }

  /**
   * If a mouse if clicked addNote at the location to the model.
   */
  @Override
  public void mouseClicked(MouseEvent e) {
    //TODO Nothing
 //   view.addNote(e, 5);
  }

  @Override
  public void mousePressed(MouseEvent e) {
    this.tempo = op.getTempo() / 1000;
    timer = new Timer(tempo, f -> {
      view.nextBeat();
      time++;
    });
    timer.start();
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    timer.stop();
    view.addNote(e, time);
    view.nextBeat();
    time = 1;
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

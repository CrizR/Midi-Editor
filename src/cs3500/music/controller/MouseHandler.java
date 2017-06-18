package cs3500.music.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import cs3500.music.mechanics.Note;
import cs3500.music.model.IMusicOperations;
import cs3500.music.view.IView;
import cs3500.music.view.graphicsview.GuiViewFrame;
import cs3500.music.view.graphicsview.PianoPanel;

/**
 * Created by ChrisRisley on 6/18/17.
 */
public class MouseHandler implements MouseListener {
  IMusicOperations op;
  IView view;


  public MouseHandler(IMusicOperations op, IView view) {
    this.op = op;
    this.view = view;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    for (int i = 0; i < PianoPanel.keys.size(); i++) {
      PianoPanel.Key k = PianoPanel.keys.get(i);
      if (k.onKey(e.getX(), e.getY() - GuiViewFrame.MIDI_HEIGHT)) {
        if (k.getPitch().isSharp()) {
          op.addNote(new Note(k.getPitch(), 1, 1, 1, 10),
                  GuiViewFrame.BEAT);
          view.nextBeat();
          break;
        } else {
          for (int j = i; j < PianoPanel.keys.size(); j++) {
            if (k.onKey(e.getX(), e.getY() - GuiViewFrame.MIDI_HEIGHT)) {
              op.addNote(new Note(k.getPitch(), 1, 1, 1, 10),
                      GuiViewFrame.BEAT);
              view.nextBeat();
              break;
            }
          }
        }
      }
    }
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

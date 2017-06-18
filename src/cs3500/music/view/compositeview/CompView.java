package cs3500.music.view.compositeview;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;

import cs3500.music.model.IMusicOperations;
import cs3500.music.view.GuiView;
import cs3500.music.view.graphicsview.GuiViewFrame;
import cs3500.music.view.midiview.MidiViewImpl;

/**
 * Created by ChrisRisley on 6/18/17.
 */
public class CompView extends JFrame implements GuiView {
  int beat;
  MidiViewImpl midiDelegate;
  GuiViewFrame guiDelegate;
  boolean playing;
  IMusicOperations op;

  public CompView(IMusicOperations op) throws MidiUnavailableException {
    this.op = op;
    this.midiDelegate = new MidiViewImpl(op, MidiSystem.getSynthesizer());
    this.guiDelegate = new GuiViewFrame(op);

  }

  @Override
  public void togglePlay() {
    if (playing) {

    }

  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  @Override
  public void refresh() {

  }

  @Override
  public void initialize() {
    this.midiDelegate.initialize();
    this.guiDelegate.initialize();
  }

  @Override
  public void prevBeat() {
    if (beat - 1 >= 0) {
      beat--;
    }
    refresh();
  }

  @Override
  public void nextBeat() {
    if (beat + 1 <= op.lastBeat() + 1) {
      beat++;
    }
    refresh();
  }

  @Override
  public void toEnd() {
    beat = op.lastBeat();
  }

  @Override
  public void toBeginning() {
    beat = 0;
  }

}

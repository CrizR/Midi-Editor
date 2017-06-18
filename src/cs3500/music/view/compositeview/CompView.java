package cs3500.music.view.compositeview;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

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
  public void addKeyListener(KeyListener e) {
    super.addKeyListener(e);
    this.midiDelegate.addKeyListener(e);
    this.guiDelegate.addKeyListener(e);
  }

  @Override
  public void addMouseListener(MouseListener e) {
    super.addMouseListener(e);
    this.midiDelegate.addMouseListener(e);
    this.guiDelegate.addMouseListener(e);
  }

  @Override
  public void togglePlay() {
    this.midiDelegate.togglePlay();
  }

  @Override
  public void resetFocus() {
    // this.setFocusable(true);
    //this.requestFocus();
    this.midiDelegate.resetFocus();
    this.guiDelegate.resetFocus();
  }

  @Override
  public void refresh() {
    this.midiDelegate.refresh();
    this.guiDelegate.refresh();
  }

  @Override
  public void initialize() {
    this.midiDelegate.initialize();
    this.guiDelegate.initialize();
  }

  @Override
  public void prevBeat() {
    if (GuiViewFrame.BEAT - 1 >= 0) {
      GuiViewFrame.BEAT--;
    }
    refresh();
  }

  @Override
  public void nextBeat() {
    if (GuiViewFrame.BEAT + 1 <= op.lastBeat() + 1) {
      GuiViewFrame.BEAT++;
    }
    refresh();
  }

  @Override
  public void toEnd() {
    this.guiDelegate.toEnd();
    this.midiDelegate.toEnd();
  }

  @Override
  public void toBeginning() {
    this.guiDelegate.toBeginning();
    this.midiDelegate.toBeginning();
  }
}

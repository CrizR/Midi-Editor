package cs3500.music.view.compositeview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.TimerTask;

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
  IMusicOperations op;
  boolean playing;
  Timer timer;

  public CompView(IMusicOperations op) throws MidiUnavailableException {
    this.op = op;
    this.midiDelegate = new MidiViewImpl(op, MidiSystem.getSynthesizer());
    this.guiDelegate = new GuiViewFrame(op);

    //Set up timer to drive animation events.
    timer = new Timer(100, new refreshFrame());
    timer.setInitialDelay(10);
    this.playing = false;
  }

  private boolean isPlaying() {
    return this.midiDelegate.isPlaying();
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
  public void addNote(MouseEvent e) {
    if (!isPlaying()) {
      this.guiDelegate.addNote(e);
      this.midiDelegate.refresh();
    }
  }

  @Override
  public void togglePlay() {
    this.midiDelegate.togglePlay();
    if (playing) {
      timer.stop();
    }
    else {
      timer.start();
    }
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
    if (!isPlaying()) {
      this.guiDelegate.prevBeat();
    }
  }

  @Override
  public void nextBeat() {
    if (!isPlaying()) {
      this.guiDelegate.nextBeat();
    }
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

  public class refreshFrame implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      guiDelegate.sync(midiDelegate.currentBeat());
      refresh();
    }
  }
}

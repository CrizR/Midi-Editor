package cs3500.music.view.compositeview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventListener;
import java.util.TimerTask;

import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
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
  int tempo;

  public CompView(IMusicOperations op) throws MidiUnavailableException {
    this.op = op;
    this.tempo = this.op.getTempo();
    this.midiDelegate = new MidiViewImpl(op, MidiSystem.getSynthesizer());
    this.guiDelegate = new GuiViewFrame(op);

//    //Set up timer to drive animation events.
//    timer = new Timer(op.getTempo()/ 2000, new refreshFrame());
//    timer.setInitialDelay(0);
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
    if (playing) {
//      timer.stop();
      this.midiDelegate.togglePlay();
      this.playing = false;
    }
    else {
//      timer.start();
      this.midiDelegate.togglePlay();
      this.playing = true;
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
//      guiDelegate.sync(midiDelegate.currentBeat());
//      guiDelegate.refresh();
      guiDelegate.nextBeat();
    }
  }

  public class Refresh implements MetaEventListener {
    @Override
    public void meta(MetaMessage meta) {
      guiDelegate.nextBeat();
    }
  }
}
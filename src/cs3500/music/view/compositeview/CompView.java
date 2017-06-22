package cs3500.music.view.compositeview;

import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Track;

import cs3500.music.mechanics.Note;
import cs3500.music.model.IMusicOperations;
import cs3500.music.view.IView;
import cs3500.music.view.graphicsview.GuiPanel;
import cs3500.music.view.graphicsview.GuiViewFrame;
import cs3500.music.view.midiview.MidiViewImpl;

/**
 * A Class that represents the MidiView. Via the implementation of the Midi Receiver and Synthesizer
 * this view allows the user to hear the notes found in the music model.
 */
public class CompView extends MidiViewImpl implements IView{
  private final ArrayList<Integer> beats;
  boolean play = false;
  GuiViewFrame guiDelegate;


  /**
   * Builds a MidiViewImpl.
   * The MidiView has an IMusicOperation, the last beat in the model, a synthesizer,
   * a receiver, and the starting beats.
   *
   * @param op Represents the model to read from.
   * @throws MidiUnavailableException throws an exception if the midi fails.
   */
  public CompView(IMusicOperations op, Synthesizer synth) throws MidiUnavailableException {
    super(op, synth);
    this.guiDelegate = new GuiViewFrame(op);
    this.beats = op.getStartingBeats();
    Track t = this.sequence.createTrack();
    MetaMessage tick = new MetaMessage();
    int lastBeat = this.op.lastBeat();
    for (int i = 0; i <= lastBeat; i ++) {
      MidiEvent tic = new MidiEvent(tick, i);
      t.add(tic);
    }

    sequencer.addMetaEventListener(new Refresh());
  }

  @Override
  public void initialize() {
    this.guiDelegate.initialize();

    for (int i : beats) {
      for (Note n : op.getNotes(i).values()) {
        try {
          super.playNote(n.getTone(), n.getDuration(), i, n.getVolume(), n.getInstrument() - 1);
        } catch (InvalidMidiDataException e) {
          //failed to init Midi
        }
      }
    }

    try {
      sequencer.setSequence(sequence);
    } catch (InvalidMidiDataException e) {
      // failed to get midi data
    }
    if (play) {
      sequencer.start();
    }
  }

  @Override
  public void togglePlay() {
    if (this.play) {
      sequencer.stop();
      this.play = false;
    } else {
      sequencer.start();
      sequencer.setTempoInMPQ(super.tempo);
      this.play = true;
    }
  }

  @Override
  public void prevBeat() {
    if (!play) {
      this.guiDelegate.prevBeat();
      super.prevBeat();
    }
  }

  @Override
  public void nextBeat() {
    if (!play) {
      this.guiDelegate.nextBeat();
      super.nextBeat();
    }
  }

  @Override
  public void toEnd() {
    this.guiDelegate.toEnd();
    this.sequencer.setTickPosition(this.op.lastBeat() * this.tempo);
  }

  @Override
  public void toBeginning() {
    this.guiDelegate.toBeginning();
    this.sequencer.setTickPosition(0);
    if (play) {
      this.sequencer.start();
    }
  }

  @Override
  public void resetFocus() {
    this.guiDelegate.resetFocus();
  }

  @Override
  public void refresh() {
    super.initialize();
    this.guiDelegate.refresh();
  }

  @Override
  public void addKeyListener(KeyListener e) {
    this.guiDelegate.addKeyListener(e);
  }

  @Override
  public void addMouseListener(MouseListener e) {
    this.guiDelegate.addMouseListener(e);
  }

  @Override
  public void addNote(MouseEvent e) {
    if (!play) {
      this.guiDelegate.addNote(e);
      this.refresh();
    }
  }

  public class Refresh implements MetaEventListener {
    @Override
    public void meta(MetaMessage meta) {
      GuiViewFrame.BEAT = (int)sequencer.getTickPosition();
      guiDelegate.movePanel();
      guiDelegate.refresh();
    }
  }
}

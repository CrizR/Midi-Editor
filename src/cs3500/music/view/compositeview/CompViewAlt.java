package cs3500.music.view.compositeview;

import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Track;
import javax.sound.midi.Transmitter;

import cs3500.music.mechanics.Note;
import cs3500.music.mechanics.Pitch;
import cs3500.music.model.IMusicOperations;
import cs3500.music.view.IView;
import cs3500.music.view.graphicsview.GuiViewFrame;

/**
 * A Class that represents the MidiView. Via the implementation of the Midi Receiver and Synthesizer
 * this view allows the user to hear the notes found in the music model.
 */
public class CompViewAlt implements IView {
  private final IMusicOperations op;
  private final int tempo;
  private final ArrayList<Integer> beats;
  private Sequence sequence;
  private Sequencer sequencer;
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
  public CompViewAlt(IMusicOperations op, Synthesizer synth) throws MidiUnavailableException {
    this.op = op;
    this.guiDelegate = new GuiViewFrame(op);
    this.tempo = op.getTempo();
    this.beats = op.getStartingBeats();
    try {
      sequencer = MidiSystem.getSequencer();
      sequence = new Sequence(Sequence.PPQ, this.tempo * 4);
    } catch (InvalidMidiDataException e) {
      // failed to use mid
    }
  }

  /**
   * Plays the given note as defined by the following parameters.
   *
   * @param tone       Represents the tone of the note to play.
   * @param duration   Represents the duration of the note to play.
   * @param startBeat  Represents the startBeat of the note to play.
   * @param volume     Represents the volume of the note to play.
   * @param instrument Represents the instrument of the note to play.
   * @throws InvalidMidiDataException if the midi fails to play the note.
   */
  private void playNote(String tone, int duration, int startBeat, int volume, int instrument) throws InvalidMidiDataException {
    MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, instrument, Pitch.toneIndex.indexOf(tone), volume);
    MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, instrument, Pitch.toneIndex.indexOf(tone), volume);

    MidiEvent startNote = new MidiEvent(start, this.tempo * startBeat);
    MidiEvent endNote = new MidiEvent(stop, this.tempo * (startBeat + duration));
    Track t = sequence.createTrack();
    t.add(startNote);
    t.add(endNote);
  }

  @Override
  public void initialize() {
    this.guiDelegate.initialize();

    try {
      sequencer.open();
    } catch (MidiUnavailableException e) {
      // failed to connect to mid
    }
    for (int i : beats) {
      for (Note n : op.getNotes(i).values()) {
        try {
          this.playNote(n.getTone(), n.getDuration(), i, n.getVolume(), n.getInstrument() - 1);
        } catch (InvalidMidiDataException e) {
          //failed to init Midi
        }
      }
    }
    for (int i = 0; i <= op.lastBeat(); i ++) {
      MetaMessage tick = new MetaMessage();
      MidiEvent tic = new MidiEvent(tick, i * tempo);
      Track t = sequence.createTrack();
      t.add(tic);
    }

    sequencer.addMetaEventListener(new Refresh());

    try {
      sequencer.setSequence(sequence);
    } catch (InvalidMidiDataException e) {
      // failed to get midi data
    }


    if (this.play) {
      try {
        sequencer.start();
      } catch (IllegalStateException e) {
        System.out.println("Invalid");
      }
    }
  }

  @Override
  public void togglePlay() {
    if (this.play) {
      sequencer.stop();
      this.play = false;
    } else {
      sequencer.start();
      this.play = true;
    }
  }


  public int currentBeat() {
    return (int) sequencer.getMicrosecondPosition() / this.tempo;
  }

  @Override
  public void prevBeat() {
    if (!play) {
      this.guiDelegate.prevBeat();
    }
  }

  @Override
  public void nextBeat() {
    if (!play) {
      this.guiDelegate.nextBeat();
    }
  }

  @Override
  public void toEnd() {
    this.guiDelegate.toEnd();
    this.sequencer.setTickPosition(op.lastBeat() * this.tempo);
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
    try {
//      this.synth.open();
      sequencer.open();
    } catch (MidiUnavailableException e) {
      // failed to connect to mid
    }
    for (int i : beats) {
      for (Note n : op.getNotes(i).values()) {
        try {
          this.playNote(n.getTone(), n.getDuration(), i, n.getVolume(), n.getInstrument() - 1);
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
      guiDelegate.sync(currentBeat());
      guiDelegate.refresh();
    }
  }
}

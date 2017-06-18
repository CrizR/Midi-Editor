package cs3500.music.view.midiview;


import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.sound.midi.InvalidMidiDataException;
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

/**
 * A Class that represents the MidiView. Via the implementation of the Midi Receiver and Synthesizer
 * this view allows the user to hear the notes found in the music model.
 */
public class MidiViewImpl implements IView {
  //  private final Synthesizer synth;
//  private final Receiver receiver;
  private final IMusicOperations op;
  private final int tempo;
  private final ArrayList<Integer> beats;
  private Sequence sequence;
  private Sequencer sequencer;
  //  private Track track;
  Transmitter seqTrans;
  Synthesizer synth;
  Receiver receiver;
  boolean play = false;

  /**
   * Builds a MidiViewImpl.
   * The MidiView has an IMusicOperation, the last beat in the model, a synthesizer,
   * a receiver, and the starting beats.
   *
   * @param op Represents the model to read from.
   * @throws MidiUnavailableException throws an exception if the midi fails.
   */
  public MidiViewImpl(IMusicOperations op, Synthesizer synth) throws MidiUnavailableException {
    this.op = op;
    this.tempo = op.getTempo();
    this.synth = synth;
    this.receiver = synth.getReceiver();
    this.beats = op.getStartingBeats();
    try {
      sequencer = MidiSystem.getSequencer();
      sequence = new Sequence(Sequence.PPQ, this.tempo * 4);
//      seqTrans = sequencer.getTransmitter();
//      synth   = MidiSystem.getSynthesizer();
//      receiver = synth.getReceiver();
//      seqTrans.setReceiver(receiver);
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
//    receiver.send(start, this.tempo * startBeat);
//    receiver.send(stop, this.tempo * (startBeat + duration));

    MidiEvent startNote = new MidiEvent(start, this.tempo * startBeat);
    MidiEvent endNote = new MidiEvent(stop, this.tempo * (startBeat + duration));
    Track t = sequence.createTrack();
    t.add(startNote);
    t.add(endNote);
  }

  @Override
  public void initialize() {
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

    if (this.play) {
      try {
        sequencer.start();
      } catch (IllegalStateException e) {
        System.out.println("Invalid");
      }
    }
//    this.receiver.close();
  }

  @Override
  public void togglePlay() {
    if (this.play) {
      System.out.print("Stop");
      sequencer.stop();
      this.play = false;
    }
    else {
      sequencer.start();
      this.play = true;
    }
  }

  public long currentBeat() {
    return sequencer.getMicrosecondPosition();
  }

  @Override
  public void prevBeat() {
  }

  @Override
  public void nextBeat() {

  }

  @Override
  public void toEnd() {
    this.sequencer.setTickPosition(op.lastBeat() * this.tempo);
  }

  @Override
  public void toBeginning() {
    this.sequencer.setTickPosition(0);
    if (play) {
      this.sequencer.start();
    }
  }
  @Override
  public void resetFocus() {

  }

  @Override
  public void refresh() {

  }

  @Override
  public void addKeyListener(KeyListener listener) {

  }

  @Override
  public void addMouseListener(MouseListener listener) {
  }
}

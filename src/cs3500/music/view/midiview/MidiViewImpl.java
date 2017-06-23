package cs3500.music.view.midiview;


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

import cs3500.music.mechanics.Note;
import cs3500.music.mechanics.Pitch;
import cs3500.music.model.IMusicOperations;
import cs3500.music.view.IView;

/**
 * A Class that represents the MidiView. Via the implementation of the Midi Receiver and Synthesizer
 * this view allows the user to hear the notes found in the music model.
 */
public class MidiViewImpl implements IView {
  protected final IMusicOperations op;
  protected final int tempo;
  private final ArrayList<Integer> beats;
  protected Sequence sequence;
  protected Sequencer sequencer;
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
  public MidiViewImpl(IMusicOperations op, Synthesizer synth, boolean play) throws MidiUnavailableException {
    this.op = op;
    this.tempo = op.getTempo();
    this.synth = synth;
    this.receiver = synth.getReceiver();
    this.beats = op.getStartingBeats();
    this.play = play;
    try {
      sequencer = MidiSystem.getSequencer();
      sequence = new Sequence(Sequence.PPQ, 1);
      sequencer.setTempoInMPQ(this.tempo);
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
  protected void playNote(String tone, int duration, int startBeat, int volume, int instrument) throws InvalidMidiDataException {
    MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, instrument, Pitch.toneIndex.indexOf(tone), volume);
    MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, instrument, Pitch.toneIndex.indexOf(tone), volume);

    MidiEvent startNote = new MidiEvent(start, startBeat);
    MidiEvent endNote = new MidiEvent(stop, startBeat + duration);
    Track t = sequence.createTrack();
    t.add(startNote);
    t.add(endNote);
    try {
      sequencer.open();
    } catch (MidiUnavailableException e) {
      // failed to connect to mid
    }
  }

  @Override
  public void initialize() {
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
    if(play) {
      sequencer.start();
    }
  }

  public void refresh() {
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
  }

  @Override
  public void prevBeat() {
    this.sequencer.setTickPosition(this.sequencer.getTickPosition() - 1);
  }

  @Override
  public void nextBeat() {
    this.sequencer.setTickPosition(this.sequencer.getTickPosition() + 1);
  }

}

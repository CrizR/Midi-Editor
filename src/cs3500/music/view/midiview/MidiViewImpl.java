package cs3500.music.view.midiview;


import java.util.ArrayList;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;

import cs3500.music.mechanics.Note;
import cs3500.music.mechanics.Pitch;
import cs3500.music.model.IMusicOperations;
import cs3500.music.view.View;

/**
 * A Class that represents the MidiView. Via the implementation of the Midi Receiver and Synthesizer
 * this view allows the user to hear the notes found in the music model.
 */
public class MidiViewImpl implements View {
  private final Receiver receiver;
  private final IMusicOperations op;
  private final int tempo;
  private final ArrayList<Integer> beats;

  /**
   * Builds a MidiViewImpl.
   * The MidiView has an IMusicOperation, the last BEAT in the model, a synthesizer,
   * a receiver, and the starting beats.
   *
   * @param op Represents the model to read from.
   * @throws MidiUnavailableException throws an exception if the midi fails.
   */
  public MidiViewImpl(IMusicOperations op, Synthesizer synth) throws MidiUnavailableException {
    this.op = op;
    this.tempo = op.getTempo();
    this.receiver = synth.getReceiver();
    synth.open();
    this.beats = op.getStartingBeats();
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
  private void playNote(String tone, int duration, int startBeat, int volume, int instrument)
          throws InvalidMidiDataException {
    MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, instrument,
            Pitch.toneIndex.indexOf(tone), volume);
    MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, instrument,
            Pitch.toneIndex.indexOf(tone), volume);
    receiver.send(start, this.tempo * startBeat);
    receiver.send(stop, this.tempo * (startBeat + duration));
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
    this.receiver.close();
  }
}
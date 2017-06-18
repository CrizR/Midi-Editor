package cs3500.music.view.playmidiview;

import java.io.File;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

import cs3500.music.view.View;

/**
 * This view plays a midi file. It imports a midi file from the repository given the file name,
 * and uses the sequencer from the MidiSystem to play the music.
 */
public class PlayMidiFile implements View {
  private Sequence sequence;
  private Sequencer sequencer;

  /**
   * This method constructs a PlayMidiFile view by passing in the midi file name, it creates a
   * sequence from the mid file and also initialize a sequencer.
   *
   * @param song the file name of the midi file (starwars.mid)
   * @throws MidiUnavailableException if can not get valid midi data
   */
  public PlayMidiFile(String song) throws MidiUnavailableException {
    try {
      sequence = MidiSystem.getSequence(new File(song));
    } catch (InvalidMidiDataException | IOException e) {
      // failed to use mid
    }
    sequencer = MidiSystem.getSequencer();
  }

  /**
   * This method initialize the view and opens the sequencer and sets the sequence and then
   * plays everything in the sequencer.
   */
  public void initialize() {
    try {
      sequencer.open();
    } catch (MidiUnavailableException e) {
      // failed to connect to mid
    }
    try {
      sequencer.setSequence(sequence);
    } catch (InvalidMidiDataException e) {
      // failed to get midi data
    }
    try {
      sequencer.start();
    } catch (IllegalStateException e) {
      System.out.println("Invalid Midi File");
    }
  }
}

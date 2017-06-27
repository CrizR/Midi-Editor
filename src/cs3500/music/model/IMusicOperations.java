package cs3500.music.model;

import java.util.ArrayList;
import java.util.HashMap;

import cs3500.music.mechanics.Note;
import cs3500.music.mechanics.repeats.Repeat;
import cs3500.music.mechanics.repeats.RepeatType;
import cs3500.music.mechanics.repeats.TimeBar;

/**
 * This interface is an interface for the music editor,
 * and has the methods for the model's operations.
 */
public interface IMusicOperations {

  /**
   * Adds a note to the music starting at a specific BEAT. The starting BEAT of this note must be
   * a positive integer. This note must not over lap with another note with the same pitch.
   *
   * @param n     the music note being added
   * @param start the specific BEAT the note will start in the music
   * @throws IllegalArgumentException throws an exception if the starting BEAT is invalid
   */
  void addNote(Note n, int start);

  /**
   * Edit an existing note in the music. The starting BEAT of the note or the note itself
   * can all be changed. The note should be changed and the starting BEAT of the note, as well
   * as the new note to be changed to and the new starting BEAT should all be specified through
   * the parameters.
   *
   * @param existingNote the note in the music to be edited
   * @param oldStart     the starting BEAT of the existing note
   * @param newNote      the new edited note
   * @param newStart     the new start of the edited note
   * @throws IllegalArgumentException when the note to be edited does not exist in this music
   */
  void editNote(Note existingNote, int oldStart, Note newNote, int newStart);

  /**
   * Delete a note from the existing music. The note entered must already exist in the music.
   *
   * @param n     the noted in the music to be deleted
   * @param start the starting BEAT of the note to be deleted
   * @throws IllegalArgumentException when the note with the BEAT does not exist in this music
   */
  void deleteNote(Note n, int start);

  /**
   * Combine two pieces of music by playing them at the same time. Notes can over lap on top
   * of each other because this is 2 music playing at the same time. They will have the
   * same starting BEAT and be played simultaneously.
   *
   * @param m the music to be combined
   */
  void combine(Music m);

  /**
   * Combine two piece of music by having the playing the next song after. This combines two
   * music into one, by yhe music passed in will start 2 beats after the end of the other music.
   *
   * @param m the music to be added to play next.
   */
  void playNext(Music m);

  /**
   * Gets a hash map of notes that all start at a specific BEAT. The BEAT as an integer is passed
   * into the method and the hash map of notes is mapped by tones is returned.
   *
   * @param beat the BEAT that all the notes start at
   * @return Hash map of notes starting with the specific BEAT
   */
  HashMap<String, Note> getNotes(int beat);

  /**
   * Gets a hash map of notes that are active at a specific BEAT. The BEAT as an integer is passed
   * into the method and the hash map of notes is mapped by tones is returned.
   *
   * @param beat represents the BEAT that we use to determine what notes are active.
   * @return Hash map of notes that are active
   */

  HashMap<String, Note> activeNotes(int beat);

  /**
   * Gets a list of tones, in string format, in this music. This is the tone range
   * of the music, from the lowest tone to the highest tone. It can contain tones
   * that does not have any notes, but as long as they are in between the lowest tone
   * and the highest tone, they will be included in this range. Tones are represented with strings.
   *
   * @return ArrayList the range of tones in this music
   */
  ArrayList<String> getTones();

  /**
   * This method calculates the last BEAT in this music by first finding the group of notes
   * with the latest starting BEAT and then finds the note with the longest duration out of that
   * group. The last BEAT is then equals to the last starting BEAT plus the duration of the last
   * note - 1.
   *
   * @return integer: the last BEAT of this piece of music
   */
  int lastBeat();

  /**
   * Visualize this piece of music in the console in the string format. A column of numbers
   * represent the beats, printed right-justified and padded with leading spaces,
   * that is exactly as wide as necessary.  A sequence of columns, each five characters wide,
   * representing each pitch. The first line prints out the names of the pitches,
   * more-or-less centered within the five-character column. I.e., "  F2 "
   * and " G#3 " and " D#10".
   * <p></p>
   * Each note-head is rendered as an "  X  ", and each note-sustain is rendered as "  |  ".
   * When a note is not played, five spaces are rendered (as "     ").
   * As a consequence: every line is exactly the same length. Every item, including the last one,
   * ends in a newline.
   *
   * @return string representation of the music
   */
  String visualize();

  /**
   * Sets the tempo in the model.
   *
   * @param tempo Represents the tempo to set.
   */
  void setTempo(int tempo);

  /**
   * Determines the tempo to play the notes found in the model.
   *
   * @return the tempo as an integer.
   */
  int getTempo();

  /**
   * Returns the key values to every note in the model in order.
   *
   * @return the key set values as an arraylist.
   */
  ArrayList<Integer> getStartingBeats();

  /**
   * Builds a deep copy of the model to prevent future mutation issues.
   *
   * @return the clone of the model.
   */
  IMusicOperations clone();

  /**
   * TODO:
   */
  HashMap<Integer, Repeat> getRepeats();

  /**
   * Adds a repeat at the current location.
   */
  void addRepeat(int startingPosition, RepeatType type);

  ArrayList<Integer> repeatBeats();


  void addTimebar(TimeBar t);

  ArrayList<TimeBar> getTimebars();
}

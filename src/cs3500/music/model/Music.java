package cs3500.music.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import cs3500.music.builder.CompositionBuilder;
import cs3500.music.mechanics.ConsoleVisualizer;
import cs3500.music.mechanics.Note;
import cs3500.music.mechanics.Pitch;
import cs3500.music.mechanics.Set;
import cs3500.music.mechanics.ToneRange;
import cs3500.music.mechanics.repeats.DoubleRepeat;
import cs3500.music.mechanics.repeats.Repeat;
import cs3500.music.mechanics.repeats.RepeatType;
import cs3500.music.mechanics.repeats.SingleRepeat;

/**
 * This represents a piece of music model which implements the IMusicOperations interface.
 * A music model has a list of notes with the starting BEAT mapping to notes.
 * It also has a deque to keep track of the pitches. This model can also be
 * visualized in the console in string format.
 * MODIFIED on June25, added repeats to the model.
 */
public class Music implements IMusicOperations {
  protected ToneRange toneList;
  protected HashMap<Integer, Set> noteMap;
  protected int tempo;
  private HashMap<Integer, Repeat> repeats;

  /**
   * Initialize the music model. Set the pitchList to a new pitchList and
   * the noteMap to a new empty HashMap.
   */
  public Music() {
    this.toneList = new ToneRange();
    this.repeats = new HashMap<>();
    this.noteMap = new HashMap<>();
  }

  /**
   * Builds a Music given a tonelist, notemap, and tempo.
   *
   * @param toneList Represents the toneList to set the model to.
   * @param noteMap  Represents the noteMap that the new Model will contain.
   * @param tempo    Represents the tempo that the model will have.
   */
  public Music(ToneRange toneList, HashMap<Integer, Set> noteMap, int tempo) {
    this.toneList = toneList.clone();
    this.noteMap = copyMap(noteMap);
    this.repeats = new HashMap<>();
    this.tempo = tempo;
  }

  //creates a copy of the notemap
  private HashMap<Integer, Set> copyMap(HashMap<Integer, Set> noteMap) {
    HashMap<Integer, Set> copy = new HashMap<>();
    for (Map.Entry<Integer, Set> entry : noteMap.entrySet()) {
      Set value = entry.getValue();
      int key = entry.getKey();
      copy.put(key, value.copy());
    }
    return copy;
  }

  @Override
  public void addNote(Note newNote, int newStart) {
    this.checkStartingBeat(newStart);
    this.toneList.addTone(newNote);
    this.addToBeat(newNote, newStart);
  }

  @Override
  public void editNote(Note existingNote, int oldStart, Note newNote, int newStart) {
    this.deleteNote(existingNote, oldStart);
    this.addNote(newNote, newStart);
  }

  @Override
  public void deleteNote(Note n, int start) {
    checkStartingBeat(start);
    Set s = this.noteMap.get(start);
    checkNoteExist(n, start, s);
    s.remove(n);
    if (s.isEmpty()) {
      this.noteMap.remove(start);
    }
    this.toneList.deleteTone(n.getTone(), this.noteMap);
  }

  @Override
  public void combine(Music newSong) {
    for (int key : newSong.noteMap.keySet()) {
      for (Note n : newSong.noteMap.get(key).getNotes()) {
        this.addToBeat(n, key);
      }
    }
  }

  @Override
  public void playNext(Music newSong) {
    int lastBeat = this.lastBeat();
    for (int key : newSong.noteMap.keySet()) {
      for (Note n : newSong.noteMap.get(key).getNotes()) {
        this.addNote(n, key + lastBeat + 2);
      }
    }
  }

  @Override
  public HashMap<String, Note> getNotes(int beat) {
    HashMap<String, Note> result = new HashMap<>();
    if (!this.noteMap.containsKey(beat)) {
      return new HashMap<>();
    } else {
      ArrayList<Note> notes = this.noteMap.get(beat).getNotes();
      for (Note n : notes) {
        result.put(n.getTone(), n);
      }
      return result;
    }
  }

  @Override
  public HashMap<String, Note> activeNotes(int beat) {
    HashMap<String, Note> result = new HashMap<>();
    for (int b : this.noteMap.keySet()) {
      for (Note n : this.noteMap.get(b).activeNotes(b, beat)) {
        result.put(n.getTone(), n);
      }
    }
    return result;
  }

  @Override
  public ArrayList<String> getTones() {
    return this.toneList.getList();
  }

  /**
   * This method calculates the last BEAT in this music by first finding the group of notes
   * with the latest starting BEAT and then finds the note with the longest duration out of that
   * group. The last BEAT is then equals to the last starting BEAT plus the duration of the last
   * note - 1.
   *
   * @return integer: the last BEAT of this piece of music
   */
  @Override
  public int lastBeat() {
    int lastBeat = 0;
    for (int key : this.noteMap.keySet()) {
      for (Note n : this.noteMap.get(key).getNotes()) {
        lastBeat = Math.max(key + n.getDuration() - 1, lastBeat);
      }
    }
    return lastBeat;
  }

  @Override
  public String visualize() {
    if (emptyMusic()) {
      return "";
    }
    ConsoleVisualizer consoleView = new ConsoleVisualizer(
            this.lastBeat(), this.toneList.getList(), this.noteMap);
    return consoleView.render();
  }

  @Override
  public void setTempo(int tempo) {
    this.tempo = tempo;
  }

  @Override
  public int getTempo() {
    return this.tempo;
  }

  @Override
  public ArrayList<Integer> getStartingBeats() {
    ArrayList<Integer> result = new ArrayList<>();
    result.addAll(noteMap.keySet());
    Collections.sort(result);
    return result;
  }

  @Override
  public IMusicOperations clone() {
    return new Music(this.toneList, this.noteMap, this.tempo);
  }

  /**
   * Check if a starting BEAT is not negative, do nothing if true; else throws an exception.
   *
   * @param start the starting BEAT being checked
   * @throws IllegalArgumentException thrown when the starting BEAT is less than 0
   */
  private void checkStartingBeat(int start) {
    if (start < 0) {
      throw new IllegalArgumentException("Please enter a positive integer for starting BEAT.");
    }
  }

  /**
   * Check if there are any of the same notes that starts at this BEAT already exists in the music.
   *
   * @param newNote the new notes being checked by
   * @param start   the starting BEAT of the new note
   * @param s       the BEAT being checked to see if it has a note that is the same as the new note
   * @throws IllegalArgumentException when the note with the BEAT does not exist in this music
   */
  private void checkNoteExist(Note newNote, int start, Set s) {
    if (!this.noteMap.containsKey(start) || !s.contains(newNote)) {
      throw new IllegalArgumentException("Such note does not exist.");
    }
  }

  /**
   * Add a note to the a BEAT to the noteMap, mapped by the starting BEAT.
   * First checks to see if the music has the new starting BEAT, and add the note to that BEAT
   * if the BEAT exists. If the BEAT does not exist in the noteMap, add an new entry with the
   * starting BEAT as the key and the note as a new BEAT containing the new note as the value.
   *
   * @param newNote  the new note being added to a BEAT in the noteMap
   * @param newStart the starting BEAT of the note, it is the key of the Beat
   */
  private void addToBeat(Note newNote, int newStart) {
    if (!this.noteMap.containsKey(newStart)) {
      noteMap.put(newStart, new Set(newNote));
    } else {
      this.noteMap.get(newStart).add(newNote);
    }
  }

  /**
   * Check if the music has any notes.
   *
   * @return boolean, true if there is no notes in the music.
   */
  private boolean emptyMusic() {
    boolean result = true;
    for (Set notes : this.noteMap.values()) {
      result = notes.isEmpty() && result;
    }
    return result;
  }

  /**
   * TODO:
   */
  public HashMap<Integer, Repeat> getRepeats() {
    return (HashMap<Integer, Repeat>) repeats.clone();
  }

  /**
   * Adds a repeat at the current location.
   */
  public void addRepeat(int startingPosition, RepeatType type) {
    if (type == RepeatType.BOTH) {
      repeats.put(startingPosition, new DoubleRepeat());
    } else {
      repeats.put(startingPosition, new SingleRepeat(type));
    }
  }


  /**
   * Builder class to convert midi-text into commands that our model can interpret.
   */
  public static final class Builder implements CompositionBuilder<IMusicOperations> {
    IMusicOperations op = new Music();


    @Override
    public IMusicOperations build() {
      return op.clone();
    }

    @Override
    public CompositionBuilder<IMusicOperations> setTempo(int tempo) {
      op.setTempo(tempo);
      return this;
    }

    @Override
    public CompositionBuilder<IMusicOperations> addNote(int start, int end, int instrument,

                                                        int pitch, int volume) {
      if (end - start <= 0) {
        return this;
      }
      op.addNote(new Note(Pitch.values()[pitch % 12], (pitch / 12) - 1,
              (end - start), instrument, volume), start);
      return this;
    }


  }
}

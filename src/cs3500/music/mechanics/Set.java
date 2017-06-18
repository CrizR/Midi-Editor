package cs3500.music.mechanics;

import java.util.ArrayList;

/**
 * The Set class represents a set of music notes that start at the same BEAT. The notes are
 * stored as an arrayList of notes. A set must have at least a music note when being constructed,
 * and other notes can be added or deleted.
 */
public class Set {
  private ArrayList<Note> notes;


  /**
   * Constructs a set, by passing in a note. The note is stored in the Array list.
   *
   * @param note the music note that is in this set
   */
  public Set(Note note) {
    notes = new ArrayList<>();
    notes.add(note);
  }

  /**
   * Constructs a set given an arraylist of notes to start with,
   *
   * @param notes Represents the arraylists of notes to initialize the set with.
   */
  public Set(ArrayList<Note> notes) {
    this.notes = notes;
  }

  /**
   * Gets the array list of the notes that are in this set.
   *
   * @return an array list of the notes in this set
   */
  public ArrayList<Note> getNotes() {
    ArrayList<Note> result = new ArrayList<>();
    for (Note n : notes) {
      result.add(new Note(n.getPitch(), n.getOctave(),
              n.getDuration(), n.getInstrument(), n.getVolume()));
    }
    return result;
  }

  /**
   * Gets the array list of the notes that are active and in this set.
   *
   * @return an array list of the active notes in this set
   */

  public ArrayList<Note> activeNotes(int startBeat, int currentBeat) {
    ArrayList<Note> result = new ArrayList<>();
    for (Note n : notes) {
      if (n.active(startBeat, currentBeat)) {
        result.add(new Note(n.getPitch(), n.getOctave(), n.getDuration(), n.getInstrument(),
                n.getVolume()));
      }
    }
    return result;

  }

  /**
   * Add a music note to this set.
   *
   * @param note the note to be added to this set
   */
  public void add(Note note) {
    this.notes.add(note);
  }

  /**
   * Checks if this set has a certain note.
   *
   * @param n the note being checked
   * @return boolean: true if this set contains this note
   */
  public boolean contains(Note n) {
    return this.notes.contains(n);
  }

  /**
   * Remove a certain note from this set.
   *
   * @param n the note being removed
   */
  public void remove(Note n) {
    this.notes.remove(n);
  }

  /**
   * Check if this set is empty. It is empty if the array list does not have any notes.
   *
   * @return true if this et is empty.
   */
  public boolean isEmpty() {
    return this.notes.isEmpty();
  }

  /**
   * Check if this set of notes has any notes in a certain tone. Return true if there is no
   * notes with the tone
   *
   * @param tone the tone being checked for
   * @return boolean true if this set has no notes with this tone
   */
  public boolean doesNotHaveTone(String tone) {
    boolean acc = true;
    for (Note n : notes) {
      acc = !n.getTone().equals(tone) && acc;
    }
    return acc;
  }

  /**
   * Clones the set.
   *
   * @return the set with references to new Notes.
   */
  public Set copy() {
    ArrayList<Note> res = new ArrayList<>();
    for (Note n : this.notes) {
      res.add(n.copy());
    }
    return new Set(res);
  }
}
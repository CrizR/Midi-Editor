package cs3500.music.mechanics;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This is a model that creates that visualize the notes in the console with String.
 * This console view only shows the notes that are in the music, the column represents
 * the pitch and the rows represents the beats. The first and the last column is never empty
 * and the last row is also never empty. The start of a note is a X and the rest of a note is | .
 */
public class ConsoleVisualizer {
  private HashMap<String, Integer> pitchIndex;
  private int lastBeat;
  private ArrayList<String> tones;
  private ArrayList<ArrayList<String>> visual;
  private HashMap<Integer, Set> noteMap;

  /**
   * Constructs the consoleVisualizer by initializing the variables. Creates an empty visual 2D
   * Array, the column represents the pitches, and the row represents the BEAT number. The width
   * of the array depends on how many pitches there are and the height depends on how many beats
   * do the notes have.
   * <p></p>
   * Also creates a pitch index HashMap that holds the correct index for each pitch
   * that can be looked up when trying to draw the notes corresponding to the right pitch column,
   * because noteMap is a hashMap and it has no orders.
   *
   * @param lastBeat the last BEAT out of all the notes to be drawn in this visualization
   * @param tones    the list of tones to be drawn for this visualization
   * @param noteMap  the map of notes to be drawn for this visualization
   */
  public ConsoleVisualizer(int lastBeat, ArrayList<String> tones,
                           HashMap<Integer, Set> noteMap) {

    this.visual = new ArrayList<>();
    this.pitchIndex = new HashMap<>();
    this.lastBeat = lastBeat;
    this.noteMap = noteMap;
    this.tones = tones;
    int counter = 1;

    for (int i = 0; i <= this.tones.size(); i++) {
      visual.add(new ArrayList<>());
      for (int j = 0; j <= lastBeat + 1; j++) {
        visual.get(i).add("     ");
      }
    }

    for (String p : tones) {
      pitchIndex.put(p, counter);
      counter++;
    }
  }

  /**
   * This methods renders all the notes in the console with strings. It draws the pitch heading,
   * the number column, and then all the notes by placing the strings into the corresponding index
   * in the visual 2D array. Then it appends everything in the 2D array into a StringBuilder and
   * gives the string using toString.
   *
   * @return String the console string view of all of the notes
   */
  public String render() {
    StringBuilder result = new StringBuilder();
    this.drawPitchRow();
    this.drawNumberColumn();
    this.drawNotes();
    for (int j = 0; j <= this.lastBeat + 1; j++) {
      for (ArrayList<String> aVisual : visual) {
        result.append(aVisual.get(j));
      }
    }
    return result.toString();
  }

  /**
   * This method creates the first row of the visualization, which is the name of the pitches.
   * The first spot of this 2D array is set as blank. The string of pitches is in a HashMap
   * with the index as the key, and each one is pulled out by index to be added into the first
   * row of the visualization. They are centered within the five-character
   * column. I.e., "  F2 " and " G#3 " and " D#10"
   */
  private void drawPitchRow() {
    for (int i = 1; i < visual.size(); i++) {
      String pitch = this.tones.get(i - 1);
      visual.get(i).set(0, this.padPitch(pitch));
    }
  }

  /**
   * This method creates the first column of numbers of the visualization representing the beats.
   * They are printed right-justified and padded with leading spaces, that is exactly
   * as wide as necessary. (So if your piece is 999 beats long, it uses three columns of
   * characters; if it’s 1000 beats long, it uses four.)
   */
  private void drawNumberColumn() {
    int lastBeatDigit = Integer.toString(lastBeat).length();
    visual.get(0).set(0, this.makeSpaces(lastBeatDigit));
    for (int i = 1; i <= lastBeat + 1; i++) {
      String number = Integer.toString(i - 1);
      visual.get(0).set(i, "\n" + this.makeSpaces(lastBeatDigit - number.length()) + number);
    }
  }

  /**
   * This methods draws all of the notes into the 2D array visual. It loops through the
   * noteMap and puts the string of each notes into the right indices in the 2D array.
   */
  private void drawNotes() {
    for (int startingBeat : this.noteMap.keySet()) {
      for (Note note : this.noteMap.get(startingBeat).getNotes()) {
        setNoteVisual(startingBeat + 1, note.getTone(), note.getDuration());
      }
    }
  }

  /**
   * Draws a note by putting the visual of the note into the visual 2D array with
   * pitch corresponding to the correct pitch column and the beats corresponding to the
   * correct BEAT rows. The heading of the note is a X and the rest of the note is |.
   */
  private void setNoteVisual(int start, String pitch, int duration) {
    int index = pitchIndex.get(pitch);
    for (int i = 0; i < duration; i++) {
      if (i == 0) {
        visual.get(index).set(start, "  X  ");
        start++;
      } else {
        visual.get(index).set(start, "  |  ");
        start++;
      }
    }
  }

  /**
   * This method creates spaces based on the size given. For example, it creates 3 spaces of given
   * the integer 3.
   *
   * @param size the amount of spaces needed to be created
   * @return the spaces of strings created based on the number give
   */
  private String makeSpaces(int size) {
    StringBuilder result = new StringBuilder("");
    for (int i = 0; i < size; i++) {
      result.append(" ");
    }
    return result.toString();
  }

  /**
   * This method pads a pitch with the correct spaces before and after.
   * Since the pitches are printed in a 5 spaces column, depending on the length of the string,
   * there could be different amount of spaces before. I.e., "  F2 " and " G#3 " and " D#10"
   *
   * @param pitch the String, the pitch being padded with spaces
   * @return the string with the spaces padded
   */
  private String padPitch(String pitch) {
    switch (pitch.length()) {
      case 2:
        return "  " + pitch + " ";
      case 3:
        return " " + pitch + " ";
      default:
        return " " + pitch;
    }
  }
}

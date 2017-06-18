package cs3500.music.mechanics;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents the range of tones in music, a tone, represented with Strings,
 * is made of the string of a Pitch and an octave as string. For example a tone could be A3, or C#3.
 * This class has a deque of strings (tones) to keep track of all of the tones in a music.
 * The first and the last tone both have notes that are in them, and every tone in between
 * will be in the deque regardless if they have notes in them from the noteMap.
 */
public class ToneRange {
  private ArrayDeque<String> tones;

  /**
   * Construct a tone list, by initializing an empty array deque of string tones.
   */
  public ToneRange() {
    tones = new ArrayDeque<>();
  }

  /**
   * Creates a ToneRange given the tones to initialize it at.
   *
   * @param tones Represents the tones to start with.
   */
  public ToneRange(ArrayDeque<String> tones) {
    this.tones = tones;
  }

  /**
   * Gets an ArrayList of the this tone deque. It puts every string tone in this deque
   * and puts them in an ArrayList with the original order.
   *
   * @return the ArrayList of string tones from the Deque in the right order.
   */
  public ArrayList<String> getList() {
    ArrayList<String> result = new ArrayList<>();
    result.addAll(tones);
    return result;
  }

  /**
   * This method adds the note's tone to this tone deque if the tone is not already
   * in the deque. It does nothing, if the deque already has the tone. If this deque is empty,
   * just add it.
   * <p></p>
   * If this new tone does not exist in the deque, add the new tone and every tone
   * in between either the first of the deque or the last of the deque in the right order.
   * For example, if the new tone is after the last existing tone, it will adding all the tone
   * between the last tone and the new tone to the deque. And this goes the same for adding a
   * tone that is before the deque. The order of tones is determined by the tone index from the
   * Pitch class.
   *
   * @param note the tone of the note that is being added to the tone deque
   */
  public void addTone(Note note) {
    this.addToneIfEmpty(note);

    if (!tones.contains(note.getTone())) {
      String firstTone = this.tones.peekFirst();
      String lastTone = this.tones.peekLast();

      int firstIndex = Pitch.toneIndex.indexOf(firstTone);
      int lastIndex = Pitch.toneIndex.indexOf(lastTone);
      int newIndex = note.toneIndex();

      this.addToneHelper(newIndex, firstIndex, lastIndex);
    }
  }

  /**
   * This method deletes a string tone from the tone deque if the tone is the first or the last
   * of the tone deque. If this tone is the last of the deque, delete the tone from the deque
   * but also check if the following tone should be deleted. The next tone should be deleted
   * if there is no notes with this tone in the noteMap, and it will keep checking to delete
   * the next one until it reaches a tone that is in the noteMap. It stops deleting when the break
   * statement hits so it scales properly.
   * <p></p>
   * This goes the same if the tone is the first on deque. It does nothing if the tone is not
   * the first nor the last.
   *
   * @param tone    the tone to be deleted
   * @param noteMap the noteMap to look up if there is any notes with a certain tone
   */
  public void deleteTone(String tone, HashMap<Integer, Set> noteMap) {
    String firstTone = this.tones.peekFirst();
    String lastTone = this.tones.peekLast();
    int firstIndex = Pitch.toneIndex.indexOf(firstTone);
    int lastIndex = Pitch.toneIndex.indexOf(lastTone);

    if (tone.equals(lastTone)) {
      for (int i = lastIndex; i > firstIndex; i--) {
        if (removeHelper(noteMap, i)) {
          break;
        }
      }
    } else if (tone.equals(firstTone)) {
      for (int i = firstIndex; i < lastIndex; i++) {
        if (removeHelper(noteMap, i)) {
          break;
        }
      }
    }
  }

  /**
   * This method adds a string tone to the deque when the deque is empty. If it is not empty,
   * it does nothing.
   *
   * @param note the tone of the note that is being added to the tone deque
   */
  private void addToneIfEmpty(Note note) {
    if (this.tones.isEmpty()) {
      this.tones.add(note.getTone());
    }
  }

  /**
   * This is the helper method to remove tones that are not in the notes. If there are no notes
   * with this tone, the tone will be removed and this will return false so that deleteTone
   * will keep looping to check the next tone. The index passed in is the index of the tone
   * stored and represents the tone. If the tone is not useless, this method returns true, and
   * the tone will reach a break statement to stop deleting to scale properly.
   *
   * @param index   the index of the tone the tone to be deleted
   * @param noteMap the noteMap to look up if there is any notes with a certain tone
   * @return boolean true if something
   */
  private boolean removeHelper(HashMap<Integer, Set> noteMap, int index) {
    String toneToCheck = Pitch.toneIndex.get(index);
    if (this.uselessTone(toneToCheck, noteMap)) {
      this.tones.remove(toneToCheck);
    } else {
      return true;
    }
    return false;
  }

  /**
   * This methods checks to see if a tone is useless, it is useless when there is no notes
   * from the noteMap that has this tone.
   *
   * @param tone    the tone being checked
   * @param noteMap the noteMap that has all the notes to look up
   * @return boolean true if this tone is useless
   */
  private boolean uselessTone(String tone, HashMap<Integer, Set> noteMap) {
    boolean acc = true;
    for (Set listOfNotes : noteMap.values()) {
      acc = acc && listOfNotes.doesNotHaveTone(tone);
    }
    return acc;
  }

  /**
   * This methods helps to add a tone and all the tones between either the first or the last
   * tone of the deque depending on the index of the tones. If the tone index is in between
   * the first and last index of the tones, inclusive, method does nothing.
   * <p></p>
   * If the new tone is after the last existing tone, it will add all the tones
   * between the last tone and the new tone to the deque. And this goes the same for adding a
   * tone that is before the deque. The index of the tones are based on the Arraylist index
   * from the tone class.
   *
   * @param newIndex   the index of the tone being added
   * @param firstIndex the index of the first tone in the deque
   * @param lastIndex  the index of the last tone in the deque
   */
  private void addToneHelper(int newIndex, int firstIndex, int lastIndex) {
    if (newIndex > lastIndex) {
      for (int i = lastIndex + 1; i <= newIndex; i++) {
        this.tones.add(Pitch.toneIndex.get(i));
      }
    } else if (newIndex < firstIndex) {
      for (int i = firstIndex - 1; i >= newIndex; i--) {
        this.tones.addFirst(Pitch.toneIndex.get(i));
      }
    }
  }

  @Override
  public ToneRange clone() {
    ArrayDeque<String> res = new ArrayDeque<>();
    for (String s : this.tones) {
      res.add(s);
    }
    return new ToneRange(res);
  }
}

package cs3500.music.tests;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import cs3500.music.mechanics.Note;
import cs3500.music.mechanics.Pitch;
import cs3500.music.mechanics.Set;
import cs3500.music.mechanics.ToneRange;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


/**
 * The test.txt.txt class for ToneRange.
 */
public class ToneRangeTest {
  // test.txt.txt to see if it can get an array list of String tones
  @Test
  public void testGetters() {
    Note cSharp = new Note(Pitch.C_SHARP, 4, 2, 0, 2);
    Note e = new Note(Pitch.E, 4, 2, 0, 2);
    ToneRange tones = new ToneRange();
    tones.addTone(e);
    tones.addTone(cSharp);

    assertEquals(new ArrayList<>(Arrays.asList("C#4", "D4", "D#4", "E4")), tones.getList());
  }

  // test.txt.txt if a tone will be added correctly passing in a note, the new tone comes after
  @Test
  public void testAddAfter() {
    Note a = new Note(Pitch.A, 4, 2, 0, 2);
    Note c = new Note(Pitch.C, 5, 2, 0, 2);
    ToneRange tones = new ToneRange();
    tones.addTone(c);
    tones.addTone(a);

    assertEquals(new ArrayList<>(Arrays.asList("A4", "A#4", "B4", "C5")), tones.getList());
  }

  // test.txt.txt if a tone will be added correctly passing in a note, the new tone comes before
  @Test
  public void testAddBefore() {
    Note d = new Note(Pitch.D, 5, 2, 0, 2);
    Note a = new Note(Pitch.A, 4, 2, 0, 2);
    ToneRange tones = new ToneRange();
    tones.addTone(d);
    tones.addTone(a);

    assertEquals(new ArrayList<>(Arrays.asList("A4", "A#4", "B4", "C5", "C#5", "D5")),
            tones.getList());
  }

  // test.txt.txt if nothing happens when a note has a tone that is already in the range
  @Test
  public void addNothing() {
    Note d = new Note(Pitch.D, 5, 2, 0, 2);
    Note a = new Note(Pitch.A, 4, 2, 0, 2);
    Note b = new Note(Pitch.B, 4, 2, 0, 2);
    ToneRange tones = new ToneRange();
    tones.addTone(d);
    tones.addTone(a);
    tones.addTone(b);

    assertEquals(new ArrayList<>(Arrays.asList("A4", "A#4", "B4", "C5", "C#5", "D5")),
            tones.getList());
  }

  // test.txt.txt if a tone will be deleted correctly passing in a note, the new list will scale
  // when the note map does not have the tone
  @Test
  public void deleteTest() {
    Note d = new Note(Pitch.D, 5, 2, 0, 2);
    Note a = new Note(Pitch.A, 4, 2, 0, 2);
    Set set1 = new Set(a);
    Set set2 = new Set(d);
    HashMap<Integer, Set> notes = new HashMap<>();
    notes.put(0, set1);
    notes.put(5, set2);
    ToneRange tones = new ToneRange();
    tones.addTone(d);
    tones.addTone(a);

    assertEquals(new ArrayList<>(Arrays.asList("A4", "A#4", "B4", "C5", "C#5", "D5")),
            tones.getList());

    notes.remove(5);
    tones.deleteTone("D5", notes);
    assertEquals(new ArrayList<>(Collections.singletonList("A4")),
            tones.getList());
  }

  // test.txt.txt if a tone will be deleted correctly passing in a note, the new list will scale
  // when the note map does not have the tone
  @Test
  public void deleteTest2() {
    Note c = new Note(Pitch.C, 5, 2, 0, 2);
    Note a = new Note(Pitch.A, 4, 2, 0, 2);
    Set set1 = new Set(c);
    Set set2 = new Set(a);
    HashMap<Integer, Set> notes = new HashMap<>();
    notes.put(0, set1);
    notes.put(5, set2);
    ToneRange tones = new ToneRange();
    tones.addTone(a);
    tones.addTone(c);

    assertEquals(new ArrayList<>(Arrays.asList("A4", "A#4", "B4", "C5")),
            tones.getList());

    notes.remove(5);
    tones.deleteTone("A4", notes);
    assertEquals(new ArrayList<>(Collections.singletonList("C5")),
            tones.getList());
  }

  // test.txt.txt if nothing happens when the note map still has other notes with the tone
  @Test
  public void deleteNothing() {
    Note c = new Note(Pitch.C, 5, 2, 0, 2);
    Note a = new Note(Pitch.A, 4, 2, 0, 2);
    Set set1 = new Set(c);
    Set set2 = new Set(a);
    HashMap<Integer, Set> notes = new HashMap<>();
    notes.put(0, set1);
    notes.put(5, set2);
    ToneRange tones = new ToneRange();
    tones.addTone(a);
    tones.addTone(c);

    assertEquals(new ArrayList<>(Arrays.asList("A4", "A#4", "B4", "C5")),
            tones.getList());

    notes.remove(5);
    assertEquals(new ArrayList<>(Arrays.asList("A4", "A#4", "B4", "C5")),
            tones.getList());
  }

  @Test
  public void testClone() {
    Note a = new Note(Pitch.A, 4, 2, 0, 2);
    Note c = new Note(Pitch.C, 5, 2, 0, 2);
    ToneRange tones = new ToneRange();
    tones.addTone(c);
    tones.addTone(a);
    ToneRange tones2 = tones.clone();
    assertEquals(tones.getList(), tones2.getList());
    tones2.addTone(new Note());
    assertNotEquals(tones.getList(), tones2.getList());
  }
}

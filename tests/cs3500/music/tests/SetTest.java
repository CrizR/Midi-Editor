package cs3500.music.tests;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import cs3500.music.mechanics.Note;
import cs3500.music.mechanics.Pitch;
import cs3500.music.mechanics.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Test class for the Set class.
 */
public class SetTest {
  // test.txt getters returns the primitive value
  @Test
  public void getNotesTest() {
    Note cSharp = new Note(Pitch.C_SHARP, 4, 2, 0, 2);
    Set set1 = new Set(cSharp);

    assertEquals(new ArrayList<>(Collections.singletonList(
            new Note(Pitch.C_SHARP, 4, 2, 0, 2))), set1.getNotes());
  }

  // test.txt adding a note to this set
  @Test
  public void testAdd() {
    Note cSharp = new Note(Pitch.C_SHARP, 4, 2, 0, 2);
    Note e3 = new Note(Pitch.E, 3, 2, 0, 2);
    Set set1 = new Set(cSharp);

    assertEquals(new ArrayList<>(Collections.singletonList(
            new Note(Pitch.C_SHARP, 4, 2, 0, 2))), set1.getNotes());
    set1.add(e3);
    assertEquals(new ArrayList<>(Arrays.asList(
            new Note(Pitch.C_SHARP, 4, 2, 0, 2),
            new Note(Pitch.E, 3, 2, 0, 2))), set1.getNotes());
  }

  // test.txt if it properly checks contains
  @Test
  public void testContains() {
    Note cSharp = new Note(Pitch.C_SHARP, 4, 2, 0, 2);
    Note e3 = new Note(Pitch.E, 3, 2, 0, 2);
    Set set1 = new Set(cSharp);

    set1.contains(cSharp);
    assertEquals(true, set1.contains(cSharp));
    assertEquals(false, set1.contains(e3));
  }

  // check if it knows if it is empty
  @Test
  public void emptyTest() {
    Note cSharp = new Note(Pitch.C_SHARP, 4, 2, 0, 2);
    Set set1 = new Set(cSharp);

    assertEquals(false, set1.isEmpty());
    set1.remove(cSharp);
    assertEquals(true, set1.isEmpty());
  }

  // check if it removes correctly
  @Test
  public void removeTest() {
    Note cSharp = new Note(Pitch.C_SHARP, 4, 2, 0, 2);
    Set set1 = new Set(cSharp);

    assertEquals(true, set1.contains(cSharp));
    set1.remove(cSharp);
    assertEquals(false, set1.contains(cSharp));
  }

  // test.txt if it can detect if a pitch is in this set
  @Test
  public void pitchTest() {
    Note cSharp = new Note(Pitch.C_SHARP, 4, 2, 0, 2);
    String p1 = "C#4";
    String p2 = "E4";
    Set set1 = new Set(cSharp);

    assertEquals(false, set1.doesNotHaveTone(p1));
    assertEquals(true, set1.doesNotHaveTone(p2));
  }

  @Test
  // tests a cloned set
  public void testCloneSet() {
    Note cSharp = new Note(Pitch.C_SHARP, 4, 2, 0, 2);
    Note e3 = new Note(Pitch.E, 3, 2, 0, 2);
    Set set1 = new Set(cSharp);
    Set set2 = set1.copy();
    assertEquals(set1.getNotes(), set2.getNotes());
    set2.add(e3);
    assertNotEquals(set1.getNotes(), set2.getNotes());
  }
}

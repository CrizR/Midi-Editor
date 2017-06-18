package cs3500.music.tests;

import org.junit.Test;

import cs3500.music.mechanics.Note;
import cs3500.music.mechanics.Pitch;

import static org.junit.Assert.assertEquals;

/**
 * This is the test.txt.txt class to test.txt.txt the methods in the Note Class.
 */
public class NoteTests {
  int instrument = 0;
  int volume = 0;

  // test.txt.txt invalid octave
  @Test(expected = IllegalArgumentException.class)
  public void invalidOctave1() {
    Note invalid = new Note(Pitch.C_SHARP, 0, 2, instrument, volume);
  }

  // test.txt.txt invalid octave
  @Test(expected = IllegalArgumentException.class)
  public void invalidOctave2() {
    Note invalid = new Note(Pitch.C_SHARP, 11, 2, instrument, volume);
  }

  // test.txt.txt invalid duration
  @Test(expected = IllegalArgumentException.class)
  public void invalidDuration() {
    Note invalid = new Note(Pitch.C_SHARP, 11, 0, instrument, volume);
  }


  // test.txt.txt getters returns the primitive value
  @Test
  public void testGetters() {
    Note cSharp = new Note(Pitch.C_SHARP, 4, 2, instrument, volume);
    assertEquals(Pitch.C_SHARP, cSharp.getPitch());
    assertEquals(4, cSharp.getOctave());
    assertEquals(2, cSharp.getDuration());
    assertEquals("C#4", cSharp.getTone());
  }

  // test.txt.txt equality
  @Test
  public void equalsTest() {
    Note cSharp = new Note(Pitch.C_SHARP, 4, 2, instrument, volume);
    Note cSharp1 = new Note(Pitch.C_SHARP, 4, 2, instrument, volume);
    Note c = new Note(Pitch.C, 4, 2, instrument, volume);
    assertEquals(true, cSharp.equals(cSharp));
    assertEquals(true, cSharp.equals(cSharp1));
    assertEquals(false, cSharp.equals(c));
  }

  // test.txt.txt hashcode
  @Test
  public void hashTest() {
    Note cSharp = new Note(Pitch.C_SHARP, 4, 2, instrument, volume);
    assertEquals(63, cSharp.hashCode());
  }

  // test.txt.txt the printing out the pitch in string with the octave
  @Test
  public void stringPitchTest() {
    Note cSharp = new Note(Pitch.C_SHARP, 4, 2, instrument, volume);
    assertEquals("C#4", cSharp.getTone());
  }

  // test.txt.txt getting the index of the pitch of a note
  @Test
  public void pitchIndexTest() {
    Note cSharp = new Note(Pitch.C_SHARP, 4, 2, instrument, volume);
    assertEquals(61, cSharp.toneIndex());
  }

  @Test
  //tests the clone method
  public void cloneNote() {
    Note cSharp = new Note(Pitch.C_SHARP, 4, 2, instrument, volume);
    Note csharpclone = cSharp.copy();
    assertEquals(cSharp.getDuration(), csharpclone.getDuration());
    assertEquals(cSharp.getVolume(), csharpclone.getVolume());
    assertEquals(cSharp.getOctave(), csharpclone.getOctave());
    assertEquals(cSharp.getInstrument(), csharpclone.getInstrument());
    assertEquals(cSharp.getPitch(), csharpclone.getPitch());
  }
}

package cs3500.music.tests;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import cs3500.music.mechanics.Note;
import cs3500.music.mechanics.Pitch;
import cs3500.music.model.IMusicOperations;
import cs3500.music.model.Music;

import static org.junit.Assert.assertEquals;

/**
 * This the test.txt.txt class to test.txt.txt the IMusicOperation interface methods.
 */
public class MusicTests {

  // test.txt.txt when a node is successfully added
  // add a note with the last BEAT longer than the current last BEAT and see if it scales
  // add a note that is in a new octave and see if the new octave column is added
  @Test
  public void addNoteSuccess() {
    Music song = new Music();
    Note gSharp3 = new Note(Pitch.G_SHARP, 3, 2, 0, 2);
    Note e4 = new Note(Pitch.E, 4, 5, 0, 2);
    Note aSharp4 = new Note(Pitch.A_SHARP, 4, 5, 0, 2);
    Note c2 = new Note(Pitch.C, 2, 3, 0, 2);

    assertEquals("", song.visualize());

    song.addNote(gSharp3, 0);
    assertEquals("  G#3 \n" +
            "0  X  \n" +
            "1  |  ", song.visualize());

    song.addNote(e4, 5);
    assertEquals("  G#3   A3  A#3   B3   C4  C#4   D4  D#4   E4 \n" +
            "0  X                                          \n" +
            "1  |                                          \n" +
            "2                                             \n" +
            "3                                             \n" +
            "4                                             \n" +
            "5                                          X  \n" +
            "6                                          |  \n" +
            "7                                          |  \n" +
            "8                                          |  \n" +
            "9                                          |  ", song.visualize());

    song.addNote(aSharp4, 3);
    assertEquals(
            "  G#3   A3  A#3   B3   C4  C#4   D4  D#4   E4   F4  F#4   G4  G#4   A4  " +
                    "A#4 \n" +
                    "0  X                                                       " +
                    "                 \n" +
                    "1  |                                                         " +
                    "               \n" +
                    "2                                                            " +
                    "               \n" +
                    "3                                                              " +
                    "          X  \n" +
                    "4                                                               " +
                    "         |  \n" +
                    "5                                          X                    " +
                    "         |  \n" +
                    "6                                          |                    " +
                    "         |  \n" +
                    "7                                          |                    " +
                    "         |  \n" +
                    "8                                          |                    " +
                    "            \n" +
                    "9                                          |                                ",
            song.visualize());
    song.addNote(c2, 3);
    assertEquals("   C2  C#2   D2  D#2   E2   F2  F#2   G2  G#2   A2  A#2   B2   C3  " +
            "C#3   D3  D#3   E3   F3  F#3   G3  G#3   A3  A#3   B3   C4  C#4   D4  D#4   E4   " +
            "F4  F#4   G4  G#4   A4  A#4 \n" +
            "0                                                                                  " +
            "                    X                                                              " +
            "          \n" +
            "1                                                                                  " +
            "                    |                                                              " +
            "          \n" +
            "2                                                                                  " +
            "                                                                                   " +
            "          \n" +
            "3  X                                                                               " +
            "                                                                                   " +
            "       X  \n" +
            "4  |                                                                               " +
            "                                                                                   " +
            "       |  \n" +
            "5  |                                                                               " +
            "                                                            X                      " +
            "       |  \n" +
            "6                                                                                 " +
            "                                                             |                     " +
            "        |  \n" +

            "7                                                                                  " +
            "                                                            |                      " +
            "       |  \n" +
            "8                                                                                  " +
            "                                                            |                      " +
            "          \n" +
            "9                                                                                  " +
            "                                                            |                      " +
            "          ", song.visualize());
  }

  // Throw exception when a node with a negative starting BEAT is being added
  @Test(expected = IllegalArgumentException.class)
  public void negativeStartingBeat() {
    Music song = new Music();
    Note cSharp = new Note(Pitch.C_SHARP, 3, 2, 0, 2);

    song.addNote(cSharp, -1);
  }

  // delete note successfully
  @Test
  public void deleteNoteSuccessfully() {
    Music song = new Music();
    Note gSharp3 = new Note(Pitch.G_SHARP, 3, 2, 0, 2);
    Note e4 = new Note(Pitch.E, 4, 5, 0, 2);
    Note b3 = new Note(Pitch.B, 3, 3, 0, 2);

    song.addNote(gSharp3, 0);
    song.addNote(b3, 2);
    song.addNote(e4, 5);
    assertEquals(
            "  G#3   A3  A#3   B3   C4  C#4   D4  D#4   E4 \n" +
                    "0  X                                          \n" +
                    "1  |                                          \n" +
                    "2                 X                           \n" +
                    "3                 |                           \n" +
                    "4                 |                           \n" +
                    "5                                          X  \n" +
                    "6                                          |  \n" +
                    "7                                          |  \n" +
                    "8                                          |  \n" +
                    "9                                          |  ", song.visualize());

    song.deleteNote(e4, 5);
    assertEquals("  G#3   A3  A#3   B3 \n" +
            "0  X                 \n" +
            "1  |                 \n" +
            "2                 X  \n" +
            "3                 |  \n" +
            "4                 |  ", song.visualize());

    song.deleteNote(gSharp3, 0);
    assertEquals("   B3 \n" +
            "0     \n" +
            "1     \n" +
            "2  X  \n" +
            "3  |  \n" +
            "4  |  ", song.visualize());

    song.deleteNote(b3, 2);
    assertEquals("", song.visualize());
  }

  // Throw exception when deleting a node with a negative starting BEAT
  @Test(expected = IllegalArgumentException.class)
  public void negativeStartingBeatDelete() {
    Music song = new Music();
    Note cSharp = new Note(Pitch.C_SHARP, 3, 2, 0, 2);
    song.addNote(cSharp, 0);
    song.addNote(cSharp, -1);
  }

  // Throw exception when deleting a node that does not exist
  @Test(expected = IllegalArgumentException.class)
  public void deleteNonExistingNote() {
    Music song = new Music();
    Note cSharp = new Note(Pitch.C_SHARP, 3, 2, 0, 2);
    Note none = new Note(Pitch.C_SHARP, 1, 1, 0, 2);
    song.addNote(cSharp, 0);
    song.deleteNote(none, 0);
  }

  // delete note successfully
  // test.txt.txt to see when music has the same note,
  // OVERLAPPING NOTE NEED TO BE COMBINED
  @Test
  public void combineMusicTest() {
    Music song1 = new Music();
    Note gSharp = new Note(Pitch.G_SHARP, 3, 2, 0, 2);
    Note e4 = new Note(Pitch.E, 4, 5, 0, 2);
    Music song2 = new Music();
    Note e4Overlap = new Note(Pitch.E, 4, 3, 0, 2);
    Note c4 = new Note(Pitch.C, 4, 2, 0, 2);

    song1.addNote(gSharp, 0);
    song1.addNote(e4, 5);
    assertEquals(
            "  G#3   A3  A#3   B3   C4  C#4   D4  D#4   E4 \n" +
                    "0  X                                          \n" +
                    "1  |                                          \n" +
                    "2                                             \n" +
                    "3                                             \n" +
                    "4                                             \n" +
                    "5                                          X  \n" +
                    "6                                          |  \n" +
                    "7                                          |  \n" +
                    "8                                          |  \n" +
                    "9                                          |  ", song1.visualize());

    song2.addNote(gSharp, 0);
    song2.addNote(e4Overlap, 4);
    song2.addNote(c4, 4);
    assertEquals(
            "  G#3   A3  A#3   B3   C4  C#4   D4  D#4   E4 \n" +
                    "0  X                                          \n" +
                    "1  |                                          \n" +
                    "2                                             \n" +
                    "3                                             \n" +
                    "4                      X                   X  \n" +
                    "5                      |                   |  \n" +
                    "6                                          |  ", song2.visualize());
    song1.combine(song2);
    assertEquals(
            "  G#3   A3  A#3   B3   C4  C#4   D4  D#4   E4 \n" +
                    "0  X                                          \n" +
                    "1  |                                          \n" +
                    "2                                             \n" +
                    "3                                             \n" +
                    "4                      X                   X  \n" +
                    "5                      |                   X  \n" +
                    "6                                          |  \n" +
                    "7                                          |  \n" +
                    "8                                          |  \n" +
                    "9                                          |  ", song1.visualize());
    song1.deleteNote(e4, 5);
    assertEquals(
            "  G#3   A3  A#3   B3   C4  C#4   D4  D#4   E4 \n" +
                    "0  X                                          \n" +
                    "1  |                                          \n" +
                    "2                                             \n" +
                    "3                                             \n" +
                    "4                      X                   X  \n" +
                    "5                      |                   |  \n" +
                    "6                                          |  ", song1.visualize());
  }

  // test.txt.txt combine music by adding a song after
  @Test
  public void addSongNextTest() {
    Music song1 = new Music();
    Note gSharp3 = new Note(Pitch.G_SHARP, 3, 2, 0, 2);
    Note e4 = new Note(Pitch.E, 4, 5, 0, 2);
    Music song2 = new Music();
    Note a3 = new Note(Pitch.A, 3, 3, 0, 2);
    Note c4 = new Note(Pitch.C, 4, 2, 0, 2);

    song1.addNote(gSharp3, 0);
    song1.addNote(e4, 5);
    assertEquals("  G#3   A3  A#3   B3   C4  C#4   D4  D#4   E4 \n" +
            "0  X                                          \n" +
            "1  |                                          \n" +
            "2                                             \n" +
            "3                                             \n" +
            "4                                             \n" +
            "5                                          X  \n" +
            "6                                          |  \n" +
            "7                                          |  \n" +
            "8                                          |  \n" +
            "9                                          |  ", song1.visualize());

    song2.addNote(gSharp3, 0);
    song2.addNote(a3, 2);
    song2.addNote(c4, 4);
    assertEquals("  G#3   A3  A#3   B3   C4 \n" +
            "0  X                      \n" +
            "1  |                      \n" +
            "2       X                 \n" +
            "3       |                 \n" +
            "4       |              X  \n" +
            "5                      |  ", song2.visualize());

    song1.playNext(song2);
    assertEquals("   G#3   A3  A#3   B3   C4  C#4   D4  D#4   E4 \n" +
            " 0  X                                          \n" +
            " 1  |                                          \n" +
            " 2                                             \n" +
            " 3                                             \n" +
            " 4                                             \n" +
            " 5                                          X  \n" +
            " 6                                          |  \n" +
            " 7                                          |  \n" +
            " 8                                          |  \n" +
            " 9                                          |  \n" +
            "10                                             \n" +
            "11  X                                          \n" +
            "12  |                                          \n" +
            "13       X                                     \n" +
            "14       |                                     \n" +
            "15       |              X                      \n" +
            "16                      |                      ", song1.visualize());
  }

  // test.txt.txt editing notes
  @Test
  public void editTest() {
    Music song = new Music();
    Note gSharp3 = new Note(Pitch.G_SHARP, 3, 2, 0, 2);
    Note e4 = new Note(Pitch.E, 4, 5, 0, 2);
    Note aSharp4 = new Note(Pitch.A_SHARP, 4, 5, 0, 2);
    song.addNote(gSharp3, 0);
    song.addNote(e4, 2);

    assertEquals("  G#3   A3  A#3   B3   C4  C#4   D4  D#4   E4 \n" +
            "0  X                                          \n" +
            "1  |                                          \n" +
            "2                                          X  \n" +
            "3                                          |  \n" +
            "4                                          |  \n" +
            "5                                          |  \n" +
            "6                                          |  ", song.visualize());

    song.editNote(gSharp3, 0, aSharp4, 2);

    assertEquals("   E4   F4  F#4   G4  G#4   A4  A#4 \n" +
            "0                                   \n" +
            "1                                   \n" +
            "2  X                             X  \n" +
            "3  |                             |  \n" +
            "4  |                             |  \n" +
            "5  |                             |  \n" +
            "6  |                             |  ", song.visualize());
  }

  // visualize the music in console
  // empty string for a music with no notes
  // test.txt.txt to see if prints starting from the correct pitch
  // test.txt.txt to see if add a note with a next octave prints the next octave
  // add an octave that jumps an octave
  // right padding depending on the BEAT digits
  @Test
  public void printMusic() {
    Music song = new Music();
    assertEquals("", song.visualize());

    Note g4 = new Note(Pitch.G, 3, 4, 0, 2);
    Note e4 = new Note(Pitch.E, 4, 2, 0, 2);
    Note b2 = new Note(Pitch.B, 2, 2, 0, 2);

    song.addNote(g4, 0);
    assertEquals("   G3 \n" +
            "0  X  \n" +
            "1  |  \n" +
            "2  |  \n" +
            "3  |  ", song.visualize());

    song.addNote(e4, 0);
    assertEquals("   G3  G#3   A3  A#3   B3   C4  C#4   D4  D#4   E4 \n" +
            "0  X                                            X  \n" +
            "1  |                                            |  \n" +
            "2  |                                               \n" +
            "3  |                                               ", song.visualize());

    song.addNote(b2, 9);
    assertEquals(
            "    B2   C3  C#3   D3  D#3   E3   F3  F#3   G3  G#3   A3  A#3   B3   C4  " +
                    "C#4   D4  D#4   E4 \n" +
                    " 0                                          X                               " +
                    "        " +
                    "     X  \n" +
                    " 1                                          |                               " +
                    "        " +
                    "     |  \n" +
                    " 2                                          |                               " +
                    "        " +
                    "        \n" +
                    " 3                                          |                               " +
                    "        " +
                    "        \n" +
                    " 4                                                                         " +
                    "         " +
                    "        \n" +
                    " 5                                                                         " +
                    "         " +
                    "        \n" +
                    " 6                                                                        " +
                    "          " +
                    "        \n" +
                    " 7                                                                       " +
                    "           " +
                    "        \n" +
                    " 8                                                                        " +
                    "          " +
                    "        \n" +
                    " 9  X                                                                     " +
                    "          " +
                    "        \n" +
                    "10  |                                                                     " +
                    "          " +
                    "        ", song.visualize());
  }

  // test.txt.txt if it can get the last BEAT of the music
  @Test
  public void testLastBeat() {
    Music song = new Music();
    Note gSharp3 = new Note(Pitch.G_SHARP, 3, 2, 0, 2);
    Note e4 = new Note(Pitch.E, 4, 10, 0, 2);
    song.addNote(gSharp3, 3);
    song.addNote(e4, 0);

    assertEquals(9, song.lastBeat());
  }

  // test.txt.txt if it can get the last BEAT of the music
  @Test
  public void testGetTones() {
    Music song = new Music();
    Note gSharp3 = new Note(Pitch.G_SHARP, 3, 2, 0, 2);
    Note e4 = new Note(Pitch.E, 4, 10, 0, 2);
    song.addNote(gSharp3, 3);
    song.addNote(e4, 0);

    assertEquals(new ArrayList<>(Arrays.asList("G#3", "A3", "A#3", "B3", "C4", "C#4",
            "D4", "D#4", "E4")), song.getTones());
  }

  // test.txt.txt if it can get a hashMap of notes
  @Test
  public void testGetNotes() {
    Music song = new Music();
    Note gSharp3 = new Note(Pitch.G_SHARP, 3, 2, 0, 2);
    Note e4 = new Note(Pitch.E, 4, 10, 0, 2);
    song.addNote(gSharp3, 3);
    song.addNote(e4, 0);
    HashMap<String, Note> result = new HashMap<>();

    result.put("E4", e4);
    assertEquals(result, song.getNotes(0));
  }


  @Test
  //tests set temp
  public void testSetTempo() {
    IMusicOperations op = new Music();
    op.setTempo(5);
    assertEquals(5, op.getTempo());
  }

  @Test
  //tests get tempo
  public void testGetTempo() {
    IMusicOperations op = new Music();
    assertEquals(0, op.getTempo());
  }

  @Test
  //test.txt.txt the active notes method
  public void testActiveNotes() {
    IMusicOperations op = new Music();
    Note gSharp3 = new Note(Pitch.G_SHARP, 3, 2, 0, 2);
    Note e4 = new Note(Pitch.E, 4, 5, 0, 2);
    Note aSharp4 = new Note(Pitch.A_SHARP, 4, 5, 0, 2);
    Note g4 = new Note(Pitch.G, 3, 4, 0, 2);
    Note b2 = new Note(Pitch.B, 2, 2, 0, 2);
    op.addNote(b2, 9);
    op.addNote(gSharp3, 0);
    op.addNote(e4, 5);
    op.addNote(aSharp4, 7);
    op.addNote(g4, 14);
    assertEquals(1, op.activeNotes(5).keySet().toArray().length);

  }

  @Test
  //tests the active notes method on the duration of one note.
  public void testActiveNotes2() {
    IMusicOperations op = new Music();
    Note gSharp3 = new Note(Pitch.G_SHARP, 3, 2, 0, 2);
    Note e4 = new Note(Pitch.E, 4, 5, 0, 2);
    Note aSharp4 = new Note(Pitch.A_SHARP, 4, 5, 0, 2);
    Note g4 = new Note(Pitch.G, 3, 4, 0, 2);
    Note b2 = new Note(Pitch.B, 2, 2, 0, 2);
    op.addNote(b2, 9);
    op.addNote(gSharp3, 0);
    op.addNote(e4, 5);
    op.addNote(aSharp4, 7);
    op.addNote(g4, 14);
    assertEquals(2, op.activeNotes(7).keySet().toArray().length);

  }

  @Test
  //tests get starting BEAT
  public void testGetStartingBeats() {
    IMusicOperations op = new Music();
    Note gSharp3 = new Note(Pitch.G_SHARP, 3, 2, 0, 2);
    Note e4 = new Note(Pitch.E, 4, 5, 0, 2);
    Note aSharp4 = new Note(Pitch.A_SHARP, 4, 5, 0, 2);
    Note g4 = new Note(Pitch.G, 3, 4, 0, 2);
    Note b2 = new Note(Pitch.B, 2, 2, 0, 2);
    op.addNote(b2, 9);
    op.addNote(gSharp3, 0);
    op.addNote(e4, 5);
    op.addNote(aSharp4, 7);
    op.addNote(g4, 14);
    ArrayList<Integer> startingBeats = new ArrayList<>(Arrays.asList(0, 5, 7, 9, 14));
    assertEquals(startingBeats, op.getStartingBeats());
  }

}

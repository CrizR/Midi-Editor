package cs3500.music.tests;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import cs3500.music.mechanics.Note;
import cs3500.music.mechanics.Pitch;
import cs3500.music.model.Music;
import cs3500.music.view.IView;
import cs3500.music.view.ViewBuilder;

import static junit.framework.TestCase.assertEquals;

/**
 * Test class for the TextView.
 */
public class TestTextView {
  private ByteArrayOutputStream baos = new ByteArrayOutputStream();

  @Test //test.txt.txt when the model is empty
  public void testEmptyModel() throws Exception {
    Music op = new Music();
    IView console = ViewBuilder.createView("console", op);
    System.setOut(new PrintStream(baos));
    console.initialize();
    assertEquals("", baos.toString());
  }


  // test.txt.txt when a node is successfully added
  @Test
  public void addNoteSuccess() {
    Music op = new Music();
    Note gSharp3 = new Note(Pitch.G_SHARP, 3, 2, 0, 2);


    IView console = ViewBuilder.createView("console", op);
    System.setOut(new PrintStream(baos));
    console.initialize();
    Assert.assertEquals("", baos.toString());

  }

  @Test
  //tests if it the printed view scales like the model does.
  public void addNoteSuccess2() {
    Music op = new Music();
    Note gSharp3 = new Note(Pitch.G_SHARP, 3, 2, 0, 2);

    op.addNote(gSharp3, 0);
    IView console = ViewBuilder.createView("console", op);
    System.setOut(new PrintStream(baos));
    console.initialize();
    Assert.assertEquals("  G#3 \n" +
            "0  X  \n" +
            "1  |  ", baos.toString());

  }

  @Test
  //tests if it the printed view scales like the model does.
  public void addNoteSuccess3() {
    Music op = new Music();
    Note e4 = new Note(Pitch.E, 4, 5, 0, 2);
    Note gSharp3 = new Note(Pitch.G_SHARP, 3, 2, 0, 2);
    op.addNote(e4, 5);
    op.addNote(gSharp3, 0);
    IView console = ViewBuilder.createView("console", op);
    System.setOut(new PrintStream(baos));
    console.initialize();
    Assert.assertEquals("  G#3   A3  A#3   B3   C4  C#4   D4  D#4   E4 \n" +
            "0  X                                          \n" +
            "1  |                                          \n" +
            "2                                             \n" +
            "3                                             \n" +
            "4                                             \n" +
            "5                                          X  \n" +
            "6                                          |  \n" +
            "7                                          |  \n" +
            "8                                          |  \n" +
            "9                                          |  ", baos.toString());

  }

  @Test
  //tests if it the printed view scales like the model does.
  public void addNoteSuccess4() {
    Music op = new Music();
    Note aSharp4 = new Note(Pitch.A_SHARP, 4, 5, 0, 2);
    Note e4 = new Note(Pitch.E, 4, 5, 0, 2);
    Note gSharp3 = new Note(Pitch.G_SHARP, 3, 2, 0, 2);
    op.addNote(gSharp3, 0);
    op.addNote(e4, 5);
    op.addNote(aSharp4, 3);
    IView console = ViewBuilder.createView("console", op);
    System.setOut(new PrintStream(baos));
    console.initialize();
    Assert.assertEquals(
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
            baos.toString());
  }

  @Test
  //tests if it the printed view scales like the model does.
  public void AddNoteSucces5() {
    Music op = new Music();
    Note aSharp4 = new Note(Pitch.A_SHARP, 4, 5, 0, 2);
    Note e4 = new Note(Pitch.E, 4, 5, 0, 2);
    Note gSharp3 = new Note(Pitch.G_SHARP, 3, 2, 0, 2);
    Note c2 = new Note(Pitch.C, 2, 3, 0, 2);
    op.addNote(gSharp3, 0);
    op.addNote(e4, 5);
    op.addNote(aSharp4, 3);
    op.addNote(c2, 3);
    IView console = ViewBuilder.createView("console", op);
    System.setOut(new PrintStream(baos));
    console.initialize();
    Assert.assertEquals("   C2  C#2   D2  D#2   E2   F2  F#2   G2  G#2   A2  A#2   B2   C3  " +
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
            "          ", baos.toString());
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
    song.deleteNote(e4, 5);
    IView console = ViewBuilder.createView("console", song);
    System.setOut(new PrintStream(baos));
    console.initialize();
    Assert.assertEquals("  G#3   A3  A#3   B3 \n" +
            "0  X                 \n" +
            "1  |                 \n" +
            "2                 X  \n" +
            "3                 |  \n" +
            "4                 |  ", baos.toString());

  }

  // delete note successfully
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
    song2.addNote(gSharp, 0);
    song2.addNote(e4Overlap, 4);
    song2.addNote(c4, 4);
    song1.combine(song2);
    IView console = ViewBuilder.createView("console", song1);
    System.setOut(new PrintStream(baos));
    console.initialize();
    Assert.assertEquals(
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
                    "9                                          |  ", baos.toString());
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

    song2.addNote(gSharp3, 0);
    song2.addNote(a3, 2);
    song2.addNote(c4, 4);
    song1.playNext(song2);

    IView console = ViewBuilder.createView("console", song1);
    System.setOut(new PrintStream(baos));
    console.initialize();

    Assert.assertEquals("   G#3   A3  A#3   B3   C4  C#4   D4  D#4   E4 \n" +
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
            "16                      |                      ", baos.toString());
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

    song.editNote(gSharp3, 0, aSharp4, 2);
    IView console = ViewBuilder.createView("console", song);
    System.setOut(new PrintStream(baos));
    console.initialize();

    Assert.assertEquals("   E4   F4  F#4   G4  G#4   A4  A#4 \n" +
            "0                                   \n" +
            "1                                   \n" +
            "2  X                             X  \n" +
            "3  |                             |  \n" +
            "4  |                             |  \n" +
            "5  |                             |  \n" +
            "6  |                             |  ", baos.toString());
  }

}

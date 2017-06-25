package cs3500.music.tests;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import cs3500.music.mechanics.ConsoleVisualizer;
import cs3500.music.mechanics.Note;
import cs3500.music.mechanics.Pitch;
import cs3500.music.mechanics.Set;

import static org.junit.Assert.assertEquals;

/**
 * This is a test.txt.txt class for consoleVisualizer.
 */
public class ConsoleVisualizerTest {
  // test.txt.txt of it draws one note and scales from the right side
  @Test
  public void renderTest() {
    ArrayList<String> tones = new ArrayList(Arrays.asList("G3"));
    Note g = new Note(Pitch.G, 3, 2, 0, 2);
    HashMap<Integer, Set> notes = new HashMap<>();
    Set set1 = new Set(g);

    notes.put(1, set1);

    ConsoleVisualizer visual = new ConsoleVisualizer(3, tones, notes);
    assertEquals("   G3 \n" +
            "0     \n" +
            "1  X  \n" +
            "2  |  \n" +
            "3     ", visual.render());
    tones.add("G#3");
    tones.add("A3");
    tones.add("A#3");
    Note a = new Note(Pitch.A_SHARP, 3, 5, 0, 2);
    Set set2 = new Set(a);
    notes.put(4, set2);
    ConsoleVisualizer visual2 = new ConsoleVisualizer(8, tones, notes);
    assertEquals("   G3  G#3   A3  A#3 \n" +
            "0                    \n" +
            "1  X                 \n" +
            "2  |                 \n" +
            "3                    \n" +
            "4                 X  \n" +
            "5                 |  \n" +
            "6                 |  \n" +
            "7                 |  \n" +
            "8                 |  ", visual2.render());
  }

  // test.txt.txt of it draws one note and scales from the left side
  @Test
  public void renderTest2() {
    ArrayList<String> tones = new ArrayList(Arrays.asList("A#3"));
    Note a = new Note(Pitch.A_SHARP, 3, 5, 0, 2);
    HashMap<Integer, Set> notes = new HashMap<>();
    Set set1 = new Set(a);
    notes.put(4, set1);

    ConsoleVisualizer visual = new ConsoleVisualizer(8, tones, notes);
    assertEquals("  A#3 \n" +
            "0     \n" +
            "1     \n" +
            "2     \n" +
            "3     \n" +
            "4  X  \n" +
            "5  |  \n" +
            "6  |  \n" +
            "7  |  \n" +
            "8  |  ", visual.render());

    ArrayList<String> tones1 = new ArrayList(Arrays.asList("G3", "G#3", "A3", "A#3"));
    Note g = new Note(Pitch.G, 3, 2, 0, 2);
    Set set2 = new Set(g);
    notes.put(1, set2);
    ConsoleVisualizer visual2 = new ConsoleVisualizer(8, tones1, notes);
    assertEquals("   G3  G#3   A3  A#3 \n" +
            "0                    \n" +
            "1  X                 \n" +
            "2  |                 \n" +
            "3                    \n" +
            "4                 X  \n" +
            "5                 |  \n" +
            "6                 |  \n" +
            "7                 |  \n" +
            "8                 |  ", visual2.render());
  }
}

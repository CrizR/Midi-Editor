package cs3500.music.mechanics;

import java.util.ArrayList;

/**
 * Represents a pitch in music. This is for the twelve distinct pitches
 * in the Western system of music. They are C C♯ D D♯ E F F♯ G G♯ A A♯ B.
 * The note C♯, pronounced “Cee-sharp” is the same note as “Dee-flat”, written D♭;
 * notes without these “accidentals” are called natural.
 */
public enum Pitch {
  C("C"), C_SHARP("C#"), D("D"), D_SHARP("D#"), E("E"), F("F"),
  F_SHARP("F#"), G("G"), G_SHARP("G#"), A("A"), A_SHARP("A#"), B("B");

  private String pitch;
  static Pitch[] ListofPitches = Pitch.values();
  public static final ArrayList<String> toneIndex = new ArrayList<>();

  /*
   * A tone, a Pitch combined with an octave from C1 to B10, is assigned to an unique index via
   * putting them in an arrayList of Strings. This is necessary when scaling the console
   * visualization because the corresponding index will indicate the order to help scale
   * the console drawing properly. For example, the last pitch is A3, and C4 is being added
   * however, every pitch from A3 to C4 will be added as well.
   * <p></p>
   * The order of tones goes from C1 C♯1 D1 D♯1 E1 F1 F♯1 G1 G♯1 A1 A♯1 B1 C2 C♯2 D2
   * and the octave goes up to 10. This array is static.
   */
  static {
    for (int i = -1; i <= 10; i++) {
      for (Pitch p : ListofPitches) {
        toneIndex.add(p.toString() + Integer.toString(i));
      }
    }
  }

  Pitch(String pitch) {
    this.pitch = pitch;
  }

//TODO JAVADOC AND ADD MODIFY FILE

  public boolean isSharp() {
    return this.pitch.contains("#");
  }

  @Override
  public String toString() {
    return this.pitch;
  }
}
package cs3500.music.mechanics;

/**
 * Represents a note in music. A note has a pitch, an octave, and a measure. A pitch represents
 * the pitch of the note. The octave is an integer, represents the octave of the note. The duration
 * is an integer, represents the duration of the note, in number of beats.
 */
public class Note {
  private Pitch pitch;
  private int octave;
  private int duration;
  private int instrument;
  private int volume;

  /**
   * Constructs a music note, and initialize the pitch, octave, duration variables with
   * the parameters passed in.
   *
   * @param pitch      the pitch for constructing this note
   * @param octave     the octave for constructing this note
   * @param duration   the measure for constructing this note
   * @param instrument the type of instrument playing the note
   * @param volume     the volume of the note.
   * @throws IllegalArgumentException if the octave is not between 1 and 10
   */
  public Note(Pitch pitch, int octave, int duration, int instrument, int volume) {
    if (octave < 1 || octave > 10 || duration < 0) {
      throw new IllegalArgumentException("Invalid note!");
    }
    this.pitch = pitch;
    this.octave = octave;
    this.duration = duration;
    this.volume = volume;
    this.instrument = instrument;
  }

  /**
   * Creates a note with default settings. This is used for testing. The default settings are
   * Pitch.C, octave 4, duration 4, instrument 2, volume 5.
   */
  public Note() {
    this.pitch = Pitch.C;
    this.octave = 4;
    this.duration = 4;
    this.instrument = 2;
    this.volume = 5;
  }

  /**
   * Determines the note's instrument.
   *
   * @return the given note's instrument as an int.
   */
  public int getInstrument() {
    return instrument;
  }

  /**
   * Determines the note's volume.
   *
   * @return the given note's volume as an int.
   */
  public int getVolume() {
    return volume;
  }

  /**
   * Gets the primitive value of this pitch.
   *
   * @return pitch the pitch of this note
   */
  public Pitch getPitch() {
    return this.pitch;
  }

  /**
   * Gets the primitive value of this octave.
   *
   * @return int the octave of this note
   */
  public int getOctave() {
    return this.octave;
  }

  /**
   * Gets the primitive value of this duration.
   *
   * @return int the duration of this note
   */
  public int getDuration() {
    return this.duration;
  }

  /**
   * Gets the tone of this tone by combing the pitch and the octave in string. Tone is represented
   * in strings. Converts the octave from integer to string, and appends the string onto the
   * pitch in string.
   *
   * @return the pitch plus the octave in String format
   */
  public String getTone() {
    return this.pitch.toString() + Integer.toString(this.octave);
  }

  /**
   * Gets the tone index of this note. Every pitch from C1 to B10, has an index that is assigned
   * in the Pitch class and can be accessed via a static method.
   *
   * @return int the index of this pitch
   */
  public int toneIndex() {
    return Pitch.toneIndex.indexOf(this.getTone());
  }

  /**
   * Determines if this note is actively being played given a start BEAT and current BEAT.
   *
   * @param startBeat   Represents the starting BEAT.
   * @param currentBeat Represents the current BEAT.
   * @return whether or not the note is active.
   */
  public boolean active(int startBeat, int currentBeat) {
    return currentBeat >= startBeat && currentBeat <= startBeat + duration - 1;

  }

  @Override
  public boolean equals(Object that) {
    return this == that
            || that instanceof Note
            && ((Note) that).pitch == this.pitch
            && ((Note) that).octave == this.octave
            && ((Note) that).duration == this.duration;
  }

  @Override
  public int hashCode() {
    return this.toneIndex() + this.duration;
  }


  /**
   * Constructs a new Note with all of the same elements of the given note.
   *
   * @return the cloned Note.
   */
  public Note copy() {
    return new Note(this.pitch, this.octave, this.duration, this.instrument, this.volume);
  }
}

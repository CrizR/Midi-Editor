package cs3500.music.mechanics;

/**
 * Created by ChrisRisley on 6/23/17.
 */
public class Repeat {
  int duration;
  RepeatType type;

  public enum RepeatType {
    RIGHT, LEFT, BOTH
  }

  public Repeat(int duration, RepeatType type) {
    this.duration = duration;
    this.type = type;
  }
}

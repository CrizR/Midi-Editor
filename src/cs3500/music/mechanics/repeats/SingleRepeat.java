package cs3500.music.mechanics.repeats;

/**
 * Created by ChrisRisley on 6/25/17.
 */
public class SingleRepeat extends Repeat {

  private RepeatType type;

  public SingleRepeat(RepeatType type) {
    this.type = type;
  }

  boolean played;

  @Override
  boolean isPlayed() {
    return played;
  }

  @Override
  boolean isDoubleRepeat() {
    return false;
  }

  void makePlayed() {
    this.played = true;
  }
}

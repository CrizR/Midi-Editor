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
  public boolean isPlayed() {
    return played;
  }

  @Override
  public boolean isDoubleRepeat() {
    return false;
  }

  @Override
  public RepeatType getType() {
    return this.type;
  }

  @Override
  public boolean isEndPlayed() {
    if (this.type == RepeatType.END) {
      return this.played;
    } else {
      return true;
    }
  }

  @Override
  public boolean isBeginPlayed() {
    if (this.type == RepeatType.BEGIN) {
      return this.played;
    } else {
      return true;
    }
  }

  void makePlayed() {
    this.played = true;
  }
}

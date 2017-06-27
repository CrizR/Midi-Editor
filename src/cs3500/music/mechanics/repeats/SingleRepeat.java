package cs3500.music.mechanics.repeats;

/**
 * Created by ChrisRisley on 6/25/17.
 */
public class SingleRepeat extends Repeat {

  public SingleRepeat() {
  }

  public SingleRepeat(TimeBar timebar) {
    super(timebar);
  }

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
  public void setHasntPlayed() {
    this.played = false;

  }

  @Override
  public void setPlayed() {
    this.played = true;
  }

}

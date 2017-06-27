package cs3500.music.mechanics.repeats;

/**
 * Created by ChrisRisley on 6/25/17.
 */
public class DoubleRepeat extends Repeat {

  public DoubleRepeat() {
  }

  public DoubleRepeat(TimeBar timebar) {
    super(timebar);
  }

  @Override
  public boolean isPlayed() {
    return false;
  }

  @Override
  public boolean isDoubleRepeat() {
    return true;
  }

  @Override
  public RepeatType getType() {
    return RepeatType.BOTH;
  }

  boolean leftPlayed;
  boolean rightPlayed;

  @Override
  public boolean isEndPlayed() {
    return leftPlayed;
  }

  @Override
  public void setHasntPlayed() {
    this.leftPlayed = false;
    this.rightPlayed = false;
  }

  @Override
  public void setPlayed() {
    this.leftPlayed = true;
  }

}

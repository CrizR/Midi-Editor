package cs3500.music.mechanics.repeats;

/**
 * Created by ChrisRisley on 6/25/17.
 */
public class DoubleRepeat extends Repeat {

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
  public boolean isBeginPlayed() {
    return rightPlayed;
  }

  public void playRight() {
    this.rightPlayed = true;
  }

  public void playLeft() {
    this.leftPlayed = true;
  }
}

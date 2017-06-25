package cs3500.music.mechanics.repeats;

/**
 * Created by ChrisRisley on 6/25/17.
 */
public class DoubleRepeat extends Repeat {
  @Override
  boolean isPlayed() {
    return false;
  }

  @Override
  boolean isDoubleRepeat() {
    return true;
  }

  boolean leftPlayed;
  boolean rightPlayed;

  boolean isLeftPlayed() {
    return leftPlayed;
  }

  boolean isRightPlayed() {
    return rightPlayed;
  }

  public void playRight() {
    this.rightPlayed = true;
  }

  public void playLeft() {
    this.leftPlayed = true;
  }
}

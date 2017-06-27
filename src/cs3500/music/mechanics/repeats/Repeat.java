package cs3500.music.mechanics.repeats;

/**
 * Created by ChrisRisley on 6/23/17.
 */
public abstract class Repeat {

  TimeBar timebar;

  public Repeat() {
  }

  public Repeat(TimeBar timebar) {
    this.timebar = timebar;
  }

  public void setTimebar(TimeBar timebar) {
    this.timebar = timebar;
  }

  public boolean hasTimebar() {
    return this.timebar != null;
  }

  public TimeBar getTimebar() {
    return timebar;
  }

  public abstract boolean isPlayed();

  public abstract boolean isDoubleRepeat();

  public abstract RepeatType getType();

  public abstract boolean isEndPlayed();

  public abstract void setHasntPlayed();

  public abstract void setPlayed();
}

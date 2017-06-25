package cs3500.music.mechanics.repeats;

/**
 * Created by ChrisRisley on 6/23/17.
 */
public abstract class Repeat {

  public abstract boolean isPlayed();

  public abstract boolean isDoubleRepeat();

  public abstract RepeatType getType();

  public abstract boolean isEndPlayed();

  public abstract boolean isBeginPlayed();


}

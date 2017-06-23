package cs3500.music.model;
import java.util.HashMap;

import cs3500.music.mechanics.Repeat;

/**
 * Created by ChrisRisley on 6/23/17.
 */
public class MusicCapo extends Music {
  private HashMap<Integer, Repeat> repeats;

  public MusicCapo(HashMap<Integer, Repeat> repeats) {
    this.repeats = repeats;
  }

  public HashMap<Integer, Repeat> getRepeats() {
    return repeats;
  }

  /**
   * Adds a repeat at the current location.
   */
  public void addRepeat(int startingPosition, int endingPosition, Repeat.RepeatType type) {
    repeats.put(startingPosition, new Repeat(endingPosition - startingPosition, type));
  }

}

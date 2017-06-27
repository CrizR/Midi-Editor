package cs3500.music.mechanics.repeats;

/**
 * Created by ChrisRisley on 6/27/17.
 */
public class TimeBar {
  int startingBeat;
  int endingNumber;

  public TimeBar(int startingBeat, int endingNumber) {
    this.startingBeat = startingBeat;
    this.endingNumber = endingNumber;
  }

  public int getStartingBeat() {
    return startingBeat;
  }

  public int getEndingNumber() {
    return endingNumber;
  }

}

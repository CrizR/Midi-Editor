package cs3500.music.model;

import java.util.HashMap;

import cs3500.music.builder.CompositionBuilder;
import cs3500.music.mechanics.Note;
import cs3500.music.mechanics.Pitch;
import cs3500.music.mechanics.Set;
import cs3500.music.mechanics.ToneRange;
import cs3500.music.mechanics.repeats.DoubleRepeat;
import cs3500.music.mechanics.repeats.Repeat;
import cs3500.music.mechanics.repeats.RepeatType;
import cs3500.music.mechanics.repeats.SingleRepeat;

/**
 * TODO:
 */
public class MusicCapo extends Music {

  private HashMap<Integer, Repeat> repeats;

  /**
   * TODO:
   */
  public MusicCapo(HashMap<Integer, Repeat> repeats) {
    this.repeats = repeats;
  }

  public MusicCapo() {
    super.toneList = new ToneRange();
    super.noteMap = new HashMap<>();
  }

  /**
   * TODO:
   */
  public HashMap<Integer, Repeat> getRepeats() {
    return repeats;
  }

  /**
   * Adds a repeat at the current location.
   */
  public void addRepeat(int startingPosition, int endingPosition, RepeatType type) {
    if (type == RepeatType.BOTH) {
      repeats.put(startingPosition, new DoubleRepeat());
    } else {
      repeats.put(startingPosition, new SingleRepeat(type));
    }
  }

  /**
   * Builder class to convert midi-text into commands that our model can interpret.
   */
  public static final class Builder implements CompositionBuilder<IMusicOperations> {
    IMusicOperations op = new MusicCapo();


    @Override
    public IMusicOperations build() {
      return op.clone();
    }

    @Override
    public CompositionBuilder<IMusicOperations> setTempo(int tempo) {
      op.setTempo(tempo);
      return this;
    }

    @Override
    public CompositionBuilder<IMusicOperations> addNote(int start, int end, int instrument,

                                                            int pitch, int volume) {
      if (end - start <= 0) {
        return this;
      }
      op.addNote(new Note(Pitch.values()[pitch % 12], (pitch / 12) - 1,
              (end - start), instrument, volume), start);
      return this;
    }


  }

}

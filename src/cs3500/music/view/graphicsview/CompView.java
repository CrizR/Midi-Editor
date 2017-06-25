package cs3500.music.view.graphicsview;

import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Track;

import cs3500.music.mechanics.Note;
import cs3500.music.model.IMusicOperations;
import cs3500.music.view.midiview.MidiViewImpl;
import cs3500.music.view.textview.GuiView;

/**
 * A Class that represents both the MidiView and the GuiView.
 * Via the implementation of the Midi Receiver and Synthesizer and JFrame's methods
 * it allows the user to see the editor and the notes while also hearing them play.
 */
public class CompView extends MidiViewImpl implements GuiView {
  private final ArrayList<Integer> beats;
  private boolean play = false;
  private final GuiViewFrame guiDelegate;
  private int lastBeat;
  private boolean practicing;
  ArrayList<String> tonesPlayed;


  /**
   * Builds a MidiViewImpl.
   * The MidiView has an IMusicOperation, the last beat in the model, a synthesizer,
   * a receiver, and the starting beats.
   *
   * @param op Represents the model to read from.
   * @throws MidiUnavailableException throws an exception if the midi fails.
   */
  public CompView(IMusicOperations op) throws MidiUnavailableException {
    super(op, MidiSystem.getSequencer(), false);
    this.tonesPlayed = new ArrayList<>();
    this.guiDelegate = new GuiViewFrame(op);
    this.beats = op.getStartingBeats();
    Track t = this.sequence.createTrack();
    MetaMessage tick = new MetaMessage();
    this.lastBeat = this.op.lastBeat();
    for (int i = 0; i <= lastBeat; i++) {
      MidiEvent tic = new MidiEvent(tick, i);
      t.add(tic);
    }

    sequencer.addMetaEventListener(new Refresh());
  }

  public void update() {
    Track t = this.sequence.createTrack();
    MetaMessage tick = new MetaMessage();
    this.lastBeat = this.op.lastBeat();
    for (int i = 0; i <= lastBeat; i++) {
      MidiEvent tic = new MidiEvent(tick, i);
      t.add(tic);
    }
  }

  @Override
  public void initialize() {
    this.guiDelegate.initialize();

    for (int i : beats) {
      for (Note n : op.getNotes(i).values()) {
        try {
          super.playNote(n.getTone(), n.getDuration(), i, n.getVolume(), n.getInstrument() - 1);
        } catch (InvalidMidiDataException e) {
          //failed to init Midi
        }
      }
    }

    try {
      sequencer.setSequence(sequence);
    } catch (InvalidMidiDataException e) {
      // failed to get midi data
    }
    if (play) {
      sequencer.start();
    }
  }

  @Override
  public void togglePlay() {
    if (!practicing) {
      this.guiDelegate.movePanel();
      if (this.play) {
        sequencer.stop();
        this.play = false;
      } else {
        sequencer.start();
        sequencer.setTempoInMPQ(super.tempo);
        this.play = true;
      }
    }
  }

  @Override
  public void prevBeat() {
    if (!play) {
      this.guiDelegate.prevBeat();
      super.prevBeat();
    }
  }

  @Override
  public void nextBeat() {
    if (!practicing) {
      if (!play) {
        this.guiDelegate.nextBeat();
        super.nextBeat();
      }
    } else {
      if (playedAllNotes()) {
        super.playNextBeat(); //PLAYS THE NOTE
        this.guiDelegate.nextBeat();
        super.nextBeat();
        tonesPlayed = new ArrayList<>();
      }
    }
  }

  private boolean playedAllNotes() {
    for (String key : op.activeNotes(GuiViewFrame.BEAT).keySet()) {
      if (!tonesPlayed.contains(key)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public void toEnd() {
    this.guiDelegate.toEnd();
    this.sequencer.setTickPosition(op.lastBeat());
  }

  @Override
  public void toBeginning() {
    this.guiDelegate.toBeginning();
    this.sequencer.setTickPosition(0);
    this.sequencer.setTempoInMPQ(this.tempo);
    if (play) {
      this.sequencer.start();
    }
  }

  @Override
  public void resetFocus() {
    this.guiDelegate.resetFocus();
  }

  @Override
  public void refresh() {
    super.refresh();
    this.update();
    this.guiDelegate.refresh();
  }

  @Override
  public void addKeyListener(KeyListener e) {
    this.guiDelegate.addKeyListener(e);
  }

  @Override
  public void addMouseListener(MouseListener e) {
    this.guiDelegate.addMouseListener(e);
  }

  @Override
  public void addNote(MouseEvent e, int duration) {
    if (!practicing) {
      if (!play) {
        this.guiDelegate.addNote(e, duration);
        refresh();
      }
    } else {
      for (int i = 0; i < PianoPanel.KEYS.size(); i++) {
        PianoPanel.Key k = PianoPanel.KEYS.get(i);
        if (k.onKey(e.getX(), e.getY() - 500)) {
          if (k.getPitch().isSharp()) {
            tonesPlayed.add(k.getPitch().toString() + Integer.toString(k.getOctave()));
            break;
          } else {
            for (int j = i; j < PianoPanel.KEYS.size(); j++) {
              if (k.onKey(e.getX(), e.getY() - 500)) {
                tonesPlayed.add(k.getPitch().toString() + Integer.toString(k.getOctave()));
                break;
              }
            }
          }
        }
      }
      this.nextBeat();
    }
  }

  @Override
  public void movePanel() {
    //do nothing
  }

  @Override
  public void addRepeat() {
//    op.addRepeat(this.guiDelegate.ge)
  }

  @Override
  public void increaseTempo() {
    if (!play) {
      op.setTempo(op.getTempo() - 5000);
      super.refresh();
    }
  }

  @Override
  public void decreaseTempo() {
    if (!play) {
      op.setTempo(op.getTempo() + 5000);
      super.refresh();
    }
  }

  @Override
  public void togglePractice() {
    if (!play) {
      if (practicing) {
        practicing = false;
      } else {
        practicing = true;
      }
    }
//    JLabel practice = new JLabel("Practice Mode");
//    practice.setText("Test");
//    this.guiDelegate.add(practice);
//    this.guiDelegate.refresh();
  }

  @Override
  public void startCreate() {
    this.guiDelegate.startCreate();
  }

  public class Refresh implements MetaEventListener {
    @Override
    public void meta(MetaMessage meta) {
      GuiViewFrame.BEAT = (int) sequencer.getTickPosition();
      guiDelegate.movePanel();
      guiDelegate.refresh();
    }
  }
}

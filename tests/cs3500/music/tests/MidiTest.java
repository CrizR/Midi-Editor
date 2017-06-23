package cs3500.music.tests;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Track;
import javax.sound.midi.Transmitter;

import cs3500.music.model.IMusicOperations;
import cs3500.music.model.Music;
import cs3500.music.util.MusicReader;
import cs3500.music.view.IView;
import cs3500.music.view.midiview.MidiViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the Midi IView.
 */
public class MidiTest {
  // testing the midi view using a mock midi device
  @Test
  public void testPlayNotes() {
    BufferedReader br = null;
    try {
      br = new BufferedReader(new FileReader("mary-little-lamb.txt"));
    } catch (FileNotFoundException e) {
      //can't get file
    }
    Music.Builder x = new Music.Builder();
    IMusicOperations op = MusicReader.parseFile(br, x);
    MockMidiDevice mockMidiDevice = new MockMidiDevice();
    IView view = null;
    try {
      view = new MidiViewImpl(op, mockMidiDevice, true);
    } catch (MidiUnavailableException e) {
      //can't play midi
    }
    view.initialize();
    assertEquals("open() Called\n" +
            "open() Called\n" +
            "open() Called\n" +
            "open() Called\n" +
            "open() Called\n" +
            "open() Called\n" +
            "open() Called\n" +
            "open() Called\n" +
            "open() Called\n" +
            "open() Called\n" +
            "open() Called\n" +
            "open() Called\n" +
            "open() Called\n" +
            "open() Called\n" +
            "open() Called\n" +
            "open() Called\n" +
            "open() Called\n" +
            "open() Called\n" +
            "open() Called\n" +
            "open() Called\n" +
            "open() Called\n" +
            "open() Called\n" +
            "open() Called\n" +
            "open() Called\n" +
            "open() Called\n" +
            "open() Called\n" +
            "open() Called\n" +
            "open() Called\n" +
            "open() Called\n" +
            "open() Called\n" +
            "open() Called\n" +
            "open() Called\n" +
            "open() Called\n" +
            "open() Called\n" +
            "send() Called : send in Track \n" +
            "note added() at beat 0 144 \n" +
            "note added() at beat 7 128 \n" +
            "note added() at beat 7 255 \n" +
            "note added() at beat 0 144 \n" +
            "note added() at beat 2 128 \n" +
            "note added() at beat 2 255 \n" +
            "note added() at beat 2 144 \n" +
            "note added() at beat 4 128 \n" +
            "note added() at beat 4 255 \n" +
            "note added() at beat 4 144 \n" +
            "note added() at beat 6 128 \n" +
            "note added() at beat 6 255 \n" +
            "note added() at beat 6 144 \n" +
            "note added() at beat 8 128 \n" +
            "note added() at beat 8 255 \n" +
            "note added() at beat 8 144 \n" +
            "note added() at beat 15 128 \n" +
            "note added() at beat 15 255 \n" +
            "note added() at beat 8 144 \n" +
            "note added() at beat 10 128 \n" +
            "note added() at beat 10 255 \n" +
            "note added() at beat 10 144 \n" +
            "note added() at beat 12 128 \n" +
            "note added() at beat 12 255 \n" +
            "note added() at beat 12 144 \n" +
            "note added() at beat 15 128 \n" +
            "note added() at beat 15 255 \n" +
            "note added() at beat 16 144 \n" +
            "note added() at beat 18 128 \n" +
            "note added() at beat 18 255 \n" +
            "note added() at beat 16 144 \n" +
            "note added() at beat 24 128 \n" +
            "note added() at beat 24 255 \n" +
            "note added() at beat 18 144 \n" +
            "note added() at beat 20 128 \n" +
            "note added() at beat 20 255 \n" +
            "note added() at beat 20 144 \n" +
            "note added() at beat 24 128 \n" +
            "note added() at beat 24 255 \n" +
            "note added() at beat 24 144 \n" +
            "note added() at beat 26 128 \n" +
            "note added() at beat 26 255 \n" +
            "note added() at beat 24 144 \n" +
            "note added() at beat 26 128 \n" +
            "note added() at beat 26 255 \n" +
            "note added() at beat 26 144 \n" +
            "note added() at beat 28 128 \n" +
            "note added() at beat 28 255 \n" +
            "note added() at beat 28 144 \n" +
            "note added() at beat 32 128 \n" +
            "note added() at beat 32 255 \n" +
            "note added() at beat 32 144 \n" +
            "note added() at beat 40 128 \n" +
            "note added() at beat 40 255 \n" +
            "note added() at beat 32 144 \n" +
            "note added() at beat 34 128 \n" +
            "note added() at beat 34 255 \n" +
            "note added() at beat 34 144 \n" +
            "note added() at beat 36 128 \n" +
            "note added() at beat 36 255 \n" +
            "note added() at beat 36 144 \n" +
            "note added() at beat 38 128 \n" +
            "note added() at beat 38 255 \n" +
            "note added() at beat 38 144 \n" +
            "note added() at beat 40 128 \n" +
            "note added() at beat 40 255 \n" +
            "note added() at beat 40 144 \n" +
            "note added() at beat 48 128 \n" +
            "note added() at beat 48 255 \n" +
            "note added() at beat 40 144 \n" +
            "note added() at beat 42 128 \n" +
            "note added() at beat 42 255 \n" +
            "note added() at beat 42 144 \n" +
            "note added() at beat 44 128 \n" +
            "note added() at beat 44 255 \n" +
            "note added() at beat 44 144 \n" +
            "note added() at beat 46 128 \n" +
            "note added() at beat 46 255 \n" +
            "note added() at beat 46 144 \n" +
            "note added() at beat 48 128 \n" +
            "note added() at beat 48 255 \n" +
            "note added() at beat 48 144 \n" +
            "note added() at beat 50 128 \n" +
            "note added() at beat 50 255 \n" +
            "note added() at beat 48 144 \n" +
            "note added() at beat 56 128 \n" +
            "note added() at beat 56 255 \n" +
            "note added() at beat 50 144 \n" +
            "note added() at beat 52 128 \n" +
            "note added() at beat 52 255 \n" +
            "note added() at beat 52 144 \n" +
            "note added() at beat 54 128 \n" +
            "note added() at beat 54 255 \n" +
            "note added() at beat 54 144 \n" +
            "note added() at beat 56 128 \n" +
            "note added() at beat 56 255 \n" +
            "note added() at beat 56 144 \n" +
            "note added() at beat 64 128 \n" +
            "note added() at beat 64 255 \n" +
            "note added() at beat 56 144 \n" +
            "note added() at beat 64 128 \n" +
            "note added() at beat 64 255 \n" +
            "start() Called\n", mockMidiDevice.testResult.toString());
  }

  // test.txt.txt to see if the right number of notes is being played
  @Test
  public void testNumNotes() {
    BufferedReader br = null;
    try {
      br = new BufferedReader(new FileReader("mystery-3.txt"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    Music.Builder x = new Music.Builder();
    IMusicOperations op = MusicReader.parseFile(br, x);
    MockMidiDevice mockMidiDevice = new MockMidiDevice();
    IView view = null;
    try {
      view = new MidiViewImpl(op, mockMidiDevice, true);
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    view.initialize();
    assertEquals(1414, mockMidiDevice.totalNotesPlayed());
  }


  private class MockMidiDevice implements Sequencer {
    private StringBuilder testResult = new StringBuilder();
    private int noteCounter = 0;

    /**
     * Calculate the total number of notes played through this device.
     *
     * @return the total number of notes played through the mock synthesizer
     */
    int totalNotesPlayed() {
      return noteCounter / 2;
    }

    @Override
    public Info getDeviceInfo() {
      return null;
    }

    @Override
    public void open() throws MidiUnavailableException {
      testResult.append("open() Called\n");
    }

    @Override
    public void close() {
      //Do Nothing.
    }

    @Override
    public boolean isOpen() {
      return false;
    }

    @Override
    public void setSequence(Sequence sequence) throws InvalidMidiDataException {
      testResult.append("send() Called : send in Track \n");
      for (Track t : sequence.getTracks()) {
        for (int i = 0; i < t.size(); i++) {
          testResult
                  .append("note added() at beat ")
                  .append(t.get(i).getTick())
                  .append(" ")
                  .append(t.get(i).getMessage().getStatus())
                  .append(" \n");
          noteCounter++;
        }
      }
    }

    @Override
    public void setSequence(InputStream stream) throws IOException, InvalidMidiDataException {

    }

    @Override
    public Sequence getSequence() {
      return null;
    }

    @Override
    public void start() {
      testResult.append("start() Called\n");
    }

    @Override
    public void stop() {
      testResult.append("stop() Called\n");
    }

    @Override
    public boolean isRunning() {
      return false;
    }

    @Override
    public void startRecording() {
      //Do Nothing.
    }

    @Override
    public void stopRecording() {
      //Do Nothing.
    }

    @Override
    public boolean isRecording() {
      return false;
    }

    @Override
    public void recordEnable(Track track, int channel) {

    }

    @Override
    public void recordDisable(Track track) {
      //Do Nothing.
    }

    @Override
    public float getTempoInBPM() {
      return 0;
    }

    @Override
    public void setTempoInBPM(float bpm) {

    }

    @Override
    public float getTempoInMPQ() {
      return 0;
    }

    @Override
    public void setTempoInMPQ(float mpq) {
      //Do Nothing.
    }

    @Override
    public void setTempoFactor(float factor) {
      //Do Nothing.
    }

    @Override
    public float getTempoFactor() {
      return 0;
    }

    @Override
    public long getTickLength() {
      return 0;
    }

    @Override
    public long getTickPosition() {
      return 0;
    }

    @Override
    public void setTickPosition(long tick) {

    }

    @Override
    public long getMicrosecondLength() {
      return 0;
    }

    @Override
    public long getMicrosecondPosition() {
      return 0;
    }

    @Override
    public void setMicrosecondPosition(long microseconds) {

    }

    @Override
    public void setMasterSyncMode(SyncMode sync) {
      //Do Nothing.
    }

    @Override
    public SyncMode getMasterSyncMode() {
      return null;
    }

    @Override
    public SyncMode[] getMasterSyncModes() {
      return new SyncMode[0];
    }

    @Override
    public void setSlaveSyncMode(SyncMode sync) {

    }

    @Override
    public SyncMode getSlaveSyncMode() {
      return null;
    }

    @Override
    public SyncMode[] getSlaveSyncModes() {
      return new SyncMode[0];
    }

    @Override
    public void setTrackMute(int track, boolean mute) {

    }

    @Override
    public boolean getTrackMute(int track) {
      return false;
    }

    @Override
    public void setTrackSolo(int track, boolean solo) {
      //Do Nothing.
    }

    @Override
    public boolean getTrackSolo(int track) {
      return false;
    }

    @Override
    public boolean addMetaEventListener(MetaEventListener listener) {
      return false;
    }

    @Override
    public void removeMetaEventListener(MetaEventListener listener) {
      //Do Nothing.
    }

    @Override
    public int[] addControllerEventListener(ControllerEventListener listener, int[] controllers) {
      return new int[0];
    }

    @Override
    public int[] removeControllerEventListener(ControllerEventListener listener,
                                               int[] controllers) {
      return new int[0];
    }

    @Override
    public void setLoopStartPoint(long tick) {
      //Do Nothing.
    }

    @Override
    public long getLoopStartPoint() {
      return 0;
    }

    @Override
    public void setLoopEndPoint(long tick) {

    }

    @Override
    public long getLoopEndPoint() {
      return 0;
    }

    @Override
    public void setLoopCount(int count) {
      //Do Nothing.
    }

    @Override
    public int getLoopCount() {
      return 0;
    }

    @Override
    public int getMaxReceivers() {
      return 0;
    }

    @Override
    public int getMaxTransmitters() {
      return 0;
    }

    @Override
    public Receiver getReceiver() throws MidiUnavailableException {
      return null;
    }

    @Override
    public List<Receiver> getReceivers() {
      testResult.append(" getReceivers() Called\n");
      return null;
    }

    @Override
    public Transmitter getTransmitter() throws MidiUnavailableException {
      return null;
    }

    @Override
    public List<Transmitter> getTransmitters() {
      return null;
    }
  }
}
package cs3500.music.tests;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MidiMessage;
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
            "send() called : send in note [-112, 55, 70]\n" +
            "send() called : send in note [-128, 55, 70]\n" +
            "send() called : send in note [-112, 64, 72]\n" +
            "send() called : send in note [-128, 64, 72]\n" +
            "send() called : send in note [-112, 62, 72]\n" +
            "send() called : send in note [-128, 62, 72]\n" +
            "send() called : send in note [-112, 60, 71]\n" +
            "send() called : send in note [-128, 60, 71]\n" +
            "send() called : send in note [-112, 62, 79]\n" +
            "send() called : send in note [-128, 62, 79]\n" +
            "send() called : send in note [-112, 55, 79]\n" +
            "send() called : send in note [-128, 55, 79]\n" +
            "send() called : send in note [-112, 64, 85]\n" +
            "send() called : send in note [-128, 64, 85]\n" +
            "send() called : send in note [-112, 64, 78]\n" +
            "send() called : send in note [-128, 64, 78]\n" +
            "send() called : send in note [-112, 64, 74]\n" +
            "send() called : send in note [-128, 64, 74]\n" +
            "send() called : send in note [-112, 62, 75]\n" +
            "send() called : send in note [-128, 62, 75]\n" +
            "send() called : send in note [-112, 55, 77]\n" +
            "send() called : send in note [-128, 55, 77]\n" +
            "send() called : send in note [-112, 62, 77]\n" +
            "send() called : send in note [-128, 62, 77]\n" +
            "send() called : send in note [-112, 62, 75]\n" +
            "send() called : send in note [-128, 62, 75]\n" +
            "send() called : send in note [-112, 55, 79]\n" +
            "send() called : send in note [-128, 55, 79]\n" +
            "send() called : send in note [-112, 64, 82]\n" +
            "send() called : send in note [-128, 64, 82]\n" +
            "send() called : send in note [-112, 67, 84]\n" +
            "send() called : send in note [-128, 67, 84]\n" +
            "send() called : send in note [-112, 67, 75]\n" +
            "send() called : send in note [-128, 67, 75]\n" +
            "send() called : send in note [-112, 55, 78]\n" +
            "send() called : send in note [-128, 55, 78]\n" +
            "send() called : send in note [-112, 64, 73]\n" +
            "send() called : send in note [-128, 64, 73]\n" +
            "send() called : send in note [-112, 62, 69]\n" +
            "send() called : send in note [-128, 62, 69]\n" +
            "send() called : send in note [-112, 60, 71]\n" +
            "send() called : send in note [-128, 60, 71]\n" +
            "send() called : send in note [-112, 62, 80]\n" +
            "send() called : send in note [-128, 62, 80]\n" +
            "send() called : send in note [-112, 55, 79]\n" +
            "send() called : send in note [-128, 55, 79]\n" +
            "send() called : send in note [-112, 64, 84]\n" +
            "send() called : send in note [-128, 64, 84]\n" +
            "send() called : send in note [-112, 64, 76]\n" +
            "send() called : send in note [-128, 64, 76]\n" +
            "send() called : send in note [-112, 64, 74]\n" +
            "send() called : send in note [-128, 64, 74]\n" +
            "send() called : send in note [-112, 64, 77]\n" +
            "send() called : send in note [-128, 64, 77]\n" +
            "send() called : send in note [-112, 62, 75]\n" +
            "send() called : send in note [-128, 62, 75]\n" +
            "send() called : send in note [-112, 55, 78]\n" +
            "send() called : send in note [-128, 55, 78]\n" +
            "send() called : send in note [-112, 62, 74]\n" +
            "send() called : send in note [-128, 62, 74]\n" +
            "send() called : send in note [-112, 64, 81]\n" +
            "send() called : send in note [-128, 64, 81]\n" +
            "send() called : send in note [-112, 62, 70]\n" +
            "send() called : send in note [-128, 62, 70]\n" +
            "send() called : send in note [-112, 60, 73]\n" +
            "send() called : send in note [-128, 60, 73]\n" +
            "send() called : send in note [-112, 52, 72]\n" +
            "send() called : send in note [-128, 52, 72]\n", mockMidiDevice.testResult.toString());
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
    MockMidiDevice modckMidiDevice = new MockMidiDevice();
    IView view = null;
    try {
      view = new MidiViewImpl(op, modckMidiDevice, true);
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    view.initialize();
    assertEquals(943, modckMidiDevice.totalNotesPlayed());
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

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
      return false;
    }

    @Override
    public void startRecording() {

    }

    @Override
    public void stopRecording() {

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

    }

    @Override
    public void setTempoFactor(float factor) {

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

    }

    @Override
    public int[] addControllerEventListener(ControllerEventListener listener, int[] controllers) {
      return new int[0];
    }

    @Override
    public int[] removeControllerEventListener(ControllerEventListener listener, int[] controllers) {
      return new int[0];
    }

    @Override
    public void setLoopStartPoint(long tick) {

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
      return new Receiver() {

        @Override
        public void send(MidiMessage message, long timeStamp) {
          testResult.append("send() called : send in note "
                  + Arrays.toString(message.getMessage()) + "\n");
          noteCounter++;
        }

        @Override
        public void close() {
          //Do Nothing.
        }
      };
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
package cs3500.music.tests;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Patch;
import javax.sound.midi.Receiver;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Transmitter;
import javax.sound.midi.VoiceStatus;

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
      view = new MidiViewImpl(op, mockMidiDevice);
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

  // test.txt to see if the right number of notes is being played
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
      view = new MidiViewImpl(op, modckMidiDevice);
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    view.initialize();
    assertEquals(943, modckMidiDevice.totalNotesPlayed());
  }


  private class MockMidiDevice implements Synthesizer {
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
    public long getMicrosecondPosition() {
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

    @Override
    public int getMaxPolyphony() {
      return 0;
    }

    @Override
    public long getLatency() {
      return 0;
    }

    @Override
    public MidiChannel[] getChannels() {
      return new MidiChannel[0];
    }

    @Override
    public VoiceStatus[] getVoiceStatus() {
      return new VoiceStatus[0];
    }

    @Override
    public boolean isSoundbankSupported(Soundbank soundbank) {
      return false;
    }

    @Override
    public boolean loadInstrument(Instrument instrument) {
      return false;
    }

    @Override
    public void unloadInstrument(Instrument instrument) {
      //Do Nothing.
    }

    @Override
    public boolean remapInstrument(Instrument from, Instrument to) {
      return false;
    }

    @Override
    public Soundbank getDefaultSoundbank() {
      return null;
    }

    @Override
    public Instrument[] getAvailableInstruments() {
      return new Instrument[0];
    }

    @Override
    public Instrument[] getLoadedInstruments() {
      return new Instrument[0];
    }

    @Override
    public boolean loadAllInstruments(Soundbank soundbank) {
      return false;
    }

    @Override
    public void unloadAllInstruments(Soundbank soundbank) {
      //Do Nothing.
    }

    @Override
    public boolean loadInstruments(Soundbank soundbank, Patch[] patchList) {
      return false;
    }

    @Override
    public void unloadInstruments(Soundbank soundbank, Patch[] patchList) {
      //Do Nothing.
    }
  }
}
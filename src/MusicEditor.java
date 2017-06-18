import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.controller.Controller;


/**
 * Main class to initiate the program.
 */
public class
MusicEditor {
  /**
   * Main method starts the program.
   *
   * @param args represents the args
   * @throws IOException              throws an IOException
   * @throws InvalidMidiDataException throws an InvalidMidiDataException
   */
  public static void main(String[] args) throws IOException, InvalidMidiDataException {
    try {
      new Controller().start(args);
    } catch (FileNotFoundException e) {
      System.out.println("File Not Found");
    }
  }
}

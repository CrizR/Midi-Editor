import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.controller.Controller;
import cs3500.music.model.IMusicOperations;
import cs3500.music.model.Music;
import cs3500.music.util.MusicReader;
import cs3500.music.view.IView;
import cs3500.music.view.ViewBuilder;


/**
 * Main class to initiate the program.
 */
public class MusicEditor {
  /**
   * Main method starts the program.
   *
   * @param args represents the args
   * @throws IOException              throws an IOException
   * @throws InvalidMidiDataException throws an InvalidMidiDataException
   */
  public static void main(String[] args) throws IOException, InvalidMidiDataException {
    BufferedReader br = new BufferedReader(new FileReader(args[0]));
    Music.Builder x = new Music.Builder(); //Once built, returns a copy of the model.
    IMusicOperations op = MusicReader.parseFile(br, x);
    IView view = ViewBuilder.createView(args[1], op);
    new Controller(op).setView(view);
    view.initialize();
  }
}

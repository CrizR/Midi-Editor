import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.controller.Controller;
import cs3500.music.controller.KeyboardHandler;
import cs3500.music.controller.MouseHandler;
import cs3500.music.model.IMusicOperations;
import cs3500.music.model.Music;
import cs3500.music.util.MusicReader;
import cs3500.music.view.IView;
import cs3500.music.view.ViewBuilder;
import cs3500.music.view.textview.GuiView;


/**
 * Main class to initiate the program.
 */
public class MusicEditor {
  /**
   * Main method starts the program and sets the configurations.
   *
   * @param args represents the args
   * @throws IOException              throws an IOException
   * @throws InvalidMidiDataException throws an InvalidMidiDataException
   */
  public static void main(String[] args) throws IOException, InvalidMidiDataException {
    if (args.length == 2) {
      try {
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        Music.Builder x = new Music.Builder(); //Once built, returns a copy of the model.
        IMusicOperations op;
        op = MusicReader.parseFile(br, x);
        IView view = ViewBuilder.createView(args[1], op);
        if (args[1].equals("visual") || args[1].equals("composite")) {
          KeyboardHandler kl = new KeyboardHandler();
          MouseHandler mh = new MouseHandler(op, (GuiView) view);
          new Controller(op, kl, mh).setView((GuiView) view);
          view.initialize();
        }
      } catch (FileNotFoundException e) {
        System.out.println("Invalid File");
      }
    } else {
      System.out.println("No configurations");
    }
  }
}

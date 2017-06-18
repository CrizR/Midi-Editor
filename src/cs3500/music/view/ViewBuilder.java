package cs3500.music.view;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;

import cs3500.music.model.IMusicOperations;
import cs3500.music.view.graphicsview.GuiIViewFrame;
import cs3500.music.view.midiview.MidiViewImpl;
import cs3500.music.view.textview.ConsoleIView;

/**
 * Builds the program's view via the usage of its method createView.
 */
public class ViewBuilder {
  /**
   * Builds a via as determined by the given string. If the incorrect view is given
   * it will return a ConsoleIView;
   *
   * @param view The type of view to return.
   * @param op   the model to build the view with.
   * @return the constructed view.
   */
  public static IView createView(String view, IMusicOperations op) {
    switch (view) {
      case "console":
        return new ConsoleIView(op);
      case "visual":
        return new GuiIViewFrame(op);
      case "midi":
        try {
          return new MidiViewImpl(op, MidiSystem.getSynthesizer());
        } catch (MidiUnavailableException e) {
          System.out.println("Could not start MidiView");
          return new ConsoleIView(op);
        }
      default:
        System.out.println("Invalid IView, Reverting to Console IView");
        return new ConsoleIView(op);
    }
  }
}

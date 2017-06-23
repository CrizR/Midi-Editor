package cs3500.music.view;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;

import cs3500.music.model.IMusicOperations;
//import cs3500.music.view.compositeview.CompView;
import cs3500.music.view.compositeview.CompView;
import cs3500.music.view.graphicsview.GuiViewFrame;
import cs3500.music.view.midiview.MidiViewImpl;
import cs3500.music.view.textview.ConsoleView;

/**
 * Builds the program's view via the usage of its method createView.
 */
public class ViewBuilder {
  /**
   * Builds a via as determined by the given string. If the incorrect view is given
   * it will return a ConsoleView;
   *
   * @param view The type of view to return.
   * @param op   the model to build the view with.
   * @return the constructed view.
   */
  public static IView createView(String view, IMusicOperations op) {
    switch (view) {
      case "console":
        return new ConsoleView(op);
      case "visual":
        return new GuiViewFrame(op);
      case "midi":
        try {
          return new MidiViewImpl(op, MidiSystem.getSynthesizer(), true);
        } catch (MidiUnavailableException e) {
          System.out.println("Could not start MidiView");
          return new ConsoleView(op);
        }
      case "composite":
        try {
          return new CompView(op, MidiSystem.getSynthesizer());
        } catch (MidiUnavailableException e) {
          return new ConsoleView(op);
        }
      default:
        System.out.println("Invalid IView, Reverting to Console IView");
        return new ConsoleView(op);
    }
  }
}

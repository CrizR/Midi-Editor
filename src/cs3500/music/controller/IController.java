package cs3500.music.controller;

import java.io.FileNotFoundException;

/**
 * Interface that is to be implemented by the Controller class. This interface defines the methods
 * to be used by the controller.
 */
public interface IController {
  /**
   * Determines what file the user wants to play, and what view to play it in. This is done
   * by using a scanner to parse the user's input which is then saved in a basic private
   * class called settings. After the settings have been set, the view and music model are
   * initialized with the parameters set in the settings.
   *
   * @param args Represents the initial program input.
   */
  void start(String[] args) throws FileNotFoundException;
}

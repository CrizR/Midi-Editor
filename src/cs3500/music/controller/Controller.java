package cs3500.music.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.sound.midi.MidiUnavailableException;

import cs3500.music.model.IMusicOperations;
import cs3500.music.model.Music;
import cs3500.music.util.MusicReader;
import cs3500.music.view.View;
import cs3500.music.view.ViewBuilder;
import cs3500.music.view.playmidiview.PlayMidiFile;

/**
 * Basic Controller class to aid in the initial setup of the music editor. The user
 * inputs a view type and a file name in the config and it runs the program.
 */
public class Controller implements IController {

  /**
   * Class to save the user's inputted start settings. These settings include
   * the type of view, and the type of file.
   */
  private class Settings {
    String view = "";
    String file = "";

    /**
     * Determines the set view.
     *
     * @return the view of the Settings.
     */
    String getView() {
      return view;
    }

    /**
     * Determines the file type.
     *
     * @return the type of file saved in the settings.
     */
    String getFile() {
      return file;
    }

    /**
     * Sets the view in the settings.
     *
     * @param view represents the view to set.
     */
    void setView(String view) {
      this.view = view;
    }

    /**
     * Sets the file in the settings.
     *
     * @param file represents the filetype.
     */
    void setFile(String file) {
      this.file = file;
    }
  }

  @Override
  public void start(String[] args) throws FileNotFoundException {
    Settings settings = new Settings();
    initSettings(args, settings);
    System.out.println("Initializing Editor");
    BufferedReader br = new BufferedReader(new FileReader(settings.getFile()));
    Music.Builder x = new Music.Builder(); //Once built, returns a copy of the model.
    IMusicOperations op = MusicReader.parseFile(br, x);
    View view = ViewBuilder.createView(settings.getView(), op);
    view.initialize();
    try {
      Thread.sleep(1000000000);
    } catch (InterruptedException e) {
      //Can't sleep
    }
  }

  //Determines the congif settings.
  private void initSettings(String[] input, Settings settings) {
    for (String s : input) {
      s = s.toLowerCase();
      if (s.contains(".mid")) {
        System.out.println("Playing midi file " + s);
        try {
          PlayMidiFile song = new PlayMidiFile(s);
          song.initialize();
        } catch (MidiUnavailableException e) {
          //can't play midi
        }
      } else if (s.contains(".txt")) {
        settings.setFile(s);
      } else if (s.equals("console")) {
        settings.setView(s);
      } else if (s.equals("visual")) {
        settings.setView(s);
      } else if (s.equals("midi")) {
        settings.setView(s);
      }
    }
  }
}

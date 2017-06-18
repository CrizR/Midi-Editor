package cs3500.music.controller;


import java.awt.event.KeyEvent;

import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;



import cs3500.music.controller.commands.KeyboardHandler;
import cs3500.music.model.IMusicOperations;

import cs3500.music.view.IView;


/**
 * Basic Controller class to aid in the initial setup of the music editor. The user
 * inputs a view type and a file name in the config and it runs the program.
 */
public class Controller implements IController {
  
  private IMusicOperations op;
  private IView view;

  public Controller(IMusicOperations op){
    this.op = op;
  }


  @Override
  public void setView(IView view){
    this.view = view;
    
    keyBoardSetup();
    mouseSetup();
  }

  private void mouseSetup() {
    //add a note
    //remove a note
    HashMap<Integer, Runnable> mouseClicked = new HashMap<>();
    mouseClicked.put(MouseEvent.MOUSE_CLICKED, () -> view.addNote());

  }

  private void keyBoardSetup() {
    Map<Character,Runnable> keyTypes = new HashMap<>();
    Map<Integer,Runnable> keyPresses = new HashMap<>();
    Map<Integer,Runnable> keyReleases = new HashMap<>();

    //play and pause
    //right and left arrows
    keyPresses.put(KeyEvent.VK_LEFT, () -> view.prevBeat());
    keyPresses.put(KeyEvent.VK_RIGHT, () -> view.nextBeat());
    keyPresses.put(KeyEvent.VK_SPACE, () -> view.togglePlay());

    KeyboardHandler kbd = new KeyboardHandler();
    kbd.setKeyTypedMap(keyTypes);
    kbd.setKeyPressedMap(keyPresses);
    kbd.setKeyReleasedMap(keyReleases);

    view.addKeyListener(kbd);
  }
}

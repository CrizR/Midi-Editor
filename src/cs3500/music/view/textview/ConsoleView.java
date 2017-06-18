package cs3500.music.view.textview;

import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import cs3500.music.model.IMusicOperations;
import cs3500.music.view.IView;

/**
 * Represents the Console view. The view is printed out to the console for the user to see.
 */
public class ConsoleView implements IView {
  IMusicOperations op;

  /**
   * Builds the Console view with the given model and initializes it.
   */
  public ConsoleView(IMusicOperations op) {
    this.op = op;
  }

  @Override
  public void initialize() {
    System.out.print(op.visualize());
  }

  @Override
  public void prevBeat() {

  }

  @Override
  public void nextBeat() {

  }

  @Override
  public void toEnd() {

  }

  @Override
  public void toBeginning() {

  }

  @Override
  public void togglePlay() {

  }

  @Override
  public void resetFocus() {

  }

  @Override
  public void refresh() {

  }

  @Override
  public void addKeyListener(KeyListener listener) {

  }

  @Override
  public void addMouseListener(MouseListener listener) {

  }

  @Override
  public void addNote(MouseEvent e) {

  }


}

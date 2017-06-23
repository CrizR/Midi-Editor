package cs3500.music.view.textview;

import cs3500.music.model.IMusicOperations;
import cs3500.music.view.IView;

/**
 * Represents the Console view. The view prints out the editor to the console for the user to see.
 */
public class ConsoleView implements IView {
  private final IMusicOperations op;

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
    //Do Nothing
  }

  @Override
  public void nextBeat() {
    //Do Nothing
  }

}

package cs3500.music.view.graphicsview;

import java.awt.*;

import cs3500.music.model.IMusicOperations;

/**
 * Created by ChrisRisley on 6/25/17.
 */
public class GuiPanelWithRepeat extends GuiPanel {
  /**
   * Builds the panel by first determining the number of tones then
   * getting all of the notes, and then finally by painting the view.
   *
   * @param op Represents the IMusicOperations to build the view off of.
   */
  GuiPanelWithRepeat(IMusicOperations op) {
    super(op);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.drawRepeats();
  }

  private void drawRepeats() {
//    HashMap<Integer, Repeat> repeats = op.ge
//    for (Repeat
  }
}

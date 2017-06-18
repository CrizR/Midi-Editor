package cs3500.music.view.graphicsview;


import cs3500.music.view.View;

/**
 * View interface with methods specified for the GuiView.
 */
public interface GuiView extends View {

  /**
   * Signal the view to draw itself.
   */
  void refresh();

}

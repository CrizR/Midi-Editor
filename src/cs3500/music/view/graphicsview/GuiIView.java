package cs3500.music.view.graphicsview;


import cs3500.music.view.IView;

/**
 * IView interface with methods specified for the GuiIView.
 */
public interface GuiIView extends IView {

  /**
   * Signal the view to draw itself.
   */
  void refresh();

}

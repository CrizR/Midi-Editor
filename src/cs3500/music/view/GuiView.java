package cs3500.music.view;


import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * IView interface with methods specified for the GuiView.
 */
public interface GuiView extends IView {

  /**
   * Signal the view to draw itself.
   */
  void refresh();


}

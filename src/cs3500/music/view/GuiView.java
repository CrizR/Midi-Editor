package cs3500.music.view;

import javax.sound.midi.MetaEventListener;

/**
 * IView interface with methods specified for the GuiView.
 */
public interface GuiView extends IView {

  /**
   * Signal the view to draw itself.
   */
  void refresh();


}

package cs3500.music.view;
/**
 * IView class that represents the methods to be used by all of the editors views.
 */
public interface IView {

  /**
   * Sets the current beat to the beat - 1.
   */
  void prevBeat();

  /**
   * Sets the current beat to the beat + 1.
   */
  void nextBeat();


  /**
   * Make the view visible. This is usually called
   * after the view is constructed
   */
  void initialize();

}
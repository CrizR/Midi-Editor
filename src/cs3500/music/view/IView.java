package cs3500.music.view;

/**
 * IView class that represents the methods to be used by all of the editors views.
 * It has three methods: prevBeat, nextBeat, and initialize.
 */
public interface IView {

  /**
   * Sets the current beat to the beat minus 1.
   */
  void prevBeat();

  /**
   * Sets the current beat to the beat plus 1.
   */
  void nextBeat();


  /**
   * Make the view visible. This is usually called
   * after the view is constructed
   */
  void initialize();

}
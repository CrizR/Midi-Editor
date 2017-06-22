package cs3500.music.view;

/**
 * IView class that represents the methods to be used by all of the editors views.
 * //TODO: MODIFIED
 */
public interface IView {


  /**
   * Make the view visible. This is usually called
   * after the view is constructed
   */
  void initialize();

  void toEnd();

  void toBeginning();
}
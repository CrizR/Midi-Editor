package cs3500.music.view.graphicsview;

import java.awt.*;
import java.awt.event.MouseEvent;

import javax.swing.*;

import cs3500.music.mechanics.Note;
import cs3500.music.model.IMusicOperations;
import cs3500.music.view.textview.GuiView;


/**
 * IView that represents the graphical view for the user. This view shows each note on a grid
 * above KEYS. As the user clicks the left or right arrow, the bar on the
 * midi editor moves in accordance to the changed BEAT. The KEYS light up
 * when the bar is on top of the corresponding note. This is represented with two JPanels,
 * the midiPanel and the piano panel. We made the BEAT public and static in this class so that
 * our panels have access to the current BEAT being "played." The current BEAT is updated in the
 * view because the model is a read only object.
 * Modified, June 22, abstracted out some of the logic in the constructor to a helper method.
 * Added methods that were added to the GuiView interface.
 */
public class GuiViewFrame extends JFrame implements GuiView {
  private IMusicOperations op;
  public static int BEAT;
  private final int pianoWidth = 1000;
  private final int pianoHeight = 250;
  private final int midiWidth = 1000;
  private final int midiHeight = 500;
  private JPanel midiPanel;
  private JPanel pianoPanel;
  private JScrollPane scrollPane;
  private boolean practicing;
  private boolean creatingNote;

  /**
   * Constructs a GuiView frame by first instantiating the midipanel and piano panel
   * to the given IMusicOperations. It then sets the size of each panel, adds key listeners
   * to each panel, sets up a scroll bar for the midi panel, and finally sets the size for this
   * frame.
   *
   * @param op Represents the IMusicOperations to create the view for.
   */
  public GuiViewFrame(IMusicOperations op) {
    this.op = op;
    this.creatingNote = false;
    midiPanel = new GuiPanel(op);
    pianoPanel = new PianoPanel(op);
    update();

    //make midi panel scrollable
    scrollPane = new JScrollPane(midiPanel,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setPreferredSize(new Dimension(midiWidth, midiHeight));
    scrollPane.getVerticalScrollBar().setUnitIncrement(128);
    scrollPane.getHorizontalScrollBar().setUnitIncrement(128);

    //add to frame
    this.add(scrollPane, BorderLayout.NORTH);
    this.add(pianoPanel, BorderLayout.SOUTH);
    this.setSize(1000, 750);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.pack();

    this.setResizable(false);
  }

  private void update() {
    //init piano panel
    pianoPanel.setPreferredSize(new Dimension(pianoWidth, pianoHeight));
    pianoPanel.setBackground(Color.gray.brighter());
    //this.pianoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
    midiPanel.setAutoscrolls(true);
    midiPanel.setPreferredSize(new Dimension(
            (op.lastBeat() + 2) * GuiPanel.CELL_WIDTH,
            (op.getTones().size() + 2) * GuiPanel.CELL_HEIGHT));
  }

  @Override
  public void togglePlay() {
    //Do Nothing
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }


  @Override
  public void initialize() {
    this.setVisible(true);
  }

  @Override
  public void prevBeat() {
    if (BEAT - 1 >= 0) {
      if (BEAT % (midiWidth / GuiPanel.CELL_WIDTH) == 0) {
        this.scrollPane.getHorizontalScrollBar().setValue(
                GuiViewFrame.BEAT * GuiPanel.CELL_WIDTH -
                        scrollPane.getWidth());
      }
      BEAT--;
    }
    refresh();
  }

  @Override
  public void startCreate() {
    this.creatingNote = true;
  }

  @Override
  public void addBeginRepeat() {
    //DO NOTHING
  }

  @Override
  public void addEndRepeat() {
    //DO NOTHING
  }

  @Override
  public void addDoubleRepeat() {
    //DO NOTHING
  }

  @Override
  public void nextBeat() {
    if (creatingNote) {
      movePanel();
      BEAT++;
    } else if (BEAT + 1 <= op.lastBeat() + 1) {
      movePanel();
      BEAT++;
    }
    refresh();
  }

  @Override
  public void toEnd() {
    BEAT = op.lastBeat() + 1;
    this.scrollPane.getHorizontalScrollBar().setValue(GuiViewFrame.BEAT * GuiPanel.CELL_WIDTH);
    this.refresh();
  }

  @Override
  public void toBeginning() {
    BEAT = 0;
    this.scrollPane.getHorizontalScrollBar().setValue(GuiViewFrame.BEAT * GuiPanel.CELL_WIDTH);
    refresh();
  }

  @Override
  public void refresh() {
    this.revalidate();
    this.midiPanel.repaint(this.scrollPane.getHorizontalScrollBar().getValue(),
            this.scrollPane.getVerticalScrollBar().getValue(), this.midiPanel.getWidth(),
            this.midiPanel.getHeight());
    this.pianoPanel.repaint();
    this.update();
  }

  @Override
  public void addNote(MouseEvent e, int duration) {
    for (int i = 0; i < PianoPanel.KEYS.size(); i++) {
      PianoPanel.Key k = PianoPanel.KEYS.get(i);
      if (k.onKey(e.getX(), e.getY() - this.midiHeight)) {
        if (k.getPitch().isSharp()) {
          op.addNote(new Note(k.getPitch(), k.getOctave(), duration, 1, 60),
                  (BEAT + 1) - duration);
          System.out.println("test1");
        } else {
          for (int j = i; j < PianoPanel.KEYS.size(); j++) {
            if (k.onKey(e.getX(), e.getY() - this.midiHeight)) {
              op.addNote(new Note(k.getPitch(), k.getOctave(), duration, 1, 60),
                      (BEAT + 1) - duration);
              System.out.println("test2");
              break;
            }
          }
        }
      }
      refresh();
      resetFocus();
      creatingNote = false;
    }
  }

  @Override
  public void movePanel() {
    if (GuiViewFrame.BEAT % (this.midiWidth / GuiPanel.CELL_WIDTH) == 0) {
      this.scrollPane.getHorizontalScrollBar().setValue(GuiViewFrame.BEAT * GuiPanel.CELL_WIDTH);
    }
  }


  @Override
  public void increaseTempo() {
    //Do nothing

  }

  @Override
  public void decreaseTempo() {
    //Do nothing
  }

  @Override
  public void togglePractice() {
    //Do nothing
  }
}

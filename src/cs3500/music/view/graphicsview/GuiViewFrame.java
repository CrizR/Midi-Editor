package cs3500.music.view.graphicsview;

import java.awt.*;

import javax.swing.*;

import cs3500.music.model.IMusicOperations;
import cs3500.music.view.GuiView;


/**
 * IView that represents the graphical view for the user. This view shows each note on a grid
 * above keys. As the user clicks the left or right arrow, the bar on the
 * midi editor moves in accordance to the changed BEAT. The keys light up
 * when the bar is on top of the corresponding note. This is represented with two JPanels,
 * the midiPanel and the piano panel. We made the BEAT public and static in this class so that
 * our panels have access to the current BEAT being "played." The current BEAT is updated in the
 * view because the model is a read only object.
 */
public class GuiViewFrame extends JFrame implements GuiView {
  private IMusicOperations op;
  public static int BEAT;
  public static final int PIANO_WIDTH = 1000;
  public static final int PIANO_HEIGHT = 250;
  public static final int MIDI_WIDTH = 1000;
  public static final int MIDI_HEIGHT = 500;
  JPanel midiPanel;
  JPanel pianoPanel;

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
    midiPanel = new GuiPanel(op);
    pianoPanel = new PianoPanel(op);
    update();

    //make midi panel scrollable
    JScrollPane scrollPane = new JScrollPane(midiPanel,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setPreferredSize(new Dimension(MIDI_WIDTH, MIDI_HEIGHT));
    scrollPane.getVerticalScrollBar().setUnitIncrement(128);
    scrollPane.getHorizontalScrollBar().setUnitIncrement(128);

    //add to frame
    this.add(scrollPane, BorderLayout.NORTH);
    this.add(pianoPanel, BorderLayout.SOUTH);
    this.setSize(1000, 750);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    this.setResizable(false);
  }

  private void update() {
    //init piano panel
    pianoPanel.setPreferredSize(new Dimension(PIANO_WIDTH, PIANO_HEIGHT));
    pianoPanel.setBackground(Color.gray.brighter());
    //this.pianoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
    midiPanel.setAutoscrolls(true);
    midiPanel.setPreferredSize(new Dimension(
            (op.lastBeat() + 2) * GuiPanel.cellWidth,
            (op.getTones().size() + 2) * GuiPanel.cellHeight));
  }


  @Override
  public void togglePlay() {

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
      BEAT--;
    }
    refresh();
  }

  @Override
  public void nextBeat() {
    if (BEAT + 1 <= op.lastBeat() + 1) {
      BEAT++;
    }
    refresh();
  }

  @Override
  public void toEnd() {
    BEAT = op.lastBeat() + 1;
    refresh();
  }

  @Override
  public void toBeginning() {
    BEAT = 0;
    refresh();
  }

  @Override
  public void refresh() {
    this.revalidate();
    this.repaint();
    this.update();
  }
}

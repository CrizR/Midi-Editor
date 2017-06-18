package cs3500.music.view.graphicsview;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import cs3500.music.model.IMusicOperations;


/**
 * IView that represents the graphical view for the user. This view shows each note on a grid
 * above keys. As the user clicks the left or right arrow, the bar on the
 * midi editor moves in accordance to the changed BEAT. The keys light up
 * when the bar is on top of the corresponding note. This is represented with two JPanels,
 * the midiPanel and the piano panel. We made the BEAT public and static in this class so that
 * our panels have access to the current BEAT being "played." The current BEAT is updated in the
 * view because the model is a read only object.
 */
public class GuiIViewFrame extends JFrame implements GuiIView {
  private final IMusicOperations op;
  public static int BEAT;
  private final KeyListener keyListener = new KeyListener() {

    @Override
    public void keyTyped(KeyEvent e) {
      //Do Nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
      int key = e.getKeyCode();
      if (key == KeyEvent.VK_RIGHT) {
        if (BEAT + 1 <= op.lastBeat() + 1) {
          BEAT++;
        }
        refresh();
      } else if (key == KeyEvent.VK_LEFT) {
        if (BEAT - 1 >= 0) {
          BEAT--;
        }
        refresh();
      }
    }

    @Override
    public void keyReleased(KeyEvent e) {
      //Do Nothing
    }
  };

  /**
   * Constructs a GuiIView frame by first instantiating the midipanel and piano panel
   * to the given IMusicOperations. It then sets the size of each panel, adds key listeners
   * to each panel, sets up a scroll bar for the midi panel, and finally sets the size for this
   * frame.
   *
   * @param op Represents the IMusicOperations to create the view for.
   */
  public GuiIViewFrame(IMusicOperations op) {
    this.op = op;
    JPanel midiPanel = new GuiPanel(op);
    JPanel pianoPanel = new PianoPanel(op);

    //init piano panel
    pianoPanel.setPreferredSize(new Dimension(1000, 250));
    pianoPanel.setBackground(Color.gray.brighter());
    //this.pianoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));


    //add key listeners
    midiPanel.addKeyListener(keyListener);
    pianoPanel.addKeyListener(keyListener);
    midiPanel.setFocusable(true);
    pianoPanel.setFocusable(true);


    //make midi panel scrollable
    JScrollPane scrollPane = new JScrollPane(midiPanel,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    midiPanel.setAutoscrolls(true);
    midiPanel.setPreferredSize(new Dimension(
            (op.lastBeat() + 2) * GuiPanel.cellWidth,
            (op.getTones().size() + 2) * GuiPanel.cellHeight));
    scrollPane.setPreferredSize(new Dimension(1000, 500));
    scrollPane.getVerticalScrollBar().setUnitIncrement(128);
    scrollPane.getHorizontalScrollBar().setUnitIncrement(128);


    //add to frame
    this.add(scrollPane, BorderLayout.NORTH);
    this.add(pianoPanel, BorderLayout.SOUTH);
    this.setSize(1000, 750);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    this.setResizable(false);
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

  }

  @Override
  public void nextBeat() {

  }

  @Override
  public void refresh() {
    this.revalidate();
    this.repaint();
  }

}

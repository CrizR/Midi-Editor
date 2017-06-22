package cs3500.music.view.graphicsview;

import java.awt.*;
import java.awt.event.MouseEvent;

import javax.swing.*;

import cs3500.music.mechanics.Note;
import cs3500.music.model.IMusicOperations;
import cs3500.music.view.IView;


/**
 * IView that represents the graphical view for the user. This view shows each note on a grid
 * above keys. As the user clicks the left or right arrow, the bar on the
 * midi editor moves in accordance to the changed BEAT. The keys light up
 * when the bar is on top of the corresponding note. This is represented with two JPanels,
 * the midiPanel and the piano panel. We made the BEAT public and static in this class so that
 * our panels have access to the current BEAT being "played." The current BEAT is updated in the
 * view because the model is a read only object.
 */
public class GuiViewFrame extends JFrame implements IView {
  private IMusicOperations op;
  public static int BEAT;
  public static final int PIANO_WIDTH = 1000;
  public static final int PIANO_HEIGHT = 250;
  public static final int MIDI_WIDTH = 1000;
  public static final int MIDI_HEIGHT = 500;
  JPanel midiPanel;
  JPanel pianoPanel;
  JScrollPane scrollPane;

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
    scrollPane = new JScrollPane(midiPanel,
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
      if (BEAT % (MIDI_WIDTH / GuiPanel.cellWidth) == 0) {
        this.scrollPane.getHorizontalScrollBar().setValue(BEAT - (MIDI_WIDTH / GuiPanel.cellWidth)
                * GuiPanel.cellWidth);
      }
      BEAT--;
    }
    refresh();
  }

  @Override
  public void nextBeat() {
    if (BEAT + 1 <= op.lastBeat() + 1) {
      movePanel();
      BEAT++;
    }
    refresh();
  }

  @Override
  public void toEnd() {
    BEAT = op.lastBeat() + 1;
    this.scrollPane.getHorizontalScrollBar().setValue(GuiViewFrame.BEAT * GuiPanel.cellWidth);
    refresh();
  }

  @Override
  public void toBeginning() {
    BEAT = 0;
    this.scrollPane.getHorizontalScrollBar().setValue(GuiViewFrame.BEAT * GuiPanel.cellWidth);
    refresh();
  }

  @Override
  public void refresh() {
    this.revalidate();
    this.repaint();
    this.update();
  }

  @Override
  public void addNote(MouseEvent e) {
    for (int i = 0; i < PianoPanel.keys.size(); i++) {
      PianoPanel.Key k = PianoPanel.keys.get(i);
      if (k.onKey(e.getX(), e.getY() - GuiViewFrame.MIDI_HEIGHT)) {
        if (k.getPitch().isSharp()) {
          op.addNote(new Note(k.getPitch(), k.getOctave(), 1, 1, 60),
                  GuiViewFrame.BEAT);
          this.nextBeat();
          break;
        } else {
          for (int j = i; j < PianoPanel.keys.size(); j++) {
            if (k.onKey(e.getX(), e.getY() - GuiViewFrame.MIDI_HEIGHT)) {
              op.addNote(new Note(k.getPitch(), k.getOctave(), 1, 1, 60),
                      GuiViewFrame.BEAT);
              this.nextBeat();
              break;
            }
          }
        }
      }
    }

  }

  public void movePanel() {
    if (GuiViewFrame.BEAT % (GuiViewFrame.MIDI_WIDTH / GuiPanel.cellWidth) == 0) {
      this.scrollPane.getHorizontalScrollBar().setValue(GuiViewFrame.BEAT * GuiPanel.cellWidth);
    }
  }
}

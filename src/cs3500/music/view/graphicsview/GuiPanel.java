package cs3500.music.view.graphicsview;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import cs3500.music.mechanics.Note;
import cs3500.music.mechanics.repeats.Repeat;
import cs3500.music.mechanics.repeats.RepeatType;
import cs3500.music.model.IMusicOperations;


/**
 * Represents the notes part of the view. This class builds a panel that includes every note
 * in the model placed in the right position, a grid that fits four notes per column, a
 * current BEAT line, and an octave line.
 */
public class GuiPanel extends JPanel {

  private final IMusicOperations op;
  private final HashMap<String, Integer> pitchToY = new HashMap<>();
  private ArrayList<String> tones;
  private int numOfTones;
  private ArrayList<String> revTone;
  private final int fromTop = 25;
  private final int fromSide = 40;
  private static int scale = 1;
  protected static final int CELL_WIDTH = 42 * scale;
  protected static final int CELL_HEIGHT = 25 * scale;
  private int columnNum;

  /**
   * Builds the panel by first determining the number of tones then
   * getting all of the notes, and then finally by painting the view.
   *
   * @param op Represents the IMusicOperations to build the view off of.
   */
  GuiPanel(IMusicOperations op) {
    this.op = op;
    update();
    repaint();
  }

  @Override
  public void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    super.paintComponent(g2);

    update();

    for (int i : op.getStartingBeats()) {
      HashMap<String, Note> notes = this.op.getNotes(i);
      for (String t : notes.keySet()) {
        drawANote(g2, i, t, notes.get(t).getDuration());
      }
    }
    this.drawGrid(g2);
    this.drawTones(g2);
    this.drawBeats(g2);
    this.drawOctaveLine(g2);
    g2.setColor(Color.MAGENTA);
    this.drawLine(g2);
    this.drawRepeats(g2);
  }

  //Updates the GuiPanel every time the component is called.
  private void update() {
    numOfTones = this.op.getTones().size();
    tones = this.op.getTones();
    Collections.reverse(this.tones);
    revTone = tones;
    columnNum = Math.round(this.op.lastBeat() / 4);
    for (int i = 0; i < numOfTones; i++) {
      this.pitchToY.put(tones.get(i), fromTop + i * CELL_HEIGHT);
    }
  }

  // draws the the line
  private void drawLine(Graphics g2) {
    g2.drawLine(fromSide + (GuiViewFrame.BEAT * CELL_WIDTH), fromTop,
            fromSide + (GuiViewFrame.BEAT * CELL_WIDTH),
            fromTop + CELL_HEIGHT * this.numOfTones);
  }

  // Draws a note by a given starting BEAT and the tone
  private void drawANote(Graphics g2, int beat, String tone, int duration) {
    g2.setColor(Color.GREEN);
    g2.fillRect(fromSide + beat * CELL_WIDTH, this.pitchToY.get(tone),
            duration * CELL_WIDTH, CELL_HEIGHT);
    g2.setColor(Color.black);
    g2.fillRect(fromSide + beat * CELL_WIDTH, this.pitchToY.get(tone),
            CELL_WIDTH, CELL_HEIGHT);
  }

  // draws the tone labeling
  private void drawTones(Graphics g2) {
    for (int i = 0; i < this.revTone.size(); i++) {
      g2.drawString(this.revTone.get(i), fromSide - 30, i * CELL_HEIGHT + fromTop + 20);
    }
  }

  // draws the beats labeling
  private void drawBeats(Graphics g2) {
    for (int i = 0; i <= columnNum; i++) {
      g2.drawString(Integer.toString(i * 4), fromSide + (CELL_WIDTH * 4) * i,
              fromTop - 5);
    }
  }

  // draws the grid
  private void drawGrid(Graphics g2) {
    g2.setColor(Color.BLACK);
    for (int j = 0; j <= columnNum; j++) {
      g2.drawRect(fromSide + j * CELL_WIDTH * 4, fromTop,
              CELL_WIDTH * 4, CELL_HEIGHT * numOfTones);

      for (int i = 0; i <= numOfTones; i++) {
        g2.drawLine(fromSide + j * CELL_WIDTH * 4, fromTop + i * CELL_HEIGHT,
                fromSide + (j + 1) * CELL_WIDTH * 4, fromTop + i * CELL_HEIGHT);
      }
    }
  }

  // draw the bold line across octave
  private void drawOctaveLine(Graphics2D g2) {
    for (int i = 0; i < tones.size(); i++) {
      if (tones.get(i).charAt(0) == 'B' && i != tones.size() - 1) {
        g2.setStroke(new BasicStroke(3));
        g2.drawLine(fromSide, fromTop + i * CELL_HEIGHT,
                fromSide +
                        (int) (Math.floor(this.op.lastBeat() / 4) + 1) * CELL_WIDTH * 4,
                fromTop + i * CELL_HEIGHT);
      }
    }
  }

  private void drawRepeats(Graphics g2) {
    HashMap<Integer, Repeat> repeats = op.getRepeats();
    for (Map.Entry<Integer, Repeat> entry : repeats.entrySet()) {
      if (entry.getValue().isDoubleRepeat()) {
        g2.setColor(Color.RED);
        g2.fillRect(fromSide + entry.getKey() * CELL_WIDTH, fromTop, 2, this.tones.size() * CELL_HEIGHT);
        g2.fillOval((fromSide + entry.getKey() * CELL_WIDTH) + CELL_WIDTH / 7, (this.tones.size() * CELL_HEIGHT / 2) + 20, 7, 7);
        g2.fillOval((fromSide + entry.getKey() * CELL_WIDTH) + CELL_WIDTH / 7, (this.tones.size() * CELL_HEIGHT / 2) - 20, 7, 7);
        g2.fillOval((fromSide + entry.getKey() * CELL_WIDTH) - CELL_WIDTH / 3, (this.tones.size() * CELL_HEIGHT / 2) + 20, 7, 7);
        g2.fillOval((fromSide + entry.getKey() * CELL_WIDTH) - CELL_WIDTH / 3, (this.tones.size() * CELL_HEIGHT / 2) - 20, 7, 7);
      } else if (entry.getValue().getType() == RepeatType.END) {
        g2.setColor(Color.RED);
        g2.fillRect(fromSide + entry.getKey() * CELL_WIDTH, fromTop, 2, this.tones.size() * CELL_HEIGHT);
        g2.fillOval((fromSide + entry.getKey() * CELL_WIDTH) + CELL_WIDTH / 8, (this.tones.size() * CELL_HEIGHT / 2) + 20, 7, 7);
        g2.fillOval((fromSide + entry.getKey() * CELL_WIDTH) + CELL_WIDTH / 8, (this.tones.size() * CELL_HEIGHT / 2) - 20, 7, 7);

      } else {
        g2.setColor(Color.RED);
        g2.fillRect(fromSide + entry.getKey() * CELL_WIDTH, fromTop, 2, this.tones.size() * CELL_HEIGHT);
        g2.fillOval((fromSide + entry.getKey() * CELL_WIDTH) - CELL_WIDTH / 3, (this.tones.size() * CELL_HEIGHT / 2) + 20, 7, 7);
        g2.fillOval((fromSide + entry.getKey() * CELL_WIDTH) - CELL_WIDTH / 3, (this.tones.size() * CELL_HEIGHT / 2) - 20, 7, 7);

      }
    }
  }
}
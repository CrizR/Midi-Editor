package cs3500.music.view.graphicsview;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.JPanel;

import cs3500.music.mechanics.Note;
import cs3500.music.model.IMusicOperations;


/**
 * Represents the notes part of the view. This class builds a panel that includes every note
 * in the model placed in the right position, a grid that fits four notes per column, a
 * current BEAT line, and an octave line.
 */
public class GuiPanel extends JPanel {

  private final IMusicOperations op;
  private final HashMap<String, Integer> pitchToY = new HashMap<>();
  private final ArrayList<String> tones;
  private final int numOfTones;
  private final ArrayList<String> revTone;
  private final int fromTop = 25;
  private final int fromSide = 40;
  private static int scale = 1;
  static final int cellWidth = 42 * scale;
  static final int cellHeight = 25 * scale;
  private final int columnNum;

  /**
   * Builds the panel by first determining the number of tones then
   * getting all of the notes, and then finally by painting the view.
   *
   * @param op Represents the IMusicOperations to build the view off of.
   */
  GuiPanel(IMusicOperations op) {
    this.op = op;
    numOfTones = this.op.getTones().size();
    tones = this.op.getTones();
    Collections.reverse(this.tones);
    revTone = tones;
    columnNum = (int) Math.round(this.op.lastBeat() / 4);

    for (int i = 0; i < numOfTones; i++) {
      this.pitchToY.put(tones.get(i), fromTop + i * cellHeight);
    }
    repaint();
  }

  @Override
  public void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    super.paintComponent(g2);



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
  }

  // draws the the line
  private void drawLine(Graphics g2) {
    g2.drawLine(fromSide + (GuiViewFrame.BEAT * cellWidth), fromTop,
            fromSide + (GuiViewFrame.BEAT * cellWidth),
            fromTop + cellHeight * this.numOfTones);
  }

  // Draws a note by a given starting BEAT and the tone
  private void drawANote(Graphics g2, int beat, String tone, int duration) {
    g2.setColor(Color.GREEN);
    g2.fillRect(fromSide + beat * cellWidth, this.pitchToY.get(tone),
            duration * cellWidth, cellHeight);
    g2.setColor(Color.black);
    g2.fillRect(fromSide + beat * cellWidth, this.pitchToY.get(tone),
            cellWidth, cellHeight);
  }

  // draws the tone labeling
  private void drawTones(Graphics g2) {
    for (int i = 0; i < this.revTone.size(); i++) {
      g2.drawString(this.revTone.get(i), fromSide - 30, i * cellHeight + fromTop + 20);
    }
  }

  // draws the beats labeling
  private void drawBeats(Graphics g2) {
    for (int i = 0; i <= columnNum; i++) {
      g2.drawString(Integer.toString(i * 4), fromSide + (cellWidth * 4) * i,
              fromTop - 5);
    }
  }

  // draws the grid
  private void drawGrid(Graphics g2) {
    g2.setColor(Color.BLACK);
    for (int j = 0; j <= columnNum; j++) {
      g2.drawRect(fromSide + j * cellWidth * 4, fromTop,
              cellWidth * 4, cellHeight * numOfTones);

      for (int i = 0; i <= numOfTones; i++) {
        g2.drawLine(fromSide + j * cellWidth * 4, fromTop + i * cellHeight,
                fromSide + (j + 1) * cellWidth * 4, fromTop + i * cellHeight);
      }
    }
  }

  // draw the bold line across octave
  private void drawOctaveLine(Graphics2D g2) {
    for (int i = 0; i < tones.size(); i++) {
      if (tones.get(i).charAt(0) == 'B' && i != tones.size() - 1) {
        g2.setStroke(new BasicStroke(3));
        g2.drawLine(fromSide, fromTop + i * cellHeight,
                fromSide +
                        (int) (Math.floor(this.op.lastBeat() / 4) + 1) * cellWidth * 4,
                fromTop + i * cellHeight);
      }
    }
  }
}
package cs3500.music.view.graphicsview;

import java.awt.Color;
import java.awt.Graphics;

import java.util.ArrayList;

import javax.swing.JPanel;

import cs3500.music.mechanics.Pitch;
import cs3500.music.model.IMusicOperations;


/**
 * A class that represents the piano aspect of the view. This panel draws the white and black
 * KEYS at the right positions, given the set width, set length, and IMusicOperations.
 */
public class PianoPanel extends JPanel {
  private final int whiteKeyLength = 200;
  private final int blackKeyLength = 100;
  private final int keyWidth = 12;
  private final Color playedColor = Color.ORANGE;
  private final IMusicOperations op;
  protected static ArrayList<Key> KEYS = new ArrayList<>();

  /**
   * Constructs a piano panel by first setting the IMusicOperations
   * and then drawing it.
   */
  PianoPanel(IMusicOperations op) {
    this.op = op;
    repaint();
  }

  //Class that represents a key. A key has a boolean that represents whether or not it is
  //currently being play, a color, a tone, and a position.
  public class Key {
    boolean played;
    Color color;
    Pitch p;
    int x;
    int y;
    int octave;

    //Builds a key via the given boolean, color, tone, and position
    private Key(boolean played, Color color, Pitch p, int x, int y, int octave) {
      this.played = played;
      this.color = color;
      this.p = p;
      this.x = x;
      this.y = y;
      this.octave = octave;
    }

    /**
     * Determines if the position is on the position of the key
     *
     * @param x1 x value of pos
     * @param y1 y value of pos
     * @return whether or not the pos is on the key
     */
    protected boolean onKey(int x1, int y1) {
      if (p.isSharp()) {
        return x1 > x && x1 < x + keyWidth
                && y1 > y && y1 < y + blackKeyLength;
      } else {
        return x1 > x && x1 < x + keyWidth
                && y1 > y && y1 < y + whiteKeyLength;
      }
    }

    /**
     * Returns the pitch of the key
     *
     * @return the pitch of this object
     */
    public Pitch getPitch() {
      return p;
    }

    /**
     * Returns the octave of this key
     *
     * @return the octave of this key.
     */
    protected int getOctave() {
      return this.octave;
    }
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    int x = 50;
    int y = 0;
    KEYS = new ArrayList<>();
    initKeys(x, y);
    drawWhite(g);
    drawBlack(g);
  }

  //Draws the white KEYS
  private void drawWhite(Graphics g) {
    for (Key key : this.KEYS) {
      if (key.played) {
        g.setColor(playedColor);
      } else {
        g.setColor(key.color);
      }
      if (!key.getPitch().isSharp()) {
        g.fillRect(key.x, key.y, keyWidth, whiteKeyLength);
        g.setColor(Color.BLACK);
        g.drawRect(key.x, key.y, keyWidth, whiteKeyLength);//test.txt.txt
      }
    }
  }

  //Draws the black KEYS
  private void drawBlack(Graphics g) {
    for (Key key : this.KEYS) {
      if (key.played) {
        g.setColor(playedColor);
      } else {
        g.setColor(key.color);
      }
      if (key.getPitch().isSharp()) {
        g.fillRect(key.x + 3, key.y, keyWidth - 2, blackKeyLength);
        g.setColor(Color.BLACK);
        g.drawRect(key.x + 3, key.y, keyWidth - 2, blackKeyLength);
      }
    }
  }

  //initializes the KEYS to draw
  private void initKeys(int x, int y) {
    //initialize the KEYS
    for (int i = 1; i <= 10; i++) {
      for (Pitch p : Pitch.values()) {
        String tone = p.toString() + Integer.toString(i);
        boolean played = false;
        if (op.activeNotes(GuiViewFrame.BEAT).containsKey(tone)) {
          played = true;
        }
        if (p.isSharp()) {
          x += keyWidth / 3;
          this.KEYS.add(new Key(played, Color.BLACK, p, x, y, i));
          x -= keyWidth / 3;
        } else {
          x += keyWidth;
          this.KEYS.add(new Key(played, Color.WHITE, p, x, y, i));
        }
      }
    }
  }
}

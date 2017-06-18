package cs3500.music.view.graphicsview;

import java.awt.Color;
import java.awt.Graphics;

import java.util.ArrayList;

import javax.swing.JPanel;

import cs3500.music.mechanics.Pitch;
import cs3500.music.model.IMusicOperations;


/**
 * A class that represents the piano aspect of the view. This panel draws the white and black
 * keys at the right positions, given the set width, set length, and IMusicOperations.
 */
public class PianoPanel extends JPanel {
  private final int whiteKeyLength = 200;
  private final int blackKeyLength = 100;
  private final int keyWidth = 12;
  private final Color playedColor = Color.ORANGE;
  private final IMusicOperations op;

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
  private class Key {
    boolean played;
    Color color;
    String tone;
    int x;
    int y;

    //Builds a key via the given boolean, color, tone, and position
    private Key(boolean played, Color color, String tone, int x, int y) {
      this.played = played;
      this.color = color;
      this.tone = tone;
      this.x = x;
      this.y = y;
    }
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    int x = 25;
    int y = 0;
    ArrayList<Key> keys = new ArrayList<>();
    initKeys(x, y, keys);
    drawWhite(g, keys);
    drawBlack(g, keys);
  }

  //Draws the white keys
  private void drawWhite(Graphics g, ArrayList<Key> keys) {
    for (Key key : keys) {
      if (key.played) {
        g.setColor(playedColor);
      } else {
        g.setColor(key.color);
      }
      if (!key.tone.contains("#")) {
        g.fillRect(key.x, key.y, keyWidth, whiteKeyLength);
        g.setColor(Color.BLACK);
        g.drawRect(key.x, key.y, keyWidth, whiteKeyLength);//test.txt
      }
    }
  }

  //Draws the black keys
  private void drawBlack(Graphics g, ArrayList<Key> keys) {
    for (Key key : keys) {
      if (key.played) {
        g.setColor(playedColor);
      } else {
        g.setColor(key.color);
      }
      if (key.tone.contains("#")) {
        g.fillRect(key.x + 3, key.y, keyWidth - 2, blackKeyLength);
        g.setColor(Color.BLACK);
        g.drawRect(key.x + 3, key.y, keyWidth - 2, blackKeyLength);
      }
    }
  }

  //initializes the keys to draw
  private void initKeys(int x, int y, ArrayList<Key> keys) {
    //initialize the keys
    for (int i = 0; i <= 10; i++) {
      for (Pitch p : Pitch.values()) {
        String tone = p.toString() + Integer.toString(i);
        boolean played = false;
        if (op.activeNotes(GuiIViewFrame.BEAT).containsKey(tone)) {
          played = true;
        }
        if (p.toString().contains("#")) {
          x += keyWidth / 3;
          keys.add(new Key(played, Color.BLACK, tone, x, y));
          x -= keyWidth / 3;
        } else {
          x += keyWidth;
          keys.add(new Key(played, Color.WHITE, tone, x, y));
        }
      }
    }
  }

}

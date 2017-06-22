package cs3500.music.tests;

import org.junit.Test;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import cs3500.music.controller.Controller;
import cs3500.music.model.IMusicOperations;
import cs3500.music.model.Music;
import cs3500.music.view.IView;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by ChrisRisley on 6/19/17.
 */
public class ControllerTests {
  IMusicOperations op = new Music();
//  MockView mock = new MockView();
//  Controller compControl = new Controller(op).setView(mock);

//
//  private class MockView extends JFrame implements IView {
//    StringBuilder log;
//
//    public String getLog() {
//      return log.toString();
//    }
//
//    @Override
//    public void initialize() {
//      log.append("Initialized").append("\n");
//    }
//
//    @Override
//    public void prevBeat() {
//      log.append("Previous Beat").append("\n");
//    }
//
//    @Override
//    public void nextBeat() {
//      log.append("Next Beat").append("\n");
//    }
//
//    @Override
//    public void toEnd() {
//      log.append("To End").append("\n");
//    }
//
//    @Override
//    public void toBeginning() {
//
//      log.append("To Beginning").append("\n");
//    }
//
//    @Override
//    public void togglePlay() {
//      log.append("To Beginning").append("\n");
//
//    }
//
//    @Override
//    public void resetFocus() {
//
//    }
//
//    @Override
//    public void refresh() {
//
//    }
//
//    @Override
//    public void addKeyListener(KeyListener listener) {
//
//    }
//
//    @Override
//    public void addMouseListener(MouseListener listener) {
//
//    }
//
//    @Override
//    public void addNote(MouseEvent e) {
//      log.append("Added Note").append("\n");
//
//    }
//  }

  private class KListener implements KeyListener {
    StringBuilder s = new StringBuilder();

    @Override
    public void keyTyped(KeyEvent e) {
      s.append("Typed").append("\n");
    }

    @Override
    public void keyPressed(KeyEvent e) {
      s.append("Pressed").append("\n");

    }

    @Override
    public void keyReleased(KeyEvent e) {
      s.append("Released").append("\n");

    }

    public String getLog() {
      return s.toString();
    }
  }

  @Test
  public void testControl1() {
//    KListener x = new KListener();
//    mock.addKeyListener(x);
//    KeyEvent key = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UP, 'K');
//    x.keyPressed(key);
//    assertEquals("", x.getLog());
//

  }
}




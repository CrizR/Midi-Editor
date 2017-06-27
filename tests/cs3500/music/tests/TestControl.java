package cs3500.music.tests;

import org.junit.Test;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.*;

import cs3500.music.controller.Controller;
import cs3500.music.controller.KeyboardHandler;
import cs3500.music.controller.MouseHandler;
import cs3500.music.model.IMusicOperations;
import cs3500.music.model.Music;
import cs3500.music.view.IView;
import cs3500.music.view.ViewBuilder;
import cs3500.music.view.textview.GuiView;

import static junit.framework.TestCase.assertEquals;

/**
 * Tests the controller.
 */
public class TestControl {
  private IMusicOperations op = new Music();
  private IView view = ViewBuilder.createView("visual", op);
  private KeyboardHandler kh = new KeyboardHandler();
  private MouseHandler mh = new MouseHandler((GuiView) view, op);
  private JPanel test = new JPanel();
  private boolean testRunnable = false;

  //test mock view
  class MockView implements GuiView {
    StringBuilder log = new StringBuilder();

    public String getLog() {
      return log.toString();
    }

    @Override
    public void initialize() {
      log.append("Initialize").append("\n");
    }

    @Override
    public void prevBeat() {
      log.append("prevBeat").append("\n");
    }

    @Override
    public void nextBeat() {
      log.append("nextBeat").append("\n");
    }

    @Override
    public void toEnd() {
      log.append("toEnd").append("\n");
    }

    @Override
    public void toBeginning() {
      log.append("toBeginning").append("\n");
    }

    @Override
    public void togglePlay() {
      log.append("togglePlay").append("\n");
    }

    @Override
    public void resetFocus() {
      log.append("resetFocus").append("\n");
    }

    @Override
    public void refresh() {
      log.append("refresh").append("\n");
    }

    @Override
    public void addKeyListener(KeyListener listener) {
      log.append("addKeyListener").append("\n");
    }

    @Override
    public void addMouseListener(MouseListener listener) {
      log.append("addMouseListener").append("\n");
    }

    @Override
    public void addNote(MouseEvent e, int duration) {
      log.append("addNote").append("\n");
    }

    @Override
    public void movePanel() {
      //do nothing
    }


    @Override
    public void increaseTempo() {
      log.append("Increase Tempo").append("\n");
    }

    @Override
    public void decreaseTempo() {
      log.append("Decrease Tempo").append("\n");
    }

    @Override
    public void togglePractice() {

    }

    @Override
    public void startCreate() {

    }

    @Override
    public void addBeginRepeat() {

    }

    @Override
    public void addEndRepeat() {

    }

    @Override
    public void addDoubleRepeat() {

    }

    @Override
    public void addTimebar() {

    }
  }


  @Test
  //tests to see if the  keyhandler properly maps the runnable and then executes
  public void testKeyHandler() {
    testRunnable = false;
    HashMap<Integer, Runnable> testRunnables = new HashMap<>();
    testRunnables.put(KeyEvent.VK_UP, () -> testMethod());
    kh.setKeyPressedMap(testRunnables);
    KeyEvent key = new KeyEvent(test, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,
            KeyEvent.VK_UP, 'c');
    kh.keyPressed(key);

    assertEquals(true, testRunnable);
  }

  @Test
  //tests to see if the  keyhandler properly maps the runnable and then doesn't execute in this case
  public void testKeyHandler1() {
    testRunnable = false;
    HashMap<Integer, Runnable> testRunnables = new HashMap<>();
    testRunnables.put(KeyEvent.VK_UP, () -> testMethod());
    kh.setKeyPressedMap(testRunnables);

    KeyEvent key = new KeyEvent(test, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,
            KeyEvent.VK_DOWN, 'K');
    kh.keyPressed(key);

    assertEquals(false, testRunnable);
  }

  @Test
  //tests to see if the  keyhandler properly calls the right methods
  public void testKeyHandler2() {
    Controller c1 = new Controller(kh, mh);
    MockView x = new MockView();
    c1.setView(x);

    KeyEvent key = new KeyEvent(test, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,
            KeyEvent.VK_RIGHT, 's');
    kh.keyPressed(key);
    assertEquals("addKeyListener\n" +
            "resetFocus\n" +
            "addMouseListener\n" +
            "resetFocus\n" +
            "nextBeat\n", x.getLog());

  }

  @Test
  //tests to see if the  keyhandler properly calls the right methods
  public void testKeyHandler3() {
    Controller c1 = new Controller(kh, mh);
    MockView x = new MockView();
    c1.setView(x);

    KeyEvent key = new KeyEvent(test, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,
            KeyEvent.VK_HOME, 's');
    kh.keyPressed(key);
    assertEquals("addKeyListener\n" +
            "resetFocus\n" +
            "addMouseListener\n" +
            "resetFocus\n" +
            "toBeginning\n", x.getLog());

  }

  @Test
  //tests to see if the  keyhandler properly calls the right methods
  public void testKeyHandler4() {
    Controller c1 = new Controller(kh, mh);
    MockView x = new MockView();
    c1.setView(x);

    KeyEvent key = new KeyEvent(test, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,
            KeyEvent.VK_END, 's');
    kh.keyPressed(key);
    assertEquals("addKeyListener\n" +
            "resetFocus\n" +
            "addMouseListener\n" +
            "resetFocus\n" +
            "toEnd\n", x.getLog());

  }

  @Test
  //tests to see if the  keyhandler properly calls the right methods
  public void testKeyHandler5() {
    Controller c1 = new Controller(kh, mh);
    MockView x = new MockView();
    c1.setView(x);

    KeyEvent key = new KeyEvent(test, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,
            KeyEvent.VK_SPACE, 's');
    kh.keyPressed(key);
    assertEquals("addKeyListener\n" +
            "resetFocus\n" +
            "addMouseListener\n" +
            "resetFocus\n" +
            "togglePlay\n", x.getLog());

  }

  @Test
  //tests to see if the mousehandler properly calls the right methods
  public void testMouseHandler5() {
    MockView x = new MockView();
    MouseHandler mh = new MouseHandler(x, op);
    Controller c1 = new Controller(kh, mh);
    c1.setView(x);
    MouseEvent mouse = new MouseEvent(test, // which
            MouseEvent.MOUSE_CLICKED, // what
            System.currentTimeMillis(), // when
            0, // no modifiers
            10, 10, // where: at (10, 10}
            1, // only 1 click
            false);
    mh.mouseClicked(mouse);
    assertEquals("addKeyListener\n" +
            "resetFocus\n" +
            "addMouseListener\n" +
            "resetFocus\n" +
            "addNote\n", x.getLog());
  }

  //used for testing the keyhandler
  private void testMethod() {
    testRunnable = true;
  }
}

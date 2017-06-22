package cs3500.music.tests;

import org.junit.Test;

import java.awt.event.KeyEvent;
import java.util.HashMap;

import javax.swing.*;

import cs3500.music.controller.KeyboardHandler;
import cs3500.music.model.IMusicOperations;
import cs3500.music.model.Music;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by ChrisRisley on 6/19/17.
 */
public class TestKeyboardHandler {
  KeyboardHandler kh = new KeyboardHandler();
  IMusicOperations op = new Music();
  JPanel test = new JPanel();
  boolean testRunnable = false;
  

  @Test
  public void testKeyHandler() throws Exception {
    testRunnable = false;
    HashMap<Integer, Runnable> testRunnables = new HashMap<>();
    testRunnables.put(KeyEvent.VK_UP, () -> testMethod());
    kh.setKeyPressedMap(testRunnables);
    KeyEvent key = new KeyEvent(test, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UP, 'K');
    kh.keyPressed(key);

    assertEquals(true, testRunnable);
  }

  @Test
  public void testKeyHandler1() throws Exception {
    testRunnable = false;
    HashMap<Integer, Runnable> testRunnables = new HashMap<>();
    testRunnables.put(KeyEvent.VK_UP, () -> testMethod());
    kh.setKeyPressedMap(testRunnables);

    KeyEvent key = new KeyEvent(test, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DOWN, 'K');
    kh.keyPressed(key);

    assertEquals(false, testRunnable);
  }

  private void testMethod() {
    testRunnable = true;
  }
}

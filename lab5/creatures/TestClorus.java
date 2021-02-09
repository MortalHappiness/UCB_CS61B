package creatures;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

public class TestClorus {

  @Test
  public void testBasics() {
    Clorus c = new Clorus(1);
    assertEquals(1, c.energy(), 0.01);
    assertEquals(new Color(34, 0, 231), c.color());
    c.move();
    assertEquals(0.97, c.energy(), 0.01);
    c.move();
    assertEquals(0.94, c.energy(), 0.01);
    c.stay();
    assertEquals(0.93, c.energy(), 0.01);
    c.stay();
    assertEquals(0.92, c.energy(), 0.01);
  }

  @Test
  public void testReplicate() {
    Clorus c = new Clorus(2);
    Clorus child = c.replicate();
    assertNotEquals(c, child);
    assertEquals(1.0, c.energy(), 0.01);
    assertEquals(1.0, child.energy(), 0.01);
  }

  @Test
  public void testAttack() {
    Clorus c = new Clorus(1);
    Plip p = new Plip(2);
    c.attack(p);
    assertEquals(3.0, c.energy(), 0.01);
  }

  @Test
  public void testChoose() {

    // No empty adjacent spaces; stay.
    Clorus c = new Clorus(1.2);
    HashMap<Direction, Occupant> surrounded = new HashMap<>();
    surrounded.put(Direction.TOP, new Impassible());
    surrounded.put(Direction.BOTTOM, new Impassible());
    surrounded.put(Direction.LEFT, new Impassible());
    surrounded.put(Direction.RIGHT, new Impassible());

    Action actual = c.chooseAction(surrounded);
    Action expected = new Action(Action.ActionType.STAY);

    assertEquals(expected, actual);

    // Has empty space and seen Plip, attack it.
    c = new Clorus(1.2);
    HashMap<Direction, Occupant> topPlip = new HashMap<>();
    topPlip.put(Direction.TOP, new Plip(1.0));
    topPlip.put(Direction.BOTTOM, new Empty());
    topPlip.put(Direction.LEFT, new Empty());
    topPlip.put(Direction.RIGHT, new Empty());

    actual = c.chooseAction(topPlip);
    expected = new Action(Action.ActionType.ATTACK, Direction.TOP);
    assertEquals(expected, actual);

    // Energy >= 1; replicate towards an empty space.
    c = new Clorus(1.2);
    HashMap<Direction, Occupant> topEmpty = new HashMap<Direction, Occupant>();
    topEmpty.put(Direction.TOP, new Empty());
    topEmpty.put(Direction.BOTTOM, new Impassible());
    topEmpty.put(Direction.LEFT, new Impassible());
    topEmpty.put(Direction.RIGHT, new Impassible());

    actual = c.chooseAction(topEmpty);
    expected = new Action(Action.ActionType.REPLICATE, Direction.TOP);

    assertEquals(expected, actual);

    // Energy >= 1; replicate towards an empty space.
    c = new Clorus(1.2);
    HashMap<Direction, Occupant> allEmpty = new HashMap<Direction, Occupant>();
    allEmpty.put(Direction.TOP, new Empty());
    allEmpty.put(Direction.BOTTOM, new Empty());
    allEmpty.put(Direction.LEFT, new Empty());
    allEmpty.put(Direction.RIGHT, new Empty());

    actual = c.chooseAction(allEmpty);
    Action unexpected = new Action(Action.ActionType.STAY);

    assertNotEquals(unexpected, actual);

    // Energy < 1; move.
    c = new Clorus(.99);

    actual = c.chooseAction(allEmpty);
    unexpected = new Action(Action.ActionType.STAY);

    assertNotEquals(expected, unexpected);

    // Energy < 1; move.
    c = new Clorus(.99);

    actual = c.chooseAction(topEmpty);
    expected = new Action(Action.ActionType.MOVE, Direction.TOP);

    assertEquals(expected, actual);
  }
}

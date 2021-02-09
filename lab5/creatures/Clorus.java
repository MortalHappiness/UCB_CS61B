package creatures;

import edu.princeton.cs.algs4.ST;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.HugLifeUtils;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Map;

/**
 * An implementation of a fierce blue-colored predator.
 *
 * @author MortalHappiness
 */
public class Clorus extends Creature {

  /** red color. */
  private final int r = 34;
  /** green color. */
  private final int g = 0;
  /** blue color. */
  private final int b = 231;

  private final double MIN_ENERGY = 0.0;

  /** creates plip with energy equal to E. */
  public Clorus(double e) {
    super("clorus");
    energy = e;
  }

  public Clorus() {
    this(1);
  }

  public Color color() {
    return color(r, g, b);
  }

  public void attack(Creature c) {
    this.energy += c.energy();
  }

  public void move() {
    this.energy -= 0.03;
  }

  public void stay() {
    this.energy -= 0.01;
  }

  public Clorus replicate() {
    double newEnergy = this.energy * 0.5;
    this.energy = newEnergy;
    return new Clorus(newEnergy);
  }

  public Action chooseAction(Map<Direction, Occupant> neighbors) {
    Deque<Direction> emptyNeighbors = new ArrayDeque<>();
    Deque<Direction> plipNeighbors = new ArrayDeque<>();

    for (Map.Entry<Direction, Occupant> entry : neighbors.entrySet()) {
      String name = entry.getValue().name();
      if (name.equals("empty")) {
        emptyNeighbors.add(entry.getKey());
      } else if (name.equals("plip")) {
        plipNeighbors.add(entry.getKey());
      }
    }

    // Rule 1
    if (emptyNeighbors.size() == 0) return new Action(Action.ActionType.STAY);

    // Rule 2
    if (plipNeighbors.size() != 0)
      return new Action(Action.ActionType.ATTACK, HugLifeUtils.randomEntry(plipNeighbors));

    // Rule 3
    if (this.energy >= 1.0)
      return new Action(Action.ActionType.REPLICATE, HugLifeUtils.randomEntry(emptyNeighbors));

    // Rule 4
    return new Action(Action.ActionType.MOVE, HugLifeUtils.randomEntry(emptyNeighbors));
  }
}

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
 * An implementation of a motile pacifist photosynthesizer.
 *
 * @author Josh Hug
 */
public class Plip extends Creature {

  /** red color. */
  private final int r = 99;
  /** green color. */
  // private final int g = 63;
  /** blue color. */
  private final int b = 76;

  private final double MIN_ENERGY = 0.0;
  private final double MAX_ENERGY = 2.0;

  /** creates plip with energy equal to E. */
  public Plip(double e) {
    super("plip");
    energy = e;
  }

  /** creates a plip with energy equal to 1. */
  public Plip() {
    this(1);
  }

  /**
   * Should return a color with red = 99, blue = 76, and green that varies linearly based on the
   * energy of the Plip. If the plip has zero energy, it should have a green value of 63. If it has
   * max energy, it should have a green value of 255. The green value should vary with energy
   * linearly in between these two extremes. It's not absolutely vital that you get this exactly
   * correct.
   */
  public Color color() {
    return color(r, Math.min((int) (96 * energy + 63), 255), b);
  }

  /** Do nothing with C, Plips are pacifists. */
  public void attack(Creature c) {
    // do nothing.
  }

  /**
   * Plips should lose 0.15 units of energy when moving. If you want to to avoid the magic number
   * warning, you'll need to make a private static final variable. This is not required for this
   * lab.
   */
  public void move() {
    this.energy = Math.max(this.energy - 0.15, MIN_ENERGY);
  }

  /** Plips gain 0.2 energy when staying due to photosynthesis. */
  public void stay() {
    this.energy = Math.min(this.energy + 0.2, MAX_ENERGY);
  }

  /**
   * Plips and their offspring each get 50% of the energy, with none lost to the process. Now that's
   * efficiency! Returns a baby Plip.
   */
  public Plip replicate() {
    double newEnergy = this.energy * 0.5;
    this.energy = newEnergy;
    return new Plip(newEnergy);
  }

  /**
   * Plips take exactly the following actions based on NEIGHBORS:
   *
   * <ol>
   *   <li>1. If no empty adjacent spaces, STAY.
   *   <li>2. Otherwise, if energy >= 1, REPLICATE towards an empty direction chosen at random.
   *   <li>3. Otherwise, if any Cloruses, MOVE with 50% probability, towards an empty direction
   *       chosen at random.
   *   <li>4. Otherwise, if nothing else, STAY
   * </ol>
   *
   * <p>Returns an object of type Action. See Action.java for the scoop on how Actions work. See
   * SampleCreature.chooseAction() for an example to follow.
   */
  public Action chooseAction(Map<Direction, Occupant> neighbors) {
    Deque<Direction> emptyNeighbors = new ArrayDeque<>();
    boolean anyClorus = false;

    for (Map.Entry<Direction, Occupant> entry : neighbors.entrySet()) {
      String name = entry.getValue().name();
      if (name.equals("empty")) {
        emptyNeighbors.add(entry.getKey());
      } else if (name.equals("clorus")) {
        anyClorus = true;
      }
    }

    // Rule 1
    if (emptyNeighbors.size() == 0) return new Action(Action.ActionType.STAY);

    // Rule 2
    if (this.energy >= 1.0)
      return new Action(Action.ActionType.REPLICATE, HugLifeUtils.randomEntry(emptyNeighbors));

    // Rule 3
    if (anyClorus && HugLifeUtils.randomInt(1) == 0)
      return new Action(Action.ActionType.MOVE, HugLifeUtils.randomEntry(emptyNeighbors));

    // Rule 4
    return new Action(Action.ActionType.STAY);
  }
}

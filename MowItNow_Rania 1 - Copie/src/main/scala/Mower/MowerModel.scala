package Mower

/**
 * Direction constants.
 * N for North.
 * E for East.
 * W for West.
 * S for South.
 */
object Direction extends Enumeration {
  val N, E, W, S = Value
}

/**
 * Command constants.
 * D to turn 90 deg to right.
 * G to turn 90 deg to left.
 * A to move forward one step.
 */
object Command extends Enumeration {
  val D, G, A = Value
}

//--------------------------------------

/**
 * Coordinate data model.
 * @param x X coordinate
 * @param y Y coordinate
 */
case class Coordonnees (x: Int, y: Int)

//--------------------------------------

/**
 * Data model for Lawn.
 * @param upRight Top right corner Coordinates of pelous
 */
case class Lawn (upRight: Coordonnees)

//---------------------------------------
/**
 * Data model for Mower.
 * @param coordonnees Current coordinates
 * @param direction Current direction
 */
case class Mower (coordonnees: Coordonnees, direction: Direction.Value) {
  def print = this.coordonnees.x + " " + this.coordonnees.y + " " + this.direction.toString
}
//----------------------------------------

package Mower

class MowerPositionManagement {
  /**
   * Calculate the new position of Mower in the lawn and returns a new Mower object with updated position.
   * @param inputMower
   * @param lawn
   * @param Command
   * @return Mower: updated Mower position and direction
   */
  def moveMower(inputMower: Mower, lawn: Lawn, command: Command.Value): Mower = {
    command match {
      case Command.G => inputMower.copy(direction = leftRight(inputMower.direction)._1)
      case Command.D => inputMower.copy(direction = leftRight(inputMower.direction)._2)
      case Command.A => {
        val newCoordinates = calculateCordinates(inputMower.coordonnees, inputMower.direction)
        //Check if new coordinates are in the lawn otherwise conserving the original position
        if((newCoordinates.x >= 0)
          && (newCoordinates.y >= 0)
          && (newCoordinates.x <= lawn.upRight.x)
          && (newCoordinates.y <= lawn.upRight.y))
            inputMower.copy(coordonnees = newCoordinates)
        else
          inputMower
      }
      case _ => inputMower
    }
  }

  // Helper function that returns the new directions obtained by rotating the mower 90 deg left and right from current direction
  private def leftRight(currentDirection: Direction.Value): (Direction.Value, Direction.Value) = currentDirection match {
    case Direction.N => (Direction.W, Direction.E)
    case Direction.E => (Direction.N, Direction.S)
    case Direction.S => (Direction.E, Direction.W)
    case Direction.W => (Direction.S, Direction.N)
    case _ => (currentDirection, currentDirection)
  }

  // Helper function that calculates the new coordinates using the current coordiante and direction of mower

  private def calculateCordinates(currentCoordinates: Coordonnees, currentDirection: Direction.Value): Coordonnees = currentDirection match {
    case Direction.N => currentCoordinates.copy(y = currentCoordinates.y + 1)
    case Direction.E => currentCoordinates.copy(x = currentCoordinates.x + 1)
    case Direction.S => currentCoordinates.copy(y = currentCoordinates.y - 1)
    case Direction.W => currentCoordinates.copy(x = currentCoordinates.x - 1)
    case _ => currentCoordinates
  }

}

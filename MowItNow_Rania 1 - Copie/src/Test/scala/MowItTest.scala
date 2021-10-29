
import Mower._
import org.junit.Test
import org.junit.Assert.assertEquals
import service.TondeuseService

class MowItTest {
  /**
   * Running the following test case:
   * 5 5
   * 1 2 N
   * GAGAGAGAA
   * 3 3 E
   * AADAADADDA
   */
  @Test def testCase() = {
    val tondesueService = new TondeuseService
    val pelouse = Pelouse(Coordonnees(5, 5))

    val tendeuse1 = Tondeuse(Coordonnees(1, 2), Direction.N)
    val commands1: Seq[Commande.Value] = Seq(Commande.G, Commande.A, Commande.G, Commande.A, Commande.G, Commande.A, Commande.G, Commande.A, Commande.A)
    val movedTondeuse1 = commands1.foldLeft(tendeuse1)((accumulator, command)  => tondesueService.moveTondeuse(accumulator, pelouse, command))
    assertEquals(1, movedTondeuse1.coordonnees.x)
    assertEquals(3, movedTondeuse1.coordonnees.y)
    assertEquals(Direction.N, movedTondeuse1.direction)

    val tendeuse2 = Tondeuse(Coordonnees(3, 3), Direction.E)
    val commands2: Seq[Commande.Value] = Seq(Commande.A, Commande.A, Commande.D, Commande.A, Commande.A, Commande.D, Commande.A, Commande.D, Commande.D, Commande.A)
    val movedTondeuse2 = commands2.foldLeft(tendeuse2)((accumulator, command)  => tondesueService.moveTondeuse(accumulator, pelouse, command))
    assertEquals(5, movedTondeuse2.coordonnees.x)
    assertEquals(1, movedTondeuse2.coordonnees.y)
    assertEquals(Direction.E, movedTondeuse2.direction)
  }
}



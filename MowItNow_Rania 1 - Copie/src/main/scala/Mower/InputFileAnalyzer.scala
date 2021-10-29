package Mower

import java.io.File
import scala.io.Source

class InputFileAnalyzer {

  /**
   * Analyse the input file to extract the first line and define it as an object Lawn
   * @param file
   * @return Lawn or None
   */
  def getTheLawn(file:File): Option[Lawn] = {
    val input = Source.fromFile(file).getLines().toList
    if(!input.isEmpty) {
      val LawnArray = input(0).split(" ")
      if(LawnArray.length != 2) None
      else Some(Lawn(Coordonnees(LawnArray(0).toInt, LawnArray(1).toInt)))
    } else None
  }

  /**
   * Analyse the input file to extract all the odd lines and convert them into Mower objects.
   * @param file
   * @return Mower
   */

  def getMowers(file: File): List[Mower] = {
    val input = Source.fromFile(file).getLines().toList
    if(!input.isEmpty && input.length > 1) {
      val mowerLines = input.zipWithIndex.filter{
        case (item, index) => index != 0 && index % 2 != 0
      }.map(_._1)
      mowerLines.filter(_.split(" ").length == 3).map(line => {
        val mowerParams = line.split(" ")
        Mower(Coordonnees(mowerParams(0).toInt, mowerParams(1).toInt), Direction.withName(mowerParams(2)))
      })
    } else List.empty[Mower]
  }

  /**
   * Analyse the input file to extract all the even lines and convert them into List of commands.
   * @param file
   * @return List of commands
   */
  def getCommands(file: File): List[List[Command.Value]] = {
    val input = Source.fromFile(file).getLines().toList
    if(!input.isEmpty && input.length > 2) {
      val commandLines = input.zipWithIndex.filter{
        case (item, index) => index != 0 && index % 2 == 0
      }.map(_._1)
      commandLines.map(line => line.toList.map(cmd => Command.withName(cmd.toString)))
    } else List.empty[List[Command.Value]]
  }
}

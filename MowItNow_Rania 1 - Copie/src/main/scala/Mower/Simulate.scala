package Mower

import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter

// METTRE LES VARIABLEs à l'interieur du try et mettre le tout au debut
object Simulate extends App {
  //Allow user to choose a file from your directory
  var chooser=new JFileChooser()
  chooser.setCurrentDirectory(new java.io.File("."))
  chooser.setDialogTitle("Please choose an input file")
  // Allow user to choose only ".txt" files
  val filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text")
  chooser.setFileFilter(filter)
  chooser.setAcceptAllFileFilterUsed(false)

  try{
    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
      val inputFile=chooser.getSelectedFile
      //if(inputFile !=null)
      try{
        val fileAnalyse = new InputFileAnalyzer()
        val ourLawn = fileAnalyse.getTheLawn(inputFile)
        ourLawn match{
          case Some(lawn)=>
            val mowers=fileAnalyse.getMowers(inputFile)
            val commands=fileAnalyse.getCommands(inputFile)
            mowers.zipWithIndex.foreach{
              case (mower,index) if commands.length >= index +1 =>  //When there is a list of command for the mower
                val mowerService = new MowerPositionManagement()
                val moveMower = commands(index).foldLeft(mower)((accumulator, command) => mowerService.moveMower(accumulator, lawn, command))
                println(s"Tondeuse ${index+1} \n ${moveMower.coordonnees.x} ${moveMower.coordonnees.y} ${moveMower.direction} ")

            }
        }
      }
    }
    else {
      println("No Selection ")
    }
  }
  catch{
    case e:NumberFormatException =>println("Please enter a valid input format")
    case _ => println("Please enter a valid file")
  }
}

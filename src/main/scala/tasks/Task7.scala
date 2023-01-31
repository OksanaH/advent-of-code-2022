package tasks

import scala.io.Source
import scala.util.matching.Regex

object Task7 extends App{

  val fileStream = getClass.getResourceAsStream("/resources/Day7_input.txt")
  var inputLines = Source.fromInputStream(fileStream).getLines.toList
  var sizes: scala.collection.mutable.Map[String, Int] =  scala.collection.mutable.Map("/home"-> 0)
  main()

  def main(): Unit ={
    processLog()

    println(sizes.filter(_._2<100000).map(_._2).sum)
  }
  def findFoldersOfSize(size: Long): Long = {
    sizes.filter(_._2<100000).map(_._2).sum
  }
  def processLog(): Unit ={
    var currentPath = ""
    inputLines foreach(line =>{
      line match {
        case line if line.contains("ls") => None
        case line if line.contains("cd ..") => {
            currentPath = currentPath.substring(0, currentPath.lastIndexOf("/"))
            println(s" going back into $currentPath, line = $line")
        }
        case line if line.contains("$ cd /") => currentPath = "/home"
        case line if line.contains("$ cd") && (!line.contains("/") && !line.contains("..")) => {
          println(s" current directory to cd into: $currentPath, line = $line")
          //if (currentPath == "")
           // currentPath = "/home"
          currentPath = currentPath.concat("/").concat(line.split(" ").last)
          sizes(currentPath)= 0
        }

        case line if line.exists(_.isDigit) => {
          val size = line.split(" ").toList.head
          var temp = currentPath
          while (temp != "") {
            sizes(temp) += size.toInt
            temp = temp.substring(0, temp.lastIndexOf("/"))
          }
        }
        case _ => None
      }
    })

    println(sizes)
  }
}
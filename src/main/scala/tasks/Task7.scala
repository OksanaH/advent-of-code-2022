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
  def processLog(): Unit ={
    var currentPath = ""
    inputLines foreach(line =>{
      line match {
        case line if line.contains("$ ls") || line.startsWith("dir ")=> None
        case line if line.contains("$ cd ..") => {
          if (currentPath!= "/home") {
            currentPath = currentPath.substring(0, currentPath.lastIndexOf("/"))
          }
        }
        case line if line.contains("$ cd /")  => currentPath = "/home"
        case line if line.contains("$ cd") && (!line.contains("/") && !line.contains("..")) => {
          val dir = line.split(" ").last
          currentPath = s"$currentPath/$dir"

          println(s"Went into dir $dir, current dir is $currentPath")
          sizes.put(currentPath, 0)
          println(sizes)
        }

        case line if line.exists(_.isDigit) => {
          val size = line.split(" ").toList.head
          var temp = currentPath
          while (temp != "") {
            println(s"Updating folder $temp; current directory is $currentPath")
            sizes(temp) += size.toInt
            temp = temp.substring(0, temp.lastIndexOf("/"))
          }
        }
        case _ => {
          println(s"no match $line")
        }
      }
    })
  }
}
package tasks

import scala.io.Source

object Task7 extends App{

  val fileStream = getClass.getResourceAsStream("/resources/Day7_input.txt")
  var inputLines = Source.fromInputStream(fileStream).getLines.toList
  var sizes: scala.collection.mutable.Map[String, Int] =  scala.collection.mutable.Map("/home"-> 0)

  main()

  def main(): Unit ={
    processLog()
    println(sizes.filter(_._2<100000).map(_._2).sum)

    //Part 2
    val spaceNeeded:Int = 30000000-(70000000-sizes("/home"))
    val folderToDelete = sizes.filter(s=>spaceNeeded<=s._2).map(_._2).min
    println(folderToDelete)
  }
  def processLog(): Unit ={
    var currentPath = "/home"
    inputLines.drop(1) foreach(line =>{
      line match {
        case line if line.contains("$ cd") =>
          line match {
            case line if line.contains("..") =>
              if (currentPath != "/home")
                 currentPath = currentPath.substring(0, currentPath.lastIndexOf("/"))

              if (currentPath=="/") currentPath = "/home"
            case _ =>
              val dir = line.split(" ").last
              currentPath = s"$currentPath/$dir"
              sizes.put(currentPath, 0)
          }
        case line if line.contains("$ ls") || line.startsWith("dir ") => None

        case line if line.exists(_.isDigit) =>
          val size = line.split(" ").toList.head
          var temp = currentPath
          while (temp != "") {
            sizes(temp) += size.toInt
            temp = temp.substring(0, temp.lastIndexOf("/"))
          }
        case _ => None
      }
    })
  }
}
package tasks

import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer
import scala.io.Source
import scala.util.matching.Regex

object Task7 extends App{

  val fileStream = getClass.getResourceAsStream("/resources/Day7_input.txt")
  var inputLines = Source.fromInputStream(fileStream).getLines.toList.zipWithIndex
  var fileSystem = ArrayBuffer[ElfDeviceFileSystemFile]()

  main()

  def main(): Unit ={
    processLog(inputLines.headOption.get, "/")
    println(findFoldersOfSize(1000000))
  }

  def findFoldersOfSize(size: Long): Long = {
    var total:Long= 0
    var allPaths = fileSystem.map(file=>{
      file.path.split("/").filterNot(_.isEmpty).toList
    }).flatten.distinct
    println(allPaths.toString)
    allPaths.foreach(p=>{
      val folderSize = calculateFolderSize(p)
      if (folderSize<=size)
        total += folderSize
    })
    total
  }

  @tailrec
  def processLog(line: (String,Int), curDir:String): Unit ={
   println(s"Got $line")
    var inDirectory = curDir
    line match {
      case (l, _) if l.contains("cd /") => None
      case (l, _) if l.contains("cd") && (!l.contains("/") && !l.contains("..")) => {
        inDirectory = curDir match {
          case "/"=>curDir.concat(l.replace("$ cd", "").trim)
          case _ => curDir.concat(l.replace("$ cd ", "/").trim)
        }
      }
      case (l, _) if l.contains("cd ..") => {
        inDirectory = goUpAFolder(inDirectory) // inDirectory.split("/").reverse.take(1).mkString
        println(s" new inDir = $inDirectory")
      }
      case (l, _) if (new Regex("\\d{1,}\\s[a-zA-z.]{1,}").findAllIn(l)).length == 1 => { //line._1.forall(Character.isDigit) && !line._1.contains("dir") => {
        val size =  (new Regex("\\d{1,}") findFirstIn  l).map(_.toLong).get
        val name = l.replace(size.toString(), "").trim
        println(ElfDeviceFileSystemFile(name, curDir ,size).toString)
        fileSystem += ElfDeviceFileSystemFile(name, curDir ,size)
      }
      case (l, _) if l.contains ("ls") => None
      case _ => None
    }
    if (line._2 + 1 < inputLines.length) {
      processLog(inputLines(line._2 + 1), inDirectory)
    }
  }

  def goUpAFolder(path:String): String ={
    path.count(_ == '/') match {
      case 1 => return "/"
      case 0 => throw new Exception("Wrong")
      case _ => path.substring(0, path.lastIndexOf("/"))
    }
  }

  def calculateFolderSize(folderName: String): Long = {
      fileSystem.filter(_.path.contains(s"/$folderName")).map(f => f.size).sum
  }

  case class ElfDeviceFileSystemFile(var name: String, var path: String, var size: Long)
}
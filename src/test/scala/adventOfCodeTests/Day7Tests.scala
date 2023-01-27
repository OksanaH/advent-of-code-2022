package adventOfCodeTests

import org.scalatest.funsuite.AnyFunSuite
import tasks.Task7
import tasks.Task7.ElfDeviceFileSystemFile

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

class Day7Tests extends AnyFunSuite {

  val fileSystem = ArrayBuffer(
    ElfDeviceFileSystemFile("a", "/home/data", 100),
    ElfDeviceFileSystemFile("b", "/home/data", 200),
    ElfDeviceFileSystemFile("c", "/var/opt", 300),
    ElfDeviceFileSystemFile("d", "/var/opt/cache", 400),
    ElfDeviceFileSystemFile("e", "/var/opt/cache/log", 500),
    ElfDeviceFileSystemFile("f", "/var/opt/cache/log/20202", 600),
    ElfDeviceFileSystemFile("g", "/var/opt/cache/log/20203", 600),
  )
  val dirSizes = Seq(("/home", 300), ("/home/data", 300), ("/var", 2400), ("/var/opt/cache", 2100), ("/var/opt/cache/log", 1700))

  dirSizes.foreach { dir =>
    test(s"Verify folder size calculated correctly for ${dir._1}") {
      assert(Task7.calculateFolderSize(dir._1) == dir._2)
    }
  }

  test("Verify you can go up a folder correctly") {
    val data = Seq(("/a/b/c/d", "/a/b/c"), ("/a/b/c", "/a/b"), ("/a/b", "/a"), ("/a", "/"))
    data.foreach { d =>
      assert(Task7.goUpAFolder(d._1) === d._2)
    }
  }

  test("Verify log parsed correctly") {
    val fileStream = getClass.getResourceAsStream("/Day7_testData.txt")
    var inputLines = Source.fromInputStream(fileStream).getLines.toList.zipWithIndex
    Task7.inputLines = inputLines
    Task7.fileSystem = ArrayBuffer[ElfDeviceFileSystemFile]()

    val expected = ArrayBuffer(
      ElfDeviceFileSystemFile("b.txt", "/", 14848514),
      ElfDeviceFileSystemFile("c.dat", "/", 8504156),
      ElfDeviceFileSystemFile("f", "/a", 29116),
      ElfDeviceFileSystemFile("g", "/a", 2557),
      ElfDeviceFileSystemFile("h.lst", "/a", 62596),
      ElfDeviceFileSystemFile("i", "/a/e", 584),
      ElfDeviceFileSystemFile("d.log", "/d", 8033020),
      ElfDeviceFileSystemFile("j", "/d", 4060174),
      ElfDeviceFileSystemFile("d.ext", "/d", 5626152),
      ElfDeviceFileSystemFile("k", "/d", 7214296)
    )
    Task7.processLog(inputLines.headOption.get, "/")

    println(Task7.fileSystem)

    Task7.fileSystem foreach {file =>{
        assert(expected.contains(file))
      }
  }}

  test("Verify folders with size max 100000 are identified correctly") {
    val fileStream = getClass.getResourceAsStream("/Day7_testData.txt")
    var inputLines = Source.fromInputStream(fileStream).getLines.toList.zipWithIndex
    Task7.inputLines = inputLines
    Task7.fileSystem = ArrayBuffer[ElfDeviceFileSystemFile]()

    val files = ArrayBuffer(
      ElfDeviceFileSystemFile("b.txt", "/", 14848514),
      ElfDeviceFileSystemFile("c.dat", "/", 8504156),
      ElfDeviceFileSystemFile("f", "/a", 29116),
      ElfDeviceFileSystemFile("g", "/a", 2557),
      ElfDeviceFileSystemFile("h.lst", "/a", 62596),
      ElfDeviceFileSystemFile("i", "/a/e", 584),
      ElfDeviceFileSystemFile("d.log", "/d", 8033020),
      ElfDeviceFileSystemFile("j", "/d", 4060174),
      ElfDeviceFileSystemFile("d.ext", "/d", 5626152),
      ElfDeviceFileSystemFile("k", "/d", 7214296)
    )
    Task7.fileSystem = files
    assert (Task7.findFoldersOfSize(100000) == 95437)
  }
}

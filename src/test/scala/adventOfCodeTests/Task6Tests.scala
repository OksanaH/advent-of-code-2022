package adventOfCodeTests

import org.scalatest.funsuite.AnyFunSuite
import tasks.Task6

class Task6Tests extends AnyFunSuite {

  val input = List(
    ("bvwbjplbgvbhsrlpgdmjqwftvncz", 5),
    ("nppdvjthqldpwncqszvftbrmjlhg", 6),
    ("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 11)
  )

  input.foreach{i=>
    test(s"Verify marker identified correctly for ${i._1}") {
      Task6.inputLines = i._1.toSeq
     assert(Task6.getStartOfPacket(0) === i._2)
    }
  }

}

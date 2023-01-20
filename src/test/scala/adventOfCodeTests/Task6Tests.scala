package adventOfCodeTests

import org.scalatest.funsuite.AnyFunSuite
import tasks.Task6

import scala.collection.mutable
import scala.collection.mutable.Stack

class Task6Tests extends AnyFunSuite {

  val input = List(
    ("bvwbjplbgvbhsrlpgdmjqwftvncz", 5),
    ("nppdvjthqldpwncqszvftbrmjlhg", 6),
    ("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 11)
  )

  input.foreach{i=>
    test(s"Verify marker identified correctly for ${i._1}") {
     assert(Task6.getStartOfPacket(i._1) === i._2)
    }
  }

}

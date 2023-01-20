package adventOfCodeTests

import org.scalatest.funsuite.AnyFunSuite
import tasks.Task5
import scala.collection.mutable
import scala.io.Source
import scala.collection.mutable.Stack

class Task5Tests extends AnyFunSuite {

  test("Verify element moved correctly") {
    Task5.stacks  =  Stack(mutable.Stack("A","B", "C", "D", "E"), Stack( "G", "H", "I") , Stack("W", "X", "Y", "Z"))
    Task5.moveElement("move 1 from 3 to 1")
    assert(Task5.stacks (0)== Stack( "W", "A","B", "C", "D", "E"))
    assert(Task5.stacks (2)== Stack(  "X", "Y", "Z"))

    Task5.moveElement("move 3 from 1 to 2")
    assert(Task5.stacks(0) == Stack("C", "D", "E"))
    assert(Task5.stacks(1) == Stack("B", "A","W", "G", "H", "I"))

  }

  test("Verify elements moved correctly by crane") {
    Task5.stacks = Stack(mutable.Stack("A", "B", "C", "D", "E"), Stack("G", "H", "I"), Stack("W", "X", "Y", "Z"))
    Task5.moveByCrane("move 3 from 3 to 1")
    assert(Task5.stacks(0) == Stack("W", "X", "Y", "A", "B", "C", "D", "E"))
    assert(Task5.stacks(2) == Stack("Z"))

    Task5.moveByCrane("move 2 from 2 to 3")
    assert(Task5.stacks(1) == Stack("I"))
    assert(Task5.stacks(2) == Stack("G", "H", "Z"))

  }
}

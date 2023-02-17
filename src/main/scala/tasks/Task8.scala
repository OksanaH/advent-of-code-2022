package tasks

import scala.io.Source

object Task8 extends App{

  val fileStream = getClass.getResourceAsStream("/resources/Day8_input.txt")
  var inputLines = Source.fromInputStream(fileStream).getLines.toList
  var sizes = Array[Array[Int]]()
  var treesVisible = 0

  main()

  def main(): Unit ={

   sizes = inputLines.map(_.split("").map(_.toInt)).toArray
    val sizesIndexed = sizes.zipWithIndex
    sizesIndexed.foreach{
      case (thisRow,thisRowIdx)=>
        val rowIndexed = thisRow.zipWithIndex
        rowIndexed foreach{
          case (treeHeight, treeIdx) =>
            if ((treeIdx == 0 || treeIdx == rowIndexed.last._2 ||  thisRowIdx == sizesIndexed.last._2 || thisRowIdx == 0) ||
              (treeVisibleInRow(thisRow, treeHeight, treeIdx) || treeVisibleInColumn(thisRowIdx, treeIdx, treeHeight))){
              println(s"adding tree from row ${thisRow.mkString}, index $treeIdx, height $treeHeight")
              treesVisible += 1
            }
        }
    }

    println(s"Trees visible $treesVisible")
  }

  def treeVisibleInRow(thisRow: Array[Int], treeHeight: Int, treeIndex: Int): Boolean = {
    val rowWithIdx=thisRow
    val visibleFromLeft = rowWithIdx.take(treeIndex).forall { t => treeHeight > t }
    val visibleFromRight = rowWithIdx.takeRight(thisRow.length-treeIndex-1).forall{t => treeHeight > t}
    println(s"visible from left = ${visibleFromLeft}, visible from right = ${visibleFromRight}")
    visibleFromLeft || visibleFromRight
  }

  def treeVisibleInColumn(rowIndex:Int, elIndex:Int, treeHeight:Int): Boolean ={
    val sizesIndexed = sizes.zipWithIndex
    val visibleFromTop = sizesIndexed.take(rowIndex).map(s=>s._1(elIndex)).forall{h=>treeHeight > h}
    val visibleFromBottom = sizesIndexed.takeRight(sizesIndexed.length-rowIndex-1).map(s=>s._1(elIndex)).forall{h=>treeHeight > h}
    println(s"visible from top = ${visibleFromTop}, visible from bottom = ${visibleFromBottom}")
    visibleFromTop || visibleFromBottom
  }
}
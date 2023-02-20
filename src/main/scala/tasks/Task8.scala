package tasks

import scala.io.Source

object Task8 extends App{

  val fileStream = getClass.getResourceAsStream("/resources/Day8_input.txt")
  var inputLines = Source.fromInputStream(fileStream).getLines.toList
  var sizes = Array[Array[Int]]()
  var treesVisible = 0

  main()

  def main(): Unit ={
    var highestScenicScore = 0

   sizes = inputLines.map(_.split("").map(_.toInt)).toArray
    val sizesIndexed = sizes.zipWithIndex
    sizesIndexed.foreach{
      case (thisRow,thisRowIdx)=>
        val rowIndexed = thisRow.zipWithIndex
        rowIndexed foreach{
          case (treeHeight, treeIdx) =>
            if ( isEdgeTree(treeIdx,thisRowIdx) ||
              treeVisibleInRow(thisRow, treeHeight, treeIdx) || treeVisibleInColumn(thisRowIdx, treeIdx, treeHeight)){
              //println(s"adding tree from row ${thisRow.mkString}, index $treeIdx, height $treeHeight")
              treesVisible += 1
            }
            calcScenicScore(treeIdx,thisRowIdx) match {
              case score if score > highestScenicScore =>
                highestScenicScore = score
                println(s"new score $score - $treeIdx, $thisRowIdx")
              case _ => None
            }
        }
    }
    println(s"Trees visible $treesVisible")
    println(s"Highest scenic score $highestScenicScore")
  }
  def isEdgeTree(treeIdx: Int,thisRowIdx:Int ): Boolean ={
    treeIdx == 0 || treeIdx == sizes.take(1).length-1 ||  thisRowIdx == sizes.zipWithIndex.last._2 || thisRowIdx == 0
  }

  def calcScenicScore(treeIdx: Int,rowIdx:Int ): Int = {
    var score = 0
    val sizesIndexed = sizes.zipWithIndex
    val treeHeight = sizes(rowIdx)(treeIdx)
    if (!isEdgeTree(treeIdx,rowIdx)){
     var (scoreTop, scoreBottom, scoreLeft, scoreRight) = (0,0,0,0)
      //score top
      sizesIndexed.take(rowIdx).map(s=>s._1(treeIdx)).reverse.zipWithIndex.find(t=>t._1 >= treeHeight) match {
        case Some(value) => scoreTop = value._2 + 1
        case None => scoreTop = sizesIndexed.take(rowIdx).map(s => s._1(treeIdx)).length
      }
      //scoreBottom
     sizesIndexed.takeRight(sizesIndexed.length-rowIdx-1).map(s=>s._1(treeIdx))
       .zipWithIndex.find(t => t._1 >= treeHeight) match {
        case Some(value) => scoreBottom = value._2+1
        case None => scoreBottom = sizesIndexed.takeRight(sizesIndexed.length-rowIdx-1).map(s=>s._1(treeIdx)).length
      }
      //score left
     sizes(rowIdx).take(treeIdx).reverse.zipWithIndex.find(t => t._1 >= treeHeight) match {
        case Some(value) => scoreLeft = value._2
        case None => scoreLeft = treeIdx
      }
      //score right
     sizes(rowIdx).takeRight(sizes(rowIdx).length-treeIdx-1).zipWithIndex.find(t => t._1 >= treeHeight) match {
       case Some(value) => scoreRight = value._2 + 1
       case None => scoreRight = sizes(rowIdx).length - treeIdx - 1
     }
     score = scoreTop * scoreLeft * scoreBottom * scoreRight
   }
    score
  }

  def treeVisibleInRow(thisRow: Array[Int], treeHeight: Int, treeIndex: Int): Boolean = {
    val rowWithIdx=thisRow
    val visibleFromLeft = rowWithIdx.take(treeIndex).forall { t => treeHeight > t }
    val visibleFromRight = rowWithIdx.takeRight(thisRow.length-treeIndex-1).forall{t => treeHeight > t}
    //println(s"visible from left = ${visibleFromLeft}, visible from right = ${visibleFromRight}")
    visibleFromLeft || visibleFromRight
  }

  def treeVisibleInColumn(rowIndex:Int, elIndex:Int, treeHeight:Int): Boolean ={
    val sizesIndexed = sizes.zipWithIndex
    val visibleFromTop = sizesIndexed.take(rowIndex).map(s=>s._1(elIndex)).forall{h=>treeHeight > h}
    val visibleFromBottom = sizesIndexed.takeRight(sizesIndexed.length-rowIndex-1).map(s=>s._1(elIndex)).forall{h=>treeHeight > h}
    //println(s"visible from top = ${visibleFromTop}, visible from bottom = ${visibleFromBottom}")
    visibleFromTop || visibleFromBottom
  }
}
package at.logic.skeptik.util

import at.logic.skeptik.util.math._
import annotation.tailrec

object pretty {
  def prettyTable[A](t: Seq[Seq[A]], sep: String = "   ", header: Boolean = true) = {
    val tTrans = t.transpose
    val widths: Seq[Seq[Int]] = t map {r => r map {e => e.toString.length}}
    val sepWidth = sep.length
    //val totalWidth = ((0:Int) /: widths) {(w:Int, e) => w + e + sepWidth}
    val columnWidths: Seq[Int] = {
      val widthsTrans = widths.transpose
      widthsTrans map {column => max(column, (x:Int) => x)}
    }
    val fixedWidthTableTrans: Seq[Seq[String]] = {
      for (columnWidthPair <- (tTrans zip columnWidths)) yield {
        val (column, width) = columnWidthPair
        column map {e => 
          val s = e.toString
          s + blankString("", width - s.length)
        }
      }
    }
    val fixedWidthTable: Seq[Seq[String]] = fixedWidthTableTrans.transpose
    if (header) (fixedWidthTable.head.mkString("", sep, "\n") /: (fixedWidthTable.tail map { row => row.mkString("", sep, "\n") })) {_ + _}
    else ("" /: (fixedWidthTable map { row => row.mkString("", sep, "\n") })) {_ + _}
  }
  
  @tailrec def blankString(s: String, length: Int): String = {
    if (length <= 0) s
    else blankString(s + " ", length - 1)
  }
                                         
}
    

package day2

import scala.io.Source.fromFile
import scala.language.implicitConversions

object day2 {
  def main(args: Array[String]): Unit ={
    implicit lazy val lines: Seq[String] = fromFile(args(0)).getLines.toSeq
    println(part1)
  }

  def countLetters(str: String): Map[Char, Int] = str.groupBy(c => c).mapValues(_.length)
  implicit def toInt(b: Boolean): Int = if (b) 1 else 0

  def part1(implicit lines: Seq[String]): Int ={
    val (twocount, threecount) = lines.foldLeft((0,0)) { case ((two, three), id) =>
      val idCounts = countLetters(id).values.toSet
      (two + idCounts.contains(2), three + idCounts.contains(3))
    }
    twocount * threecount
  }

  def part2(implicit lines: Seq[String]): String ={
    for {
      Seq(one, two) <- lines.combinations(2)
      common = one.zip(two).filter({ case (c1, c2) => c1 == c2 }).map(_._1)
    }
  }
}

package day1

import scala.io.Source.fromFile
import scala.collection.mutable

object day1 {
  def main(args: Array[String]): Unit ={
    implicit lazy val lines: Traversable[String] = fromFile(args(0)).getLines.toTraversable
    println(part1)
    println(part2)
  }

  def part1(implicit  lines: Traversable[String]): Int= {
   lines.map(f => f.toInt).sum
  }

  def part2(implicit lines: Traversable[String]): Int= {
    def recursive(cumulative: Int = 0, sums: mutable.Set[Int] = mutable.Set[Int]()): Int ={
      var sum = cumulative
      lines.map(f => f.toInt).foreach(f => {
        sum += f
        if (!sums.add(sum))
          return sum
      })
      recursive(sum, sums)
    }
    recursive()
  }
}

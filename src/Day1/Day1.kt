package Day1

import java.io.File
import kotlin.math.abs

private fun main() {
  val fileContent = File("src/Day1/input.txt").readText()
  val lines = fileContent.split("\n")

  val (left, right) =
    lines
      .map {
        val (l, r) = it.split("\\s+".toRegex())
        Pair(l.toInt(), r.toInt())
      }
      .unzip()

  val sortedLeft = left.sorted()
  val sortedRight = right.sorted()

  val part1 = sortedLeft.zip(sortedRight).sumOf { abs(it.first - it.second) }
  println(part1)

  val part2 = left.sumOf { l -> l * right.count { it == l } }
  println(part2)
}

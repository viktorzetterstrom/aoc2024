package Day2

import java.io.File
import kotlin.math.abs

private fun arraySameSign(arr: List<Int>): Boolean {
  return arr.all { it > 0 } || arr.all { it < 0 }
}

private fun calculateDiffs(row: List<Int>): List<Int> {
  return row.windowed(2).map {
    val (a, b) = it
    a - b
  }
}

private fun isSafe(row: List<Int>): Boolean {
  val diffs = calculateDiffs(row)

  // The levels are either all increasing or all decreasing.
  if (!arraySameSign(diffs)) return false

  // Any two adjacent levels differ by at least one and at most three.
  if (diffs.any { abs(it) > 3 }) return false

  return true
}

private fun isSafe2BruteForce(row: List<Int>): Boolean {
  val combinations = row.indices.map { row.filterIndexed { index, _ -> index != it } }
  return combinations.any { isSafe(it) }
}

private fun main() {
  val fileContent = File("src/Day2/input.txt").readText()
  val rows = fileContent.split("\n")

  val parsedRows = rows.map { it.split("\\s+".toRegex()).map { s -> s.toInt() } }

  val part1 = parsedRows.count { isSafe(it) }
  println(part1)

  val part2 = parsedRows.count { isSafe2BruteForce(it) }
  println(part2)
}

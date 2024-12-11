import java.io.File

private fun main() {
    val fileContent = File("src/Day3/input.txt").readText()

    val part1 =
        Regex("mul\\((\\d+),(\\d+)\\)").findAll(fileContent).map { it.groups.drop(1).map { it!!.value.toInt() } }
            .map { it[0] * it[1] }.sum()

    println(part1)

    val instructions = Regex("mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\)").findAll(fileContent)
        .map { it.value }

    var on = true

    val part2 = instructions.fold(0) { acc, s ->
        when (s) {
            "do()" -> {
                on = true
                acc
            }

            "don't()" -> {
                on = false
                acc
            }

            else -> {
                if (on) {
                    val (a, b) = Regex("mul\\((\\d+),(\\d+)\\)").find(s)!!.destructured
                    acc + a.toInt() * b.toInt()
                } else {
                    acc
                }
            }
        }
    }

    println(part2)
}
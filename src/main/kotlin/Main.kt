import kotlin.collections.contains as contains1

//enum class PieceType(val char: Char) { KING('K'), QUEEN('Q'), ROOK('R'), BISHOP('B'), KNIGHT('N'), PAWN('P') }
//class Command(val pieceType: PieceType, val dir: Dir, val steps: Int)
//class Command(val piece: Piece, val cline: Int, val ccol: Int, val tline: Int, tcol: Int)

fun main() {
    var game = Board()
    while (true) {
        game = game.makeMove(readLine()!!)
        game.toString()
    }
}

/*
/**
 * Return command if valid or null
 */
fun readCommand(): String? {
    print("> ")
    val cmd = readLine()
    if (cmd()) return cmd
    return null
}
 */

/*
fun String.buildCommand(): Command {
    val pieceType = when (this[0]) {
        'k' -> PieceType.KING
        'q' -> PieceType.QUEEN
        'r' -> PieceType.ROOK
        'b' -> PieceType.BISHOP
        'n' -> PieceType.KNIGHT
        else -> PieceType.PAWN
    }
    val dx = this[4] - this[2]
    val dy = this[3] - this[1]
    val dir =
        if (dx > 1) {
            if (dy < 1) Dir.FRONT_LEFT
            if (dy == 0) Dir.FRONT
            else Dir.FRONT_RIGHT
        }
        else if (dx == 0) {
            if (dy < 1) Dir.LEFT
            if (dy == 0) null
            else Dir.RIGHT
        }
        else {
            if (dy < 1) Dir.BACK_LEFT
            if (dy == 0) Dir.BACK
            else Dir.BACK_RIGHT
        }
    val steps = kotlin.math.abs(dx-dy)
    return Command(pieceType,dir,steps)
}
 */
import kotlin.collections.contains as contains1

//enum class PieceType(val char: Char) { KING('K'), QUEEN('Q'), ROOK('R'), BISHOP('B'), KNIGHT('N'), PAWN('P') }
//class Command(val pieceType: PieceType, val dir: Dir, val steps: Int)
val PieceTypes = arrayOf('K','Q','R','B','N','P')
class Command(val piece: Piece, val cline: Int, val ccol: Int, val tline: Int, tcol: Int)

fun main() {
    var game = Board()
    while (true) {
        val cmd = readCommand()
        if (cmd != null)
            game = game.makeMove(cmd)!!
        game.toString()
    }
}

/**
 * Return command if valid or null
 */
fun readCommand(): String? {
    print("> ")
    val cmd = readLine()
    if (cmd!!.checkInput()) return cmd
    return null
}


fun String.checkInput(): Boolean {
    val line = this.trim()
    if (!PieceTypes.contains1(line[0])) return false
    val cLine = line[2]
    val cCol = line[1]
    val nLine = line[4]
    val nCol = line[3]
    // checks line
    if (cLine <= '1' || cLine >= '8' || nLine <= '1' || nLine >= '8' ) return false
    // checks col
    if (cCol <= 'a' || cCol >= 'h' || nCol <= 'a' || nCol >= 'h' ) return false
    return true
}

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
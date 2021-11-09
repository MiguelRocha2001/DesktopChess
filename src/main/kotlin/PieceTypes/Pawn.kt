
/**
 * Checks if the given Move tried to move a Pawn in a valid direction.
 */
fun tryToMovePawn(move: Board.Move, board: Array<Array<Board.Piece?>>): Boolean {
    // if the player chooses the same place
    if (move.target.row === move.cur.row && move.target.column === move.cur.column) return false
    if (checksDiagonalForward(move,board)) return true
    return checksForward(move,board)
    return false
}

/**
 * Checks if the given Move tried to move a Pawn in a valid diagonal direction.
 * Returns false if there inst already a piece there
 */
private fun checksDiagonalForward(move: Board.Move, board: Array<Array<Board.Piece?>>): Boolean {
    val rowDif = move.target.row.n - move.cur.row.n
    var colDif = move.target.column.n - move.cur.column.n
    val rowDifAbs = kotlin.math.abs(rowDif)
    var colDifAbs = kotlin.math.abs(colDif)

    // if the player tries to go forward-left
    if (colDifAbs == -1 && rowDifAbs == 1)
    // if there's a Piece on the forward-left square
        if (board[move.cur.row.n + 1][move.cur.column.n - 1] != null) return true
    // if the player tries to go forward-right
    if (colDifAbs == 1 && rowDif == 1)
    // if there's a Piece on the forward-right
        if (board[move.cur.row.n + 1][move.cur.column.n + 1] != null) return true
    return false
}

/**
 * Checks if the given Move tried to move a Pawn in a valid forward direction.
 */
private fun checksForward(move: Board.Move, board: Array<Array<Board.Piece?>>): Boolean {
    var rowDif = move.target.row.n - move.cur.row.n
    if (board[move.cur.row.n][move.cur.column.n]!!.player === Board.Player.BLACK)
        //asserts value
        rowDif = -rowDif

    // if the player tries to go forward
    if (rowDif > 0 && move.target.column === move.cur.column) {
        // if the player tries to go forward 2 steps
        if (rowDif == 2) {
            val pawn = board[move.cur.row.n][move.cur.column.n]!!.type as Pawn
            if (!pawn.hasPlayed)
            // if there isn't already a piece there
                if (board[move.target.row.n][move.target.column.n] == null) return true
        }
        // if the player tries to go forward 1 step
        if (rowDif == 1) {
            // if there isn't already a piece there
            if (board[move.cur.row.n + 1][move.cur.column.n] == null) return true
        }
    }
    return false
}
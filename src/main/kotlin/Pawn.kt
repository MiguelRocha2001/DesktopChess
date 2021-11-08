
/**
 * Checks if the given Move tried to move a Pawn in a valid direction.
 */
fun tryToMovePawn(move: Board.Move, board: Array<Array<Board.Piece?>>): Boolean {
    // if the player chooses the same place
    if (move.target.row === move.cur.row && move.target.column === move.cur.column) return false
    // for the WHITE player
    if (board[move.cur.row.n][move.cur.column.n]!!.player === Board.Player.WHITE) {
        if (!checksDiagonalForward(move,board)) return false
        return checksForward(move,board)
    }
    return false
}

/**
 * Checks if the given Move tried to move a Pawn in a valid diagonal direction.
 */
private fun checksDiagonalForward(move: Board.Move, board: Array<Array<Board.Piece?>>): Boolean {
    // if the player tries to go forward-left
    if (move.target.column < move.cur.column)
    // if there's a Piece on the forward-left
        if (board[move.cur.row.n + 1][move.cur.column.n - 1] != null) return true
    // if the player tries to go forward-right
    if (move.target.column > move.cur.column)
    // if there's a Piece on the forward-right
        if (board[move.cur.row.n + 1][move.cur.column.n + 1] != null) return true
    return false
}

/**
 * Checks if the given Move tried to move a Pawn in a valid forward direction.
 */
private fun checksForward(move: Board.Move, board: Array<Array<Board.Piece?>>): Boolean {
    // if the player tries to go forward
    if (move.target.row > move.cur.row && move.target.column === move.cur.column) {
        // if the player tries to go forward 2 steps
        if (move.target.row.n - move.cur.row.n == 2) {
            val pawn = board[move.cur.row.n][move.cur.column.n] as Pawn
            if (!pawn.hasPlayed)
            // if there isn't already a piece there
                if (board[move.target.row.n][move.target.column.n] == null) return true
        }
        // if the player tries to go forward 1 step
        if (move.target.row.n - move.cur.row.n == 1) {
            // if there isn't already a piece there
            if (board[move.cur.row.n + 1][move.cur.column.n] == null) return true
        }
    }
    return false
}
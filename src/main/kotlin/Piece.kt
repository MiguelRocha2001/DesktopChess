
enum class PieceType { K, Q, B, N, R, P, k, q, b, n, r, p }

abstract class Piece(val line: Int, val col: Int) {
    abstract fun move(board: Array<Array<Piece?>>, l: Int, c: Int): Piece?
    abstract fun valid(board: Array<Array<Piece?>>, prev: Piece): Boolean
}

private class Pawn(line: Int, col: Int): Piece(line, col) {
    override fun move(board: Array<Array<Piece?>>, l: Int, c: Int): Piece? {
        val new = Pawn(l,c)
        return if (new.valid(board,this)) new else null
    }

    override fun valid(board: Array<Array<Piece?>>, prev: Piece): Boolean {
        if (board[line][col] != null) return false
        if (col != prev.col) return false
        if (line - prev.line > 2) return false
        return true
    }
}

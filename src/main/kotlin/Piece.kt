
//enum class PieceType { K, Q, B, N, R, P, k, q, b, n, r, p }

abstract class Piece(val line: Int, val col: Int) {
    fun valid(board: Array<Array<Piece?>>, prev: Piece): Boolean
    fun move(board: Array<Array<Piece?>>, l: Int, c: Int): Piece? {
        val new = Pawn(l,c)
        return if (new.valid(board,this)) new else null
    }
}

class Pawn(line: Int, col: Int): Piece(line, col) {
    override fun valid(board: Array<Array<Piece?>>, prev: Piece): Boolean {
        if (board[line][col] != null) return false
        if (col != prev.col) return false
        if (line - prev.line > 2) return false
        return true
    }
}

class Knight(line: Int, col: Int): Piece(line, col) {

}

class Bishop(line: Int, col: Int): Piece(line, col) {
    override fun valid(board: Array<Array<Piece?>>, prev: Piece): Boolean {
        if (board[line][col] != null) return false
        if (line - prev.line == kotlin.math.abs(col-prev.col)) return true
        return false
    }
}

class Rook(line: Int, col: Int): Piece(line, col) {
    override fun valid(board: Array<Array<Piece?>>, prev: Piece): Boolean {
        if (board[line][col] != null) return false
        if (line == prev.line || col == prev.col) return true
        return false
    }
}

class Queen(line: Int, col: Int): Piece(line, col) {
    override fun move(board: Array<Array<Piece?>>, l: Int, c: Int): Piece? {
        TODO("Not yet implemented")
    }
}

class King(line: Int, col: Int): Piece(line, col) {
    override fun move(board: Array<Array<Piece?>>, l: Int, c: Int): Piece? {
        TODO("Not yet implemented")
    }
}
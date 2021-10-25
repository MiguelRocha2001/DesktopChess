
class Board {
    enum class PieceType { K, Q, B, N, R, P, k, q, b, n, r, p }
    private class Piece(val type: PieceType, val line: Int, val col: Int)

    private val LINES = 8
    private val COLS = 8
    private val board: Array<Array<Piece?>> = Array(LINES) { Array(COLS) { null } }

    constructor() {
        init()
    }

    /*
    private constructor(board: Map<Pair<Int,Int>,Piece?>) {
        this.board = board
    }

     */

    /**
     * Iniciates the board
     */
    private fun init() {
        initPlayer1()
        initPlayer2()
    }

    /**
     * Iniciates the board fo the first player
     */
    private fun initPlayer1() {
        addPiece(PieceType.r, 8, 1)
        addPiece(PieceType.n, 8, 2)
        addPiece(PieceType.b, 8, 3)
        addPiece(PieceType.q, 8, 4)
        addPiece(PieceType.k, 8, 5)
        addPiece(PieceType.b, 8, 6)
        addPiece(PieceType.n, 8, 7)
        addPiece(PieceType.r, 8, 8)
        for (i in 1..8) {
            addPiece(PieceType.p, 7, i)
        }
    }

    /**
     * Iniciates the board fo the second player
     */
    private fun initPlayer2() {
        addPiece(PieceType.R, 1, 1)
        addPiece(PieceType.N, 1, 2)
        addPiece(PieceType.B, 1, 3)
        addPiece(PieceType.Q, 1, 4)
        addPiece(PieceType.K, 1, 5)
        addPiece(PieceType.B, 1, 6)
        addPiece(PieceType.N, 1, 7)
        addPiece(PieceType.R, 1, 8)
        for (i in 1..8) {
            addPiece(PieceType.P, 2, i)
        }
    }

    /**
     * Ads a new piece to the boad
     */
    private fun addPiece(type: PieceType, line: Int, col: Int) {
        board[line-1][col-1] = Piece(type, line, col)
    }

    /**
     * Indicates if the given line is empty
     */
    private fun isLineEmpty(line: Int): Boolean {
        for (piece in board[line])
            if (piece != null) return false
        return true
    }

    /**
     * Converts the current state of the game in a String
     */
    override fun toString(): String {
        var str = ""
        for (line in board.indices) {
            if (isLineEmpty(line)) {
                str = ' ' + str
                continue
            }
            var aux = ""
            for (piece in board[line]) {
                aux += piece?.type?.toString() ?: ' '
            }
            str = aux + str
        }
        return str
    }

    /*
    fun makeMove(move: String) {
        val type = move[0] // type of the piece
        val piece = board.get(Pair(move[2].toInt(),move[1]-'a')
        return Board(board + (Pair(move[2].toInt(),move[1]-'a', Piece(move[2].toInt(),move[1]-'a')))
    }

     */
}

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")

}
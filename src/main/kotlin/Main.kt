enum class PieceType() {
    K, Q, B, N, R, P
}

private class Piece(val type: PieceType, val line: Int, val col: Char)

class Board {
    private val board = HashSet<Piece>()

    constructor() {

    }

    private fun init() {
        initPlayer1()

    }

    private fun initPlayer1() {
        addPiece(PieceType.R, 1, 'a')
        addPiece(PieceType.N, 1, 'b')
        addPiece(PieceType.B, 1, 'c')
        addPiece(PieceType.Q, 1, 'd')
        addPiece(PieceType.K, 1, 'e')
        addPiece(PieceType.B, 1, 'f')
        addPiece(PieceType.N, 1, 'g')
        addPiece(PieceType.R, 1, 'h')
        for (i in 1..8) {
            addPiece(PieceType.P, 1, 'a' + (i - 1))
        }
    }

    private fun initPlayer2() {
        addPiece(PieceType.R, 8, 'a')
        addPiece(PieceType.N, 8, 'b')
        addPiece(PieceType.B, 8, 'c')
        addPiece(PieceType.Q, 8, 'd')
        addPiece(PieceType.K, 8, 'e')
        addPiece(PieceType.B, 8, 'f')
        addPiece(PieceType.N, 8, 'g')
        addPiece(PieceType.R, 8, 'h')
        for (i in 1..8) {
            addPiece(PieceType.P, 7, 'a' + (i - 1))
        }
    }


    private fun addPiece(type: PieceType, line: Int, col: Char) {
        board.add(createPiece(type,line,col))
    }


    private fun createPiece(type: PieceType, line: Int, col: Char) = Piece(type, line, col)


    override fun toString(): String {
        return super.toString()
    }
}

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}
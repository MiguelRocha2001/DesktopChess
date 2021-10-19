enum class PieceType() {
    K, Q, B, N, R, P, k, q, b, n, r, p
}

private class Piece(val type: PieceType, val line: Int, val col: Char)

class Board {
    private val board = HashMap<Pair<Int,Char>,Piece>()

    constructor() {

    }

    private fun init() {
        initPlayer1()
        initPlayer2()
    }

    private fun initPlayer2() {
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

    private fun initPlayer1() {
        addPiece(PieceType.r, 8, 'a')
        addPiece(PieceType.n, 8, 'b')
        addPiece(PieceType.b, 8, 'c')
        addPiece(PieceType.q, 8, 'd')
        addPiece(PieceType.k, 8, 'e')
        addPiece(PieceType.b, 8, 'f')
        addPiece(PieceType.n, 8, 'g')
        addPiece(PieceType.r, 8, 'h')
        for (i in 1..8) {
            addPiece(PieceType.p, 7, 'a' + (i - 1))
        }
    }


    private fun addPiece(type: PieceType, line: Int, col: Char) {
        board.add(Pair(line,col), createPiece(type,line,col))
    }


    private fun createPiece(type: PieceType, line: Int, col: Char) = Piece(type, line, col)


    override fun toString(): String {
        val str = ""
        val iterator = board.iterator()
        var aux
        while (iterator.hasNext()) {
            aux = iterator.next()
            str += iterator.next().type.toString()
        }
    }
}

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}
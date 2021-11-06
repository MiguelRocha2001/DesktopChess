import java.util.*


class Board {

    enum class Player { WHITE, BLACK;
        fun advance() = if (this === WHITE) BLACK else WHITE
    }
    enum class Column(val char: Char) {A('a'), B('b'), C('c'), D('d'), E('e'), F('f'), G('g'), H('h')}
    enum class Row(val line: Int) {ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8)}
    class Square(column: Column, row: Row)
    class Piece(val type: PieceType, val player: Player)
    class Move(val piece: PieceType, val cur: Square, val target: Square)

    private val LINES = 8
    private val COLS = 8
    private val board: Array<Array<Pair<PieceType,Player>?>>


    constructor() {
        currentPlayer = Player.WHITE
        board = Array(LINES) { Array(COLS) { null } }
        init()
    }

    private constructor(board: Array<Array<Pair<PieceType,Player>?>>, currentPlayer: Player) {
        this.currentPlayer = currentPlayer
        this.board = board
    }


    /**
     * Initiates the board
     */
    private fun init() {
        initPlayer(Player.WHITE)
        initPlayer(Player.BLACK)
    }

    private fun initPlayer(player: Player) {
        var firstRow = 0
        var secondRow = firstRow+1
        if (player == Player.BLACK){
            firstRow = LINES-1
            secondRow = firstRow-1
        }

        board[firstRow][0] = Pair(Rook(), player)
        board[firstRow][1] = Pair(Knight(), player)
        board[firstRow][2] = Pair(Bishop(), player)
        board[firstRow][3] = Pair(Queen(), player)
        board[firstRow][4] = Pair(King(), player)
        board[firstRow][5] = Pair(Bishop(), player)
        board[firstRow][6] = Pair(Knight(), player)
        board[firstRow][7] = Pair(Rook(), player)
        for (i in 0..7) {
            board[secondRow][i] = Pair(Pawn(),player)
        }
    }

    /**
     * Converts the current state of the game in a String
     */
    override fun toString(): String {
        var str = ""
        for (line in board.indices.reversed()) {
            for (piece in board[line]) {
                if (piece != null) {
                    str += if (piece.second == Player.BLACK)
                        piece.first.toStr().lowercase(Locale.getDefault())
                    else
                        piece.first.toStr()
                }
                else str += ' '
            }
        }
        return str
    }

    /**
     * Durante o jogo guarda-se os estados do jogo através de uma lista de Move().
     * Na base de dados guarda-se as jogadas ocurridas (sempre válidas) no tipo String
     */

    /**
     * recebe uma string e retorna um tipo MOVE() que tem a jogada com as coordenadas iniciais e finais e a peça
     */
    fun toMoveOrNull(str: String): Move? {
        val row = str.trim()
        val currCol = getColumn(row[1])
        val currRow = getRow(row[2])
        val newCol = getColumn(row[3])
        val newRow = getRow(row[4])
        val pieceType = getPieceType(str[0])
        if (currCol == null || currRow == null || newCol == null || newRow == null || pieceType == null) return null
        return Move(pieceType,Square(currCol,currRow),Square(newCol,newRow))
    }

    /**
     * Recebe um tipo MOVE() sempre valido e aplica a jogada.
     * Utilizado mais para fazer jogadas no jogo corrente
     */
    fun makeMove(move: Move) {
        TODO()
    }

    /**
     * Recebe uma string do servidor com as jogadas sempre validas e aplica as jogadas todas guadradads no servidor ate chegar ao estado corrente
     * Utilizado mais para fazer jogadas para repor o jogo atraves das strings que estao guardadas no servidor
     */
    fun makeMove(move: String): Board {
        val currCol = move[1] - 'a'
        val currLine = move[2] - '1'
        val newCol = move[3] - 'a'
        val newLine = move[4] - '1'

        // Creates a new array board
        val newBoard = board.clone()
        newBoard[currLine][currCol] = null
        newBoard[newLine][newCol] = Pair(type,currentPlayer)
        return Board(newBoard,currentPlayer.advance())
    }

    fun getColumn(column: Char) =
        when(column) {
            'A' -> Column.A
            'B' -> Column.B
            'C' -> Column.C
            'D' -> Column.D
            'E' -> Column.E
            'F' -> Column.F
            'G' -> Column.G
            'H' -> Column.H
            else -> null
        }
    fun getRow(row: Char) =
        when(row) {
            '1' -> Row.ONE
            '2' -> Row.TWO
            '3' -> Row.THREE
            '4' -> Row.FOUR
            '5' -> Row.FIVE
            '6' -> Row.SIX
            '7' -> Row.SEVEN
            '8' -> Row.EIGHT
            else -> null
        }

}


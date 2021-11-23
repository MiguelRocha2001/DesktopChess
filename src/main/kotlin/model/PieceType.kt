import chess.model.*
import model.Board
import model.Move
import model.Player


/**
 * Cada peça vai receber uma tentativa de jogada, ou seja, com as coordenadas da jogada e o tabuleiro com as outras peças e DIZER se essa jogada foi valida
 */
sealed class PieceType
class Pawn: PieceType()
class Knight: PieceType()
class Bishop: PieceType()
class Rook: PieceType()
class Queen: PieceType()
class King: PieceType()

var allAvailablePositions = mutableListOf<Square>()

/**
 * Get all squares possible to move to, from one piece
 */
private fun getAllMoves(curSquare:Square, board: Array<Array<Board.Piece?>>, pieceType: PieceType): List<Square> {
    val player = board[curSquare.row.ordinal][curSquare.column.ordinal]!!.player //Current player
    allAvailablePositions = mutableListOf()
    var newRow = curSquare.row.ordinal //Nova Row do Square
    var newCol = curSquare.column.ordinal //Nova Collum do Square
    if(pieceType !is Bishop) { //Se for Bishop não é possivel mover para cima ou para baixo
        //Move Up
        newRow = curSquare.row.ordinal - 1
        if(newRow in 0..7) { //Verificar se a linha está out Of Bounds
            if (!(player === Player.BLACK && pieceType is Pawn)) { //Verificar se a peça não é preta e não é Pawn
                if (!(board[newRow][newCol] != null && pieceType is Pawn)) { //Verificar se o sítio para onde se vai está vazio
                    //Verificar se é possivel mover a peça para o novo Square, quando não for o while acaba
                    while (newRow >= Row.EIGHT.ordinal && tryToMove(curSquare, board, Square(curSquare.column.ordinal.toColumn(), newRow.toRow()), pieceType)) {
                        //King e Pawn só podem andar uma posição por isso este caso especial
                        if (pieceType is King || (pieceType is Pawn && newRow != Row.THREE.ordinal && board[newRow][newCol] == null)) break
                        newRow--
                    }
                }
            }
        }
        //Move Down
        newRow = curSquare.row.ordinal + 1
        if(newRow in 0..7) { //Verificar se a linha está out Of Bounds
            if (!(player === Player.WHITE && pieceType is Pawn)) { //Verificar se a peça não é branca e não é Pawn
                if (!(board[newRow][newCol] != null && pieceType is Pawn)) { //Verificar se o sítio para onde se vai está vazio
                    //Verificar se é possivel mover a peça para o novo Square, quando não for o while acaba
                    while (newRow <= Row.ONE.ordinal && tryToMove(curSquare, board, Square(curSquare.column.ordinal.toColumn(), newRow.toRow()), pieceType)) {
                        //King e Pawn só podem andar uma posição por isso este caso especial
                        if (pieceType is King || (pieceType is Pawn && newRow != Row.SIX.ordinal && board[newRow][newCol] == null)) break
                        newRow++
                    }
                }
            }
        }
    }
    //Mover para a direita e para a esquerda
    newCol = curSquare.column.ordinal + 1
    if(!(pieceType is Bishop || pieceType is Pawn)) { //O Bishop e o Pawn não se podem mover para os lados
        while(newCol <= Column.H.ordinal && tryToMove(curSquare, board, Square(newCol.toColumn(), curSquare.row.ordinal.toRow()), pieceType)) {
            if(pieceType is King) break //O king só se pode mover uma posição novamente
            newCol++
        }
        newCol = curSquare.column.ordinal - 1
        while(newCol >= Column.A.ordinal && tryToMove(curSquare, board, Square(newCol.toColumn(), curSquare.row.ordinal.toRow()), pieceType)) {
            if(pieceType is King) break //O king só se pode mover uma posição novamente
            newCol--
        }
    }
    //Mover nas diagonais para cima
    if(pieceType !is Rook && !(player===Player.BLACK && pieceType is Pawn)) { //O rook e o Pawn preto não se podem mover nas diagonais para cima
        newRow = curSquare.row.ordinal - 1
        newCol = curSquare.column.ordinal + 1
        if(newCol in 0..7) { //Check if Col is not out of bounds from the board
            //Verificar se para onde o Pawn se está a mover está uma peça inimiga
            if (!(pieceType is Pawn && (board[newRow][newCol] == null || player === board[newRow][newCol]!!.player))) {
                while (newRow >= Row.EIGHT.ordinal && newCol <= Column.H.ordinal && tryToMove(curSquare, board, Square(newCol.toColumn(), newRow.toRow()), pieceType)) {
                    if (pieceType is King) break //O king só se pode mover uma posição novamente
                    newRow--
                    newCol++
                }
            }
        }
        newRow = curSquare.row.ordinal - 1
        newCol = curSquare.column.ordinal - 1
        if(newCol in 0..7) { //Check if Col is not out of bounds from the board
            //Verificar se para onde o Pawn se está a mover está uma peça inimiga
            if (!(pieceType is Pawn && (board[newRow][newCol] == null || player === board[newRow][newCol]!!.player))) {
                while (newRow >= Row.EIGHT.ordinal && newCol >= Column.A.ordinal && tryToMove(curSquare, board, Square(newCol.toColumn(), newRow.toRow()), pieceType)) {
                    if (pieceType is King) break //O king só se pode mover uma posição novamente
                    newRow--
                    newCol--
                }
            }
        }
    }
    //Mover nas diagonais para baixo
    if(pieceType !is Rook && !(player===Player.WHITE && pieceType is Pawn)) { //O rook e o Pawn branco não se podem mover nas diagonais para cima
        newRow = curSquare.row.ordinal + 1
        newCol = curSquare.column.ordinal + 1
        if(newCol in 0..7) { //Check if Col is not out of bounds from the board
            //Verificar se para onde o Pawn se está a mover está uma peça inimiga
            if (!(pieceType is Pawn && (board[newRow][newCol] == null || player === board[newRow][newCol]!!.player))) {
                while (newRow <= Row.ONE.ordinal && newCol <= Column.H.ordinal && tryToMove(curSquare, board, Square(newCol.toColumn(), newRow.toRow()), pieceType)) {
                    if (pieceType is King) break //O king só se pode mover uma posição novamente
                    newRow++
                    newCol++
                }
            }
        }
        newRow = curSquare.row.ordinal + 1
        newCol = curSquare.column.ordinal - 1
        if(newCol in 0..7) { //Check if Col is not out of bounds from the board
            //Verificar se para onde o Pawn se está a mover está uma peça inimiga
            if (!(pieceType is Pawn && (board[newRow][newCol] == null || player === board[newRow][newCol]!!.player))) {
                while (newRow <= Row.ONE.ordinal && newCol >= Column.A.ordinal && tryToMove(curSquare, board, Square(newCol.toColumn(), newRow.toRow()), pieceType)) {
                    if (pieceType is King) break //O king só se pode mover uma posição novamente
                    newRow++
                    newCol--
                }
            }
        }
    }
    return allAvailablePositions //Retornar todas as posições para onde a peça se pode mover
}

/**
 * Ver se a jogada é possível
 */
private fun tryToMove(curSquare:Square, board: Array<Array<Board.Piece?>>, newSquare:Square, piece:PieceType):Boolean {
    val move = Move(piece,curSquare,newSquare) //Criar um novo move
    val player = board[move.curSquare.row.ordinal][move.curSquare.column.ordinal]!!.player //Jogador atual

    //Verificar se o novo Square está vazio
    if(board[move.newSquare.row.ordinal][move.newSquare.column.ordinal] == null) {
        allAvailablePositions.add(newSquare)
        return true
    }
    //Verificar se o Square tem uma peça inimiga
    if(board[move.newSquare.row.ordinal][move.newSquare.column.ordinal]!!.player != player) {
        allAvailablePositions.add(newSquare)
        //Para na peça inimiga
        return false
    }
    return false
}

fun PieceType.getAllMoves(move: Move, board: Array<Array<Board.Piece?>>): List<Square> {
    return when(this) {
        //Knights moves are way too different from the other pieces
        is Knight -> getAllMovesKnight(move, board)
        else -> getAllMoves(move.curSquare, board,move.piece)
    }
}

fun getAllMovesKnight(move: Move, board: Array<Array<Board.Piece?>>): List<Square> {
    val player = board[move.curSquare.row.ordinal][move.curSquare.column.ordinal]!!.player //Jogador atual
    val offsetRow = intArrayOf(2, 1, -1, -2, -2, -1, 1, 2) //Array com os offsets das posições possíveis do cavalo
    val offsetColumn = intArrayOf(1, 2, 2, 1, -1, -2, -2, -1)
    allAvailablePositions = mutableListOf()

    // Se o jogador escolher o mesmo sitio
    if (move.newSquare.row === move.curSquare.row && move.newSquare.column === move.curSquare.column) return allAvailablePositions

    for (i in 0..7) { // Percorrer os arrays de offsets
        //Verificar se o Square está dentro do board
        if(move.curSquare.row.ordinal + offsetRow[i] in 0..7 && move.curSquare.column.ordinal + offsetColumn[i] in 0..7 ) {
            val row = move.curSquare.row.ordinal + offsetRow[i] // Nova linha
            val col = move.curSquare.column.ordinal + offsetColumn[i] //Nova coluna
            val square = Square(col.toColumn(),row.toRow()) //É criado o novo square para onde se vai verificar se a peça pode ir
            //Se esse sitio tiver uma peça é necessário ver a que jogador pertence
            if (board[move.curSquare.row.ordinal + offsetRow[i]][move.curSquare.column.ordinal + offsetColumn[i]] !== null) {
                if (player === Player.WHITE && board[move.curSquare.row.ordinal + offsetRow[i]][move.curSquare.column.ordinal + offsetColumn[i]]!!.player === Player.BLACK)
                    allAvailablePositions.add(square)

                if (player === Player.BLACK && board[move.curSquare.row.ordinal+offsetRow[i]][move.curSquare.column.ordinal+ offsetColumn[i]]!!.player === Player.WHITE)
                    allAvailablePositions.add(square)
            }
            else //Se o sitio estiver a null é possivel mover o cavalo para aí
                allAvailablePositions.add(square)

        }
    }
    return allAvailablePositions
}

fun PieceType.toStr() =
    when(this) {
        is Pawn -> "P"
        is Knight -> "N"
        is Bishop -> "B"
        is Rook -> "R"
        is Queen -> "Q"
        else -> "K"
    }

fun getPieceType(type: Char) =
    when(type) {
        'P' -> Pawn()
        'N' -> Knight()
        'B' -> Bishop()
        'R' -> Rook()
        'Q' -> Queen()
        'K' -> King()
        else -> null
    }
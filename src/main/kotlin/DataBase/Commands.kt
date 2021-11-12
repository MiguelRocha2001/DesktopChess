package DataBase

import Moves
import Game


fun getMoves(chessDb: ChessDb, authorId: String) = getMovesAction(chessDb, authorId)

fun postMoves(billboard: ChessDb, game: Game, moves: List<String>) {
    try {
        // action
        postMessageAction(billboard,game,moves)
        // show
        println("Message \"$moves\" posted by ${game.id}")
    } catch (ex: Exception) {
        // Exceptional situations
        println("Error: ${ex.message}.")
    }
}

//-------------------------------------------------------------------------------------


private fun getMovesAction(chessDb: ChessDb, playerId: String) = chessDb.getMovesByPlayer(Game(playerId))

private fun postMessageAction(billboard: ChessDb, gameId: Game, content: List<String>) {
    var str = ""
    content.forEach{move -> str += "$move " }
    check(billboard.postMoves(Moves(gameId,str))) { "Post failed" }
}

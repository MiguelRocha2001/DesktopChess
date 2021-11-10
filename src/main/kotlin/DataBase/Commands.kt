package DataBase

import Move
import Player

/**
 * Show all messages from billboard posted by the specified author or by all authors.
 * PROBLEM: Cannot be tested.
 * @param billboard to access Billboard operations
 * @param authorId the author of messages or null for all authors
 */
fun getMoves(chessDb: ChessDb, authorId: String) = getMovesAction(chessDb, authorId)


fun postMoves(billboard: ChessDb, player: Player, moves: List<String>) {
    moves.forEach{move -> postMove(billboard,player,move)}
}

//-------------------------------------------------------------------------------------

/**
 * Post a message by one author to the billboard in MongoDb.
 * Show the result of the operation.
 * PROBLEM: Cannot be tested.
 * @param billboard to access Billboard operations
 * @param player the author of message
 * @param move the content of message, cannot be null.
 */
private fun postMove(billboard: ChessDb, player: Player, move: String) {
    try {
        // action
        postMessageAction(billboard,player,move)
        // show
        println("Message \"$move\" posted by ${player.id}")
    } catch (ex: Exception) {
        // Exceptional situations
        println("Error: ${ex.message}.")
    }
}

/**
 * Returns messages from billboard posted by the specified author or by all authors.
 * QUESTION: Can throw exceptions?
 * @param chessDb to access Billboard operations
 * @param playerId the author of messages or null for all authors
 */
private fun getMovesAction(chessDb: ChessDb, playerId: String) = chessDb.getMovesByPlayer(Player(playerId))

/**
 * Post a message by one author to the billboard in MongoDb.
 * @param billboard to access Billboard operations
 * @param author the author of message
 * @param content the content of message, cannot be null.
 * @throws IllegalArgumentException if there is no content.
 * @throws IllegalStateException if post failed
 */
private fun postMessageAction(billboard: ChessDb, author: Player, content: String) {
    check(billboard.postMoves(Move(author,content))) { "Post failed" }
}

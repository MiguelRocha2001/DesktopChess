package DataBase

import Player
import org.apache.logging.log4j.message.Message

/**
 * Show all messages from billboard posted by the specified author or by all authors.
 * PROBLEM: Cannot be tested.
 * @param billboard to access Billboard operations
 * @param authorId the author of messages or null for all authors
 */
fun getMessage(billboard: ChessDb, authorId: String?) {
    // action
    val messages = getMessageAction(billboard, authorId)
    // show
    messages.forEach(::println)
}

/**
 * Post a message by one author to the billboard in MongoDb.
 * Show the result of the operation.
 * PROBLEM: Cannot be tested.
 * @param billboard to access Billboard operations
 * @param player the author of message
 * @param content the content of message, cannot be null.
 */
fun postMessage(billboard: ChessDb, player: Player, content: String?) {
    try {
        // action
        postMessageAction(billboard,player,content)
        // show
        println("Message \"$content\" posted by ${player.id}")
    } catch (ex: Exception) {
        // Exceptional situations
        println("Error: ${ex.message}.")
    }
}

/**
 * Returns messages from billboard posted by the specified author or by all authors.
 * QUESTION: Can throw exceptions?
 * @param billboard to access Billboard operations
 * @param authorId the author of messages or null for all authors
 */
fun getMessageAction(billboard: ChessDb, authorId: String?) =
    if (authorId != null)
        billboard.getMessagesByPlayer(Author(authorId))
    else
        billboard.getAllMessages()

/**
 * Post a message by one author to the billboard in MongoDb.
 * @param billboard to access Billboard operations
 * @param author the author of message
 * @param content the content of message, cannot be null.
 * @throws IllegalArgumentException if there is no content.
 * @throws IllegalStateException if post failed
 */
fun postMessageAction(billboard: ChessDb, author: Player, content: String?) {
    require(content!=null) { "Missing content" }
    check(billboard.postMessage(Message(author,content))) { "Post failed" }
}

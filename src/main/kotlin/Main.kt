fun main() {
    var game = Board()
    game = game.makeMove(TODO())
}

fun readCommand(): Move? {
    print("> ")
    return readLine()!!.parseCommand()
}

fun String.parseCommand(): Move? {
    val line = this.trim()
    val pieceType = line[0]
    val cline = line[2]
    val ccol = line[1]
    val tline = line[4]
    val tcol = line[3]
    if (pieceType < 'A' || (pieceType > 'Z' && pieceType < 'a') || pieceType > 'z') return null
    if (cline < '0' || cline > '9' || tline < '0' || tline > '9' ) return null
    if (ccol < '0' || ccol > '9' || tcol < '0' || tcol > '9' ) return null
    return Move(pieceType,cline-'0',ccol-'a',tline-'0',tcol-'a')
}
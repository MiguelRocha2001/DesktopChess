import junit.framework.Assert.assertEquals
import model.Board
import org.junit.Test

class General {
    @Test
    fun `Initial position Board`() {
        val sut = Board()
        assertEquals(
            "rnbqkbnr"+
                    "pppppppp"+
                    "        ".repeat(4) +
                    "PPPPPPPP"+
                    "RNBQKBNR", sut.toStringTest() )
    }

    @Test
    fun `MakeMove in Board`() {
        val sut = Board().makeMoveWithCorrectString("Pe2e4").makeMoveWithCorrectString("Pe7e5").makeMoveWithCorrectString("Nb1c3")
        assertEquals(
            "rnbqkbnr"+
                    "pppp ppp"+
                    "        "+
                    "    p   "+
                    "    P   "+
                    "  N     "+
                    "PPPP PPP"+
                    "R BQKBNR", sut.toStringTest() )
    }

}
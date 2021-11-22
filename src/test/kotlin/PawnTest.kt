import junit.framework.Assert.assertEquals
import model.Board
import org.junit.Test
import kotlin.test.assertNull

class PawnTest {
    @Test
    fun `Makes one step forward with Pawn in Board`() {
        val sut = Board().makeMove("Pe2e3")!!.first
        assertEquals(
            "rnbqkbnr"+
                    "pppppppp"+
                    "        "+
                    "        "+
                    "        "+
                    "    P   "+
                    "PPPP PPP"+
                    "RNBQKBNR", sut.toString() )
    }

    @Test
    fun `White eats Left`() {
        val sut = Board().makeMove("Pe2e4")!!.first.makeMove("Pd7d5")!!.first.makeMove("Pe4d5")!!.first
        assertEquals(
            "rnbqkbnr"+
                    "ppp pppp"+
                    "        "+
                    "   P    "+
                    "        "+
                    "        "+
                    "PPPP PPP"+
                    "RNBQKBNR", sut.toString() )
    }

    @Test
    fun `White eats Right`() {
        val sut = Board().makeMove("Pc2c4")!!.first.makeMove("Pd7d5")!!.first.makeMove("Pc4d5")!!.first
        assertEquals(
            "rnbqkbnr"+
                    "ppp pppp"+
                    "        "+
                    "   P    "+
                    "        "+
                    "        "+
                    "PP PPPPP"+
                    "RNBQKBNR", sut.toString() )
    }

    @Test
    fun `Black eats Left`() {
        val sut = Board().makeMove("Pe2e4")!!.first.makeMove("Pd7d5")!!.first.makeMove("Pa2a3")!!.first.makeMove("Pd5e4")!!.first
        assertEquals(
            "rnbqkbnr"+
                    "ppp pppp"+
                    "        "+
                    "        "+
                    "    p   "+
                    "P       "+
                    " PPP PPP"+
                    "RNBQKBNR", sut.toString() )
    }

    @Test
    fun `Black eats Right`() {
        val sut = Board().makeMove("Pc2c4")!!.first.makeMove("Pd7d5")!!.first.makeMove("Pa2a3")!!.first.makeMove("Pd5c4")!!.first
        assertEquals(
            "rnbqkbnr"+
                    "ppp pppp"+
                    "        "+
                    "        "+
                    "  p     "+
                    "P       "+
                    " P PPPPP"+
                    "RNBQKBNR", sut.toString() )
    }
}
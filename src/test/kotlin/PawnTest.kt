import junit.framework.Assert.assertEquals
import model.Board
import model.Player
import org.junit.Test
import kotlin.test.assertNull

class PawnTest {
    @Test
    fun `Makes one step forward with Pawn in Board`() {
        val sut = Board().makeMove("Pe2e3",Player.WHITE)!!.first
        assertEquals(
            "rnbqkbnr"+
                    "pppppppp"+
                    "        "+
                    "        "+
                    "        "+
                    "    P   "+
                    "PPPP PPP"+
                    "RNBQKBNR", sut.toStringTest() )
    }

    @Test
    fun `White eats Left`() {
        val sut = Board().makeMove("Pe2e4",Player.WHITE)!!.first.makeMove("Pd7d5",Player.BLACK)!!.first.makeMove("Pe4d5",Player.WHITE)!!.first
        assertEquals(
            "rnbqkbnr"+
                    "ppp pppp"+
                    "        "+
                    "   P    "+
                    "        "+
                    "        "+
                    "PPPP PPP"+
                    "RNBQKBNR", sut.toStringTest() )
    }

    @Test
    fun `White eats Right`() {
        val sut = Board().makeMove("Pc2c4",Player.WHITE)!!.first.makeMove("Pd7d5",Player.BLACK)!!.first.makeMove("Pc4d5",Player.WHITE)!!.first
        assertEquals(
            "rnbqkbnr"+
                    "ppp pppp"+
                    "        "+
                    "   P    "+
                    "        "+
                    "        "+
                    "PP PPPPP"+
                    "RNBQKBNR", sut.toStringTest() )
    }

    @Test
    fun `Black eats Left`() {
        val sut = Board().makeMove("Pe2e4",Player.WHITE)!!.first.makeMove("Pd7d5",Player.BLACK)!!.first.makeMove("Pa2a3",Player.WHITE)!!.first.makeMove("Pd5e4",Player.BLACK)!!.first
        assertEquals(
            "rnbqkbnr"+
                    "ppp pppp"+
                    "        "+
                    "        "+
                    "    p   "+
                    "P       "+
                    " PPP PPP"+
                    "RNBQKBNR", sut.toStringTest() )
    }

    @Test
    fun `Black eats Right`() {
        val sut = Board().makeMove("Pc2c4",Player.WHITE)!!.first.makeMove("Pd7d5",Player.BLACK)!!.first.makeMove("Pa2a3",Player.WHITE)!!.first.makeMove("Pd5c4",Player.BLACK)!!.first
        assertEquals(
            "rnbqkbnr"+
                    "ppp pppp"+
                    "        "+
                    "        "+
                    "  p     "+
                    "P       "+
                    " P PPPPP"+
                    "RNBQKBNR", sut.toStringTest())
    }
}
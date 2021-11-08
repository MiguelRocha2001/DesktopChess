import junit.framework.Assert.assertEquals
import org.junit.Test

class PawnTest {
    @Test
    fun `Makes one forward step with Pawn in Board`() {
        val sut = Board().makeMove("Pe2e3")
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
    fun `Makes two forward steps with Pawn in Board`() {
        val sut = Board().makeMove("Pe2e4")
        assertEquals(
            "rnbqkbnr"+
                    "pppppppp"+
                    "        "+
                    "        "+
                    "    P   "+
                    "        "+
                    "PPPP PPP"+
                    "RNBQKBNR", sut.toString() )
    }

    @Test
    fun `Makes one forward-left step with Pawn in Board`() {
        val sut = Board().makeMove("Pe2d3")
        assertEquals(
            "rnbqkbnr"+
                    "pppppppp"+
                    "        "+
                    "        "+
                    "        "+
                    "   P    "+
                    "PPPP PPP"+
                    "RNBQKBNR", sut.toString() )
    }
}
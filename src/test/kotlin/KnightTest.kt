import org.junit.Test
import kotlin.test.assertEquals

class KnightTest {
    @Test
    fun `Moves some Pieces`() {
        val sut = Board()
            .makeMove("Pb2b3")!!
            .makeMove("Pb7b5")!!
            .makeMove("Bc1b2")!!
            .makeMove("Bc8b7")!!
            .makeMove("Bb2c3")!!
            .makeMove("Nb8a6")!!
            .makeMove("Pb3b4")!!
            .makeMove("Na6b4")!!
        assertEquals(
            "r  qkbnr"+
                    "pbpppppp"+
                    "        "+
                    " p      "+
                    " n      "+
                    "  B     "+
                    "P PPPPPP"+
                    "RN QKBNR", sut.toString() )
    }

    @Test
    fun `Moves Knight`() {
        val sut = Board()
            .makeMove("Nb1c3")!!
        assertEquals(
             "rnbqkbnr"+
                    "pppppppp"+
                    "        "+
                    "        "+
                    "        "+
                    "  N     "+
                    "PPPPPPPP"+
                    "R BQKBNR", sut.toString() )
    }
}
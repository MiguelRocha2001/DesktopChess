import org.junit.Assert
import org.junit.Test
import kotlin.test.assertEquals


class BishopTest {
    @Test
    fun `Moves some Pieces`() {
        val sut = Board().makeMove("Pb2b3")!!
            .makeMove("Pb7b5")!!
            .makeMove("Bc1b2")!!
            .makeMove("Bc8b7")!!
            .makeMove("Bb2c3")
        assertEquals(
            "rn qkbnr"+
                    "pbpppppp"+
                    "        "+
                    " p      "+
                    "        "+
                    " PB     "+
                    "P PPPPPP"+
                    "RN QKBNR", sut.toString() )
    }

    @Test
    fun `Move pieces to eat others`() {
        val sut = Board().makeMove("Pb2b3")!!
            .makeMove("Pb7b5")!!
            .makeMove("Bc1b2")!!
            .makeMove("Bc8b7")!!
            .makeMove("Bb2g7")!!
            .makeMove("Bb7g2")!!
        assertEquals(
            "rn qkbnr"+
                    "p ppppBp"+
                    "        "+
                    " p      "+
                    "        "+
                    " P      "+
                    "P PPPPbP"+
                    "RN QKBNR", sut.toString())
    }
}
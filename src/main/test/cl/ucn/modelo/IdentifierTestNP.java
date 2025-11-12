package cl.ucn.modelo;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

//ENTREGABLE 1.1 - 10 TESTS NO PARAMETRIZADOS
public class IdentifierTestNP
{
    private Identifier id;

    @Before
    public void setUp()
    {
        id = new Identifier();
    }

    @Test
    public void testValidLength1() //string de 1 caracter valido
    {
        assertTrue("El string 'a' deberia ser valido", id.validateIdentifier("a"));
    }

    @Test
    public void testValidLength2() //string de 5 caracteres valido
    {
        assertTrue("El string 'abc12' deberia ser valido", id.validateIdentifier("abc12"));
    }

    @Test
    public void testValidFormat1() //string de solo letras
    {
        assertTrue("El string 'Hola' deberia ser valido", id.validateIdentifier("Hola"));
    }

    @Test
    public void testValidFormat2() //string con letras entre medio
    {
        assertTrue("El string 'M4V3N' deberia ser valido", id.validateIdentifier("M4V3N"));
    }

    @Test
    public void testInvalidFormat1() //string que empieza con numero
    {
        assertFalse("El string '7Up' deberia ser invalido", id.validateIdentifier("7Up"));
    }

    @Test
    public void testInvalidFormat2() //string con caracteres invalidos
    {
        assertFalse("El string '$$$' deberia ser invalido", id.validateIdentifier("$$$"));
    }

    @Test
    public void testInvalidFormat3() //string con caracteres invalidos entre medio
    {
        assertFalse("El string 'mi$h' deberia ser invalido", id.validateIdentifier("mi$h"));
    }

    @Test
    public void testInvalidLength() //string con mas de 5 caracteres
    {
        assertFalse("El string 'patrones' deberia ser invalido", id.validateIdentifier("patrones"));
    }

    @Test
    public void testInvalidSpace1() //string con espacios entre medio
    {
        assertFalse("El string 'a b c' deberia ser invalido", id.validateIdentifier("a b c"));
    }

    @Test
    public void testInvalidSpace2() //string que empieza con un espacio
    {
        assertFalse("El string ' abc' deberia ser invalido", id.validateIdentifier(" abc"));
    }

}


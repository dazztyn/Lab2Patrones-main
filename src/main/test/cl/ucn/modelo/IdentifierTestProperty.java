package cl.ucn.modelo;

import net.jqwik.api.*;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.Chars;
import net.jqwik.api.constraints.StringLength;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

//ENTREGABLE 2 - CASOS DE PROPERTY BASED TESTING
public class IdentifierTestProperty
{
    private Identifier id = new Identifier();

    @Property
    public void validString(
            @ForAll @AlphaChars @StringLength(min = 1, max = 1) String first,
            @ForAll @Chars({'a','b','c','1','2','3','4','5','6','7','8','9','0'})
            @StringLength(min = 0, max = 4) String tail
    )
    {
        String s = first + tail;

        //Esta propiedad indicará que el string resultante SIEMPRE será de largo 5
        assertTrue(id.validateIdentifier(s), "El string válido '" + s + "' fue rechazado.");
    }

    @Property
    public void invalidString(
            @ForAll @AlphaChars @StringLength(min = 1, max = 1) String first,
            @ForAll @Chars({'a','b','c','1','2','3','4','5','6','7','8','9','0'})
            @StringLength(min = 5, max = 20) String tail
    )
    {
        String s = first + tail;

        //Esta propiedad indica que el string resultante SIEMPRE será de largo mayor a 5
        assertFalse(id.validateIdentifier(s), "El string erroneo '" + s + "' fue aceptado");
    }

}
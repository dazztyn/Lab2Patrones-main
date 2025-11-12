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
    //se que está feo, pido perdón profe
    public void validString(
            @ForAll @AlphaChars @StringLength(min = 1, max = 1) String first,
            @ForAll @Chars({'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
                    'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
                    '0','1','2','3','4','5','6','7','8','9'})
            @StringLength(min = 0, max = 4) String tail
    )
    {
        String s = first + tail;

        //Esta propiedad indicará que el string resultante SIEMPRE tendrá un largo de
        //entre 1 a 5 caracteres
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
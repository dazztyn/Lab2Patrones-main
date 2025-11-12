package cl.ucn.modelo;

import net.jqwik.api.*;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.StringLength;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IdentifierTestProperty
{
    private Identifier id = new Identifier();

    @Property
    public void validString(
            @ForAll @AlphaChars @StringLength(min = 1, max = 1) String first,
            @ForAll @AlphaNumericChars @StringLength(min = 0, max = 4) String tail

    )

}
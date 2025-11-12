package cl.ucn.modelo;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

//ENTREGABLE 1
@RunWith(Parameterized.class)
public class IdentifierTest
{
        private String input;
        private boolean expected;
        private Identifier id = new Identifier();

        public IdentifierTest(String input, boolean expected)
        {
            this.input = input;
            this.expected = expected;
        }

        @Parameterized.Parameters()
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{

                    //validos
                    {"a", true},        // 1 letra
                    {"abc", true},      // letras
                    {"A1", true},       // letra + número
                    {"abc12", true},    // 5 chars
                    {"Z", true},        // válido: una letra
                    {"a1234", true},    // justo 5 válidos

                    //no validos
                    {"1abc", false},    // empieza con número
                    {"abcdef", false},  // más de 5
                    {"a b", false},      // espacio entre medio
                    {"a!", false},      // símbolo no permitido
                    {"_a", false},      // guión bajo no permitido
                    {"abc345", false}   // 6 chars, inválido
            });
        }

        @Test
        public void testParameters()
        {
            try
            {
                boolean realResult = id.validateIdentifier(input);
                assertEquals("Fallo detectado.",expected,realResult);
            }
            catch (Exception e)
            {
                assertFalse(expected);
            }
        }

}
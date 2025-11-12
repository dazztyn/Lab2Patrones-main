package cl.ucn.modelo;

public class BetterIdentifier
{
    //ENTREGABLE 4
    public IdentifierErrorEnum validateIdentifierMejorado(String s)
    {
        if(s == null || s.isEmpty())
        {
            return IdentifierErrorEnum.ERROR_VACIO; //return de etiqueta para strings vacios/null
        }

        if(s.length() > 5)
        {
            return IdentifierErrorEnum.ERROR_LONGITUD; //return de etiqueta para strings con largo mayor a 5 caracteres
        }


        //revisamos primero el char inicial debido a que la validacion de digitos pueden ser
        //2 casos distintos, el del inicio y el del resto del string
        char primerChar = s.charAt(0);
        if(!Character.isLetter(primerChar))
        {
            return IdentifierErrorEnum.ERROR_CHAR_INICIAL;
        }

        for(int i = 0; i < s.length(); i++)
        {
            char currentChar = s.charAt(i);
            // Usar Character.isLetterOrDigit()
            if (!Character.isLetterOrDigit(currentChar))
            {
                return IdentifierErrorEnum.ERROR_CHAR;
            }
        }

        //si ha pasado todas las verificaciones, el string es valido
        return IdentifierErrorEnum.VALIDO;

    }

}

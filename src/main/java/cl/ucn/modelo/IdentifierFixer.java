package cl.ucn.modelo;

public class IdentifierFixer
{
    private static int MAX_LENGTH = 5;
    private static char DEFAULT_START_CHAR = 'a';

    public String fix (String s)
    {
        if (s == null || s.isEmpty())
        {
            return String.valueOf(DEFAULT_START_CHAR); // Devuelve "a" por default si el string es null/vacio
        }

        StringBuilder sb = new StringBuilder();
        char firstChar = s.charAt(0);

        if (Character.isLetter(firstChar)) //si el primer caracter es valido se agrega
        {
            sb.append(firstChar);
        }
        else                                //si el caracter es invalido se reemplaza por "a"
        {
            sb.append(DEFAULT_START_CHAR);
        }

        //leemos el resto de caracteres
        for (int i = 1; i < s.length(); i++)
        {
            char currentChar = s.charAt(i);

            if (Character.isLetterOrDigit(currentChar)) // Si el carácter es válido lo agregamos, si no, se omite
            {
                sb.append(currentChar);
            }
        }

        if (sb.length() > MAX_LENGTH) //Si el string supera el largo maximo, se trunca y retorna solo los primeros 5 caracteres
        {
            String substring = "";

            for (int i = 0; i < MAX_LENGTH; i++)
            {
                substring += sb.charAt(i);
            }

            return substring;
        }

        // Si no excede el largo, retorna el string corregido
        return sb.toString();

    }

}

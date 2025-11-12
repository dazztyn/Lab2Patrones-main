package cl.ucn.main;


import cl.ucn.modelo.BetterIdentifier;
import cl.ucn.modelo.IdentifierErrorEnum;

public class Main
{
    public static void main(String[] args)
    {
        if (args.length == 0)
        {
            System.out.println("Uso: IdentifierMain <string>");
        }
        else
        {
            BetterIdentifier id = new BetterIdentifier();
            IdentifierErrorEnum resultado = id.validateIdentifierMejorado(args[0]);

            switch (resultado)
            {
                case VALIDO:
                    System.out.println("String valido");
                    break;

                case ERROR_VACIO:
                    System.out.println("Inválido: El identificador no puede estar vacío.");
                    break;

                case ERROR_LONGITUD:
                    System.out.println("Inválido: El identificador es muy largo (máximo 5 caracteres).");
                    break;

                case ERROR_CHAR_INICIAL:
                    System.out.println("Inválido: El identificador debe comenzar con una letra.");
                    break;

                case ERROR_CHAR:
                    System.out.println("Inválido: El identificador solo puede contener letras y números.");
                    break;

                default:
                    System.out.println("Error de validación desconocido.");
                    break;
            }
        }
    }
}

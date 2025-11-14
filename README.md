# Laboratorio II – Diseño y Evaluación Avanzada de Casos de Prueba

Vicente Ruiz Escobar - Pablo Jorquera Herrera
---

A continuación se detalla la información y reglas para los procesos de Testing y elementos solicitados en el enunciado.

# 1) JUnit Test Suite Completo
### 12 Tests Parametrizados

| Parámetro | Equivalencia | Test | Resultado |
| :-------: | :------: | :-------: | :-------: |
| Frontera | L = 1 | "a" | True |
| Longitud | 1 <= L <= 5 | "abc" | True |
| Chars | Letra + Número | "A1" | True |
| Frontera | L = 5 | "abcde" | True |
| Char Inicial | Letra | "Z" | True |
| Chars | Espacio | "a1234" | True |
| Char Inicial | Número | "1abc" | False |
| Longitud | L > 5 | "abcdef" | False |
| Chars | Espacio | "a b" | False |
| Chars | Símbolo No Permitido | "a!" | False |
| Char Inicial | Símbolo No Permitido | "_a" | False |
| Frontera | L = 6 | "abc345" | False |

### 10 Tests no Parametrizados
| Método | Tipo | Input | Resultado Esperado |
| :-------: | :------: | :-------: | :-------: |
| testValidLength1 | Longitud | "a" | assertTrue |
| testValidLength2 | Longitud | "abc12" | assertTrue |
| testValidFormat1 | Solo letras | "Hola" | assertTrue |
| testValidFormat2 | Letras + Numeros | "M4V3N" | assertTrue |
| testInvalidFormat1 | Número Inicial | "7Up" | assertFalse |
| testInvalidFormat2 | Solo chars inválidos | "$$$" | assertFalse |
| testInvalidFormat3 | Combinación con char inválido | "Mi$h" | assertFalse |
| testInvalidLength | Longitud | "patrones" | assertFalse |
| testInvalidSpace1 | Espacio | "a b c" | assertFalse |
| testInvalidSpace2 | Espacio | " abc" | assertFalse |

### 2) Property-Based Testing

Se armaron 2 casos de prueba utilizando Property en conjunto con AlphaChars y NumericChars para generar hasta 1000 posibles combinaciones para realizar testeos.
Esto se hace mediante 2 strings que eventualmente se concatenan en la variable `s`, que es el string final con el que se hace el test. Finalmente, este se revisa con
assertTrue / assertFalse según la función lo requiera.


```
public void validString(
            @ForAll @AlphaChars @StringLength(min = 1, max = 1) String first,
            @ForAll @AlphaChars @NumericChars @StringLength(min = 0, max = 4) String tail
    )
    {
        String s = first + tail;
    }
```
```
    public void invalidString(
            @ForAll @AlphaChars @StringLength(min = 1, max = 1) String first,
            @ForAll @AlphaChars @NumericChars @StringLength(min = 5, max = 20) String tail
    )
{
        String s = first + tail;
}
```

### 3) IdentifierFixer

Para arreglar los strings con errores se aplicaron una serie de reglas y correcciones en las que se hace uso de un stringBuilder
para retornar un nuevo string válido, independiente del input.

A continuación se detallan la serie de reglas aplicadas a un string inválido para resolverse con la clase IdentifierFixer

- Si el string es nulo o vacío -> retorna `"a"` por default.
- Si el primer caracter es inválido -> se reemplaza por `"a"`.
- Si alguno del resto de carácteres es inválido -> se omite.
- Si el string supera el máximo largo permitido (en este caso: 5), se trunca hasta que el string tenga un `largo = 5`.

### 4) Refactor de Identifier
Actualmente, la clase Identifier se encarga de validar a través de ciertas reglas definidas la validez de un string dependiendo de si
estas se cumple o no. El detalle es que no se dice específicamente cuál es la razón de que un string sea rechazado, por lo que para mejorar esto se
realizaron los siguientes cambios:

Se creó un nuevo archivo `IdentifierErrorEnum.java` que contiene las siguientes etiquetas:

- VALIDO
- ERROR_VACIO
- ERROR_LONGITUD
- ERROR_CHAR_INICIAL
- ERROR_CHAR

Posteriormente, se creó el archivo `BetterIdentifier.java` (para no alterar el resto de los entregables) en donde se intentará
replicar el funcionamiento de la clase Identifier original mediante el uso de las etiquetas que creamos anteriormente.

Por ejemplo, antiguamente para validar si el largo del string no excedía los 5 carácteres, se tenía lo siguiente:

```
        if (valid_id && (s.length() >= 1) && (s.length() < 6)) {
            return true;
        } else {
            return false;
        }
```
Ahora, con la refactorización, tenemos algo más sencillo y legible

```
        if(s == null || s.isEmpty())
        {
            return IdentifierErrorEnum.ERROR_VACIO;
        }

        if(s.length() > 5)
        {
            return IdentifierErrorEnum.ERROR_LONGITUD;
        }
```

Esto nos permite saber cuál es el error que evita que el string sea válido. La refactorización del método realiza la verificación
en un nuevo método llamado `validateIdentifierMejorado`

1. Primero se verifica que el string no sea null o vacío, si lo es se hace return de forma inmediata con la etiqueta ERROR_VACIO. Con esto también podemos asumir que el string tendrá largo 1 como mínimo y solucionar un problema del Identifier original, ya que no se cubre ninguno de esos casos con excepciones o condicionales.
2. Lo siguiente que se verifica es que el largo del string no sea mayor a 5, si lo es, se retorna con la etiqueta ERROR_LONGITUD. Para esto solo se revisa directamente el largo con .length en lugar del ciclo while con las múltiples condicionales que había en el Identifier original.
3. Posteriormente se lee el primer carácter del string, y se verifica manualmente con la función isLetter para revisar que no sea un dígito o un carácter. Si el primer carácter no es una letra, se retorna con la etiqueta ERROR_CHAR_INICIAL.
4. En caso de que el char inicial sea válido, se recorre con un ciclo for el resto del string y se revisa cada carácter, utilizando las funciones isLetter y isLetterOrDigit podemos verificar rápidamente que sea un carácter válido. Si hay un carácter invalido se retorna con la etiqueta ERROR_CHAR.
5. Si todo ha salido bien, se retorna con la etiqueta VALIDO.
## Sección que debe agregarse en el README 

> ### Justificación de Refactor aplicado al método base
> Explicar qué mejoró y por qué.

---



### Fecha límite: 

Entrega el **18-11-2025** por medio de campus virtual.

---


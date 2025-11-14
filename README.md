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
Cuando el identificador es inválido, el método `validateIdentifier` debe indicar POR QUÉ falla (enum con mínimo 4 causas).

---

## Sección que debe agregarse en el README 

> ### Justificación de Refactor aplicado al método base
> Explicar qué mejoró y por qué.

---



### Fecha límite: 

Entrega el **18-11-2025** por medio de campus virtual.

---


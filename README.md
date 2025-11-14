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

Property Based Testing (PBT) es una técnica de testing donde en vez de escribir casos de prueba específicos manuales,
el programador define propiedades generales que siempre deben cumplirse, independiente del input.

Luego una librería como jqwik o junit-quickcheck automáticamente genera cientos o miles de
entradas aleatorias para intentar romper la propiedad.

Ejemplo:

``` java
// Importa la API de jqwik: anotaciones (@Property, @ForAll), generadores y utilidades.
import net.jqwik.api.*;
// Usamos las aserciones de JUnit Jupiter (la plataforma donde corre jqwik).
import static org.junit.jupiter.api.Assertions.*;

// No es necesario que esta clase extienda nada. jqwik detecta métodos @Property.
class IdentifierProperties {

  // --- IDEA DE LA PROPIEDAD ---
  // "Si un string comienza con letra, NO tiene dos dígitos consecutivos
  //  y su largo total ≤ 8, entonces debe ser un identificador válido".
  //
  // jqwik generará MUCHOS valores diferentes para 'first' y 'tail' que cumplan
  // las restricciones y verificará que TODOS pasen la aserción.

  @Property // <- Indica a jqwik que este método define una propiedad a verificar muchas veces
  void validIdentifierIsAccepted(
      // first: exactamente 1 carácter y debe ser una letra (A–Z o a–z)
      @ForAll @AlphaChars @StringLength(min = 1, max = 1) String first,
      // tail: 0..7 caracteres alfanuméricos (letras o dígitos)
      @ForAll @AlphaNumericChars @StringLength(min = 0, max = 7) String tail
  ) {
    // Concatenamos: total ≡ [1..8] caracteres
    String s = first + tail;

    // Precondición: descartamos cualquier caso que tenga dos dígitos consecutivos.
    // 'Assume.that' NO falla el test; simplemente le dice a jqwik:
    // "este caso no sirve, genera otro". Útil cuando es más fácil filtrar que generar.
    Assume.that(!s.matches(".*\\d{2}.*"));

    // Aserción: bajo estas condiciones, nuestra función debe aceptar el identificador.
    assertTrue(Identifier.validateIdentifier(s));
  }
}
```
Usar  [**jqwik** ](https://jqwik.net/). Debe definir al menos 2 propiedades del sistema y testearlas.


### 3) Nueva Clase: `IdentifierFixer`
Esta clase debe poder transformar un identificador inválido en uno válido según reglas que tú defines.

Debes definir las reglas de corrección en README (sección abajo).

### 4) Enum de Código de Error
Cuando el identificador es inválido, el método `validateIdentifier` debe indicar POR QUÉ falla (enum con mínimo 4 causas).

---

## Sección que debe agregarse en el README 

> ### Tabla final de particiones de equivalencia + análisis de frontera
> Debe mostrar la tabla que usó para diseñar los tests

> ### Lista de identificadores inválidos y su versión corregida por IdentifierFixer

> ### Justificación de Refactor aplicado al método base
> Explicar qué mejoró y por qué.

---

## Ejemplo de reglas de corrección 

- Si comienza con número, reemplazar por una letra.
- Si hay 2 dígitos consecutivos, borrar uno.
- Si contiene símbolos no permitidos, eliminarlos.
- Si supera longitud, truncar hasta 8 caracteres.

Puede proponer otras reglas, pero deben quedar documentadas.

---

## Método de Evaluación

| Criterio | Ponderación |
|----------|-------------|
| Diseño de casos basados en equivalencias + frontera | 35%         |
| Correcta implementación JUnit parametrizado | 15%         |
| Property Based Testing | 15%         |
| Implementación IdentifierFixer + Enum Errors + Refactor | 20%         |
| Documentación README según formato exigido | 15%         |

---

### Fecha límite: 

Entrega el **18-11-2025** por medio de campus virtual.

---


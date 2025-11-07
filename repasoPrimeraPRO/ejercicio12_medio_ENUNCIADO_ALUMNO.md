# 🎮 Ejercicio 12: Analizador de Palabras

## 🎯 Objetivos de Aprendizaje
- Manipulación avanzada de **Strings**
- Uso de métodos de la clase String
- Trabajo con caracteres individuales
- Aplicación de bucles para recorrer cadenas

---

## 📋 Descripción del Problema

Debes crear un programa que analice palabras clave de un chat de videojuego y proporcione estadísticas sobre ellas. El sistema debe procesar Strings y extraer información útil.

El programa debe analizar una palabra y proporcionar:
- **Longitud** de la palabra
- **Cantidad de vocales**
- **Cantidad de consonantes**
- **Si es un palíndromo** (se lee igual al derecho y al revés)
- **Palabra invertida**
- **Primera y última letra**
- **Si contiene números**

---

## 📥 Entrada

El programa debe:
1. Solicitar una palabra o frase (sin espacios para simplificar)
2. Procesar y mostrar todas las estadísticas

Opcionalmente, puede repetirse para analizar varias palabras hasta que el usuario escriba "salir".

---

## 📤 Salida Esperada (orientativa)

```
=== ANALIZADOR DE PALABRAS ===
Introduce una palabra para analizar (o 'salir' para terminar): dragon

=== ANÁLISIS DE: dragon ===
Longitud: 6 caracteres
Vocales: 2 (a, o)
Consonantes: 4 (d, r, g, n)
¿Es palíndromo?: No
Palabra invertida: nogard
Primera letra: d
Última letra: n
¿Contiene números?: No

Introduce una palabra para analizar (o 'salir' para terminar): radar

=== ANÁLISIS DE: radar ===
Longitud: 5 caracteres
Vocales: 2 (a, a)
Consonantes: 3 (r, d, r)
¿Es palíndromo?: Sí
Palabra invertida: radar
Primera letra: r
Última letra: r
¿Contiene números?: No

Introduce una palabra para analizar (o 'salir' para terminar): player1

=== ANÁLISIS DE: player1 ===
Longitud: 7 caracteres
Vocales: 2 (a, e)
Consonantes: 4 (p, l, y, r)
¿Es palíndromo?: No
Palabra invertida: 1reyalp
Primera letra: p
Última letra: 1
¿Contiene números?: Sí

Introduce una palabra para analizar (o 'salir' para terminar): salir

¡Hasta luego!
```

---

## 🧪 Casos de Prueba

### **Caso 1: Palabra normal**
- Entrada: `"dragon"`
- Vocales: 2
- Consonantes: 4
- Palíndromo: No
- Invertida: "nogard"

### **Caso 2: Palíndromo**
- Entrada: `"radar"`
- Palíndromo: Sí
- Invertida: "radar"

### **Caso 3: Con números**
- Entrada: `"player1"`
- Contiene números: Sí

### **Caso 4: Solo vocales**
- Entrada: `"aeiou"`
- Vocales: 5
- Consonantes: 0

### **Caso 5: Palabra de una letra**
- Entrada: `"a"`
- Siempre es palíndromo
- Primera y última letra son la misma

---

## ✅ Criterios de Éxito

- [ ] Cuenta correctamente vocales y consonantes
- [ ] Detecta correctamente si es palíndromo
- [ ] Invierte correctamente la palabra
- [ ] Identifica primera y última letra
- [ ] Detecta si contiene números
- [ ] Funciona con palabras de diferentes tamaños
- [ ] Puede procesar múltiples palabras hasta que se escriba "salir"

---

## 💡 Pistas Generales

1. Usa el método `.length()` para obtener la longitud de un String
2. Usa `.charAt(indice)` para obtener un carácter en una posición específica
3. Para recorrer cada carácter, usa un bucle `for` desde 0 hasta `palabra.length() - 1`
4. Para verificar si un carácter es vocal, compáralo con 'a', 'e', 'i', 'o', 'u' (considera mayúsculas también)
5. Para detectar números, verifica si el carácter está entre '0' y '9'
6. Para invertir una palabra, construye un nuevo String recorriendo desde el final al inicio
7. Para verificar palíndromo, compara la palabra original con la invertida usando `.equals()`

---

## 📝 Conceptos Técnicos Relevantes

### **Métodos útiles de String:**
- `.length()` - Devuelve la longitud
- `.charAt(i)` - Devuelve el carácter en la posición i
- `.toLowerCase()` - Convierte a minúsculas
- `.equals()` - Compara dos Strings

### **Comparación de caracteres:**
- Puedes comparar caracteres con `==`: `if (letra == 'a')`
- Los caracteres son ordinales: `'a' < 'b'` es verdadero

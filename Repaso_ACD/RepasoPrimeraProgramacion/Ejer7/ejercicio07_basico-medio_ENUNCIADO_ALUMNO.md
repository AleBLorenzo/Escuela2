# EJERCICIO 07 - Generador de Tabla de Multiplicar
## Nivel: Básico-Medio

---

## 🎯 Objetivos de Aprendizaje
- Dominar el bucle for con rangos específicos
- Trabajar con operaciones aritméticas en bucles
- Formatear salida de manera tabular
- Implementar menús con repetición

---

## 📝 Descripción del Problema

Estás desarrollando una herramienta educativa para un videojuego de aventuras matemáticas. Los jugadores necesitan practicar las tablas de multiplicar para desbloquear hechizos especiales. El programa debe generar tablas de multiplicar personalizadas según las necesidades del jugador.

Debes crear un programa que:

1. **Muestre un menú inicial con opciones:**
   ```
   === GENERADOR DE TABLAS DE MULTIPLICAR ===
   
   1. Generar tabla simple (1-10)
   2. Generar tabla extendida (1-20)
   3. Generar rango personalizado
   4. Tabla de Pitágoras (cuadrícula completa)
   5. Salir
   ```

2. **Para cada opción:**

   **Opción 1 - Tabla simple:**
   - Pedir número base
   - Mostrar tabla del 1 al 10
   
   **Opción 2 - Tabla extendida:**
   - Pedir número base
   - Mostrar tabla del 1 al 20
   
   **Opción 3 - Rango personalizado:**
   - Pedir número base
   - Pedir inicio del rango
   - Pedir fin del rango
   - Mostrar tabla en ese rango
   
   **Opción 4 - Tabla de Pitágoras:**
   - Mostrar cuadrícula 10x10
   - Filas y columnas del 1 al 10
   - Intersecciones muestran el producto

3. **Requisitos adicionales:**
   - Validar que los números sean positivos
   - Permitir generar múltiples tablas sin salir del programa
   - Formatear la salida de manera alineada y legible
   - Mostrar mensaje de logro al completar cada tabla

---

## 💡 Conceptos Clave a Aplicar

- **Bucle for clásico**: `for (int i = 1; i <= 10; i++)`
- **Bucle for con rango variable**: inicio y fin dados por usuario
- **Bucles anidados**: for dentro de for (para tabla Pitágoras)
- **Formato de salida**: alineación con espacios o printf
- **Menú con switch**: similar al ejercicio anterior
- **Validación**: números deben ser positivos

---

## 🔍 Casos de Prueba

### Caso 1: Tabla simple (opción 1)
**Entrada:**
```
Opción: 1
Número base: 7
```

**Salida esperada:**
```
=== TABLA DEL 7 (Simple) ===

7 x 1 = 7
7 x 2 = 14
7 x 3 = 21
7 x 4 = 28
7 x 5 = 35
7 x 6 = 42
7 x 7 = 49
7 x 8 = 56
7 x 9 = 63
7 x 10 = 70

✓ ¡Tabla completada! +10 puntos de sabiduría
```

### Caso 2: Tabla extendida (opción 2)
**Entrada:**
```
Opción: 2
Número base: 12
```

**Salida esperada:**
```
=== TABLA DEL 12 (Extendida) ===

12 x 1 = 12
12 x 2 = 24
...
12 x 10 = 120
12 x 11 = 132
12 x 12 = 144
...
12 x 20 = 240

✓ ¡Tabla extendida completada! +20 puntos de sabiduría
```

### Caso 3: Rango personalizado (opción 3)
**Entrada:**
```
Opción: 3
Número base: 5
Inicio del rango: 8
Fin del rango: 15
```

**Salida esperada:**
```
=== TABLA DEL 5 (Rango: 8-15) ===

5 x 8 = 40
5 x 9 = 45
5 x 10 = 50
5 x 11 = 55
5 x 12 = 60
5 x 13 = 65
5 x 14 = 70
5 x 15 = 75

✓ ¡Tabla personalizada completada! +15 puntos de sabiduría
```

### Caso 4: Tabla de Pitágoras (opción 4)
**Entrada:**
```
Opción: 4
```

**Salida esperada:**
```
=== TABLA DE PITÁGORAS (10x10) ===


  1 |   1   2   3   4   5   6   7   8   9  10
  2 |   2   4   6   8  10  12  14  16  18  20
  3 |   3   6   9  12  15  18  21  24  27  30
  4 |   4   8  12  16  20  24  28  32  36  40
  5 |   5  10  15  20  25  30  35  40  45  50
  6 |   6  12  18  24  30  36  42  48  54  60
  7 |   7  14  21  28  35  42  49  56  63  70
  8 |   8  16  24  32  40  48  56  64  72  80
  9 |   9  18  27  36  45  54  63  72  81  90
 10 |  10  20  30  40  50  60  70  80  90 100

✓ ¡Tabla de Pitágoras completada! +50 puntos de sabiduría
```

### Caso 5: Validación de entrada
**Entrada:**
```
Opción: 3
Número base: -5
```

**Salida esperada:**
```
❌ Error: El número debe ser positivo.
Por favor, introduce un número mayor que 0.

[Vuelve a pedir número base]
```

---

## ✅ Criterios de Éxito

Tu programa debe:
- ✓ Implementar las 5 opciones del menú correctamente
- ✓ Usar bucle for para generar las multiplicaciones
- ✓ Usar bucles for anidados para la tabla de Pitágoras
- ✓ Validar que los números sean positivos
- ✓ Formatear la salida de manera alineada
- ✓ Permitir generar múltiples tablas sin reiniciar
- ✓ Manejar correctamente rangos personalizados (inicio > fin, etc.)

---

## 🎮 Pistas Generales

### Bucle for básico:
```
Para generar tabla del número N del 1 al 10:

para i desde 1 hasta 10:
    resultado = N * i
    mostrar "N x i = resultado"
```

### Bucle for con rango personalizado:
```
Para tabla del N desde inicio hasta fin:

para i desde inicio hasta fin:
    resultado = N * i
    mostrar "N x i = resultado"
```

### Bucles anidados para Pitágoras:
```
Mostrar encabezado

para fila desde 1 hasta 10:
    mostrar número de fila
    para columna desde 1 hasta 10:
        producto = fila * columna
        mostrar producto
    nueva línea
```

### Formateo con printf:
```java
// Para alinear números
System.out.printf("%2d x %2d = %3d\n", base, i, resultado);

// Explicación:
// %2d = entero con mínimo 2 caracteres (alineado a derecha)
// %3d = entero con mínimo 3 caracteres
```

---

## 📌 Notas Importantes

- **Validación de rangos**: Si inicio > fin, mostrar error o intercambiar valores
- **Números grandes**: Si base es muy grande, los productos pueden ser muy grandes
- **Formato de tabla Pitágoras**: El más complejo, requiere alineación cuidadosa
- **Menú repetitivo**: Usar while como en ejercicio anterior
- **Diferencia entre = y ==**: No confundir asignación con comparación en for

---

## 🤔 Preguntas de Reflexión

Antes de empezar:
1. ¿Cuál es la estructura básica de un bucle for?
2. ¿Cómo cambias el rango de un bucle for (inicio, fin, incremento)?
3. ¿Qué es un bucle anidado y cuándo se usa?
4. ¿Cómo alineas números de diferentes longitudes en la salida?
5. ¿Qué validaciones necesitas hacer antes de generar una tabla?

---

## 🎲 Desafíos Extra (Opcional)

Para los que terminen antes:

1. **Tabla inversa**: Mostrar tabla de división (N / 1, N / 2, ...)
2. **Múltiples tablas**: Generar varias tablas a la vez (del 2 al 5)
3. **Búsqueda**: Preguntar "¿7 x 8?" y validar la respuesta
4. **Colores**: Resaltar números primos o múltiplos especiales
5. **Exportar**: Guardar la tabla en un archivo de texto (si ya viste ficheros)

---

## 💭 Ejemplo de Flujo Interactivo

```
=== GENERADOR DE TABLAS ===

1. Tabla simple
2. Tabla extendida
3. Rango personalizado
4. Tabla Pitágoras
5. Salir

Elige opción: 1
Número base: 5

=== TABLA DEL 5 ===
5 x 1 = 5
5 x 2 = 10
...
5 x 10 = 50

✓ Completada!

[Vuelve al menú]

Elige opción: 3
Número base: 7
Inicio: 15
Fin: 20

=== TABLA DEL 7 (15-20) ===
7 x 15 = 105
...
7 x 20 = 140

✓ Completada!

[Vuelve al menú]

Elige opción: 5
¡Hasta pronto, matemático!
```

---

## 🔢 Fórmulas y Patrones Útiles

### Para alineación:
```
Número de 1 dígito:  "  5"  (2 espacios + número)
Número de 2 dígitos: " 12"  (1 espacio + número)
Número de 3 dígitos: "144"  (número directo)
```

### Para tabla de Pitágoras:
```
Cada celda debe tener el mismo ancho (ej: 4 caracteres)
Usar printf para mantener alineación consistente
```

---

**¡Buena suerte multiplicando! Las matemáticas te harán más fuerte. 🎓**

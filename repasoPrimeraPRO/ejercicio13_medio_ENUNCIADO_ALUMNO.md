# 🎮 Ejercicio 13: Matriz de Mapa

## 🎯 Objetivos de Aprendizaje
- Trabajar con **arrays bidimensionales (matrices)**
- Recorrer filas y columnas de matrices
- Representar estructuras espaciales con matrices
- Aplicar lógica de coordenadas (x, y)

---

## 📋 Descripción del Problema

Debes crear un programa que represente un mapa de videojuego usando una matriz. El mapa es una cuadrícula donde cada celda puede contener diferentes elementos.

El mapa debe representarse con los siguientes símbolos:
- `'.'` = Camino libre
- `'#'` = Muro/obstáculo
- `'P'` = Jugador
- `'E'` = Enemigo
- `'T'` = Tesoro

El programa debe:
1. Crear una matriz de 8x8 para el mapa
2. Inicializar todo el mapa con caminos libres `'.'`
3. Colocar elementos en posiciones específicas
4. Mostrar el mapa en pantalla
5. Proporcionar estadísticas del mapa

---

## 📥 Entrada

El programa debe:
1. Crear una matriz de 8 filas x 8 columnas
2. Solicitar la posición del jugador (fila, columna)
3. Solicitar cuántos enemigos quiere colocar (máximo 5)
4. Para cada enemigo, solicitar su posición
5. Solicitar cuántos tesoros quiere colocar (máximo 3)
6. Para cada tesoro, solicitar su posición
7. Colocar muros en el borde del mapa automáticamente

---

## 📤 Salida Esperada (orientativa)

```
=== GENERADOR DE MAPA ===

Introduce la posición del jugador:
Fila (0-7): 3
Columna (0-7): 3

¿Cuántos enemigos quieres colocar? (máx 5): 2

-- Enemigo 1 --
Fila (0-7): 2
Columna (0-7): 5

-- Enemigo 2 --
Fila (0-7): 5
Columna (0-7): 2

¿Cuántos tesoros quieres colocar? (máx 3): 1

-- Tesoro 1 --
Fila (0-7): 6
Columna (0-7): 6

=== MAPA GENERADO ===
# # # # # # # #
# . . . . . . #
# . . . . E . #
# . . . P . . #
# . . . . . . #
# . T . . . . #
# . . . . . T #
# # # # # # # #

=== ESTADÍSTICAS ===
Dimensiones: 8x8 (64 celdas)
Caminos libres: 53
Muros: 8
Jugador: 1
Enemigos: 2
Tesoros: 1
```

---

## 🧪 Casos de Prueba

### **Caso 1: Mapa básico**
- Jugador en (3,3)
- 1 enemigo en (1,5)
- 1 tesoro en (6,6)
- Verificar que se coloca correctamente

### **Caso 2: Múltiples elementos**
- Jugador en (4,4)
- 5 enemigos en diferentes posiciones
- 3 tesoros en diferentes posiciones
- Verificar que no se solapan

### **Caso 3: Validación de límites**
- Intentar colocar elementos fuera del mapa (fila 10)
- Debe rechazar o solicitar nueva posición

### **Caso 4: Bordes automáticos**
- Verificar que todo el borde del mapa (primera y última fila/columna) son muros `'#'`

---

## ✅ Criterios de Éxito

- [ ] Crea correctamente una matriz de 8x8
- [ ] Inicializa todas las celdas con `'.'`
- [ ] Coloca muros automáticamente en los bordes
- [ ] Permite al usuario colocar el jugador, enemigos y tesoros
- [ ] Valida que las posiciones estén dentro del rango (0-7)
- [ ] Muestra el mapa correctamente en formato visual
- [ ] Calcula y muestra estadísticas correctas

---

## 💡 Pistas Generales

1. Declara la matriz como: `char[][] mapa = new char[8][8];`
2. Para inicializar, usa bucles anidados:
   ```
   for cada fila:
       for cada columna:
           asignar '.'
   ```
3. Para colocar muros en bordes:
   - Primera fila (fila 0): todas las columnas `'#'`
   - Última fila (fila 7): todas las columnas `'#'`
   - Primera columna (columna 0): todas las filas `'#'`
   - Última columna (columna 7): todas las filas `'#'`
4. Para colocar un elemento en una posición: `mapa[fila][columna] = 'P';`
5. Para mostrar el mapa, usa bucles anidados y añade espacios entre caracteres para mejor visualización
6. Para contar elementos, recorre toda la matriz y usa contadores para cada tipo

---

## 📝 Conceptos Técnicos Relevantes

### **Arrays bidimensionales:**
- Se declaran con doble corchete: `tipo[][] nombre`
- Se acceden con dos índices: `matriz[fila][columna]`
- Primer índice = fila, segundo índice = columna

### **Recorrido de matrices:**
- Bucle externo para filas
- Bucle interno para columnas
- Permite procesar cada celda

---

## 🎨 Formato Visual

Para mejor visualización, añade espacios entre los símbolos:
```
# # # # #
# . P . #
# . . E #
# # # # #
```

En lugar de:
```
#####
#.P.#
#..E#
#####
```

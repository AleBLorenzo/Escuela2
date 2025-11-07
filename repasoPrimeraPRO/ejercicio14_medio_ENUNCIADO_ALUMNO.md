# 🎮 Ejercicio 14: Búsqueda de Tesoros

## 🎯 Objetivos de Aprendizaje
- Aplicar **algoritmos de búsqueda en matrices**
- Trabajar con coordenadas en arrays bidimensionales
- Implementar distancia Manhattan
- Combinar estructuras de datos y algoritmos

---

## 📋 Descripción del Problema

Debes crear un programa que ayude a un jugador a encontrar tesoros en un mapa representado por una matriz. El sistema debe buscar tesoros en el mapa y calcular cuál está más cerca del jugador.

El mapa es una matriz de 10x10 con los siguientes elementos:
- `'.'` = Terreno vacío
- `'P'` = Jugador
- `'T'` = Tesoro
- `'#'` = Obstáculo (no impide búsqueda, solo visualización)

El programa debe:
1. Crear un mapa predefinido o permitir colocar elementos
2. Buscar todos los tesoros en el mapa
3. Calcular la distancia de cada tesoro al jugador
4. Mostrar el tesoro más cercano
5. Listar todos los tesoros ordenados por distancia

---

## 📥 Entrada

El programa puede tener un mapa predefinido o solicitar:
1. Posición del jugador
2. Número de tesoros a colocar
3. Posición de cada tesoro
4. Opcionalmente, obstáculos

---

## 📤 Salida Esperada (orientativa)

```
=== MAPA DE BÚSQUEDA DE TESOROS ===
. . . # . . . . . .
. . . # . T . . . .
. P . # . . . . . .
. . . . . . . . . .
. . . . T . . . . .
. . . . . . . . . .
. . . . . . . T . .
. . . . . . . . . .
. . . . . . . . . .
. . . . . . . . . .

Jugador en posición: (2, 1)

=== BÚSQUEDA DE TESOROS ===
Buscando tesoros en el mapa...

Tesoros encontrados: 3

-- Tesoro 1 --
Posición: (1, 5)
Distancia Manhattan: 5 casillas

-- Tesoro 2 --
Posición: (4, 4)
Distancia Manhattan: 5 casillas

-- Tesoro 3 --
Posición: (6, 7)
Distancia Manhattan: 10 casillas

=== TESORO MÁS CERCANO ===
El tesoro más cercano está en: (1, 5)
Distancia: 5 casillas
```

---

## 🧪 Casos de Prueba

### **Caso 1: Tesoro único**
- Jugador en (5, 5)
- Tesoro en (7, 8)
- Distancia Manhattan: |5-7| + |5-8| = 2 + 3 = 5

### **Caso 2: Múltiples tesoros**
- Jugador en (0, 0)
- Tesoro A en (0, 5) → distancia 5
- Tesoro B en (3, 3) → distancia 6
- Tesoro C en (1, 1) → distancia 2
- Más cercano: Tesoro C

### **Caso 3: Sin tesoros**
- Mapa sin tesoros
- Debe indicar que no hay tesoros

### **Caso 4: Tesoro en misma posición**
- Jugador en (3, 3)
- Tesoro en (3, 3)
- Distancia: 0

---

## ✅ Criterios de Éxito

- [ ] Crea correctamente el mapa como matriz 10x10
- [ ] Busca todos los tesoros en el mapa recorriendo la matriz
- [ ] Almacena las posiciones de los tesoros encontrados
- [ ] Calcula correctamente la distancia Manhattan
- [ ] Identifica el tesoro más cercano
- [ ] Muestra todos los tesoros ordenados por distancia (opcional)
- [ ] Maneja el caso de no haber tesoros

---

## 💡 Pistas Generales

1. **Búsqueda de tesoros:**
   - Recorre toda la matriz con bucles anidados
   - Cuando encuentres 'T', guarda su posición
   - Usa arrays paralelos o una estructura para almacenar posiciones

2. **Distancia Manhattan:**
   - Fórmula: `distancia = |filaJugador - filaTesoro| + |colJugador - colTesoro|`
   - Usa `Math.abs()` para el valor absoluto

3. **Encontrar el mínimo:**
   - Inicializa una variable con un valor muy alto
   - Recorre todas las distancias y actualiza si encuentras una menor

4. **Almacenamiento de tesoros:**
   - Opción 1: Arrays paralelos (filasT[], colsT[])
   - Opción 2: Contar primero cuántos hay, luego almacenar

---

## 📝 Conceptos Técnicos Relevantes

### **Distancia Manhattan:**
También conocida como "distancia de taxista" o "distancia L1". Calcula la distancia en una cuadrícula donde solo puedes moverte horizontal o verticalmente (no en diagonal).

**Ejemplo:**
```
De A(2,1) a B(5,4):
  Distancia horizontal: |2-5| = 3
  Distancia vertical: |1-4| = 3
  Distancia Manhattan: 3 + 3 = 6
```

### **Búsqueda en matriz:**
Requiere bucles anidados para revisar cada celda:
```
para cada fila:
    para cada columna:
        si mapa[fila][columna] == 'T':
            guardar posición
```

### **Valor absoluto:**
Para calcular diferencias sin importar el signo, usa `Math.abs()`:
- `Math.abs(-5)` → 5
- `Math.abs(3)` → 3

---

## 🎯 Desafíos Opcionales

1. **Ruta visual:** Marcar en el mapa el camino del jugador al tesoro más cercano
2. **Ordenación:** Ordenar los tesoros por distancia de menor a mayor
3. **Tesoros más lejanos:** Identificar el tesoro más lejano
4. **Distancia Euclidiana:** Calcular también la distancia en línea recta (diagonal)

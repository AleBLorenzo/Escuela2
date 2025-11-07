# 🎮 Ejercicio 18: Clasificación de Jugadores

## 🎯 Objetivos de Aprendizaje
- Implementar **algoritmos de ordenación** personalizados
- Trabajar con **múltiples criterios de ordenación**
- Combinar **ArrayList con objetos complejos** (sin POO usando Strings estructurados)
- Aplicar **búsqueda y filtrado avanzado**

---

## 📋 Descripción del Problema

Debes crear un sistema de clasificación para un juego competitivo que ordene jugadores según diferentes criterios. El sistema debe poder ordenar por puntuación, nivel, ratio de victorias, y otros atributos, permitiendo visualizar el ranking de diferentes formas.

Cada jugador tiene:
- **Nombre** (String)
- **Nivel** (1-100)
- **Puntos** (0-10000)
- **Victorias** (int)
- **Derrotas** (int)
- **Rango**: "Bronce", "Plata", "Oro", "Platino", "Diamante", "Leyenda"

El programa debe:
1. Cargar o agregar jugadores al sistema
2. Ordenar por diferentes criterios (puntos, nivel, ratio V/D, nombre)
3. Filtrar por rango
4. Buscar jugadores por criterios
5. Mostrar top N jugadores
6. Calcular y asignar rangos automáticamente

---

## 📥 Entrada

El programa debe:
1. Permitir agregar jugadores manualmente o cargar un conjunto predefinido
2. Mostrar menú con opciones de ordenación y filtrado
3. Solicitar criterios específicos según la opción elegida

---

## 📤 Salida Esperada (orientativa)

```
=== SISTEMA DE CLASIFICACIÓN ===

1. Agregar jugador
2. Ver todos los jugadores
3. Ordenar por puntos
4. Ordenar por nivel
5. Ordenar por ratio V/D
6. Ordenar por nombre (A-Z)
7. Filtrar por rango
8. Top 5 jugadores
9. Buscar jugador
0. Salir

Opción: 1

-- AGREGAR JUGADOR --
Nombre: DragonSlayer
Nivel (1-100): 45
Puntos (0-10000): 4500
Victorias: 120
Derrotas: 80
✓ Jugador agregado
Rango asignado: Oro

Opción: 2

=== LISTA DE JUGADORES ===
1. DragonSlayer | Nivel: 45 | Puntos: 4500 | V/D: 120/80 (1.50) | Rango: Oro
2. MagicWizard | Nivel: 52 | Puntos: 5800 | V/D: 150/70 (2.14) | Rango: Platino
3. NightHunter | Nivel: 38 | Puntos: 3200 | V/D: 90/95 (0.95) | Rango: Plata
4. IronWarrior | Nivel: 67 | Puntos: 7500 | V/D: 200/50 (4.00) | Rango: Diamante
5. ShadowNinja | Nivel: 29 | Puntos: 1800 | V/D: 50/60 (0.83) | Rango: Bronce

Total: 5 jugadores

Opción: 3

=== CLASIFICACIÓN POR PUNTOS ===
(Orden descendente)

🥇 1. IronWarrior - 7500 pts [Diamante]
🥈 2. MagicWizard - 5800 pts [Platino]
🥉 3. DragonSlayer - 4500 pts [Oro]
   4. NightHunter - 3200 pts [Plata]
   5. ShadowNinja - 1800 pts [Bronce]

Opción: 5

=== CLASIFICACIÓN POR RATIO V/D ===
(Orden descendente)

1. IronWarrior - Ratio: 4.00 (200V/50D) [Diamante]
2. MagicWizard - Ratio: 2.14 (150V/70D) [Platino]
3. DragonSlayer - Ratio: 1.50 (120V/80D) [Oro]
4. NightHunter - Ratio: 0.95 (90V/95D) [Plata]
5. ShadowNinja - Ratio: 0.83 (50V/60D) [Bronce]

Opción: 7

Introduce el rango a filtrar (Bronce/Plata/Oro/Platino/Diamante/Leyenda): Oro

=== JUGADORES EN RANGO: Oro ===
1. DragonSlayer | Nivel: 45 | Puntos: 4500 | Ratio: 1.50

Total: 1 jugador(es) en rango Oro

Opción: 8

=== TOP 5 JUGADORES ===
(Por puntuación)

🏆 1. IronWarrior - 7500 pts
🏆 2. MagicWizard - 5800 pts
🏆 3. DragonSlayer - 4500 pts
🏆 4. NightHunter - 3200 pts
🏆 5. ShadowNinja - 1800 pts
```

---

## 🧪 Casos de Prueba

### **Caso 1: Ordenar por puntos**
- Jugadores con puntos: 5000, 3000, 7000
- Orden descendente: 7000, 5000, 3000

### **Caso 2: Ordenar por ratio**
- Jugador A: 100V/50D = ratio 2.00
- Jugador B: 150V/30D = ratio 5.00
- Orden: B primero (mayor ratio)

### **Caso 3: Filtrar por rango**
- 5 jugadores, 2 en "Oro"
- Filtro "Oro" debe mostrar solo esos 2

### **Caso 4: Jugador sin derrotas**
- Victorias: 50, Derrotas: 0
- Ratio debe manejarse (evitar división por cero)

### **Caso 5: Asignación de rango**
- 0-1999 pts: Bronce
- 2000-3999: Plata
- 4000-5999: Oro
- 6000-7999: Platino
- 8000-9499: Diamante
- 9500+: Leyenda

---

## ✅ Criterios de Éxito

- [ ] Almacena jugadores con todos sus atributos
- [ ] Implementa al menos 3 algoritmos de ordenación diferentes
- [ ] Ordena por puntos correctamente (descendente)
- [ ] Ordena por nivel correctamente
- [ ] Calcula y ordena por ratio V/D correctamente
- [ ] Ordena alfabéticamente por nombre
- [ ] Filtra jugadores por rango
- [ ] Muestra top N jugadores
- [ ] Asigna rangos automáticamente según puntos
- [ ] Maneja división por cero en ratio (cuando derrotas = 0)

---

## 💡 Pistas Generales

1. **Almacenamiento:**
   - Usar 6 ArrayLists paralelos para los 6 atributos
   - Mantener siempre la sincronización entre todos los arrays

2. **Ordenación:**
   - Implementar ordenación burbuja o selección
   - Al intercambiar elementos, intercambiar en TODOS los ArrayLists paralelos
   - Para ordenar descendente, cambiar el operador de comparación

3. **Ratio V/D:**
   - Fórmula: `ratio = victorias / derrotas`
   - Si derrotas = 0, ratio = victorias (convención)
   - Calcular como `double` para mantener decimales

4. **Asignación de rangos:**
   - Usar condicionales if-else o switch
   - Basarse en la puntuación del jugador

5. **Filtrado:**
   - Recorrer todos los jugadores
   - Mostrar solo aquellos cuyo rango coincida con el buscado

---

## 📝 Conceptos Técnicos Relevantes

### **Algoritmo de Ordenación Burbuja:**
Compara pares de elementos adyacentes e intercambia si están en orden incorrecto. Repite hasta que no haya más intercambios.

**Complejidad:** O(n²) - Aceptable para listas pequeñas

### **Criterios múltiples de ordenación:**
Diferentes atributos requieren diferentes comparaciones:
- Numérico descendente: `a > b`
- Numérico ascendente: `a < b`
- Alfabético: `a.compareTo(b)`

### **Ratio Victoria/Derrota:**
Métrica común en juegos competitivos. Indica cuántas victorias se tienen por cada derrota.
- Ratio > 1: Más victorias que derrotas
- Ratio < 1: Más derrotas que victorias
- Ratio = 1: Igual cantidad

---

## 🎯 Desafíos Opcionales

1. **Ordenación por múltiples criterios:** Si empatan en puntos, ordenar por nivel
2. **Búsqueda binaria:** Implementar búsqueda eficiente en lista ordenada
3. **Estadísticas:** Calcular promedio de puntos, mejor ratio, etc.
4. **Ranking por temporadas:** Agregar atributo "temporada" y filtrar
5. **Promoción/Degradación:** Subir/bajar de rango según resultados
6. **Comparación directa:** Comparar dos jugadores específicos

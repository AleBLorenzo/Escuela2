# 🎮 Ejercicio 17: Estadísticas Avanzadas

## 🎯 Objetivos de Aprendizaje
- Integrar múltiples **estructuras de datos** (ArrayList, HashMap, arrays)
- Realizar **análisis estadístico** de datos
- Implementar **métodos modulares** para procesar información
- Aplicar algoritmos de búsqueda de máximos, mínimos y promedios

---

## 📋 Descripción del Problema

Debes crear un programa que analice las estadísticas de rendimiento de jugadores en un videojuego competitivo. El sistema debe procesar datos de múltiples partidas y generar un informe completo con estadísticas individuales y globales.

Cada partida tiene:
- **Nombre del jugador**
- **Puntuación obtenida**
- **Duración de la partida** (en minutos)
- **Resultado**: "Victoria", "Derrota" o "Empate"

El programa debe:
1. Registrar múltiples partidas
2. Calcular estadísticas por jugador (promedio puntos, victorias, derrotas, empates)
3. Calcular estadísticas globales (mejor puntuación, peor, promedio general)
4. Identificar al jugador con mejor rendimiento
5. Generar un ranking de jugadores
6. Mostrar historial completo de partidas

---

## 📥 Entrada

El programa debe:
1. Permitir registrar N partidas
2. Para cada partida solicitar:
   - Nombre del jugador
   - Puntuación (0-1000)
   - Duración en minutos
   - Resultado (V/D/E)
3. Mostrar un menú con opciones de análisis

---

## 📤 Salida Esperada (orientativa)

```
=== SISTEMA DE ESTADÍSTICAS ===

1. Registrar nueva partida
2. Ver historial completo
3. Estadísticas por jugador
4. Estadísticas globales
5. Ranking de jugadores
6. Mejor/Peor partida
0. Salir

Opción: 1

-- REGISTRAR PARTIDA --
Nombre del jugador: DragonSlayer
Puntuación (0-1000): 875
Duración (minutos): 25
Resultado (V/D/E): V
✓ Partida registrada

Opción: 1

-- REGISTRAR PARTIDA --
Nombre del jugador: DragonSlayer
Puntuación (0-1000): 650
Duración (minutos): 30
Resultado (V/D/E): D
✓ Partida registrada

Opción: 1

-- REGISTRAR PARTIDA --
Nombre del jugador: MagicWizard
Puntuación (0-1000): 920
Duración (minutos): 28
Resultado (V/D/E): V
✓ Partida registrada

Opción: 3

Introduce el nombre del jugador: DragonSlayer

=== ESTADÍSTICAS: DragonSlayer ===
Total de partidas: 2
Puntuación promedio: 762.5
Puntuación máxima: 875
Puntuación mínima: 650

Resultados:
  Victorias: 1 (50%)
  Derrotas: 1 (50%)
  Empates: 0 (0%)

Duración promedio: 27.5 minutos

Opción: 4

=== ESTADÍSTICAS GLOBALES ===
Total de partidas registradas: 3
Jugadores únicos: 2

Puntuación más alta: 920 (MagicWizard)
Puntuación más baja: 650 (DragonSlayer)
Puntuación promedio global: 815.0

Distribución de resultados:
  Victorias: 2 (66.67%)
  Derrotas: 1 (33.33%)
  Empates: 0 (0%)

Duración promedio de partidas: 27.67 minutos

Opción: 5

=== RANKING DE JUGADORES ===
(Ordenado por puntuación promedio)

🥇 1. MagicWizard
   Promedio: 920.0 | Partidas: 1 | Victorias: 1

🥈 2. DragonSlayer
   Promedio: 762.5 | Partidas: 2 | Victorias: 1

Opción: 6

=== MEJORES Y PEORES PARTIDAS ===

🏆 MEJOR PARTIDA:
Jugador: MagicWizard
Puntuación: 920
Duración: 28 min
Resultado: Victoria

💀 PEOR PARTIDA:
Jugador: DragonSlayer
Puntuación: 650
Duración: 30 min
Resultado: Derrota
```

---

## 🧪 Casos de Prueba

### **Caso 1: Un jugador, múltiples partidas**
- DragonSlayer: 3 partidas (800, 750, 900)
- Promedio: 816.67
- Victoria más alta: 900

### **Caso 2: Múltiples jugadores**
- Player1: 2 partidas, promedio 700
- Player2: 3 partidas, promedio 850
- Ranking: Player2 primero

### **Caso 3: Jugador inexistente**
- Buscar estadísticas de "NoExiste"
- Debe indicar que no hay datos

### **Caso 4: Sistema vacío**
- Sin partidas registradas
- Debe indicar que no hay datos

---

## ✅ Criterios de Éxito

- [ ] Almacena correctamente todas las partidas
- [ ] Usa ArrayList para el historial de partidas
- [ ] Usa HashMap para agrupar datos por jugador
- [ ] Calcula correctamente promedios, máximos y mínimos
- [ ] Cuenta victorias, derrotas y empates por jugador
- [ ] Genera estadísticas globales correctas
- [ ] Ordena jugadores por promedio de puntuación
- [ ] Identifica mejor y peor partida
- [ ] Maneja casos especiales (sistema vacío, jugador inexistente)

---

## 💡 Pistas Generales

1. **Estructuras de datos:**
   - `ArrayList<String>` para nombres de jugadores en partidas
   - `ArrayList<Integer>` para puntuaciones
   - `ArrayList<Integer>` para duraciones
   - `ArrayList<String>` para resultados
   - O usar un solo ArrayList con Strings combinados

2. **Agrupar por jugador:**
   - Recorrer todas las partidas
   - Para cada jugador, recopilar sus partidas
   - Calcular estadísticas individuales

3. **Promedios:**
   - Sumar todos los valores
   - Dividir entre la cantidad
   - Convertir a double para decimales

4. **Máximos y mínimos:**
   - Inicializar con el primer valor
   - Comparar con el resto
   - Actualizar si encuentras mayor/menor

5. **Ordenar jugadores:**
   - Obtener lista de jugadores únicos
   - Calcular promedio de cada uno
   - Ordenar (algoritmo burbuja o similar)

---

## 📝 Conceptos Técnicos Relevantes

### **Estadísticas básicas:**
- **Media (promedio):** Suma de valores / cantidad
- **Máximo:** Mayor valor del conjunto
- **Mínimo:** Menor valor del conjunto
- **Porcentaje:** (parte / total) * 100

### **Obtener jugadores únicos:**
Recorrer todas las partidas y agregar jugadores a una lista solo si no están ya incluidos.

### **Métodos auxiliares recomendados:**
- `obtenerPartidasDeJugador(nombre)` → ArrayList con índices
- `calcularPromedio(valores)` → double
- `encontrarMaximo(valores)` → int
- `contarVictorias(jugador)` → int
- `obtenerJugadoresUnicos()` → ArrayList<String>

---

## 🎯 Desafíos Opcionales

1. **Racha de victorias:** Calcular la racha más larga de victorias consecutivas
2. **Gráfico ASCII:** Representar estadísticas con barras en modo texto
3. **Filtro por fecha:** Si se añade fecha, filtrar por periodo
4. **Exportar informe:** Generar un archivo de texto con todas las estadísticas
5. **Comparar jugadores:** Comparar directamente dos jugadores
6. **Mediana y moda:** Calcular estadísticas más avanzadas

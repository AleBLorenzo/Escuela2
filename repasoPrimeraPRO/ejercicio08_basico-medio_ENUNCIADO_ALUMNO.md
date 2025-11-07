# EJERCICIO 08 - Estadísticas de Partida
## Nivel: Básico-Medio

---

## 🎯 Objetivos de Aprendizaje
- Introducción a arrays unidimensionales
- Recorrer arrays con bucles
- Calcular estadísticas básicas (suma, promedio, máximo, mínimo)
- Buscar elementos en arrays

---

## 📝 Descripción del Problema

Estás desarrollando un sistema de análisis de rendimiento para un juego de disparos. El sistema registra los puntos obtenidos en cada ronda de una partida y luego calcula estadísticas completas del desempeño del jugador.

Debes crear un programa que:

1. **Solicite al usuario:**
   - Nombre del jugador
   - Número de rondas jugadas (entre 5 y 15)
   - Puntos obtenidos en cada ronda

2. **Almacene los puntos en un array**

3. **Calcule y muestre:**
   - Total de puntos acumulados
   - Promedio de puntos por ronda
   - Ronda con más puntos (mejor ronda)
   - Ronda con menos puntos (peor ronda)
   - Número de rondas por encima del promedio
   - Número de rondas por debajo del promedio

4. **Muestre un ranking de rondas:**
   - Listar las rondas ordenadas por puntos (de mayor a menor)

5. **Clasificación del jugador:**
   - Según su promedio de puntos

---

## 💡 Conceptos Clave a Aplicar

- **Declaración de array**: `int[] puntos = new int[tamaño];`
- **Acceso a elementos**: `puntos[i]`
- **Recorrido con for**: `for (int i = 0; i < puntos.length; i++)`
- **Operaciones estadísticas**: suma, promedio, máximo, mínimo
- **Búsqueda en arrays**: encontrar posición de máx/mín

---

## 🔍 Casos de Prueba

### Caso 1: Partida estándar
**Entrada:**
```
Nombre: ProShooter
Número de rondas: 5
Ronda 1: 150
Ronda 2: 200
Ronda 3: 175
Ronda 4: 220
Ronda 5: 190
```

**Salida esperada:**
```
=== ANÁLISIS DE RENDIMIENTO ===
Jugador: ProShooter
Rondas jugadas: 5

--- ESTADÍSTICAS GENERALES ---
Total de puntos: 935
Promedio por ronda: 187.0

--- MEJORES Y PEORES RONDAS ---
Mejor ronda: Ronda 4 con 220 puntos 🏆
Peor ronda: Ronda 1 con 150 puntos

--- ANÁLISIS COMPARATIVO ---
Rondas por encima del promedio: 3
Rondas por debajo del promedio: 2
Consistencia: 60.0% de rondas sobre el promedio

--- RANKING DE RONDAS ---
1º - Ronda 4: 220 puntos ⭐
2º - Ronda 2: 200 puntos
3º - Ronda 5: 190 puntos
4º - Ronda 3: 175 puntos
5º - Ronda 1: 150 puntos

--- CLASIFICACIÓN ---
Promedio: 187.0 puntos
Categoría: ¡EXPERTO! (150-200 puntos)
```

### Caso 2: Rendimiento irregular
**Entrada:**
```
Nombre: Newbie
Rondas: 7
Puntos: 50, 180, 75, 200, 60, 190, 85
```

**Resumen esperado:**
```
Total: 840
Promedio: 120.0
Mejor: Ronda 4 (200)
Peor: Ronda 1 (50)
Sobre promedio: 3 rondas
Bajo promedio: 4 rondas
```

---

## ✅ Criterios de Éxito

- ✓ Usar array para almacenar puntos
- ✓ Calcular correctamente suma y promedio
- ✓ Encontrar máximo y mínimo con sus posiciones
- ✓ Contar rondas sobre/bajo promedio
- ✓ Mostrar ranking ordenado
- ✓ Validar número de rondas (5-15)

---

## 🎮 Pistas Generales

### Crear y llenar el array:
```
Pedir número de rondas (n)
Crear array de tamaño n
Para cada posición i desde 0 hasta n-1:
    Pedir puntos de la ronda i+1
    Guardar en array[i]
```

### Calcular suma:
```
suma = 0
Para cada elemento en el array:
    suma = suma + elemento
```

### Encontrar máximo:
```
maximo = array[0]
posicionMaximo = 0
Para i desde 1 hasta final:
    Si array[i] > maximo:
        maximo = array[i]
        posicionMaximo = i
```

### Contar sobre promedio:
```
Calcular promedio primero
contador = 0
Para cada elemento:
    Si elemento > promedio:
        incrementar contador
```

---

## 📌 Notas Importantes

- **Índices empiezan en 0**: Primera ronda está en `puntos[0]`
- **Ronda vs Índice**: Ronda 1 = índice 0, Ronda 2 = índice 1
- **array.length**: Propiedad que da el tamaño del array
- **Promedio decimal**: Usar double para no perder decimales
- **Ranking**: Puedes mostrar en orden original o intentar ordenar (avanzado)

---

## 🎲 Desafíos Extra

1. **Ordenamiento**: Ordenar puntos de mayor a menor (bubble sort)
2. **Mediana**: Calcular la mediana de los puntos
3. **Desviación estándar**: Medir la variabilidad
4. **Gráfico ASCII**: Mostrar barra por cada ronda
5. **Rachas**: Detectar rachas de subida/bajada

---

**¡Buena suerte analizando esas estadísticas! 📊**

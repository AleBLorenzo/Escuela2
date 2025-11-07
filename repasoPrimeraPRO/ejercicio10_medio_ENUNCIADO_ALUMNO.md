# EJERCICIO 10 - Sistema de Ranking
## Nivel: Medio

## 🎯 Objetivos
- Ordenación de arrays
- Arrays paralelos avanzados
- Algoritmo de ordenamiento básico

## 📝 Descripción
Sistema de clasificación de jugadores:
- Registrar jugadores (nombre + puntos)
- Ordenar por puntos (mayor a menor)
- Mostrar podio (top 3)
- Búsqueda de jugador

**Máximo**: 15 jugadores

## Algoritmos a implementar
1. **Bubble Sort**: Ordenar por puntos
2. **Búsqueda**: Encontrar jugador
3. **Podio**: Mostrar top 3

## Ejemplo Bubble Sort
```
Inicial: [150, 300, 200, 180]
Nombres: [Ana, Luis, Eva, Juan]

Después ordenar:
Puntos:  [300, 200, 180, 150]
Nombres: [Luis, Eva, Juan, Ana]

Podio:
🥇 Luis: 300
🥈 Eva: 200
🥉 Juan: 180
```

## Pistas Bubble Sort
```
Para cada elemento excepto el último:
    Para cada par consecutivo:
        Si elemento[i] < elemento[i+1]:
            Intercambiar elementos[i] y [i+1]
            También intercambiar nombres[i] y [i+1]
```

## Conceptos
- Ordenamiento burbuja
- Intercambio de elementos
- Sincronización de arrays

**¡Crea tu ranking! 🏆**

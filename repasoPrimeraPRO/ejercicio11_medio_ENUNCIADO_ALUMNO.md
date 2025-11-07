# EJERCICIO 11 - Gestión de Equipo
## Nivel: Medio

---

## 🎯 Objetivos de Aprendizaje
- Trabajar con arrays paralelos (múltiples arrays relacionados)
- Realizar operaciones CRUD (Create, Read, Update, Delete) en estructuras de datos
- Implementar búsquedas y actualizaciones en arrays relacionados
- Gestionar índices sincronizados entre múltiples arrays

---

## 📝 Descripción del Problema

Estás desarrollando un sistema de gestión de equipos para un juego de rol multijugador (RPG). Cada jugador tiene varios atributos que deben almacenarse y gestionarse de forma coordinada.

Debes crear un programa que gestione un equipo de hasta **8 jugadores** utilizando arrays paralelos para almacenar:
- **Nombres** de los jugadores (String[])
- **Clases** (guerrero, mago, arquero, sanador) (String[])
- **Niveles** actuales (int[])
- **Puntos de vida** actuales (int[])
- **Estado** activo/inactivo (boolean[] - true=activo, false=inactivo)

---

## 🎮 Funcionalidades Requeridas

### Menú principal:
```
=== GESTIÓN DE EQUIPO RPG ===
1. Añadir jugador
2. Buscar jugador
3. Subir nivel a jugador
4. Aplicar daño a jugador
5. Curar jugador
6. Cambiar estado (activar/desactivar)
7. Mostrar equipo completo
8. Mostrar jugadores activos
9. Estadísticas del equipo
0. Salir
```

### 1. Añadir jugador
- Solicitar: nombre, clase, nivel inicial (1-100), vida inicial (1-1000)
- Estado inicial: activo (true)
- Verificar que hay espacio disponible
- No permitir jugadores con el mismo nombre

### 2. Buscar jugador
- Buscar por nombre (ignorando mayúsculas/minúsculas)
- Mostrar toda su información si existe
- Indicar si está activo o inactivo

### 3. Subir nivel
- Buscar jugador por nombre
- Incrementar su nivel en 1
- Aumentar vida máxima según clase:
  - Guerrero: +50 HP por nivel
  - Mago: +30 HP por nivel
  - Arquero: +35 HP por nivel
  - Sanador: +40 HP por nivel

### 4. Aplicar daño
- Buscar jugador por nombre
- Solicitar cantidad de daño
- Restar vida (no puede bajar de 0)
- Si vida llega a 0 → cambiar estado a inactivo

### 5. Curar jugador
- Buscar jugador por nombre
- Solicitar cantidad de curación
- Solo si está activo
- No superar vida máxima según su clase y nivel

### 6. Cambiar estado
- Buscar jugador por nombre
- Invertir su estado (activo ↔ inactivo)

### 7. Mostrar equipo completo
```
=== EQUIPO COMPLETO ===
1. Alex      | Guerrero | Nv.5  | 300/350 HP | ACTIVO
2. Luna      | Maga     | Nv.3  | 0/180 HP   | INACTIVO
3. Thor      | Arquero  | Nv.7  | 280/280 HP | ACTIVO
...
```

### 8. Mostrar jugadores activos
- Listar solo jugadores con estado = true
- Mismo formato que opción 7

### 9. Estadísticas del equipo
```
=== ESTADÍSTICAS ===
Total jugadores: 5
Jugadores activos: 3
Jugadores inactivos: 2
Nivel promedio: 4.6
Clase más común: Guerrero (2)
```

---

## 💡 Pistas Orientativas

- Los arrays paralelos comparten el mismo índice: si un jugador está en `nombres[3]`, sus datos están en `clases[3]`, `niveles[3]`, etc.
- Para buscar un jugador, recorre el array de nombres y cuando lo encuentres, usa ese mismo índice en los otros arrays
- Controla que siempre actualices TODOS los arrays relacionados cuando añadas o modifiques datos
- Para calcular vida máxima: `vidaMaxima = vidaInicial + (nivel - 1) * bonusPorClase`
- Usa un contador de jugadores para saber cuántas posiciones están ocupadas

---

## 🔍 Casos de Prueba

### Caso 1: Añadir jugador y subir nivel
**Acciones:**
```
1. Añadir: "Arthas", Guerrero, nivel 1, vida 200
2. Subir nivel a "Arthas"
3. Mostrar equipo
```

**Resultado esperado:**
```
Jugador añadido correctamente
Arthas subió a nivel 2! Vida máxima: 250 HP
---
1. Arthas | Guerrero | Nv.2 | 250/250 HP | ACTIVO
```

### Caso 2: Combate y curación
**Acciones:**
```
1. Añadir: "Merlin", Mago, nivel 3, vida 150
2. Aplicar 100 de daño a "Merlin"
3. Curar 30 a "Merlin"
4. Mostrar jugador "Merlin"
```

**Resultado esperado:**
```
Merlin recibió 100 de daño (150 → 50 HP)
Merlin se curó 30 HP (50 → 80 HP)
---
Merlin | Mago | Nv.3 | 80/210 HP | ACTIVO
```

### Caso 3: Muerte y reactivación
**Acciones:**
```
1. Aplicar 300 de daño a "Merlin" (tenía 80 HP)
2. Mostrar estado de "Merlin"
3. Cambiar estado de "Merlin"
```

**Resultado esperado:**
```
Merlin recibió 300 de daño (80 → 0 HP) - ¡JUGADOR CAÍDO!
---
Merlin | Mago | Nv.3 | 0/210 HP | INACTIVO
Estado cambiado: Merlin ahora está ACTIVO
```

---

## 📊 Criterios de Éxito

✅ Los arrays paralelos mantienen coherencia (mismo índice = mismo jugador)
✅ No se puede añadir más de 8 jugadores
✅ No se permiten nombres duplicados
✅ Las búsquedas funcionan ignorando mayúsculas
✅ Los cálculos de vida máxima son correctos según clase
✅ El estado inactivo se activa automáticamente al llegar a 0 HP
✅ Las estadísticas se calculan correctamente
✅ El menú funciona en bucle hasta seleccionar salir

---

## 📝 Notas Adicionales

- Este ejercicio combina búsquedas, actualizaciones y cálculos en arrays relacionados
- Es fundamental mantener la sincronización entre los 5 arrays en todo momento
- Representa un patrón común en videojuegos antes de usar estructuras más complejas

---

**Ejercicio 11/20** - Acceso a Datos DAM 2º

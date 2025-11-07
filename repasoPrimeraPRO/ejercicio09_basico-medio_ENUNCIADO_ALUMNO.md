# EJERCICIO 09 - Inventario Básico
## Nivel: Básico-Medio

## 🎯 Objetivos
- Arrays paralelos
- Búsqueda en arrays
- Operaciones CRUD básicas

## 📝 Descripción
Sistema de inventario RPG con:
- Array de nombres de objetos (String[])
- Array de cantidades (int[])
- Operaciones: añadir, buscar, usar, mostrar

**Capacidad**: Máximo 10 objetos

## Funcionalidades
1. Añadir objeto (si hay espacio)
2. Buscar objeto por nombre
3. Usar objeto (restar 1 cantidad, eliminar si llega a 0)
4. Mostrar inventario completo
5. Salir

## Ejemplo
```
Inventario: [Poción, Espada, Escudo, null, null...]
Cantidades: [5, 1, 1, 0, 0...]

Añadir "Flecha" x10 → [Poción, Espada, Escudo, Flecha, null...]
                       [5, 1, 1, 10, 0...]
                       
Usar "Poción" → [Poción, Espada, Escudo, Flecha, null...]
                [4, 1, 1, 10, 0...]
```

## Conceptos
- Arrays paralelos
- Búsqueda lineal
- Gestión de posiciones vacías

**¡Gestiona tu inventario! 🎒**

# 🎮 Ejercicio 15: Sistema de Logros

## 🎯 Objetivos de Aprendizaje
- Introducción a **ArrayList** como alternativa a arrays tradicionales
- Gestión dinámica de colecciones de datos
- Operaciones de búsqueda y filtrado en colecciones
- Trabajo con objetos de datos simples (Strings combinados)

---

## 📋 Descripción del Problema

Debes crear un programa que gestione un sistema de logros (achievements) de un videojuego. A diferencia de los ejercicios anteriores con arrays de tamaño fijo, este sistema debe permitir agregar logros dinámicamente sin conocer previamente cuántos habrá.

Un logro tiene la siguiente información:
- **Nombre** del logro
- **Descripción**
- **Puntos** que otorga
- **Estado**: "Desbloqueado" o "Bloqueado"

El programa debe permitir:
1. Agregar nuevos logros al sistema
2. Desbloquear un logro por su nombre
3. Mostrar todos los logros
4. Mostrar solo logros desbloqueados
5. Calcular puntos totales acumulados
6. Buscar un logro específico

---

## 📥 Entrada

El programa debe mostrar un menú con opciones:
```
1. Agregar nuevo logro
2. Desbloquear logro
3. Mostrar todos los logros
4. Mostrar logros desbloqueados
5. Ver puntos totales
6. Buscar logro
0. Salir
```

Para cada opción, solicita los datos necesarios.

---

## 📤 Salida Esperada (orientativa)

```
=== SISTEMA DE LOGROS ===

1. Agregar nuevo logro
2. Desbloquear logro
3. Mostrar todos los logros
4. Mostrar logros desbloqueados
5. Ver puntos totales
6. Buscar logro
0. Salir

Opción: 1

-- NUEVO LOGRO --
Nombre: Primera Victoria
Descripción: Gana tu primera partida
Puntos: 50
✓ Logro agregado al sistema

Opción: 1

-- NUEVO LOGRO --
Nombre: Maestro del Combo
Descripción: Realiza un combo de 10 golpes
Puntos: 100
✓ Logro agregado al sistema

Opción: 3

=== TODOS LOS LOGROS ===
[1] Primera Victoria - 50 pts [BLOQUEADO]
    Gana tu primera partida

[2] Maestro del Combo - 100 pts [BLOQUEADO]
    Realiza un combo de 10 golpes

Total: 2 logros

Opción: 2

Nombre del logro a desbloquear: Primera Victoria
🎉 ¡Logro "Primera Victoria" desbloqueado! +50 puntos

Opción: 4

=== LOGROS DESBLOQUEADOS ===
[1] Primera Victoria - 50 pts ✓
    Gana tu primera partida

Total desbloqueados: 1 de 2

Opción: 5

=== PUNTUACIÓN TOTAL ===
Puntos acumulados: 50
Logros desbloqueados: 1
Logros bloqueados: 1
Progreso: 50%

Opción: 0

¡Hasta luego!
```

---

## 🧪 Casos de Prueba

### **Caso 1: Agregar múltiples logros**
- Agregar 3 logros diferentes
- Verificar que se almacenan correctamente

### **Caso 2: Desbloquear logro existente**
- Desbloquear "Primera Victoria"
- Verificar que cambia su estado a desbloqueado

### **Caso 3: Desbloquear logro inexistente**
- Intentar desbloquear "Logro Falso"
- Debe indicar que no existe

### **Caso 4: Calcular puntos**
- Con 2 logros de 50 y 100 puntos
- Ambos desbloqueados
- Total: 150 puntos

### **Caso 5: Sistema vacío**
- Sin logros en el sistema
- Al mostrar, debe indicar que no hay logros

---

## ✅ Criterios de Éxito

- [ ] Usa ArrayList para almacenar logros dinámicamente
- [ ] Permite agregar logros sin límite predefinido
- [ ] Busca logros por nombre correctamente
- [ ] Cambia el estado de un logro a desbloqueado
- [ ] Muestra todos los logros con formato claro
- [ ] Filtra y muestra solo logros desbloqueados
- [ ] Calcula correctamente los puntos totales
- [ ] Maneja el caso de sistema vacío
- [ ] El menú funciona correctamente en bucle

---

## 💡 Pistas Generales

1. **ArrayList:**
   - Declara: `ArrayList<String> nombres = new ArrayList<>();`
   - Agregar: `nombres.add("elemento");`
   - Tamaño: `nombres.size()`
   - Acceder: `nombres.get(indice)`
   - Buscar: recorrer con bucle for

2. **Almacenamiento:**
   - Opción 1: Usar 4 ArrayLists paralelos (nombres, descripciones, puntos, estados)
   - Opción 2: Usar un ArrayList de Strings combinados (formato: "nombre|descripción|puntos|estado")

3. **Búsqueda:**
   - Recorrer el ArrayList buscando coincidencia de nombre
   - Retornar el índice o -1 si no se encuentra

4. **Estado:**
   - Almacenar como String: "bloqueado" o "desbloqueado"
   - O como boolean: `false` = bloqueado, `true` = desbloqueado

5. **Puntos totales:**
   - Recorrer todos los logros
   - Sumar puntos solo de los que estén desbloqueados

---

## 📝 Conceptos Técnicos Relevantes

### **ArrayList vs Array:**
- **Array:** Tamaño fijo definido al crear
- **ArrayList:** Tamaño dinámico, crece automáticamente

### **Operaciones básicas de ArrayList:**
- `.add(elemento)` - Agregar al final
- `.get(indice)` - Obtener elemento
- `.size()` - Cantidad de elementos
- `.remove(indice)` - Eliminar elemento
- `.contains(elemento)` - Verificar si existe

### **Importar ArrayList:**
```java
import java.util.ArrayList;
```

---

## 🎯 Desafíos Opcionales

1. **Eliminar logro:** Permitir eliminar un logro del sistema
2. **Ordenar:** Mostrar logros ordenados por puntos (mayor a menor)
3. **Categorías:** Agregar categorías a los logros (combate, exploración, social)
4. **Guardar progreso:** Mostrar porcentaje de logros desbloqueados
5. **Logros secretos:** Ocultar algunos logros hasta que se desbloqueen

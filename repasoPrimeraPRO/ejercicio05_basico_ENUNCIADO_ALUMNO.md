# EJERCICIO 05 - Menú de Tienda de Videojuegos
## Nivel: Básico

---

## 🎯 Objetivos de Aprendizaje
- Utilizar la estructura switch-case
- Implementar menús interactivos
- Trabajar con bucles while para repetición de menú
- Gestionar el flujo de control del programa

---

## 📝 Descripción del Problema

Estás desarrollando el sistema de menú para la tienda dentro de un videojuego de rol. Los jugadores pueden comprar diferentes tipos de objetos usando su oro acumulado. El menú debe ser interactivo, permitir múltiples compras y mantener actualizado el saldo del jugador.

Debes crear un programa que:

1. **Al iniciar, solicite:**
   - Nombre del jugador
   - Oro inicial disponible (número entero)

2. **Muestre un menú con las siguientes opciones:**
   ```
   === TIENDA DEL AVENTURERO ===
   
   1. Poción de Vida (50 oro)
   2. Poción de Maná (40 oro)
   3. Espada de Hierro (150 oro)
   4. Escudo de Madera (100 oro)
   5. Armadura de Cuero (200 oro)
   6. Ver inventario y saldo
   7. Salir de la tienda
   
   Tu oro actual: [cantidad]
   ¿Qué deseas comprar?
   ```

3. **Funcionalidad del programa:**
   - Permitir comprar mientras haya oro suficiente
   - Actualizar el oro después de cada compra
   - Llevar la cuenta de cuántos objetos de cada tipo se han comprado
   - Mostrar mensajes apropiados según la situación
   - Permitir ver el inventario en cualquier momento
   - Continuar mostrando el menú hasta que el usuario elija "Salir"

4. **Restricciones:**
   - No se puede comprar si no hay oro suficiente
   - No se permiten valores de oro negativos
   - Cada compra debe confirmarse con un mensaje
   - Al salir, mostrar resumen de compras

---

## 💡 Conceptos Clave a Aplicar

- **Switch-case**: para manejar las opciones del menú
- **While loop**: para mantener el menú activo
- **Variables contadoras**: para llevar cuenta de objetos comprados
- **Condicionales**: para verificar si hay oro suficiente
- **Break**: para salir del switch y del bucle
- **Default**: para opciones inválidas en el switch

---

## 🔍 Casos de Prueba

### Caso 1: Compra exitosa
**Interacción:**
```
Nombre: Arthas
Oro inicial: 500

=== TIENDA DEL AVENTURERO ===
[muestra menú]
Tu oro actual: 500
¿Qué deseas comprar? 1

✓ Has comprado: Poción de Vida
Precio: 50 oro
Oro restante: 450

[vuelve a mostrar menú]
Tu oro actual: 450
¿Qué deseas comprar? 3

✓ Has comprado: Espada de Hierro
Precio: 150 oro
Oro restante: 300

[menú continúa...]
```

### Caso 2: Oro insuficiente
**Interacción:**
```
Tu oro actual: 80
¿Qué deseas comprar? 3

✗ ¡Oro insuficiente!
Necesitas: 150 oro
Tienes: 80 oro
Te faltan: 70 oro

[vuelve al menú sin cambios en el oro]
```

### Caso 3: Ver inventario
**Interacción:**
```
¿Qué deseas comprar? 6

=== TU INVENTARIO ===
Pociones de Vida: 2
Pociones de Maná: 1
Espadas de Hierro: 1
Escudos de Madera: 0
Armaduras de Cuero: 0

Oro gastado: 190
Oro restante: 310
```

### Caso 4: Opción inválida
**Interacción:**
```
¿Qué deseas comprar? 9

✗ Opción no válida. Por favor, elige una opción entre 1 y 7.

[vuelve al menú]
```

### Caso 5: Salir del programa
**Interacción:**
```
¿Qué deseas comprar? 7

=== RESUMEN DE COMPRA ===
¡Gracias por tu visita, Arthas!

Compras realizadas:
- Pociones de Vida: 3 (150 oro)
- Pociones de Maná: 2 (80 oro)
- Espadas de Hierro: 1 (150 oro)
-----------------------------
Total gastado: 380 oro
Oro inicial: 500 oro
Oro restante: 120 oro

¡Buena suerte en tus aventuras!
```

---

## ✅ Criterios de Éxito

Tu programa debe:
- ✓ Mostrar el menú correctamente con todas las opciones
- ✓ Usar switch-case para manejar las opciones
- ✓ Mantener el menú activo con un bucle while
- ✓ Verificar oro suficiente antes de permitir compra
- ✓ Actualizar el oro después de cada compra
- ✓ Contar cuántos objetos de cada tipo se compran
- ✓ Mostrar inventario cuando se solicite
- ✓ Manejar opciones inválidas con el caso default
- ✓ Salir correctamente y mostrar resumen final

---

## 🎮 Pistas Generales

### Estructura básica con while y switch:
```
inicializar variables (oro, contadores, opción)
inicializar oroInicial para calcular gasto total

mientras (opción != 7):
    mostrar menú con oro actual
    leer opción del usuario
    
    según opción:
        caso 1: intentar comprar poción vida
        caso 2: intentar comprar poción maná
        caso 3: intentar comprar espada
        caso 4: intentar comprar escudo
        caso 5: intentar comprar armadura
        caso 6: mostrar inventario
        caso 7: salir (se terminará el while)
        otro caso: opción inválida

mostrar resumen final
```

### Para verificar compra:
```
si oro >= precio:
    restar precio del oro
    incrementar contador del objeto
    mostrar mensaje de éxito
sino:
    mostrar mensaje de oro insuficiente
    mostrar cuánto falta
```

### Variables necesarias:
- `oro` (int): oro actual del jugador
- `oroInicial` (int): oro inicial para calcular gasto
- `opcion` (int): opción elegida en el menú
- `contadorPocionVida` (int): cuántas pociones de vida compradas
- ... (un contador por cada tipo de objeto)

---

## 📌 Notas Importantes

- **Switch vs If-else**: Este ejercicio requiere específicamente switch-case
- **Bucle infinito**: El while debe seguir hasta que el usuario elija opción 7
- **Condición del while**: Puede ser `while (opcion != 7)` o `while (true)` con break
- **Default en switch**: SIEMPRE incluir caso default para opciones inválidas
- **Break en switch**: No olvides el break después de cada caso (excepto en fall-through intencional)
- **Actualizar oro**: El oro se actualiza SOLO si la compra es exitosa

---

## 🤔 Preguntas de Reflexión

Antes de empezar:
1. ¿Cuántas variables contador necesitas?
2. ¿Qué condición usarás en el while para mantener el menú activo?
3. ¿En qué momento verificas si hay oro suficiente?
4. ¿Qué pasa si el usuario escribe una opción inválida?
5. ¿Cómo calcularás el total gastado?

---

## 🎲 Desafíos Extra (Opcional)

Para los que terminen antes:

1. **Descuentos**: Si compras 3+ del mismo objeto, aplica 10% descuento
2. **Ofertas especiales**: Cada 5ta compra (de cualquier cosa) es gratis
3. **Límite de peso**: Cada objeto pesa, máximo 100 kg de carga
4. **Sistema de niveles**: Algunos objetos requieren nivel mínimo del jugador
5. **Vender objetos**: Opción para vender objetos a 70% del precio original

---

## 💭 Ejemplo de Flujo de Ejecución

```
[Inicio del programa]
Nombre del jugador: Link
Oro inicial: 300

[Primera iteración del while]
=== TIENDA ===
1. Poción Vida (50)
...
Tu oro actual: 300
Opción: 1
✓ Comprado: Poción de Vida
Oro restante: 250

[Segunda iteración del while]
=== TIENDA ===
Tu oro actual: 250
Opción: 5
✓ Comprado: Armadura de Cuero
Oro restante: 50

[Tercera iteración]
Tu oro actual: 50
Opción: 3
✗ Oro insuficiente (necesitas 150, tienes 50)

[Cuarta iteración]
Opción: 6
[Muestra inventario]

[Quinta iteración]
Opción: 7
[Sale del while, muestra resumen]
```

---

**¡Buena suerte con tu tienda! Que tus ventas sean prósperas. 🛒**

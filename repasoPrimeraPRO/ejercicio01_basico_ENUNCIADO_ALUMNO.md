# EJERCICIO 01 - Sistema de Puntuación
## Nivel: Básico

---

## 🎯 Objetivos de Aprendizaje
- Trabajar con variables de diferentes tipos (enteros, decimales, cadenas)
- Utilizar operadores aritméticos básicos
- Realizar entrada y salida de datos por consola
- Aplicar conversiones de tipo de datos

---

## 📝 Descripción del Problema

Estás desarrollando un sistema básico de puntuación para un videojuego arcade. El sistema debe solicitar al usuario información sobre una partida y calcular la puntuación final aplicando diferentes bonificaciones.

Debes crear un programa que:

1. **Solicite los siguientes datos al jugador:**
   - Nombre del jugador
   - Puntuación base obtenida (número entero)
   - Número de enemigos eliminados (número entero)
   - Tiempo empleado en segundos (número entero)
   - Si completó el nivel (respuesta: SI o NO)

2. **Calcule la puntuación final** aplicando las siguientes reglas:
   - Puntuación base introducida por el usuario
   - Bonificación por enemigos: +50 puntos por cada enemigo eliminado
   - Bonificación por tiempo: Si tardó menos de 120 segundos → +200 puntos
   - Bonificación por completar nivel: +500 puntos adicionales

3. **Muestre por pantalla:**
   - Un mensaje de bienvenida con el nombre del jugador
   - Resumen de estadísticas (base, enemigos, tiempo)
   - Puntuación final calculada
   - Un mensaje de felicitación personalizado

---

## 💡 Conceptos Clave a Aplicar

- **Variables**: necesitarás almacenar diferentes tipos de datos
- **Entrada de datos**: captura información del teclado
- **Operadores aritméticos**: suma, multiplicación
- **Operadores de comparación**: para verificar condiciones
- **Salida formateada**: presentar los resultados de manera clara

---

## 🔍 Casos de Prueba

### Caso 1: Jugador rápido que completa el nivel
**Entrada:**
```
Nombre: Alex
Puntuación base: 1000
Enemigos eliminados: 15
Tiempo: 90 segundos
Completó nivel: SI
```

**Salida esperada (orientativa):**
```
¡Bienvenido Alex!
===================
Puntuación base: 1000
Bonus por enemigos (15): 750
Bonus por velocidad: 200
Bonus por completar: 500
-------------------
PUNTUACIÓN FINAL: 2450
¡Excelente trabajo!
```

### Caso 2: Jugador lento que no completa
**Entrada:**
```
Nombre: Jordan
Puntuación base: 800
Enemigos eliminados: 8
Tiempo: 200 segundos
Completó nivel: NO
```

**Salida esperada (orientativa):**
```
¡Bienvenido Jordan!
===================
Puntuación base: 800
Bonus por enemigos (8): 400
Bonus por velocidad: 0
Bonus por completar: 0
-------------------
PUNTUACIÓN FINAL: 1200
¡Sigue intentándolo!
```

---

## ✅ Criterios de Éxito

Tu programa debe:
- ✓ Solicitar correctamente todos los datos de entrada
- ✓ Calcular correctamente la puntuación final
- ✓ Aplicar las bonificaciones según las condiciones especificadas
- ✓ Mostrar los resultados de forma clara y legible
- ✓ Utilizar nombres de variables descriptivos
- ✓ Incluir comentarios que expliquen las secciones principales

---

## 🎮 Pistas Generales

- Piensa qué tipo de variable es más adecuado para cada dato
- Para leer respuestas como "SI/NO" considera cómo compararlas
- Puedes usar operadores lógicos para evaluar condiciones
- Organiza tu código en secciones: entrada → cálculo → salida
- Recuerda que las bonificaciones se SUMAN a la puntuación base

---

## 📌 Notas Importantes

- El formato exacto de la salida puede variar según tu estilo
- Lo importante es que se muestren todos los datos relevantes
- Presta atención a la lógica de las condiciones para las bonificaciones
- Asegúrate de que tu programa sea fácil de entender y mantener

---

**¡Buena suerte y que disfrutes programando tu primer sistema de puntuación! 🎯**

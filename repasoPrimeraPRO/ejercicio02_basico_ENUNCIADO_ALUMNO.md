# EJERCICIO 02 - Verificador de Edad
## Nivel: Básico

---

## 🎯 Objetivos de Aprendizaje
- Utilizar estructuras condicionales (if-else if-else)
- Aplicar operadores relacionales y lógicos
- Trabajar con múltiples condiciones
- Generar salidas diferentes según el caso

---

## 📝 Descripción del Problema

Estás desarrollando un sistema de control de acceso para un videojuego online que tiene diferentes modos de juego con restricciones de edad. El sistema debe verificar la edad del usuario y determinar a qué modos puede acceder.

Debes crear un programa que:

1. **Solicite los siguientes datos:**
   - Nombre del usuario
   - Edad (número entero)
   - ¿Es suscriptor premium? (respuesta: SI o NO)

2. **Determine el acceso según estas reglas:**
   
   **Menores de 12 años:**
   - Acceso solo a modo "Infantil"
   - Mensaje: contenido adaptado para niños
   
   **Entre 12 y 15 años (inclusivo):**
   - Acceso a modos "Infantil" y "Adolescente"
   - Mensaje: algunos contenidos pueden requerir supervisión
   
   **Entre 16 y 17 años:**
   - Acceso a modos "Infantil", "Adolescente" y "Joven"
   - Mensaje: acceso a contenido con violencia moderada
   
   **18 años o más:**
   - Acceso a TODOS los modos (incluyendo "Adulto")
   - **Si es premium:** mensaje especial de bienvenida y acceso prioritario
   - **Si no es premium:** mensaje estándar

3. **Muestre por pantalla:**
   - Saludo personalizado con el nombre
   - Categoría de edad asignada
   - Lista de modos de juego disponibles
   - Mensaje informativo correspondiente
   - Indicación si tiene beneficios premium (solo para mayores de 18)

---

## 💡 Conceptos Clave a Aplicar

- **Condicionales anidados o encadenados**: if, else if, else
- **Operadores relacionales**: <, >, <=, >=, ==
- **Operadores lógicos**: && (AND), || (OR)
- **Comparación de rangos**: verificar si un valor está entre dos límites
- **Comparación de Strings**: para verificar respuesta SI/NO

---

## 🔍 Casos de Prueba

### Caso 1: Usuario infantil
**Entrada:**
```
Nombre: Lucas
Edad: 8
Premium: NO
```

**Salida esperada (orientativa):**
```
¡Hola Lucas!
==================
Categoría: INFANTIL
Edad: 8 años

Modos disponibles:
- Modo Infantil

NOTA: Contenido adaptado y seguro para niños.
¡Diviértete jugando!
```

### Caso 2: Usuario adolescente
**Entrada:**
```
Nombre: María
Edad: 14
Premium: SI
```

**Salida esperada (orientativa):**
```
¡Hola María!
==================
Categoría: ADOLESCENTE
Edad: 14 años

Modos disponibles:
- Modo Infantil
- Modo Adolescente

NOTA: Algunos contenidos pueden requerir supervisión parental.
¡Disfruta del juego!
```

### Caso 3: Usuario joven
**Entrada:**
```
Nombre: Carlos
Edad: 17
Premium: NO
```

**Salida esperada (orientativa):**
```
¡Hola Carlos!
==================
Categoría: JOVEN
Edad: 17 años

Modos disponibles:
- Modo Infantil
- Modo Adolescente
- Modo Joven

NOTA: Acceso a contenido con violencia moderada.
¡Buena suerte!
```

### Caso 4: Usuario adulto premium
**Entrada:**
```
Nombre: Ana
Edad: 25
Premium: SI
```

**Salida esperada (orientativa):**
```
¡Hola Ana!
==================
Categoría: ADULTO
Edad: 25 años

Modos disponibles:
- Modo Infantil
- Modo Adolescente
- Modo Joven
- Modo Adulto

⭐ USUARIO PREMIUM ⭐
Acceso prioritario a servidores
Sin publicidad

NOTA: Acceso completo a todo el contenido del juego.
¡Bienvenido de nuevo!
```

### Caso 5: Usuario adulto estándar
**Entrada:**
```
Nombre: Pedro
Edad: 20
Premium: NO
```

**Salida esperada (orientativa):**
```
¡Hola Pedro!
==================
Categoría: ADULTO
Edad: 20 años

Modos disponibles:
- Modo Infantil
- Modo Adolescente
- Modo Joven
- Modo Adulto

NOTA: Acceso completo a todo el contenido del juego.
¡Disfruta tu experiencia!
```

---

## ✅ Criterios de Éxito

Tu programa debe:
- ✓ Clasificar correctamente según la edad en las 4 categorías
- ✓ Mostrar los modos de juego correspondientes a cada categoría
- ✓ Diferenciar entre usuarios premium y no premium (solo adultos)
- ✓ Generar mensajes apropiados para cada caso
- ✓ Utilizar estructuras if-else if-else correctamente
- ✓ Manejar correctamente los límites de edad (12, 15, 17, 18)

---

## 🎮 Pistas Generales

- **Rangos de edad**: Piensa en cómo verificar si un número está entre dos valores
  - Por ejemplo: `edad >= 12 && edad <= 15` verifica el rango de 12 a 15
  
- **Orden de evaluación**: El orden de tus condiciones if-else if es importante
  - ¿Qué pasa si evalúas primero la edad más alta o la más baja?
  
- **Casos mutuamente excluyentes**: Una edad solo puede pertenecer a UNA categoría
  - Usa if-else if-else, no varios if independientes
  
- **Condición premium**: Solo aplica para mayores de 18 años
  - Necesitas una condición dentro de otra (anidada)
  
- **Comparar Strings**: Recuerda usar `.equals()` o `.equalsIgnoreCase()`

---

## 📌 Notas Importantes

- Presta especial atención a los límites: ¿12 años es infantil o adolescente?
- El estado premium solo afecta a usuarios adultos (≥18 años)
- Cada categoría tiene acceso acumulativo (los mayores acceden a todo lo anterior)
- El formato de salida puede variar, pero debe mostrar toda la información relevante
- Piensa en la estructura más clara y mantenible para las condiciones

---

## 🤔 Reflexión

Antes de empezar a programar, responde mentalmente:
1. ¿Cuántas categorías de edad hay?
2. ¿En qué orden deberías evaluar las condiciones de edad?
3. ¿Cómo verificas un rango de edad (por ejemplo, entre 12 y 15)?
4. ¿Dónde colocarías la verificación de premium?

---

**¡Suerte con tu verificador de edad! Recuerda probar todos los casos posibles. 🎮**

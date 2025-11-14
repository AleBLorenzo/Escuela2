# EJERCICIO 04 - Validador de Contraseña
## Nivel: Básico

---

## 🎯 Objetivos de Aprendizaje
- Manipular cadenas de texto (String)
- Utilizar métodos de la clase String
- Aplicar bucles para recorrer caracteres
- Implementar validaciones con múltiples condiciones

---

## 📝 Descripción del Problema

Estás desarrollando un sistema de validación de contraseñas para un videojuego online. El sistema debe verificar que una contraseña cumple con ciertos requisitos de seguridad y proporcionar feedback específico al usuario sobre qué criterios cumple o no cumple.

Debes crear un programa que:

1. **Solicite al usuario:**
   - Nombre de usuario (String)
   - Contraseña propuesta (String)
   - Confirmación de contraseña (String)

2. **Valide que la contraseña cumple TODOS estos requisitos:**
   
   ✓ **Longitud mínima**: Mínimo 8 caracteres  
   ✓ **Mayúsculas**: Al menos 1 letra mayúscula (A-Z)  
   ✓ **Minúsculas**: Al menos 1 letra minúscula (a-z)  
   ✓ **Números**: Al menos 1 dígito (0-9)  
   ✓ **Coincidencia**: Las dos contraseñas deben ser idénticas  
   ✓ **No contiene el nombre**: La contraseña no debe contener el nombre de usuario

3. **Muestre por pantalla:**
   - Lista de verificación mostrando qué criterios cumple (✓) y cuáles no (✗)
   - Nivel de seguridad: Débil / Media / Fuerte
   - Mensaje final: "Contraseña aceptada" o "Contraseña rechazada"
   - Sugerencias para mejorar la contraseña si es rechazada

4. **Clasificación de nivel de seguridad:**
   - **Débil**: Cumple requisitos mínimos (los 6 criterios básicos)
   - **Media**: Además tiene 10+ caracteres
   - **Fuerte**: Además tiene 12+ caracteres Y contiene caracteres especiales (!@#$%&*)

---

## 💡 Conceptos Clave a Aplicar

- **Métodos de String**: length(), charAt(), contains(), toLowerCase(), toUpperCase()
- **Bucles**: for o while para recorrer caracteres
- **Condiciones de caracteres**: Character.isUpperCase(), Character.isLowerCase(), Character.isDigit()
- **Contadores**: variables para contar mayúsculas, minúsculas, números
- **Comparación de Strings**: equals() para verificar coincidencia
- **Variables boolean**: flags para marcar cumplimiento de criterios

---

## 🔍 Casos de Prueba

### Caso 1: Contraseña débil pero válida
**Entrada:**
```
Nombre de usuario: player1
Contraseña: Gaming123
Confirmación: Gaming123
```

**Salida esperada:**
```
=== VALIDACIÓN DE CONTRASEÑA ===

✓ Longitud mínima (8 caracteres): SÍ [9 caracteres]
✓ Contiene mayúsculas: SÍ [G]
✓ Contiene minúsculas: SÍ [a, m, i, n, g]
✓ Contiene números: SÍ [1, 2, 3]
✓ Contraseñas coinciden: SÍ
✓ No contiene nombre de usuario: SÍ

Nivel de seguridad: DÉBIL
Estado: ✓ CONTRASEÑA ACEPTADA

Nota: Considera usar más de 10 caracteres para mayor seguridad.
```

### Caso 2: Contraseña media
**Entrada:**
```
Nombre de usuario: gamer
Contraseña: SuperGamer2024
Confirmación: SuperGamer2024
```

**Salida esperada:**
```
=== VALIDACIÓN DE CONTRASEÑA ===

✓ Longitud mínima (8 caracteres): SÍ [14 caracteres]
✓ Contiene mayúsculas: SÍ [S, G]
✓ Contiene minúsculas: SÍ [u, p, e, r...]
✓ Contiene números: SÍ [2, 0, 2, 4]
✓ Contraseñas coinciden: SÍ
✓ No contiene nombre de usuario: SÍ

Nivel de seguridad: MEDIA
Estado: ✓ CONTRASEÑA ACEPTADA

Sugerencia: Añade caracteres especiales (!@#$%&*) para hacerla más fuerte.
```

### Caso 3: Contraseña fuerte
**Entrada:**
```
Nombre de usuario: pro
Contraseña: UltraS3cur3!@
Confirmación: UltraS3cur3!@
```

**Salida esperada:**
```
=== VALIDACIÓN DE CONTRASEÑA ===

✓ Longitud mínima (8 caracteres): SÍ [14 caracteres]
✓ Contiene mayúsculas: SÍ [U, S]
✓ Contiene minúsculas: SÍ [l, t, r, a...]
✓ Contiene números: SÍ [3, 3]
✓ Contraseñas coinciden: SÍ
✓ No contiene nombre de usuario: SÍ

Nivel de seguridad: FUERTE
Estado: ✓ CONTRASEÑA ACEPTADA

¡Excelente! Tu contraseña es muy segura.
```

### Caso 4: Contraseña rechazada (múltiples problemas)
**Entrada:**
```
Nombre de usuario: test
Contraseña: test123
Confirmación: test123
```

**Salida esperada:**
```
=== VALIDACIÓN DE CONTRASEÑA ===

✓ Longitud mínima (8 caracteres): SÍ [7 caracteres... ¡ESPERA! ✗ NO]
✗ Longitud mínima (8 caracteres): NO [solo 7 caracteres]
✗ Contiene mayúsculas: NO
✓ Contiene minúsculas: SÍ
✓ Contiene números: SÍ
✓ Contraseñas coinciden: SÍ
✗ No contiene nombre de usuario: NO [contiene "test"]

Nivel de seguridad: N/A
Estado: ✗ CONTRASEÑA RECHAZADA

Problemas encontrados:
- Debe tener al menos 8 caracteres
- Debe contener al menos una mayúscula
- No debe contener tu nombre de usuario

Por favor, crea una nueva contraseña que cumpla todos los requisitos.
```

### Caso 5: Contraseñas no coinciden
**Entrada:**
```
Nombre de usuario: user
Contraseña: MyPass123
Confirmación: MyPass124
```

**Salida esperada:**
```
=== VALIDACIÓN DE CONTRASEÑA ===

✓ Longitud mínima (8 caracteres): SÍ [9 caracteres]
✓ Contiene mayúsculas: SÍ
✓ Contiene minúsculas: SÍ
✓ Contiene números: SÍ
✗ Contraseñas coinciden: NO
✓ No contiene nombre de usuario: SÍ

Nivel de seguridad: N/A
Estado: ✗ CONTRASEÑA RECHAZADA

Error: Las contraseñas no coinciden. Por favor, inténtalo de nuevo.
```

---

## ✅ Criterios de Éxito

Tu programa debe:
- ✓ Validar correctamente los 6 criterios obligatorios
- ✓ Recorrer la contraseña carácter por carácter
- ✓ Contar mayúsculas, minúsculas y números correctamente
- ✓ Detectar si el nombre de usuario está en la contraseña
- ✓ Clasificar el nivel de seguridad correctamente
- ✓ Mostrar feedback claro y específico
- ✓ Listar los problemas encontrados si la contraseña es rechazada

---

## 🎮 Pistas Generales

### Para recorrer una cadena carácter por carácter:
```
Para cada carácter desde posición 0 hasta longitud-1:
    obtener el carácter en esa posición
    verificar si es mayúscula/minúscula/número
    incrementar contador correspondiente
```

### Para verificar tipos de caracteres:
- Mayúscula: el carácter está entre 'A' y 'Z'
- Minúscula: el carácter está entre 'a' y 'z'
- Número: el carácter está entre '0' y '9'

También puedes usar métodos de Java:
- `Character.isUpperCase(caracter)`
- `Character.isLowerCase(caracter)`
- `Character.isDigit(caracter)`

### Para verificar si contiene el nombre:
- Convierte ambos a minúsculas para comparar
- Usa el método `.contains()` de String

### Para detectar caracteres especiales:
- Define una cadena con los caracteres especiales permitidos
- Verifica si algún carácter de la contraseña está en esa cadena

---

## 📌 Notas Importantes

- **Sensibilidad a mayúsculas**: "Gaming123" ≠ "gaming123"
- **Contiene nombre**: Buscar ignorando mayúsculas/minúsculas
  - Ejemplo: "player1" no debe estar en "MyPlayer123" → ¡Rechazar!
- **Caracteres especiales**: Solo para nivel FUERTE: !@#$%&*
- **Todos los criterios obligatorios**: Si falla UNO, se rechaza TODA la contraseña
- **Formato de salida**: Debe ser claro y visual (uso de ✓ y ✗)

---

## 🤔 Preguntas de Reflexión

Antes de empezar:
1. ¿Cómo obtengo cada carácter individual de una cadena?
2. ¿Cómo cuento cuántos caracteres cumplen cierta condición?
3. ¿Cómo verifico si una cadena está contenida dentro de otra?
4. ¿Necesito variables boolean para marcar cada criterio?
5. ¿En qué orden debo hacer las validaciones?

---

## 🎲 Desafíos Extra (Opcional)

Para los que terminen antes:
1. **Barra de fortaleza visual**: Mostrar `[####------]` según nivel
2. **Sugerencia de contraseña**: Generar una contraseña segura automáticamente
3. **Colores**: Usar códigos ANSI para mostrar en verde (✓) y rojo (✗)
4. **Contador de intentos**: Permitir 3 intentos antes de bloquear

---

**¡Suerte validando esas contraseñas! La seguridad es fundamental. 🔒**

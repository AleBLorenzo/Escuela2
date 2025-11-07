# 🎮 Ejercicio 16: Tienda con Carrito

## 🎯 Objetivos de Aprendizaje
- Trabajar con **HashMap** para relacionar claves y valores
- Gestionar colecciones con pares clave-valor
- Operaciones CRUD con estructuras de datos complejas
- Aplicar lógica de negocio (cálculos de precios, descuentos)

---

## 📋 Descripción del Problema

Debes crear un programa que simule una tienda de videojuegos con un sistema de carrito de compras. El sistema debe gestionar un catálogo de productos y permitir al usuario agregar/eliminar productos de su carrito.

La tienda maneja:
- **Catálogo de productos**: HashMap con nombre del producto como clave y precio como valor
- **Carrito de compras**: HashMap con nombre del producto como clave y cantidad como valor

El programa debe permitir:
1. Ver catálogo completo de productos
2. Agregar producto al carrito (especificar cantidad)
3. Eliminar producto del carrito
4. Ver contenido del carrito
5. Calcular total de la compra
6. Aplicar descuento por código promocional
7. Finalizar compra

---

## 📥 Entrada

El programa debe:
1. Inicializar un catálogo con al menos 8 productos (videojuegos) y sus precios
2. Mostrar un menú con opciones de compra
3. Solicitar nombre de producto y cantidad al agregar al carrito
4. Solicitar código promocional opcional

---

## 📤 Salida Esperada (orientativa)

```
=== TIENDA DE VIDEOJUEGOS ===

1. Ver catálogo
2. Agregar al carrito
3. Eliminar del carrito
4. Ver carrito
5. Calcular total
6. Aplicar descuento
7. Finalizar compra
0. Salir

Opción: 1

=== CATÁLOGO DE PRODUCTOS ===
The Legend of Zelda: BotW - 59.99€
Elden Ring - 49.99€
God of War Ragnarok - 69.99€
Hollow Knight - 14.99€
Hades - 24.99€
Celeste - 19.99€
Stardew Valley - 13.99€
Minecraft - 26.95€

Total productos: 8

Opción: 2

Nombre del producto: Elden Ring
Cantidad: 2
✓ Agregado: 2x Elden Ring al carrito

Opción: 2

Nombre del producto: Hollow Knight
Cantidad: 1
✓ Agregado: 1x Hollow Knight al carrito

Opción: 4

=== MI CARRITO ===
Elden Ring x2 - 49.99€ c/u = 99.98€
Hollow Knight x1 - 14.99€ c/u = 14.99€
────────────────────────────
Subtotal: 114.97€

Opción: 6

Introduce código promocional: GAME10
✓ Código válido: 10% de descuento aplicado

Opción: 5

=== RESUMEN DE COMPRA ===
Subtotal: 114.97€
Descuento (10%): -11.50€
────────────────────────────
TOTAL A PAGAR: 103.47€

Opción: 7

=== COMPRA FINALIZADA ===
Total pagado: 103.47€
Productos: 3 artículos
¡Gracias por tu compra!

Carrito vaciado.
```

---

## 🧪 Casos de Prueba

### **Caso 1: Agregar productos al carrito**
- Agregar "Elden Ring" x2
- Agregar "Minecraft" x1
- Carrito debe tener 2 productos diferentes

### **Caso 2: Agregar el mismo producto varias veces**
- Agregar "Hades" x1
- Agregar "Hades" x2 más
- Cantidad total de "Hades": 3

### **Caso 3: Producto no existente**
- Intentar agregar "Cyberpunk 3077" (no existe)
- Debe indicar que el producto no está en el catálogo

### **Caso 4: Eliminar del carrito**
- Agregar varios productos
- Eliminar "Hollow Knight"
- Verificar que ya no esté en el carrito

### **Caso 5: Descuento**
- Subtotal: 100€
- Código "GAME10" (10% descuento)
- Total: 90€

### **Caso 6: Código inválido**
- Código "FAKE"
- Debe rechazar y no aplicar descuento

---

## ✅ Criterios de Éxito

- [ ] Usa HashMap para el catálogo (producto → precio)
- [ ] Usa HashMap para el carrito (producto → cantidad)
- [ ] Inicializa el catálogo con al menos 8 productos
- [ ] Permite agregar productos verificando que existan en el catálogo
- [ ] Si un producto ya está en el carrito, suma la cantidad
- [ ] Permite eliminar productos del carrito
- [ ] Calcula correctamente el subtotal
- [ ] Aplica descuentos correctamente
- [ ] Valida códigos promocionales
- [ ] Finaliza compra y vacía el carrito

---

## 💡 Pistas Generales

1. **HashMap básico:**
   - Declarar: `HashMap<String, Double> catalogo = new HashMap<>();`
   - Agregar: `catalogo.put("producto", 59.99);`
   - Obtener valor: `double precio = catalogo.get("producto");`
   - Verificar existencia: `catalogo.containsKey("producto")`

2. **Recorrer HashMap:**
   ```
   for (String producto : catalogo.keySet()) {
       double precio = catalogo.get(producto);
       // usar producto y precio
   }
   ```

3. **Actualizar cantidad en carrito:**
   - Si el producto ya existe, obtener cantidad actual y sumar
   - Si no existe, agregarlo con la cantidad especificada

4. **Calcular subtotal:**
   - Recorrer carrito
   - Para cada producto: cantidad * precio_del_catálogo
   - Sumar todos los totales

5. **Códigos promocionales:**
   - Usar otro HashMap: `HashMap<String, Integer> codigos`
   - Clave: código, Valor: porcentaje de descuento

---

## 📝 Conceptos Técnicos Relevantes

### **HashMap:**
Estructura de datos que almacena pares clave-valor. Permite acceso rápido a valores mediante su clave.

**Características:**
- No permite claves duplicadas (si insertas la misma clave, sobrescribe el valor)
- No mantiene orden de inserción (usar LinkedHashMap si necesitas orden)
- Búsqueda, inserción y eliminación muy eficientes (O(1))

### **Operaciones básicas:**
- `.put(clave, valor)` - Agregar o actualizar
- `.get(clave)` - Obtener valor
- `.containsKey(clave)` - Verificar si existe
- `.remove(clave)` - Eliminar
- `.keySet()` - Obtener todas las claves
- `.values()` - Obtener todos los valores
- `.size()` - Cantidad de elementos

### **Importar HashMap:**
```java
import java.util.HashMap;
```

---

## 🎯 Desafíos Opcionales

1. **Stock limitado:** Agregar cantidad disponible y reducir al comprar
2. **Historial de compras:** Guardar todas las compras realizadas
3. **Categorías:** Organizar productos por categorías (acción, aventura, indie)
4. **Buscar productos:** Buscar por nombre parcial o precio máximo
5. **Lista de deseos:** Además del carrito, tener una lista de deseos
6. **IVA:** Calcular y mostrar IVA incluido en el precio final

# Nivel 2 - Ejercicio Avanzado 2: Sistema de Log de Transacciones con Recuperación

**Dificultad:** ⭐⭐⭐ Avanzado  
**Tema:** Sistema Bancario - Transaction Log con recuperación ante fallos

## Contexto

Los sistemas bancarios utilizan **transaction logs** (registros de transacciones) para mantener un historial completo de todas las operaciones. Estos logs son críticos para:
- Auditoría de operaciones
- Recuperación ante fallos del sistema
- Detección de fraude
- Resolución de disputas

Tu tarea es implementar un sistema robusto de logging que pueda:
1. Registrar transacciones en tiempo real
2. Detectar transacciones incompletas
3. Recuperar el estado del sistema tras un fallo
4. Generar reportes de auditoría

## Arquitectura del Sistema

### Componentes

1. **Transaction Log:** Archivo binario con todas las transacciones
2. **Checkpoint File:** Snapshot del estado de las cuentas
3. **Integrity Checker:** Validador de integridad del log
4. **Recovery Manager:** Recuperador del estado tras fallos

## Especificaciones del Transaction Log

### Header del Log (20 bytes)

```
Offset | Campo          | Tipo   | Bytes | Descripción
-------|----------------|--------|-------|---------------------------
0      | Magic          | int    | 4     | 0x54584C47 ("TXLG")
4      | Version        | byte   | 1     | Versión del formato (1)
5      | Reserved       | byte   | 1     | Reservado para futuro uso
6      | FirstTxn       | long   | 8     | Timestamp primera transacción
14     | LastTxn        | long   | 8     | Timestamp última transacción
22     | [Inicio TX]    |        |       | Primera transacción
```

### Formato de Transacción (tamaño variable)

```
Offset | Campo          | Tipo    | Bytes      | Descripción
-------|----------------|---------|------------|---------------------------
0      | TxnID          | long    | 8          | ID único de transacción
8      | Timestamp      | long    | 8          | Milisegundos desde epoch
16     | Tipo           | byte    | 1          | 1=Depósito, 2=Retiro, 3=Transferencia
17     | Estado         | byte    | 1          | 0=Pendiente, 1=Completada, 2=Fallida
18     | CuentaOrigen   | int     | 4          | Número de cuenta origen
22     | CuentaDestino  | int     | 4          | Número de cuenta destino (0 si N/A)
26     | Monto          | double  | 8          | Cantidad de dinero
34     | SaldoPrevio    | double  | 8          | Saldo antes de la transacción
42     | SaldoNuevo     | double  | 8          | Saldo después (0 si no completada)
50     | UsuarioID      | String  | 2+n        | ID del usuario que realizó la operación
n+50   | Descripcion    | String  | 2+m        | Descripción de la transacción
```

**Tamaño total:** 50 + len(usuario) + len(descripcion) bytes

### Estados de Transacción

```java
public static final byte ESTADO_PENDIENTE = 0;   // Iniciada pero no completada
public static final byte ESTADO_COMPLETADA = 1;  // Exitosa
public static final byte ESTADO_FALLIDA = 2;     // Error o rechazada
```

### Tipos de Transacción

```java
public static final byte TIPO_DEPOSITO = 1;       // Agregar dinero
public static final byte TIPO_RETIRO = 2;         // Extraer dinero
public static final byte TIPO_TRANSFERENCIA = 3;  // Mover entre cuentas
```

## Funcionalidades Requeridas

### 1. Inicialización del Log

#### `inicializarLog(String archivoLog)`
```java
public static void inicializarLog(String archivoLog)
```
- Crea un nuevo archivo de log vacío
- Escribe el header con valores por defecto
- Magic number: 0x54584C47
- Version: 1
- Timestamps en 0 (se actualizarán con la primera transacción)

### 2. Registro de Transacciones

#### `iniciarTransaccion(...)`
```java
public static long iniciarTransaccion(String archivoLog, byte tipo, 
        int cuentaOrigen, int cuentaDestino, double monto, 
        double saldoPrevio, String usuario, String descripcion)
```
- Genera un nuevo TxnID único (puede usar System.currentTimeMillis())
- Escribe la transacción con estado PENDIENTE
- Saldo nuevo = 0 (aún no se completa)
- Actualiza LastTxn en el header
- Devuelve el TxnID para referencia futura

#### `completarTransaccion(String archivoLog, long txnId, double saldoNuevo)`
```java
public static void completarTransaccion(String archivoLog, long txnId, 
                                        double saldoNuevo)
```
- Busca la transacción por TxnID
- Actualiza estado a COMPLETADA
- Actualiza saldoNuevo con el valor final
- **CRÍTICO:** Esta operación debe ser atómica

#### `fallarTransaccion(String archivoLog, long txnId)`
```java
public static void fallarTransaccion(String archivoLog, long txnId)
```
- Busca la transacción por TxnID
- Actualiza estado a FALLIDA
- El saldo no cambia

### 3. Lectura y Consulta

#### `leerTransacciones(String archivoLog)`
```java
public static List<Transaccion> leerTransacciones(String archivoLog)
```
- Lee todas las transacciones del log
- Devuelve lista ordenada cronológicamente
- Valida el formato del archivo

#### `buscarTransaccion(String archivoLog, long txnId)`
```java
public static Transaccion buscarTransaccion(String archivoLog, long txnId)
```
- Busca una transacción específica por ID
- Devuelve null si no se encuentra

#### `transaccionesPorCuenta(String archivoLog, int numeroCuenta)`
```java
public static List<Transaccion> transaccionesPorCuenta(String archivoLog, 
                                                        int numeroCuenta)
```
- Filtra transacciones que afectan a una cuenta específica
- Incluye transacciones donde la cuenta es origen O destino

### 4. Validación de Integridad

#### `validarIntegridad(String archivoLog)`
```java
public static boolean validarIntegridad(String archivoLog)
```
Verifica:
- Magic number correcto
- Timestamps del header coherentes
- Todas las transacciones completas o fallidas (ninguna pendiente antigua)
- Saldos coherentes (nuevo = previo ± monto)
- No hay transacciones con IDs duplicados

#### `detectarTransaccionesPendientes(String archivoLog, long timeoutMs)`
```java
public static List<Transaccion> detectarTransaccionesPendientes(
        String archivoLog, long timeoutMs)
```
- Encuentra transacciones en estado PENDIENTE
- Que tienen más de `timeoutMs` milisegundos de antigüedad
- Estas probablemente indican un fallo del sistema

### 5. Recuperación ante Fallos

#### `recuperarEstado(String archivoLog)`
```java
public static Map<Integer, Double> recuperarEstado(String archivoLog)
```
- Lee TODAS las transacciones COMPLETADAS
- Reconstruye el saldo actual de cada cuenta
- Ignora transacciones FALLIDAS
- Marca transacciones PENDIENTES antiguas como FALLIDAS
- Devuelve Map<numeroCuenta, saldoActual>

#### `crearCheckpoint(String archivoLog, String archivoCheckpoint)`
```java
public static void crearCheckpoint(String archivoLog, String archivoCheckpoint)
```
- Recupera el estado actual de todas las cuentas
- Guarda un snapshot en formato binario
- Formato del checkpoint:
  ```
  [Timestamp (long)]
  [Numero de cuentas (int)]
  [Cuenta1 (int)][Saldo1 (double)]
  [Cuenta2 (int)][Saldo2 (double)]
  ...
  ```

#### `recuperarDesdeCheckpoint(String archivoCheckpoint, String archivoLog)`
```java
public static Map<Integer, Double> recuperarDesdeCheckpoint(
        String archivoCheckpoint, String archivoLog)
```
- Carga el estado desde el checkpoint
- Aplica transacciones POSTERIORES al checkpoint
- Más rápido que recuperar desde el inicio del log

### 6. Reportes y Auditoría

#### `generarReporteAuditoria(String archivoLog, String archivoReporte)`
```java
public static void generarReporteAuditoria(String archivoLog, 
                                           String archivoReporte)
```
Genera un reporte de texto con:
- Resumen de transacciones totales
- Desglose por tipo (depósitos, retiros, transferencias)
- Desglose por estado (completadas, fallidas, pendientes)
- Monto total movido
- Transacciones sospechosas (montos muy altos, múltiples fallos, etc.)
- Top 10 cuentas más activas

#### `detectarAnomalias(String archivoLog)`
```java
public static List<String> detectarAnomalias(String archivoLog)
```
Detecta patrones sospechosos:
- Más de 5 retiros consecutivos de la misma cuenta
- Retiros/transferencias por montos inusuales (>10,000€)
- Múltiples transacciones fallidas seguidas
- Transferencias circulares (A→B→C→A)

## Clase Auxiliar: Transaccion

```java
class Transaccion {
    private long txnId;
    private long timestamp;
    private byte tipo;
    private byte estado;
    private int cuentaOrigen;
    private int cuentaDestino;
    private double monto;
    private double saldoPrevio;
    private double saldoNuevo;
    private String usuarioId;
    private String descripcion;
    
    // Métodos útiles
    public boolean estaCompletada() { return estado == ESTADO_COMPLETADA; }
    public boolean estaPendiente() { return estado == ESTADO_PENDIENTE; }
    public boolean haFallado() { return estado == ESTADO_FALLIDA; }
    
    public long getEdad() { 
        return System.currentTimeMillis() - timestamp; 
    }
    
    public String getTipoString() {
        switch(tipo) {
            case TIPO_DEPOSITO: return "DEPOSITO";
            case TIPO_RETIRO: return "RETIRO";
            case TIPO_TRANSFERENCIA: return "TRANSFERENCIA";
            default: return "DESCONOCIDO";
        }
    }
    
    public boolean afectaCuenta(int numeroCuenta) {
        return cuentaOrigen == numeroCuenta || cuentaDestino == numeroCuenta;
    }
}
```

## Escenario de Prueba: Simulación de Operaciones Bancarias

Simula el siguiente escenario:

### Cuentas Iniciales
```
Cuenta 1001: 5000.00€
Cuenta 1002: 3000.00€
Cuenta 1003: 10000.00€
Cuenta 1004: 1500.00€
```

### Secuencia de Transacciones

```java
// Día normal de operaciones
1. Depósito: Cuenta 1001 + 500€ → Completada
2. Retiro: Cuenta 1002 - 200€ → Completada
3. Transferencia: 1001 → 1003, 1000€ → Completada
4. Retiro: Cuenta 1004 - 2000€ → Fallida (saldo insuficiente)
5. Transferencia: 1003 → 1002, 500€ → Completada
6. Depósito: Cuenta 1004 + 1000€ → Completada
7. Retiro: Cuenta 1001 - 300€ → Pendiente (sistema falla AQUÍ)

// Más transacciones después del fallo (no se completan)
8. Transferencia: 1002 → 1001, 400€ → Pendiente
9. Depósito: Cuenta 1003 + 2000€ → Pendiente

// Sistema se recupera
10. El sistema detecta 3 transacciones pendientes
11. Se marcan como fallidas
12. Se crea un checkpoint
13. Continúan operaciones normales...
```

### Estado Final Esperado (Después de Recuperación)

```
Cuenta 1001: 4700.00€  (5000 + 500 - 1000 - 300 fallo)
Cuenta 1002: 3300.00€  (3000 - 200 + 500)
Cuenta 1003: 9500.00€  (10000 + 1000 - 500)
Cuenta 1004: 2500.00€  (1500 + 1000)
```

## Casos de Prueba Obligatorios

1. **Inicializar log** y verificar header
2. **Registrar 20+ transacciones** mezclando tipos
3. **Simular fallo del sistema** (dejar transacciones pendientes)
4. **Recuperar estado** desde el log completo
5. **Validar saldos** reconstruidos
6. **Crear checkpoint** del estado actual
7. **Agregar más transacciones** después del checkpoint
8. **Recuperar desde checkpoint** (más rápido)
9. **Detectar anomalías** en el log
10. **Generar reporte de auditoría** completo

## Consideraciones Técnicas

### 1. Actualización de Header

Después de cada transacción, actualiza LastTxn:
```java
// Escribir transacción al final del archivo

// Volver al inicio para actualizar header
RandomAccessFile raf = new RandomAccessFile(archivo, "rw");
raf.seek(14);  // Offset de LastTxn
raf.writeLong(timestamp);
raf.close();
```

### 2. Búsqueda de Transacción por ID

Para modificar una transacción existente:
```java
// Leer todo el log hasta encontrar el TxnID correcto
// Anotar la posición del byte de estado
// Actualizar solo ese byte usando RandomAccessFile
```

### 3. Validación de Saldos

Para cada transacción completada:
```java
double esperado;
if (tipo == TIPO_DEPOSITO) {
    esperado = saldoPrevio + monto;
} else if (tipo == TIPO_RETIRO) {
    esperado = saldoPrevio - monto;
}
assert Math.abs(saldoNuevo - esperado) < 0.01;  // Tolerancia para doubles
```

### 4. Detección de Transacciones Antiguas

```java
long ahora = System.currentTimeMillis();
long timeout = 60000;  // 1 minuto

if (txn.estaPendiente() && (ahora - txn.getTimestamp()) > timeout) {
    // Esta transacción es sospechosa
    marcarComoFallida(txn);
}
```

## Salida Esperada

```
=== SISTEMA DE TRANSACTION LOG ===

[1] Inicializando log...
✓ Log creado: transaction.log
✓ Header escrito correctamente

[2] Registrando operaciones del día...
✓ TXN-1698234567001: Depósito 1001 +500.00€ [COMPLETADA]
✓ TXN-1698234567045: Retiro 1002 -200.00€ [COMPLETADA]
✓ TXN-1698234567089: Transferencia 1001→1003 1000.00€ [COMPLETADA]
✗ TXN-1698234567123: Retiro 1004 -2000.00€ [FALLIDA - Saldo insuficiente]
✓ TXN-1698234567167: Transferencia 1003→1002 500.00€ [COMPLETADA]
✓ TXN-1698234567210: Depósito 1004 +1000.00€ [COMPLETADA]
⏳ TXN-1698234567254: Retiro 1001 -300.00€ [PENDIENTE]

💥 SIMULANDO FALLO DEL SISTEMA...

[3] Sistema reiniciado. Recuperando estado...
⚠️  Detectadas 3 transacciones pendientes antiguas
  - TXN-1698234567254 (edad: 125,456ms)
  - TXN-1698234567298 (edad: 125,412ms)
  - TXN-1698234567342 (edad: 125,368ms)
✓ Transacciones pendientes marcadas como FALLIDAS

[4] Reconstruyendo saldos...
✓ Estado recuperado de 4 cuentas:
  Cuenta 1001: 4,700.00€
  Cuenta 1002: 3,300.00€
  Cuenta 1003: 9,500.00€
  Cuenta 1004: 2,500.00€

[5] Validando integridad del log...
✓ Magic number: OK
✓ Timestamps: OK
✓ Todas las transacciones: Completadas o Fallidas
✓ Coherencia de saldos: OK
✓ Sin IDs duplicados: OK
✅ Log íntegro

[6] Creando checkpoint...
✓ Checkpoint guardado: checkpoint_1698234567500.dat
  - 4 cuentas
  - Timestamp: 2024-10-25 14:32:47

[7] Generando reporte de auditoría...
✓ Reporte guardado: auditoria_2024-10-25.txt

=== RESUMEN DE AUDITORÍA ===
Total de transacciones: 9
├─ Completadas: 5 (55.56%)
├─ Fallidas: 4 (44.44%)
└─ Pendientes: 0 (0.00%)

Por tipo:
├─ Depósitos: 3 (monto: 3,500.00€)
├─ Retiros: 3 (intentados: 2,500.00€, efectivos: 200.00€)
└─ Transferencias: 3 (monto: 1,900.00€)

Cuentas más activas:
1. Cuenta 1001: 4 transacciones
2. Cuenta 1003: 3 transacciones
3. Cuenta 1002: 3 transacciones
4. Cuenta 1004: 2 transacciones

[8] Detectando anomalías...
⚠️  1 anomalía detectada:
  - Cuenta 1004: Intento de retiro excesivo (133% del saldo)

✅ Sistema operativo y listo para continuar
```

## Retos Adicionales (Opcional)

1. **Transacciones ACID:** Implementa transacciones multi-operación que fallan todas o ninguna
2. **Compactación del log:** Elimina transacciones antiguas después de checkpoints
3. **Replicación:** Sincroniza el log con otro archivo para redundancia
4. **Índice de transacciones:** Crea índice para búsquedas O(1) por TxnID
5. **Encriptación:** Cifra transacciones sensibles en el log

## Pistas Importantes

1. Usa `RandomAccessFile` para actualizar estados sin reescribir todo
2. El timestamp es clave para ordenar y detectar problemas
3. Los checkpoints aceleran drásticamente la recuperación
4. Valida SIEMPRE la integridad antes de confiar en un log
5. Las transacciones pendientes antiguas casi siempre indican fallos

## Criterios de Evaluación

- ✅ Header y transacciones escritas correctamente
- ✅ Recuperación de estado funciona tras "fallos"
- ✅ Validación de integridad detecta problemas
- ✅ Checkpoints funcionan correctamente
- ✅ Reportes de auditoría completos y precisos
- ✅ Código robusto con manejo de errores

Este ejercicio simula sistemas reales de bases de datos transaccionales. ¡Un desafío de nivel profesional! 💼📊

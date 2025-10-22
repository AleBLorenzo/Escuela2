package com.example.Eje6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnalizadorVentas {

    private static final String ruta = "src/main/java/com/example/Eje6/ventas_historicas.json";
    private static final String rutaReporte = "src/main/java/com/example/Eje6/ventas_historicas_reporte.json";

    public static void main(String[] args) throws IOException {

        String json = "";

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {

            String linea = "";

            while ((linea = br.readLine()) != null) {
                json += linea;
            }

        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }

        JsonSerializer<LocalDateTime> serializer = (src, typeOfSrc,
                context) -> new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        JsonDeserializer<LocalDateTime> deserializer = (jsonElement, typeOfT, context) -> LocalDateTime
                .parse(jsonElement.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, serializer)
                .registerTypeAdapter(LocalDateTime.class, deserializer)
                .setPrettyPrinting()
                .create();

        SistemaVentas sv = gson.fromJson(json, SistemaVentas.class);

        Map<String, Double> totalesRegion = calcularTotalesPorRegion(sv);
        List<Map.Entry<String, Double>> top5 = calcularTop5Vendedores(sv);
        Producto productoTop = calcularProductoMasVendido(sv);
        double[] ventasMes = calcularVentasPorMes(sv);
        Map<String, Double> ventasTipoCliente = calcularVentasPorTipoCliente(sv);

        generarReporteJSON(sv, totalesRegion, top5, productoTop, ventasMes, ventasTipoCliente);

    }

    public static Map<String, Double> calcularTotalesPorRegion(SistemaVentas sv) {
        Map<String, Double> totales = new HashMap<>();

        double totalEuropa = 0.0;
        double totalAmerica = 0.0;
        double totalAsia = 0.0;
        double totalSudamerica = 0.0;

        for (Region region : sv.getRegion()) {
            if (region.getNombre().equals("Europa")) {
                for (Venta venta : region.getVentas()) {
                    totalEuropa += venta.getTotal();
                }
            } else if (region.getNombre().equals("America del Norte")) {
                for (Venta venta : region.getVentas()) {
                    totalAmerica += venta.getTotal();
                }
            } else if (region.getNombre().equals("Asia")) {
                for (Venta venta : region.getVentas()) {
                    totalAsia += venta.getTotal();
                }
            } else if (region.getNombre().equals("Sudamerica")) {
                for (Venta venta : region.getVentas()) {
                    totalSudamerica += venta.getTotal();
                }
            }
        }

        totales.put("Europa", totalEuropa);
        totales.put("America del Norte", totalAmerica);
        totales.put("Asia", totalAsia);
        totales.put("Sudamerica", totalSudamerica);

        System.out.println("Total en Europa: " + totalEuropa);
        System.out.println("Total en America del  Norte: " + totalAmerica);
        System.out.println("Total en Asia: " + totalAsia);
        System.out.println("Total en Sudamerica: " + totalSudamerica);

        System.out.println("\nConvertir moneda");
        System.out.println("Ganancias de America(USD) a EUR: " + totalAmerica * 0.86);
        System.out.println("Ganancias de Asia(JPY) a EUR: " + totalAsia * 0.0057);
        System.out.println("Ganancias de Sudamerica(ARG) a EUR: " + totalSudamerica * 0.00059);

        return totales;
    }

    public static List<Map.Entry<String, Double>> calcularTop5Vendedores(SistemaVentas sv) {
        Map<String, Double> comisiones = new HashMap<>();

        for (Region region : sv.getRegion()) {
            double cambio = 1.0;
            if (region.getMoneda().equals("USD")) {
                cambio = 0.86;
            } else if (region.getMoneda().equals("JPY")) {
                cambio = 0.0057;
            } else if (region.getMoneda().equals("ARS")) {
                cambio = 0.00059;
            }

            for (Venta venta : region.getVentas()) {
                String nombre = venta.getVendedor().getNombre();
                double comision = venta.getTotal() * venta.getVendedor().getComision();
                double comisionEUR = comision * cambio;

                if (comisiones.containsKey(nombre)) {
                    comisiones.put(nombre, comisiones.get(nombre) + comisionEUR);
                } else {
                    comisiones.put(nombre, comisionEUR);
                }
            }
        }

        List<Map.Entry<String, Double>> lista = new ArrayList<>(comisiones.entrySet());

        Collections.sort(lista, new Comparator<Map.Entry<String, Double>>() {

            @Override
            public int compare(Map.Entry<String, Double> a, Map.Entry<String, Double> b) {
                return Double.compare(b.getValue(), a.getValue());
            }
        });

        System.out.println("\nTop 5 Vendedores");
        for (int i = 0; i < Math.min(5, lista.size()); i++) {

             System.out.println((i + 1) + ". " + lista.get(i).getKey() + " - " + lista.get(i).getValue() + " EUR");
        }

        return lista;
    }

    public static Producto calcularProductoMasVendido(SistemaVentas sv) {

        Map<String, Integer> cantidades = new HashMap<>();

        Producto productoMasVendido = null;
        int maxCantidad = 0;

        for (Region region : sv.getRegion()) {
            for (Venta venta : region.getVentas()) {
                for (Producto producto : venta.getProductos()) {
                    String codigo = producto.getCodigo();
                    int cantidad = producto.getCantidad();

                    if (cantidades.containsKey(codigo)) {
                        cantidades.put(codigo, cantidades.get(codigo) + cantidad);
                    } else {
                        cantidades.put(codigo, cantidad);
                    }

                    if (cantidades.get(codigo) > maxCantidad) {
                        maxCantidad = cantidades.get(codigo);
                        productoMasVendido = producto;
                    }
                }
            }
        }

        if (productoMasVendido != null) {

            System.out.println("\nProducto más vendido:");
            System.out.println("Código: " + productoMasVendido.getCodigo());
            System.out.println("Nombre: " + productoMasVendido.getNombre());
            System.out.println("Cantidad: " + productoMasVendido.getCantidad());
            System.out.println("Precio: " + productoMasVendido.getPrecio_unitario());
        }

        return productoMasVendido;
    }

    public static double[] calcularVentasPorMes(SistemaVentas sv) {

        double[] ventasMes = new double[12];

        for (Region region : sv.getRegion()) {
            double cambio = 1.0;
            if (region.getMoneda().equals("USD")) {
                cambio = 0.86;
            } else if (region.getMoneda().equals("JPY")) {
                cambio = 0.0057;
            } else if (region.getMoneda().equals("ARS")) {
                cambio = 0.00059;
            }

            for (Venta venta : region.getVentas()) {
                int mes = venta.getFecha().getMonthValue();
                double total = venta.getTotal() * cambio;
                ventasMes[mes - 1] += total;
            }
        }

        System.out.println("\nVentas por Mes (EUR)");
        for (int i = 0; i < 12; i++) { 
            
            System.out.println("Mes " + (i + 1) + ": " + ventasMes[i]);
       
        }

        return ventasMes;
    }

    public static Map<String, Double> calcularVentasPorTipoCliente(SistemaVentas sv) {
        double totalEmpresa = 0.0;
        double totalParticular = 0.0;

        for (Region region : sv.getRegion()) {
            double cambio = 1.0;
            if (region.getMoneda().equals("USD")) {
                cambio = 0.86;
            } else if (region.getMoneda().equals("JPY")) {
                cambio = 0.0057;
            } else if (region.getMoneda().equals("ARS")) {
                cambio = 0.00059;
            }

            for (Venta venta : region.getVentas()) {
                double total = venta.getTotal() * cambio;

                if (venta.getCliente().getTipo() == TipoCliente.EMPRESA) {
                    totalEmpresa += total;
                } else {
                    totalParticular += total;
                }
            }
        }

        Map<String, Double> resultado = new HashMap<>();

        resultado.put("EMPRESA", totalEmpresa);
        resultado.put("PARTICULAR/NORMAL", totalParticular);

        System.out.println("\nVentas por tipo de cliente (EUR)");

        for (Map.Entry<String, Double> entry : resultado.entrySet()) {
            String tipo = entry.getKey();
            double total = entry.getValue();
            System.out.println(tipo + ": " + total);
        }
        return resultado;
    }

    public static void generarReporteJSON(SistemaVentas sv, Map<String, Double> totalesRegion, List<Map.Entry<String, Double>> top5, Producto productoMasVendido, double[] ventasPorMes, Map<String, Double> tipoCliente) {

        Map<String, Object> reporte = new HashMap<>();

        reporte.put("empresa", sv.getEmpresa());
        reporte.put("periodo", sv.getPeriodo());
        reporte.put("fecha_generacion", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        reporte.put("version", "1.0");
        reporte.put("totales_por_region", totalesRegion);

        List<Map<String, Object>> listaTop = new ArrayList<>();

        for (int i = 0; i < Math.min(5, top5.size()); i++) {

            Map<String, Object> v = new HashMap<>();

            v.put("nombre", top5.get(i).getKey());
            v.put("comision_ganada_EUR", top5.get(i).getValue());
            listaTop.add(v);
        }
        reporte.put("top_5_vendedores", listaTop);

        Map<String, Object> prod = new HashMap<>();

        if (productoMasVendido != null) {

            prod.put("codigo", productoMasVendido.getCodigo());
            prod.put("nombre", productoMasVendido.getNombre());
            prod.put("cantidad_vendida", productoMasVendido.getCantidad());
        }
        reporte.put("producto_mas_vendido", prod);

        List<Map<String, Object>> ventasMesList = new ArrayList<>();

        for (int i = 0; i < 12; i++) {

            Map<String, Object> mes = new HashMap<>();

            mes.put("mes", i + 1);
            mes.put("total_EUR", ventasPorMes[i]);
            ventasMesList.add(mes);
        }
        reporte.put("ventas_por_mes", ventasMesList);

        reporte.put("ventas_por_tipo_cliente", tipoCliente);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaReporte))) {

            new GsonBuilder()
                    .setPrettyPrinting()
                    .create()
                    .toJson(reporte, bw);

            System.out.println("\nArchivo de reporte generado correctamente en: " + rutaReporte);

        } catch (IOException e) {

            System.out.println("Error al generar reporte: " + e.getMessage());
        }
    }

}

package com.example.Eje6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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

        double totalEuropa = 0.0;

        for (Region regions : sv.getRegion()) {
            if (regions.getNombre().equals("Europa")) {
                for (Venta venta : regions.getVentas()) {
                    totalEuropa += venta.getTotal();
                }
            }

        }

        double totalAmerica = 0.0;
        for (Region regions : sv.getRegion()) {
            if (regions.getNombre().equals("America del Norte")) {
                for (Venta venta : regions.getVentas()) {
                    totalAmerica += venta.getTotal();

                }
            }

        }

        double totalAsia = 0.0;
        for (Region regions : sv.getRegion()) {
            if (regions.getNombre().equals("Asia")) {
                for (Venta venta : regions.getVentas()) {
                    totalAsia += venta.getTotal();

                }
            }

        }

        double totalSuda = 0.0;
        for (Region regions : sv.getRegion()) {
            if (regions.getNombre().equals("Sudamerica")) {
                for (Venta venta : regions.getVentas()) {
                    totalSuda += venta.getTotal();

                }
            }

        }
        System.out.println("Total en Europa: " + totalEuropa);
        System.out.println("Total en America del  Norte: " + totalAmerica);
        System.out.println("Total en Asia: " + totalAsia);
        System.out.println("Total en Sudamerica: " + totalSuda);

        for (Region regions : sv.getRegion()) {

            for (Venta venta : regions.getVentas()) {

                String nombreV = venta.getVendedor().getNombre();
                double totalV = venta.getTotal();

                System.out.println("\nVendedor: " + nombreV + " - " + totalV);

            }

        }

        double dolarEuro = totalAmerica * 0.86;
        double dolarYan = totalAsia * 0.00570;
        double dolarArg = totalSuda * 0.00059;

        System.out.println("\nConvertir moneda");
        System.out.println("Ganancias de America(USD) a EUR: " + dolarEuro);
        System.out.println("Ganancias de Asia(JPY) a EUR: " + dolarYan);
        System.out.println("Ganancias de Sudamerica(ARG) a EUR: " + dolarArg);

        Map<String, Double> tipoCambio = new HashMap<>();
        tipoCambio.put("EUR", 1.0);
        tipoCambio.put("USD", 0.86);
        tipoCambio.put("JPY", 0.0057);
        tipoCambio.put("ARS", 0.00059);

        Map<String, Double> comisiones = new HashMap<>();

        String nombreV = null;
        double Comision = 0;

        for (Region regions : sv.getRegion()) {

            String moneda = regions.getMoneda();
            double cambio = tipoCambio.getOrDefault(moneda, 1.0);

            for (Venta venta : regions.getVentas()) {

                nombreV = venta.getVendedor().getNombre();
                Comision = venta.getTotal() * venta.getVendedor().getComision();

                double comisionEUR = Comision * cambio;

                comisiones.put(nombreV, comisiones.getOrDefault(nombreV, 0.0) + comisionEUR);
            }

        }
        List<Map.Entry<String, Double>> lista = new ArrayList<>(comisiones.entrySet());

        Collections.sort(lista, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> a, Map.Entry<String, Double> b) {
                return Double.compare(b.getValue(), a.getValue());
            }
        });

        System.out.println("\n=== Top 5 Vendedores ===");
        for (int i = 0; i < Math.min(5, lista.size()); i++) {
            System.out.println((i + 1) + ". " + lista.get(i).getKey() + " - " + lista.get(i).getValue());
        }

        int catidadMax = 0;
        String productoTop = null;

        Map<String, Integer> map = new HashMap<>();

        for (Region regions : sv.getRegion()) {

            for (Venta venta : regions.getVentas()) {

                for (Producto producto : venta.getProductos()) {

                    String id = producto.getCodigo();
                    int cantidad = producto.getCantidad();
                    map.put(id, cantidad);

                }
            }
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > catidadMax) {
                catidadMax = entry.getValue();
                productoTop = entry.getKey();
            }
        }
        for (Region regions : sv.getRegion()) {

            for (Venta venta : regions.getVentas()) {

                for (Producto producto : venta.getProductos()) {

                    if (producto.getCodigo().equals(productoTop)) {
                        System.out.println("\nProducto mas Vendido");
                        System.out.println("Codigo: " + producto.getCodigo());
                        System.out.println("Nombre: " + producto.getNombre());
                        System.out.println("Cantidad: " + producto.getCantidad());
                        System.out.println("Precio: " + producto.getPrecio_unitario());
                        System.out.println("Descuento: " + producto.getDescuento());
                    }

                }
            }
        }

        double[] ventaspormes = new double[12];

        for (Region regions : sv.getRegion()) {

            String moneda = regions.getMoneda();

            for (Venta venta : regions.getVentas()) {

                int mes = venta.getFecha().getMonthValue();

                double total = venta.getTotal();
                if (moneda.equals("ARG")) {
                    total = total * 0.00059;
                } else if (moneda.equals("JPY")) {
                    total = total * 0.00570;
                } else if (moneda.equals("USD")) {
                    total = total * 0.86;
                }

                ventaspormes[mes - 1] += total;

            }
        }
        System.out.println("\nVenta por Mes");
        for (int i = 0; i < 12; i++) {
            System.out.println("Mes " + (i + 1) + " - Total ventas: " + ventaspormes[i]);
        }

        double totalEmpresa = 0.0;
        double totalParticular = 0.0;

        for (Region regions : sv.getRegion()) {

            String moneda = regions.getMoneda();

            for (Venta venta : regions.getVentas()) {

                TipoCliente tipo = venta.getCliente().getTipo();
                double total = venta.getTotal();

                if (moneda.equals("USD")) {
                    total *= 0.86;
                }  else if (moneda.equals("JPY")) {
                    total *= 0.0057;
                }else if (moneda.equals("ARS")) {
                    total *= 0.00059;
                }

                if (tipo == TipoCliente.EMPRESA) {
                    totalEmpresa += total;

                } else if (tipo == TipoCliente.PARTICULAR) {
                    totalParticular += total;
                } else if (tipo == TipoCliente.NORMAL) {
                    totalParticular += total;
                }

            }
        }
        System.out.println("\nReporte por tipo de cliente");
        System.out.println("Empresa: " + totalEmpresa);
        System.out.println("Particular: " + totalParticular);
    }

}

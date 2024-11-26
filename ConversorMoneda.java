package conversores;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConversorMoneda {
    private static final String API_KEY = "eb0b8542827dcc06fa8c039d";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY;

    public double convertirMonedaAUSD(String moneda, double cantidad) {
        double tasa = obtenerTasaDeCambio(moneda, "USD");
        return cantidad * tasa;
    }

    public double convertirUSDAOtraMoneda(String moneda, double cantidad) {
        double tasa = obtenerTasaDeCambio("USD", moneda);
        return cantidad * tasa;
    }

    private double obtenerTasaDeCambio(String desde, String hacia) {
        String url = BASE_URL + "/pair/" + desde + "/" + hacia;

        try {
            HttpClient cliente = HttpClient.newHttpClient();
            HttpRequest solicitud = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
            if (respuesta.statusCode() == 200) {
                String cuerpo = respuesta.body();
                String[] partes = cuerpo.split(",");
                for (String parte : partes) {
                    if (parte.contains("conversion_rate")) {
                        return Double.parseDouble(parte.split(":")[1]);
                    }
                }
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al obtener la tasa de cambio: " + e.getMessage());
        }
        return 0.0;
    }
}

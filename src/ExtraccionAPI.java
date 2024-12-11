import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;

public class ExtraccionAPI{

    public static double obtencionConTransformacionDeDatos(double cantidadDivisaEntrada, int indiceMenu, Monedas monedas) throws IOException {

        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("config.properties")){
            properties.load(input);
        }
        String apiKey = properties.getProperty("API_KEY");

        String conversionAutorizada = monedas.getListaDeConversionesAutorizadas().get(indiceMenu);
        var conversionParaURI = URLEncoder.encode(conversionAutorizada, "UTF-8");
        String direccionConversion = "https://v6.exchangerate-api.com/v6/"+apiKey+"/pair/" + conversionParaURI;

        // Making Request
        URL url = new URL(direccionConversion);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
                    request.connect();

        // Convert to JSON
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonobj = root.getAsJsonObject();

        // Accessing object
        double req_result = jsonobj.get("conversion_rate").getAsDouble();

        // Transforming Object
        double multiplicacion = req_result*cantidadDivisaEntrada;

        return multiplicacion;

    }

}

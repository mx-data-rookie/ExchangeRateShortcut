import com.google.gson.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.Scanner;

public class MainExchangeRate {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner leerOpcionIngresada = new Scanner(System.in);
        Monedas monedas = new Monedas();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .setPrettyPrinting()
                .create();

        while(true){

            System.out.println("Bienvenid@ al Centro de Divisas MORSHU");
            System.out.println("¡DONDE TE DAMOS MÁS POR TUS RUPIAS!");
            System.out.println("¿Qué intercambio de divisa quieres revisar hoy?\n");
            System.out.println("*********************************************************");
            System.out.println("      (1) Dólar EEUU => Peso Mexicano (USD => MXN)");
            System.out.println("      (2) Peso Mexicano => Dólar EEUU (MXN => USD)");
            System.out.println("      (3) Dólar EEUU => Real Brasileño (USD => BRL)");
            System.out.println("      (4) Real Brasileño => Dólar EEUU (BRL => USD)");
            System.out.println("      (5) Dólar EEUU => Peso Colombiano (USD => COP)");
            System.out.println("      (6) Peso Colombiano => Dólar EEUU (COP => USD)");
            System.out.println("      Ingresa SALIR para CERRAR el programa");
            System.out.println("      +ELIGE UNA OPCIÓN VÁLIDA+");
            System.out.println("*********************************************************");

            var buscarOpcionIngresada = leerOpcionIngresada.nextLine();

            try{

                if( buscarOpcionIngresada.equalsIgnoreCase("Salir") ){
                    break;
                }

                int indiceMenu = Integer.parseInt(buscarOpcionIngresada) - 1;

                if (indiceMenu >= 0 && indiceMenu < monedas.getListaDeConversionesAutorizadas().size()) {

                    System.out.println("Conversión "+monedas.getListaDeConversionesAutorizadas().get(indiceMenu));
                    System.out.println("¿Qué Cantidad Deseas Cambiar?");
                    double cantidadDivisaEntrada = Double.parseDouble(leerOpcionIngresada.nextLine());
                    double resultadoConversion = ExtraccionAPI.obtencionConTransformacionDeDatos(cantidadDivisaEntrada, indiceMenu, monedas);
                    String conversionElegida = monedas.getListaDeConversionesAutorizadas().get(indiceMenu);
                    String ladoIzquierdoDeLaIgualdad = conversionElegida.substring(0,3);
                    String ladoDerechoDeLaIgualdad = conversionElegida.substring(4);
                    System.out.println(String.format("\n%.2f", cantidadDivisaEntrada)+" "+ladoIzquierdoDeLaIgualdad+" = "+String.format("%.2f", resultadoConversion)+" "+ladoDerechoDeLaIgualdad+"\n");

                } else {
                    System.out.println("\n¬¬¬ OPCIÓN NO VÁLIDA, intenta de nuevo ¬¬¬\n");
                }

            } catch (NumberFormatException e) {

                System.out.println("Ups, Tuvimos un Problema: "+ e.getMessage());
                System.out.println("Vuelve a Reiniciar el Programa");

            }

        }

        System.out.println("Gracias Por Usar MORSHU, ¡Adiós!");

    }

}

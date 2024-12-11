import com.google.gson.annotations.SerializedName;

import java.util.LinkedList;

public class Monedas {

    @SerializedName("conversion_rate")
    private Double valorConversion;

    private LinkedList<String> listaDeConversionesAutorizadas = new LinkedList<>() ;


    //Setter y Getter
    public Double getValorConversion() {
        return valorConversion;
    }

    public void setValorConversion(Double valorConversion) {
        this.valorConversion = valorConversion;
    }

    public LinkedList<String> getListaDeConversionesAutorizadas() {
        return listaDeConversionesAutorizadas;
    }

    public void setListaDeConversionesAutorizadas(LinkedList<String> listaDeConversionesAutorizadas) {
        this.listaDeConversionesAutorizadas = listaDeConversionesAutorizadas;
    }
    //Setter y Getter

    //Constructor Principal
    public Monedas(){

        this.valorConversion = valorConversion;
        this.listaDeConversionesAutorizadas = listaDeConversionesAutorizadas;
        listaDeConversionesAutorizadas.add("USD/MXN");
        listaDeConversionesAutorizadas.add("MXN/USD");
        listaDeConversionesAutorizadas.add("USD/BRL");
        listaDeConversionesAutorizadas.add("BRL/USD");
        listaDeConversionesAutorizadas.add("USD/COP");
        listaDeConversionesAutorizadas.add("COP/USD");

    }

    public Monedas(MonedasAPI conversionesBuscadasAPI){

        this.valorConversion = conversionesBuscadasAPI.conversion_rate();

    }
}

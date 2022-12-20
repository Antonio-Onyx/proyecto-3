import java.io.*;
/**
 * @autor Macias Hernandez Gerardo Antonio
 */
public class Producto implements Serializable{
    private static final long serialVersionUID = 8684010104239145226L;// esto lo puse porque si no me salia error cada que seserializaba
    String codigo_de_barras;
    String nombre;
    int cantidad_de_unidades;
    int precio_unitario;
    public static int dimensionDelArray;

    public Producto(){
        
    }

    public Producto(String nombre, String codigo_de_barras, int cantidad_de_unidades, int precio_unitario){
        this.nombre = nombre;
        this.codigo_de_barras = codigo_de_barras;
        this.cantidad_de_unidades = cantidad_de_unidades;
        this.precio_unitario = precio_unitario;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    
    public String getCodigoDeBarras(){
        return this.codigo_de_barras;
    }

    public int getCantidadDeUnidades(){
        return this.cantidad_de_unidades;
    }

    public void setCantidadDeUnidades(int cantidad_de_unidades){
        this.cantidad_de_unidades = cantidad_de_unidades; 
    }

    public int getPrecioUnitario(){
        return this.precio_unitario;
    }

    public void setPrecioUnitario(int precio_unitario){
        this.precio_unitario = precio_unitario;
    }

    /* 
    public String toString(){
        return "Nombre: "+nombre+"\n"
                        +"Codigo de barras: " + codigo_de_barras + "\n"
                        +"Cantidad de unidades: " + cantidad_de_unidades+ "\n"
                        +"Precio Unitario: $" + precio_unitario + "c/u";
    }
    */
}
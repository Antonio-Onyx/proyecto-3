import java.util.ArrayList;
import java.util.Scanner;
/**
 * @autor Macias Hernandez Gerardo Antonio
 */

public class Gestion {
    //private Producto productos[] = null;
    private ArrayList<Producto> productos = null;
    Scanner sc = new Scanner(System.in);
    Scanner entrada = new Scanner(System.in);
    String nombre;
    String codigo_de_barras;
    int cantidad_de_unidades;
    int precio_unitario;

    public Gestion(){}

    public Gestion(ArrayList<Producto> productos){
        this.productos = productos;
    }

    public ArrayList<Producto> getProtuctos(){
        return productos;
    }

    /**
     * Metodo para crear un nuevo producto
     */
    public Producto anadirProductoNuevo(String nombre, String codigo_de_barras, int cantidad_de_unidades, int precio_unitario){

        Producto producto  = new Producto(nombre, codigo_de_barras, cantidad_de_unidades, precio_unitario);

        return producto;
    }

    /**
     * Metodo que revisa si la palabra p esta contenida
     * en el nombre de algun producto
     * @param p nombre a buscar
     * @param q lista donde se va a buscar
     */
    public void buscarNombre(String p, ArrayList<Producto> q){
        for(int i = 0; i < q.size(); i++){
            String nombre = q.get(i).getNombre();
            if(nombre.contains(p)){
                System.out.println("Encontrados:");
                System.out.println(q.get(i).getCodigoDeBarras()+"-"+ q.get(i).getNombre()+"-"+q.get(i).getCantidadDeUnidades()+" unidades -$"+q.get(i).getPrecioUnitario() );
            } else {
                System.out.println("No existe el producto");
            }
        }
    }

    /**
     * Metodo que actualiza la cantidad
     * de unidades de un producto
     * @param p codigo de barras
     * @param q lista donde esta el producto
     */
    public void surtirInventario(String p, ArrayList<Producto> q){
        for(int i = 0; i < q.size(); i++){
            if(q.get(i).getCodigoDeBarras().equals(p)){
                System.out.print("Cuantas unidades de " + q.get(i).getNombre() + " quieres sumar?: ");
                int n = sc.nextInt();
                int cantidad_actual = q.get(i).getCantidadDeUnidades();
                q.get(i).setCantidadDeUnidades(cantidad_actual + n);
            }
        }
    }

    public void mostrarProducto(ArrayList<Producto> p){
        for(int i = 0; i < p.size(); i++){
            System.out.println(p.get(i)+"n-------------n");
        }
    }

    public void mostrarNombreProducto(ArrayList<Producto> p ){
        for(int i=0; i<p.size(); i++){
            System.out.println(i+1+". "+p.get(i).getNombre()+"----n");
        }
    }

    public void mostrarCodigoDebarras(ArrayList<Producto> p){
        for(int i = 0; i < p.size(); i++){
            System.out.println(i+1+". "+p.get(i).getCodigoDeBarras()+"----n");
        }
    }

    /**
     * metodo que nos regrese una lista de cada producto dentro del array mostrando
     * el nombre, codigo de barras, cantidad de unidades y el precio unitario
     */
    public void verTodosLosProductos(ArrayList<Producto> p){
        for(int i = 0; i < p.size(); i++){
            System.out.println("************************************************");
            System.out.println("1. Nombre del producto: "+p.get(i).getNombre());
            System.out.println("2. Codigo de barras: "+p.get(i).getCodigoDeBarras());
            System.out.println("3. Cantidad de unidades: "+p.get(i).getCantidadDeUnidades()+" unidades");
            System.out.println("4. Precio unitario: $"+p.get(i).getPrecioUnitario()+" c/u");
        }
    }

    /**
     * Metodo que toma un String(en este caso seria el codigo de barrras)
     * y cuenta que el string deba tener 5 letras y 2 numeros
     */
    public boolean malFormato(String p) throws CodigoMalFormadoException{
        int contadorNumeros = 0;
        int contadorLetras = 0;

        for(int i = 0; i < p.length(); i++){
            char a = p.charAt(i);
            if(Character.isLetter(a)){
                contadorLetras++;
            } else {
                contadorNumeros++;
            }
        }

        if(contadorLetras == 5 && contadorNumeros == 2 ){
            //anadirProductoNuevo(nombre, codigo_de_barras, cantidad_de_unidades, precio_unitario);
            return true;
        } else {
            throw new CodigoMalFormadoException("El codigo de barras debe contener 5 letras y 2 números.");
        }
    }

    /**
     * Metodo que que checa un ArrayList y un Objeto de tipo Producto
     * no tengan el mismo codigo de barras 
     */
    public boolean codigoYaRegistrado(ArrayList<Producto> p, String q) throws CodigoYaRegistradoException{
        boolean a = false;
        for(int i = 0; i < p.size(); i++){
            if(p.get(i).getCodigoDeBarras().equals(q)){
                throw new CodigoYaRegistradoException("El codigo de barras ya está registrado");
            } //else {
                //anadirProductoNuevo(nombre, codigo_de_barras, cantidad_de_unidades , precio_unitario);
                //malFormato(q.getCodigoDeBarras());
                //p.add(q);
            
            //}
        }
        return a;
        
    }

    /**
     * Metodo que dado un arraylist y un string compara si dentro del arraylist
     * existe un string que es igual al que ponemos por parametro
     */
    public boolean codigoNoExistente(ArrayList<Producto> p, String q) throws CodigoNoExistenteException{
        System.out.println("Tamaño de la lista: " + p.size());

        int cont = 0;
        boolean flag = true;
        for(int i = 0; i < p.size(); i++){
            //System.out.println("Codigo: " + p.get(i).getCodigoDeBarras());
            if(p.get(i).getCodigoDeBarras().contains(q)){
                //System.out.println("El codigo "+ q +"esta en "+i);
                cont = i;
                //System.out.println("posicion: "+i);
                flag = true;
                break;
            } else {
                //System.out.println("ijole, el codigo no está");
                flag = false;
            }
        }
        
        if(flag == false){
            throw new CodigoNoExistenteException("El codigo de barras no existe");
        } else {
            return true;
        }
    }

    /**
     * Metodo que busca un porducto dado su codigo de barras
     * dependiendo de que queramos este metodo modificara el nombre o precio
     * del producto que queramos, calo mientras este exista
     */
    public void actualizarProducto(String q, ArrayList<Producto> p) throws CodigoMalFormadoException, CodigoNoExistenteException{
        try{
            try{
                if(malFormato(q) == true){
                    if(codigoNoExistente(p, q) == true){

                        int contador=0;
                        for(int i = 0; i < p.size(); i++){
                            if(p.get(i).getCodigoDeBarras().equals(q)){
                                contador = i;
                            }
                        }

                        System.out.println("Desea cambiar nombre o precio? ");
                        String cambio = sc.nextLine();
                        if(cambio.equals("nombre")){
                            System.out.print("Introduce el nuevo nombre: ");
                            String nuevoNombre = sc.nextLine();
                            p.get(contador).setNombre(nuevoNombre);
                            System.out.println("Nuevo nombre: "+p.get(contador).getNombre());
                        } else if(cambio.equals("precio")){
                            System.out.print("Introduce el nuevo precio: ");
                            int nuevoPrecio = sc.nextInt();
                            p.get(contador).setPrecioUnitario(nuevoPrecio);
                            System.out.println("Nuevo precio: " + p.get(contador).getPrecioUnitario());
                        }
                    }
                }
            }catch(CodigoMalFormadoException cmf){System.out.println(cmf);}
        }catch(CodigoNoExistenteException cne){
            System.out.println(cne);
        }
    }

    public void quitarProducto(String q, ArrayList<Producto> p) throws CodigoMalFormadoException, CodigoNoExistenteException{
        try{
            try{
                if(malFormato(q)==true){
                    if(codigoNoExistente(p, q)){

                        int contador = 0;
                        for(int i = 0; i < p.size(); i++){
                            if(p.get(i).getCodigoDeBarras().equals(q)){
                                contador = i;
                                break;
                            }
                        }

                        //System.out.println("Ingrese el codigo de barras del producto que quiere borrar");
                        //String borrar = sc.next();
                        System.out.println("Se borrara el articulo: " + p.get(contador).getNombre());
                        p.remove(contador);
                        verTodosLosProductos(p);
                    }
                }
            }catch(CodigoMalFormadoException cmf){System.out.println(cmf);}
        }catch(CodigoNoExistenteException cne){System.out.println(cne);}
    }
}

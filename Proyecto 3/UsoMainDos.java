import java.util.Scanner;
import java.io.*;
//import java.net.PortUnreachableException; //no se que sea esto pero aparecio solo y mejor no lo borro
import java.util.ArrayList;
/**
 * @autor Macias Hernandez Gerardo Antonio
 */
@SuppressWarnings("unchecked") //para lograr que compile
public class UsoMainDos {
    private String nombreArchivo = "productos.ser";
    ArrayList<Producto> tienda = new ArrayList<Producto>();

    
    public static void main(String[] lol) throws Exception{
        String nombreArchivo = "productos.ser";
        Scanner sc = new Scanner(System.in);
        Gestion gestion = new Gestion();
        Boolean bandera = true;
        String codigo_de_barras;
        String nombre;
        int cantidad_de_unidades;
        int precio_unitario;
        int acceso;
        ArrayList<Producto> tienda = new ArrayList<Producto>();


        boolean continuar = true;
        while(continuar){
            System.out.println("*********************");
            System.out.println("* Tipo de acceso    *");
            System.out.println("* 1. Administrador  *");
            System.out.println("* 2. Cliente        *");
            System.out.println("* 3. Salir          *");
            System.out.println("*********************");
            System.out.println("Introduzca dos veces la opcion a elegir"); //por alguna razon despues de elegir una sola vez la opcion 1
                                                                           // cuando se sale de este al menu de cliente y admin
                                                                           //ya no se puede volver a entrar y sigue preguntado la opcion
                                                                           //por eso si se pone doble vez la opcion
                                                                           //si se puede volver a entrar como admin cuantas veces quiera
            acceso = sc.nextInt();
            //sc.nextLine();
            //sc.nextInt();

        switch(acceso){
            
            case 1:
                bandera = true; // declaramos true aqui porque si no dentro del ciclo cambiara a false y asi se mantendra
                System.out.println("Administrador");

                System.out.println("Tienda de abarrotes");

                while(bandera){
                    System.out.println("*********************************");
                    System.out.println("*       Administrador           *");
                    System.out.println("* 1. Agregar un producto.       *");
                    System.out.println("* 2. Ver la lista de productos. *");
                    System.out.println("* 3. Actualizar producto.       *");
                    System.out.println("* 4. Quitar producto del stock  *");
                    System.out.println("* 5. Buscar producto.           *");
                    System.out.println("* 6. Surtir inventario.         *");
                    System.out.println("* 7. Ver ventas.                *");
                    System.out.println("* 8. Salir.                     *");
                    System.out.println("*********************************");
                    //int entrada = sc.nextInt();
                    //sc.nextLine();

                    switch(sc.nextInt()){
                        case 1: //añadir producto nuevo
                            try{
                                System.out.println("Introduce el nombre del producto: ");
                                nombre = sc.nextLine();
                                System.out.println("Introduce el codigo de barras: ");
                                codigo_de_barras = sc.next().toUpperCase();
                                System.out.println("Introduce la cantidad de unidades: ");
                                cantidad_de_unidades = sc.nextInt();
                                System.out.println("Introduce el precio unitario: ");
                                precio_unitario = sc.nextInt();

                                //creamos un producto nuevo apartir de las anteriores entradas
                                Producto a = new Producto(nombre, codigo_de_barras, cantidad_de_unidades, precio_unitario);
                                //primero checamos si el formato de nestro codigo de barras está bien
                                //si está bien, pasa a checar que el codigo de barras no esté repetido
                                //si el formato esta mal lanza la excepcion y no guarda el producto
                                //si el formato esta bien pero esta repetido, entonces lanza la excepcion y no guarda el producto
                                try{
                                    if(gestion.malFormato(codigo_de_barras) == true ){
                                        if(gestion.codigoYaRegistrado(tienda, a.getCodigoDeBarras()) == false){
                                            tienda.add(a);
                                        }
                                    }
                                }catch(CodigoMalFormadoException cmf){System.out.println(cmf);}
                                

                                System.out.println("Tamaño del array tienda: " + tienda.size());

                                //ahora intentemos serializar nuestra lista
                                try{
                                    /* 
                                    ArrayList<Producto> lista_aux = new ArrayList<>();
                                    
                                    for(int i = 0; i < tienda.size(); i++){
                                        lista_aux.add(tienda.get(i));
                                    }
                                    */
                                    //ArrayList<Producto> lis = new ArrayList<Producto>();

                                    FileOutputStream fos = new FileOutputStream(nombreArchivo);
                                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                                    oos.writeObject(tienda);
                                    oos.close();
                                    System.out.println("Se ha serializado con exito :D!!!!");
                                    System.out.println("tamaño del arreglo auxilair: "+tienda.size());
                                }catch(IOException ioe){ioe.printStackTrace();}

                            }catch(CodigoYaRegistradoException cyr){
                                System.out.println(cyr);
                            }
                        break;
                        
                        case 2:
                            try{
                                ArrayList<Producto> lista_recuperada = new ArrayList<>();
                                FileInputStream fis = new FileInputStream(nombreArchivo);
                                ObjectInputStream ois = new ObjectInputStream(fis);
                                lista_recuperada = (ArrayList<Producto>) ois.readObject();
                                ois.close();
                                System.out.println("Se han recuperado los datos con exito!!");
                                gestion.verTodosLosProductos(lista_recuperada);
                            }catch(IOException ioe){ioe.printStackTrace();}
                        break;

                        case 3:
                            System.out.println("Actualizar producto");
                            try{
                                ArrayList<Producto> lista_recuperada = new ArrayList<>();
                                FileInputStream fis = new FileInputStream(nombreArchivo);
                                ObjectInputStream ois = new ObjectInputStream(fis);
                                lista_recuperada = (ArrayList<Producto>) ois.readObject();
                                ois.close();
                                System.out.println("Se han vuelto a recuperar los archivos");
                                System.out.println("Introduce el codigo de barras del producto que buscas");
                                String buscas = sc.next().toUpperCase();

                                try{
                                    try{
                                        if(gestion.malFormato(buscas)==true){
                                            if(gestion.codigoNoExistente(lista_recuperada, buscas)){
                                                System.out.println("Se actualizara el producto "+buscas);
                                                gestion.actualizarProducto(buscas, lista_recuperada);
                                            }
                                        }
                                    }catch(CodigoMalFormadoException E){System.out.println(E);}
                                }catch(CodigoNoExistenteException e){System.out.println(e);}

                                
                                //gestion.verTodosLosProductos(lista_recuperada);

                                //intentemos serializar de nuevo
                                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo));
                                oos.writeObject(lista_recuperada);
                                oos.close();
                                System.out.println("Se ha vuelto a serializar");
                            }catch(IOException ioe){ioe.printStackTrace();}
                        break;

                        case 4:
                            System.out.println("Quitar producto del stock");
                            try{
                                ArrayList<Producto> lista = new ArrayList<Producto>();
                                FileInputStream fis = new FileInputStream(nombreArchivo);
                                ObjectInputStream ois = new ObjectInputStream(fis);
                                lista = (ArrayList<Producto>) ois.readObject();
                                ois.close();
                                //System.out.println("Otra vez se recuperaron los archivos");

                                System.out.print("Ingrese el codigo de barras del producto que quiere borrar: ");
                                String borrar = sc.next();

                                try{
                                    try{
                                        if(gestion.malFormato(borrar)==true){
                                            if(gestion.codigoNoExistente(lista, borrar)){
                                                gestion.quitarProducto(borrar, lista);
                                            }
                                        }
                                    }catch(CodigoMalFormadoException E){System.out.println(E);}
                                }catch(CodigoNoExistenteException e){System.out.println(e);}

                                //intentemos serializar de nuevo
                                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo));
                                oos.writeObject(lista);
                                oos.close();
                                //gestion.verTodosLosProductos(lista);
                            }catch(IOException ioe){ioe.printStackTrace();}
                        break;

                        case 5:
                            //System.out.println("Buscar producto");
                            try{
                                ArrayList<Producto> lista = new ArrayList<Producto>();
                                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo));
                                lista = (ArrayList<Producto>) ois.readObject();
                                ois.close();
                                //System.out.println("Se han recuperado los archivos");

                                System.out.print("Buscar producto: ");
                                String buscar = sc.nextLine();
                                gestion.buscarNombre(buscar, lista);
                            }catch(Exception e){e.printStackTrace();}
                        break;

                        case 6:
                            System.out.println("Surtir inventario");
                            try{
                                ArrayList<Producto> lista = new ArrayList<Producto>();
                                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo));
                                lista = (ArrayList<Producto>) ois.readObject();
                                ois.close();
                                
                                    System.out.print("Introduce el codigo de barras del producto: ");
                                    String c = sc.nextLine();
                                    try{    
                                        if(gestion.codigoNoExistente(lista, c)){            
                                            gestion.surtirInventario(c, lista);        
                                        }
                                    }catch(CodigoNoExistenteException cmf){System.out.println(cmf);}
                                

                                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo));
                                oos.writeObject(lista);
                                oos.close();

                            }catch(IOException e){e.printStackTrace();}
                        break;

                        case 7:
                            System.out.println("Ver ventas");
                            try{
                                ArrayList<Producto> lista = new ArrayList<Producto>();
                                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo));
                                lista = (ArrayList<Producto>) ois.readObject();
                                ois.close();

                                for(int i = 0; i < lista.size(); i++){
                                    int cantidadactual = lista.get(i).getCantidadDeUnidades();
                                    int precio = lista.get(i).getPrecioUnitario();
                                    int c = cantidadactual;
                                    System.out.println("cantidad actual: "+cantidadactual);
                                    System.out.println("cantidad guardada: "+c);
                                }

                            }catch(IOException e){e.printStackTrace();}
                        break;

                        case 8:
                            bandera = false;
                        break;

                    }
                }
            break;

            case 2:
                System.out.println("Cliente");
                try{
                    ArrayList<Producto> muestra_productos = new ArrayList<Producto>();
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo));
                    muestra_productos = (ArrayList<Producto>) ois.readObject();
                    ois.close();

                    for(int i = 0; i < muestra_productos.size(); i++){
                        System.out.println(i+". "+muestra_productos.get(i).getNombre()+", "
                        +muestra_productos.get(i).getCantidadDeUnidades()+" unidades $"+muestra_productos.get(i).getPrecioUnitario()+" c/u.");
                    }
                    System.out.println("-1. Salir.");

                    boolean seguir = true;
                    int gasto = 0;
                    while(seguir){
                        System.out.print("Qué vas a comprar?: ");
                        int opcion = sc.nextInt();
                        //int opcion = (int) o;

                        if(opcion == -1){
                            seguir = false;
                        }else if(opcion <= muestra_productos.size()-1){
                            System.out.println("Has elegido la opción "+opcion+", etonces quieres comprar "+muestra_productos.get(opcion).getNombre());
                            System.out.println("Cuantas unidades vas a comprar?: ");
                            int unidades = sc.nextInt();
                            if(unidades <= muestra_productos.get(opcion).getCantidadDeUnidades()){
                                System.out.println("vamos a restarle " + unidades + " unidades a " + muestra_productos.get(opcion).getCantidadDeUnidades() + " que son las unidades actuales.");
                                int nuevasUnidades = muestra_productos.get(opcion).getCantidadDeUnidades() - unidades;
                                System.out.println("Ahora solo hay " + nuevasUnidades + " " + muestra_productos.get(opcion).getNombre());
                                gasto = gasto + (unidades*muestra_productos.get(opcion).getPrecioUnitario());
                                muestra_productos.get(opcion).setCantidadDeUnidades(nuevasUnidades);
                            } else {System.out.println("Pides más de lo que hay");}
                        } else {
                            System.out.println("Esa opción no existe."); 
                        }
                    }
                    System.out.println("Tu cuenta final es de $"+gasto);

                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo));
                    oos.writeObject(muestra_productos);
                    oos.close();

                }catch(IOException ioe){ioe.printStackTrace();}

            break;

            case 3:
                continuar = false;
            break;
        }
        }
    }
}

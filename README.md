# proyecto-3
Supón que el dueño de una tienda de abarrotes se mudará a una app online y te
pide que le programes una aplicación para que lleve el control de sus compras,
inventario y clientes.
Al ejecutarse tu programa, te deberá preguntar si vas a acceder como administrador
o cliente.

Administrador
La tienda de abarrotes cuenta con los siguientes productos en su primer día de
apertura:
● GDHDH22 - Pizza congelada, 10 unidades $50 c/u
● JAHAA88 - Pasta dental, 23 unidades $14.50 c/u
● ADFBN17 - Refresco de limón, 15 unidades $22 c/u
● BHUWQ94 - Galletas de chocolate, 100 unidades $12 c/u
● ALUTG71 - Bolsa de papas, 40 unidades $17.50 c/u

Como podrás notar, un producto está compuesto por código de barras, nombre,
cantidad de unidades y precio unitario.

El dueño de la tienda podrá realizar lo siguiente:

1. Añadir producto nuevo
Pregunta el dueño los datos del producto a agregar: código de barras, nombre,
cantidad de unidades y precio unitario.
Los códigos de barras son valores únicos, si el dueño al trata de alta un producto
nuevo, este código ya está registrado, lanza una excepción de tipo
CodigoYaRegistradoException, en caso de que el código de barras no tenga la
forma 5 letras y 2 números, lanza una excepción de tipo
CodigoMalFormadoException.

2. Actualizar producto
Esta opción solicita el código de barras y pregunta qué se desea cambiar: Nombre o
precio. Si el código de barras está mal construído lanza la excepción
CodigoMalFormadoException, si el código no se encontró para ningún producto
lanza CodigoNoExistenteException

3. Quitar producto del stock
Esta opción pregunta el código de barras del producto y lo elimina por completo del
inventario. Si el código de barras está mal construído lanza la excepción
CodigoMalFormadoException, si el código no se encontró para ningún producto
lanza CodigoNoExistenteException

4. Ver todos los productos
Muestra un listado de todos los productos disponibles en la tienda, código de barras,
nombre, unidades disponibles y precio.

5. Buscar producto
Busca todos los productos que coincidan con una palabra.
Ejemplo: naranja
Encontrados...
RFTLL56 - Jugo de naranja 15 unidades $22 c/u
JKIOO72 - Fabuloso aroma naranja 3 unidades $43 c/u

6. Surtir inventario
Esta opción le pregunta al administrador que proporcione el código de barras del
producto y la cantidad de unidades nuevas que se van a sumar al inventario.

7. Ver ventas
Esta opción sólo mostrará un número de venta y el monto que se pagó.
Ejemplo:
1 - $120
2 - $430
3 - $52.50
....

8. Salir

Cliente
El cliente sólo podrá ver el listado de los artículos disponibles en forma de lista con
números y una opción extra de terminar compra, ejemplo:
1. Pizza congelada, 10 unidades $50 c/u

2. Pasta dental, 23 unidades $14.50 c/u
3. Refresco de limón, 15 unidades $22 c/u
4. Galletas de chocolate, 100 unidades $12 c/u
5. Bolsa de papas, 40 unidades $17.50 c/u
-1. Salir

El cliente pondrá el número del artículo seleccionado y posteriormente se le
preguntará la cantidad de unidades que desea tomar.
En caso de que la cantidad exceda el inventario, no se agregará el producto.
La opción -1 termina el proceso de compra e imprime en pantalla la cantidad a
pagar. Mostrando en pantalla nuevamente si se desea acceder como Cliente o
Administrador.

package catalogo3;

import java.util.Scanner;

public class Catalogo3 {
    
    // Clase interna Prenda
    
    static class Prenda {
        String nombre; // Atributos de la clase
        String categoria;
        double precio;

        Prenda(String nombre, String categoria, double precio) {
            
             //this se refiere a la instancia actual de la clase
             //Se usa para diferenciar entre los atributos de la clase y los parámetros de los métodos o constructores
             
            this.nombre = nombre;
            
             // this.nombre se refiere al atributo de la clase (la variable nombre de la clase Prenda).
             
            this.categoria = categoria;
            this.precio = precio;
        }

        public String toString() {
            return "Nombre: " + nombre + ", Categoría: " + categoria + ", Precio: $" + precio;
        }
    }

    // Arreglo y contador de prendas
    static Prenda[] catalogo = new Prenda[10];
    static int totalPrendas = 0;

    // Código de acceso como variable independiente
    static String codigoAcceso = "admin123"; //Codigo del empleado

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        // Agregar prendas iniciales
        agregarPrenda(new Prenda("Camiseta básica", "Ropa casual", 15.99));
        agregarPrenda(new Prenda("Jeans", "Ropa casual", 39.99));
        agregarPrenda(new Prenda("Vestido de noche", "Ropa formal", 89.99));

        do {
            System.out.println("\n=== Menú Principal ===");
            System.out.println("1. Apartado para Comprador");
            System.out.println("2. Apartado para Empleado");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    apartadoComprador(scanner);
                    break;
                case 2:
                    apartadoEmpleado(scanner);
                    break;
                case 3:
                    System.out.println("¡Gracias por usar el catálogo!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 3);

        scanner.close();
    }

    static void agregarPrenda(Prenda prenda) {
        if (totalPrendas < catalogo.length) { //Las posiciones de las letras
            catalogo[totalPrendas++] = prenda;
        } else {
            System.out.println("El catálogo está lleno. No se pueden agregar más prendas.");
        }
    }

    static void mostrarCatalogo() {
        System.out.println("\n=== Catálogo ===");
        if (totalPrendas == 0) {
            System.out.println("El catálogo está vacío.");
        } else {
            for (int i = 0; i < totalPrendas; i++) {
                System.out.println((i + 1) + ". " + catalogo[i]);
            }
        }
    }

    static void apartadoComprador(Scanner scanner) {
        System.out.println("\n=== Catálogo para Comprador ===");
        mostrarCatalogo();

        System.out.print("¿Desea comprar algo? (Sí/No): ");
        String respuesta = scanner.nextLine().toLowerCase();

        if (respuesta.equals("sí") || respuesta.equals("si")) {
            procesoDeCompra(scanner);
        } else {
            System.out.println("Regresando al menú principal.");
        }
    }

    static void procesoDeCompra(Scanner scanner) {
        double totalCompra = 0.0;
        boolean seguirComprando = true;
        
        /* Es utilizado para representar valores lógicos o booleanos
               Pueden tener solo dos valores posibles: true (verdadero) o false (falso).*/

        while (seguirComprando) {
            System.out.println("\n=== Selección de Ropa ===");
            mostrarCatalogo();
            System.out.print("Seleccione el número de la prenda que desea comprar (0 para finalizar la compra): ");
            int opcionCompra = scanner.nextInt();

            if (opcionCompra > 0 && opcionCompra <= totalPrendas) {
                Prenda prendaSeleccionada = catalogo[opcionCompra - 1];
                System.out.println("Ha seleccionado: " + prendaSeleccionada);
                totalCompra += prendaSeleccionada.precio;
                System.out.println("Total acumulado hasta ahora: $" + totalCompra);
                
            } else if (opcionCompra == 0) {
                seguirComprando = false;
                System.out.println("Compra finalizada.");
                System.out.println("Total a pagar: $" + totalCompra);
            } else {
                System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    static void apartadoEmpleado(Scanner scanner) {
        System.out.print("\nIngrese el código de acceso: ");
        String codigoIngreso = scanner.nextLine();

        if (codigoIngreso.equals(codigoAcceso)) {
            int opcion;
            do {
                System.out.println("\n=== Apartado para Empleado ===");
                System.out.println("1. Ver catálogo");
                System.out.println("2. Agregar prenda");
                System.out.println("3. Cambiar código de acceso");
                System.out.println("4. Regresar al menú principal");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        mostrarCatalogo();
                        break;
                    case 2:
                        System.out.print("Ingrese el nombre de la prenda: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Ingrese la categoría de la prenda: ");
                        String categoria = scanner.nextLine();
                        System.out.print("Ingrese el precio de la prenda: ");
                        double precio = scanner.nextDouble();
                        agregarPrenda(new Prenda(nombre, categoria, precio));
                        break;
                    case 3:
                        cambiarCodigoAcceso(scanner);
                        break;
                    case 4:
                        System.out.println("Regresando al menú principal.");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } while (opcion != 4);
        } else {
            System.out.println("Código de acceso incorrecto. Acceso denegado.");
        }
    }

    static void cambiarCodigoAcceso(Scanner scanner) {
        System.out.print("Ingrese el nuevo código de acceso: ");
        codigoAcceso = scanner.nextLine();
        System.out.println("Código de acceso actualizado exitosamente.");
    }
}

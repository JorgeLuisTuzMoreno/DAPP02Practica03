package org.uv.dapp02practica03;

import java.util.List;
import java.util.Scanner;

public class DAPP02Practica03 {

    public static void main(String[] args) {
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Menú CRUD Empleados ---");
            System.out.println("1. Agregar Empleado");
            System.out.println("2. Modificar Empleado");
            System.out.println("3. Buscar Empleado por ID");
            System.out.println("4. Mostrar Todos los Empleados");
            System.out.println("5. Eliminar Empleado");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese dirección: ");
                    String direccion = scanner.nextLine();
                    System.out.print("Ingrese teléfono: ");
                    String telefono = scanner.nextLine();

                    EmpleadoPojo nuevoEmpleado = new EmpleadoPojo();
                    nuevoEmpleado.setNombre(nombre);
                    nuevoEmpleado.setDireccion(direccion);
                    nuevoEmpleado.setTelefono(telefono);

                    EmpleadoPojo empleadoGuardado = empleadoDAO.guardar(nuevoEmpleado);
                    System.out.println("Empleado guardado: " + empleadoGuardado);
                    break;

                case 2:
                    System.out.print("Ingrese ID del empleado a modificar: ");
                    Long idModificar = scanner.nextLong();
                    scanner.nextLine(); // Consumir el salto de línea

                    EmpleadoPojo empleadoExistente = empleadoDAO.buscarById(idModificar);
                    if (empleadoExistente != null) {
                        System.out.print("Nuevo nombre (" + empleadoExistente.getNombre() + "): ");
                        String nuevoNombre = scanner.nextLine();
                        System.out.print("Nueva dirección (" + empleadoExistente.getDireccion() + "): ");
                        String nuevaDireccion = scanner.nextLine();
                        System.out.print("Nuevo teléfono (" + empleadoExistente.getTelefono() + "): ");
                        String nuevoTelefono = scanner.nextLine();

                        empleadoExistente.setNombre(nuevoNombre.isEmpty() ? empleadoExistente.getNombre() : nuevoNombre);
                        empleadoExistente.setDireccion(nuevaDireccion.isEmpty() ? empleadoExistente.getDireccion() : nuevaDireccion);
                        empleadoExistente.setTelefono(nuevoTelefono.isEmpty() ? empleadoExistente.getTelefono() : nuevoTelefono);

                        EmpleadoPojo empleadoModificado = empleadoDAO.modificar(empleadoExistente, idModificar);
                        System.out.println("Empleado modificado: " + empleadoModificado);
                    } else {
                        System.out.println("Empleado no encontrado.");
                    }
                    break;

                case 3:
                    System.out.print("Ingrese ID del empleado a buscar: ");
                    Long idBuscar = scanner.nextLong();
                    scanner.nextLine(); // Consumir el salto de línea

                    EmpleadoPojo empleadoBuscado = empleadoDAO.buscarById(idBuscar);
                    System.out.println(empleadoBuscado != null ? "Empleado encontrado: " + empleadoBuscado : "Empleado no encontrado.");
                    break;

                case 4:
                    List<EmpleadoPojo> empleados = empleadoDAO.buscarAll();
                    System.out.println("Lista de empleados:");
                    for (EmpleadoPojo emp : empleados) {
                        System.out.println(emp);
                    }
                    break;

                case 5:
                    System.out.print("Ingrese ID del empleado a eliminar: ");
                    Long idEliminar = scanner.nextLong();
                    scanner.nextLine(); // Consumir el salto de línea

                    boolean eliminado = empleadoDAO.eliminar(idEliminar);
                    System.out.println(eliminado ? "Empleado eliminado." : "Empleado no encontrado.");
                    break;

                case 6:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
}

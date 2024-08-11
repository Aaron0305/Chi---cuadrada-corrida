
package menu;

import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        boolean continuar = true;
        while (continuar) {
            System.out.println("Menu Principal");
            System.out.println("1. Multiplicador");
            System.out.println("2. Chi Cuadrada");
            System.out.println("3. Corrida");
            System.out.println("4. Salir");
            System.out.println("Seleccione una opción:");

            int opcion = entrada.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Ejecutando el programa Multiplicador...");
                    ejecutarMultiplicador();
                    break;
                case 2:
                    System.out.println("Ejecutando el programa Chi Cuadrada...");
                    ejecutarChiCuadrada();
                    break;
                case 3:
                    System.out.println("Ejecutando el programa Corrida...");
                    ejecutarCorrida();
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }
    }

    public static void ejecutarMultiplicador() {
        Scanner entrada = new Scanner(System.in);
        boolean repetir = true;
        while (repetir) {
            long semilla;
            do {
                System.out.println("Ingrese la semilla positiva de 4 digitos: ");
                while (!entrada.hasNextLong()) {
                    System.out.println("Error: Solo se pueden ingresar números. Intente de nuevo.");
                    entrada.next(); // Limpiar el buffer del scanner
                }
                semilla = entrada.nextLong();
                if (semilla < 0 || String.valueOf(semilla).length() != 4) {
                    System.out.println("La semilla debe ser un numero positivo de 4 digitos");
                }
            } while (semilla < 0 || String.valueOf(semilla).length() != 4);

            long a;
            do {
                System.out.println("Ingrese el valor de 'a' positivo de 4 digitos: ");
                while (!entrada.hasNextLong()) {
                    System.out.println("Error: Solo se pueden ingresar números. Intente de nuevo.");
                    entrada.next(); // Limpiar el buffer del scanner
                }
                a = entrada.nextLong();
                if (a < 0 || String.valueOf(a).length() != 4) {
                    System.out.println("El valor de 'a' debe ser un número positivo de 4 dígitos");
                }
            } while (a < 0 || String.valueOf(a).length() != 4);

            System.out.println("Ingrese el numero de veces que desea repetir el proceso: ");
            int n;
            while (!entrada.hasNextInt()) {
                System.out.println("Error: Solo se pueden ingresar números enteros. Intente de nuevo.");
                entrada.next(); // Limpiar el buffer del scanner
            }
            n = entrada.nextInt();

            for (int i = 1; i <= n; i++) {
                // Multiplicar a por la semilla
                long resultado = a * semilla;

                // Verificar si el resultado es "0000"
                if (resultado == 0) {
                    System.out.println("El resultado se convirtió en 0000. Saliendo del programa.");
                    break;
                }

                // Convertir el resultado a String
                String resultadoString = String.valueOf(resultado);

                // Asegurar que haya exactamente 4 dígitos
                while (resultadoString.length() < 4) {
                    resultadoString = "0" + resultadoString;
                }

                // Tomar los 4 números del medio
                String nuevaSemillaString = resultadoString.substring(resultadoString.length() / 2 - 2, resultadoString.length() / 2 + 2);
                long nuevaSemilla = Long.parseLong(nuevaSemillaString);

                // Imprimir los resultados antes y después de la división
                double resultadoAntesDeDivision = Double.parseDouble(nuevaSemillaString);
                double resultadoDespuesDeDivision = resultadoAntesDeDivision / Math.pow(10, 4);
                System.out.printf("%d. %04d --> %.4f\n", i, nuevaSemilla, resultadoDespuesDeDivision);

                // Actualizar la semilla para la siguiente iteración
                semilla = nuevaSemilla;
            }

            // Preguntar si desea repetir el proceso
            System.out.println("Desea repetir el proceso (Si: 1, No: 0)");
            int opcion;
            while (!entrada.hasNextInt()) {
                System.out.println("Error: Solo se pueden ingresar números enteros. Intente de nuevo.");
                entrada.next(); // Limpiar el buffer del scanner
            }
            opcion = entrada.nextInt();
            repetir = (opcion == 1);
        }
    }

    public static void ejecutarChiCuadrada() {
        Scanner entrada = new Scanner(System.in);
        boolean repetir = true;
        while (repetir) {
            long semilla;
            do {
                System.out.println("Ingrese la semilla positiva de 4 digitos: ");
                semilla = entrada.nextLong();
                if (semilla < 0 || String.valueOf(semilla).length() != 4) {
                    System.out.println("La semilla debe ser un numero positivo de 4 digitos");
                }
            } while (semilla < 0 || String.valueOf(semilla).length() != 4);

            long a;
            do {
                System.out.println("Ingrese el valor de 'a' positivo de 4 digitos: ");
                a = entrada.nextLong();
                if (a < 0 || String.valueOf(a).length() != 4) {
                    System.out.println("El valor de 'a' debe ser un número positivo de 4 dígitos");
                }
            } while (a < 0 || String.valueOf(a).length() != 4);

            System.out.println("Ingrese el numero de veces que desea repetir el proceso: ");
            int n = entrada.nextInt();

            // Solicitar nivel de confianza
            int nivelConfianza;
            do {
                System.out.println("Ingrese el nivel de confianza (entre 0 y 100): ");
                nivelConfianza = entrada.nextInt();
                if (nivelConfianza < 0 || nivelConfianza > 100) {
                    System.out.println("El nivel de confianza debe estar entre 0 y 100");
                }
            } while (nivelConfianza < 0 || nivelConfianza > 100);
            int lamda = 100 - nivelConfianza; // Calcular lamda

            // Convertir lamda a punto decimal
            double lamdaDecimal = lamda / 100.0;

            // Calcular la raíz cuadrada de n
            double m = Math.sqrt(n);
            double mEnTabla = m - 1;

            int[] oi = new int[10]; // Tabla para contar los datos en cada rango
            double[] ei = new double[10]; // Tabla para almacenar Ei
            double[] resultado = new double[10]; // Tabla para almacenar los resultados

            for (int i = 1; i <= n; i++) {
                // Multiplicar a por la semilla
                long resultadoMultiplicacion = a * semilla;

                // Convertir el resultado a String
                String resultadoString = String.valueOf(resultadoMultiplicacion);

                // Tomar los 4 números del medio
                String nuevaSemillaString = resultadoString.substring(resultadoString.length() / 2 - 2, resultadoString.length() / 2 + 2);
                long nuevaSemilla = Long.parseLong(nuevaSemillaString);

                // Calcular el valor del resultado antes de la división
                double resultadoAntesDeDivision = Double.parseDouble(nuevaSemillaString);
                double resultadoDespuesDeDivision = resultadoAntesDeDivision / Math.pow(10, 4);

                // Actualizar la tabla "oi" contando los datos en cada rango
                int index = (int) (resultadoDespuesDeDivision * 10);
                if (index == 10) {
                    index = 9; // Para evitar un IndexOutOfBoundsException
                }
                oi[index]++;

                // Actualizar la semilla para la siguiente iteración
                semilla = nuevaSemilla;
            }

            // Calcular Ei
            for (int j = 0; j < 10; j++) {
                ei[j] = n / m;
            }

            // Calcular Resultado
            for (int k = 0; k < 10; k++) {
                resultado[k] = Math.pow((ei[k] - oi[k]), 2) / ei[k];
            }

            // Sumar todos los resultados
            double sumaResultados = 0;
            for (double res : resultado) {
                sumaResultados += res;
            }

            // Imprimir tabla de resultados
            System.out.println("Tabla de resultados:");
            System.out.println("Rango\tOi\tEi\tResultado");
            for (int l = 0; l < 10; l++) {
                System.out.printf("%.1f-%.1f\t%d\t%.1f\t%.2f\n", l / 10.0, (l + 1) / 10.0, oi[l], ei[l], resultado[l]);
            }

            // Imprimir suma de resultados
            System.out.println("\nSuma de todos los resultados: " + sumaResultados);

            // Imprimir valor de m y mEnTabla
            System.out.println("\nValor de m en tabla: " + mEnTabla);

            // Imprimir nivel de confianza (lambda)
            System.out.println("\nNivel de confianza (lambda) en punto decimal, buscar en tabla: " + lamdaDecimal);

            // Indicar buscar valores en la tabla de chi cuadrado
            System.out.println("\nPor favor, busque los valores en la tabla de chi cuadrado.");

            System.out.println("\nSi la suma de resultados es menor al de tablas de dice que el conjunto de numeros"
                    + "sigue distribucion uniforme");
            System.out.println("Si la suma de resultados en mayor al de tablas no sigue distribucion uniforme");
            // Preguntar si desea repetir el proceso
            System.out.println("Desea repetir el proceso (Si: 1, No: 0)");
            int opcion = entrada.nextInt();
            repetir = (opcion == 1);
        }
    }

    public static void ejecutarCorrida() {
        Scanner entrada = new Scanner(System.in);
        boolean repetir = true;
        while (repetir) {
            long semilla;
            do {
                System.out.println("Ingrese la semilla positiva de 4 dígitos: ");
                while (!entrada.hasNextLong()) {
                    System.out.println("Error: Solo se pueden ingresar números. Intente de nuevo.");
                    entrada.next(); // Limpiar el buffer del scanner
                }
                semilla = entrada.nextLong();
                if (semilla < 0 || String.valueOf(semilla).length() != 4) {
                    System.out.println("La semilla debe ser un número positivo de 4 dígitos");
                }
            } while (semilla < 0 || String.valueOf(semilla).length() != 4);

            long a;
            do {
                System.out.println("Ingrese el valor de 'a' positivo de 4 dígitos: ");
                while (!entrada.hasNextLong()) {
                    System.out.println("Error: Solo se pueden ingresar números. Intente de nuevo.");
                    entrada.next(); // Limpiar el buffer del scanner
                }
                a = entrada.nextLong();
                if (a < 0 || String.valueOf(a).length() != 4) {
                    System.out.println("El valor de 'a' debe ser un número positivo de 4 dígitos");
                }
            } while (a < 0 || String.valueOf(a).length() != 4);

            System.out.println("Ingrese el número de veces que desea repetir el proceso: ");
            int n;
            while (!entrada.hasNextInt()) {
                System.out.println("Error: Solo se pueden ingresar números enteros. Intente de nuevo.");
                entrada.next(); // Limpiar el buffer del scanner
            }
            n = entrada.nextInt();

            double[] resultados = new double[n];
            for (int i = 0; i < n; i++) {
                // Multiplicar a por la semilla
                long resultado = a * semilla;

                // Convertir el resultado a String y asegurar que haya exactamente 4 dígitos
                String resultadoString = String.valueOf(resultado);
                while (resultadoString.length() < 4) {
                    resultadoString = "0" + resultadoString;
                }

                // Tomar los 4 números del medio y calcular el resultado después de la división
                String nuevaSemillaString = resultadoString.substring(resultadoString.length() / 2 - 2, resultadoString.length() / 2 + 2);
                double resultadoDespuesDeDivision = Double.parseDouble(nuevaSemillaString) / Math.pow(10, 4);

                // Almacenar el resultado en el arreglo
                resultados[i] = resultadoDespuesDeDivision;

                // Actualizar la semilla para la siguiente iteración
                semilla = Long.parseLong(nuevaSemillaString);
            }

            // Comparar resultados y almacenar el resultado en una nueva fila
            int[] nuevaFila = new int[n - 1];
            for (int i = 0; i < n - 1; i++) {
                if (resultados[i] <= resultados[i + 1]) {
                    nuevaFila[i] = 0;
                } else {
                    nuevaFila[i] = 1;
                }
            }

            // Imprimir los resultados
            System.out.println("Resultados:");
            for (double resultado : resultados) {
                System.out.printf("%.4f ", resultado);
            }
            System.out.println();

            // Imprimir la nueva fila
            System.out.println("Nueva fila de comparaciones:");
            for (int valor : nuevaFila) {
                System.out.print(valor + " ");
            }
            System.out.println(); // Agregar un salto de línea

            // Solicitar valor de Co
            System.out.println("Ingrese el valor de Co (conteo manual de cambios de 0 a 1 o viceversa): ");
            int Co;
            while (!entrada.hasNextInt()) {
                System.out.println("Error: Solo se pueden ingresar números enteros. Intente de nuevo.");
                entrada.next(); // Limpiar el buffer del scanner
            }
            Co = entrada.nextInt();

            // Calcular Mo
            double Mo = (2 * n - 1) / 3.0;

            // Calcular O2
            double O2 = (16 * n - 29) / 90.0;

            // Calcular O
            double O = (Co - Mo) / Math.sqrt(O2);

            // Solicitar nivel de confianza y calcular lamda y Z
            System.out.println("Ingrese el nivel de confianza (0-100): ");
            int nivelConfianza;
            while (!entrada.hasNextInt()) {
                System.out.println("Error: Solo se pueden ingresar números enteros. Intente de nuevo.");
                entrada.next(); // Limpiar el buffer del scanner
            }
            nivelConfianza = entrada.nextInt();
            double lamda = (100 - nivelConfianza) / 100.0;
            double Z = 1 - lamda / 2;

            // Imprimir resultados
            System.out.println("Resultados:");
            System.out.println("Mo: " + Mo);
            System.out.println("O2: " + O2);
            System.out.println("O: " + O);
            System.out.println("lamda: " + lamda);
            System.out.println("Z: " + Z);

            System.out.println("BUSCAR EL VALOR DE Z EN TABLAS Y SUMAR EL VALOR FILA Y DE LA COLUMNA, ES Zo");
            System.out.println("SI ES VALOR DE Z es mayor que Zo Son intependientes, numeros aleatorios ");
            System.out.println("Si es valor de Zo es mayor que Z No son numeros aleatorios");
            // Preguntar si desea repetir el proceso
            System.out.println("Desea repetir el proceso (Si: 1, No: 0)");
            int opcion;
            while (!entrada.hasNextInt()) {
                System.out.println("Error: Solo se pueden ingresar números enteros. Intente de nuevo.");
                entrada.next(); // Limpiar el buffer del scanner
            }
            opcion = entrada.nextInt();
            repetir = (opcion == 1);
        }
    }
}


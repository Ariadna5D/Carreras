package carreras;

import java.util.Random;
import java.util.Scanner;

public class Carreras {

    static int nCorredores = 0;
    static Corredor[] corredores;
    static boolean hayCarreraEnCurso;

    public static void main(String[] args) {
        
        Opciones();

    }

    /**
     * Muestra el menu para interactuar con el programa
     */
    private static void Opciones() {

        for (int i = 0; i < 3; i++) {
            System.out.println("");
        }
        
        System.out.println("*************************");
        System.out.println("* 1- Añadir Corredor    *");
        System.out.println("* 2- Mostrar Corredores *");
        System.out.println("* 3- Empezar Carrera    *");
        System.out.println("* 4- Vaciar Corredores  *");
        System.out.println("* 5- Salir              *");
        System.out.println("*************************");

        switch (lineaConsola("Seleccione una opcion introduciendo un número").charAt(0)) {
            case '1':
                AñadirCorredor(lineaConsola("Introduce un nombre al corredor"), entero("Introduce la velocidad a la que avanzará este corredor"), lineaConsola("Introduce un caracter con el que se mostrará en la carrera").charAt(0));
                Opciones();
                break;
            case '2':
                PintarListadeCorredores();
                Opciones();
                break;
            case '3':
                if (nCorredores>0) {
                    PrepararCorredores(entero("Introduce la longitud del recorrido"));
                    ComenzarCarrera();
                }
                else{
                    System.out.println("No hay corredores para la carrera");
                    Opciones();
                }                
                break;
            case '4':
                VaciarCorredores();
                Opciones();
                break;
            case '5':
                //Salir
                break;
            default:
                Opciones();
                throw new AssertionError();
        }

    }
    
    /**
     * Pinta una lista de corredores numerados por el oden en el que se han añadido
     */
    private static void PintarListadeCorredores(){
        int n = 1;
        for (Corredor c : corredores) {
            System.out.println(n + "- " + "Nombre: " + c.getNombre());
            n++;
        }
                
    }

    /**
     * Metodo para pedir al usuario un texto
     * @param texto : texto que se pintara antes de pedir el texto
     * @return : String
     */
    private static String lineaConsola(String texto) {
        System.out.println(texto);
        String linea = "A";
        boolean esValido = false;

        Scanner sc = new Scanner(System.in);

        while (!esValido) {
            linea = sc.nextLine();

            if (!linea.isBlank()) {
                esValido = true;

            } else {
                System.out.println("Por favor introduce un caracter como mínimo.");
                sc.nextLine();
            }
        }

        return linea;

    }

    /**
     * Metodo para pedir un entero
     * @param texto : Texto que se pintara antes de pedir el numero
     * @return :numero entero
     */
    private static int entero(String texto) {
        System.out.println(texto);
        Scanner sc = new Scanner(System.in);
        int numero = 10;
        boolean esValido = false;

        while (!esValido) {
            try {
                numero = sc.nextInt();
                if (numero >0) {
                    esValido = true;                
                }
                else{
                    System.out.println("Debe ser un numero mayor a 0");
                    sc.nextLine();
                }
            } catch (Exception e) {
                System.out.println("No es un numero.");
                sc.nextLine();
            }
        }

        return numero;

    }

    /**
     * Este método lo que hace es añadir un corredor a la lista de corredores
     *
     * @param nombre : Nombre que tendrá el corredor
     * @param velocidad : Velocidad con la que correrá cuando tenga la
     * oportunidad
     * @param simbolo : El carácter con el que se mostrará en la carrera
     */
    private static void AñadirCorredor(String nombre, int velocidad, char simbolo) {

        Corredor[] aux;
        aux = new Corredor[nCorredores + 1];
        for (int i = 0; i < aux.length; i++) {
            if (i != aux.length - 1) {
                aux[i] = corredores[i];
            } else {
                aux[i] = new Corredor(nombre, velocidad, simbolo);
            }
        }

        nCorredores++;
        corredores = aux;
    }
    
    /**
     * Vacía la lista de corredores
     */
    private static void VaciarCorredores(){
        Corredor[] aux;
        aux = new Corredor[0];
        corredores = aux;
    }
    

    /**
     * Prepara todos los corredores para correr una distancia concreta
     *
     * @param longitudCarrera : La distancia que se correrá
     */
    private static void PrepararCorredores(int longitudCarrera) {
        for (Corredor c : corredores) {
            c.PrepararCarrera(longitudCarrera);
        }
    }

    /**
     * Pinta/Refresca el recorrido, para saber cómo va cada corredor
     */
    private static void PintarRecorridos() {
        for (Corredor c : corredores) {
            System.out.println(c.Carretera());
        }

    }

    /**
     * Inicia la carrera con los parametros establecidos
     */
    private static void ComenzarCarrera() {
        for (Corredor c : corredores) {
            c.ResetPosicion();
        }
        
        hayCarreraEnCurso = true;
        
        while (hayCarreraEnCurso) {
            try {
                Thread.sleep(500);
                System.out.print("\n".repeat(30));
                AdelantarCorredor();
            } catch (Exception e) {
            }
        }
    }
    
    private static void PararCarrera(String ganador){
        hayCarreraEnCurso = false;
        for (int i = 0; i < 10; i++) {
            System.out.println("");
        }
        
        System.out.println("Ha ganado " + ganador + "!!!");
        
        
        switch (lineaConsola("Quieres repetir la carrera? S/N").charAt(0)) {
            case 'S':
            case 's':
                ComenzarCarrera();
                break;
            case 'N':
            case 'n':
                Opciones();
                break;
            default:
                Opciones();
        }
        
    }

    /**
     * Para indicar qué corredor avanzará, genera un numero aleatorio, y el
     * indicado es el que correrá, de esta manera nunca habrá empates
     */
    private static void AdelantarCorredor() {

        Random random = new Random();

        corredores[random.nextInt(corredores.length)].Correr();
        PintarRecorridos();

        for (Corredor c : corredores) {
            if (c.getHaTerminado()) {
                PararCarrera(c.getNombre());
            }
        }
    }

}

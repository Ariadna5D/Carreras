
package carreras;


public class Corredor {

private int distancia;
private char ficha;
private String nombre;
private int velocidad;
private int posicion;
private boolean haTerminado;

    /**
     * Constructor de corredor
     * @param newnombre : Indica con que nombre se lereferira
     * @param newvelocidad : Indica cuando avanza cuando corre
     * @param newficha : Indica el simbolo con el que se representara en la carrera
     */
    public Corredor(String newnombre, int newvelocidad, char newficha) {
        nombre = newnombre;    
        velocidad = newvelocidad;
        ficha = newficha;
    }
    
    /**
     * 
     * Constructor de corredor
     * @param newnombre : Indica con que nombre se lereferira
     * @param newvelocidad : Indica cuando avanza cuando corre
     * @param newficha : Indica el simbolo con el que se representara en la carrera
     * @param newdistancia : Indica la distancia del recorrido/carrera
     */
    public Corredor(String newnombre, int newvelocidad, char newficha, int newdistancia) {
        nombre = newnombre;    
        velocidad = newvelocidad;
        ficha = newficha;
        distancia = newdistancia;
    }
    
    /**
     * Asigna la distancia a la que terminara la carrera
     * @param newdistancia : Distancia del recorrido
     */
    public void PrepararCarrera(int newdistancia){
        distancia = newdistancia;
        posicion = 0;
    }
    
    /**
     * Devuelve la cadena que representa el recorrido, y por donde va el corredor
     * @return : String del recorrido "____o_"
     */
    public String Carretera(){
        String s = "";
        for (int i = 0; i < distancia; i++) {
            if (i == posicion) {
              s = s + ficha;
            }
            else{
              s = s + "_";  
            }
        }
        return s;
    }
    
    /**
     * Este metodo se encarga de avanzar al corredor
     */
    public void Correr(){
        posicion += velocidad;
        if (posicion >= distancia-1) {
            haTerminado = true;
            posicion=distancia-1;
        }  
    }
    
    /**
     * Reinicia la posicion a 0 del corredor
     */
    public void ResetPosicion(){
        haTerminado = false;
        posicion = 0;
    }

    /**
     * Devuelve el nombre del corredor
     * @return : nombre
     */
    public String getNombre(){
        return nombre;
    }
    
    /**
     * Devuelve si ha llegado al final de la carrera o no
     * @return : Si ha terminado = true
     */
    public boolean getHaTerminado(){
        return haTerminado;
    }
    
}

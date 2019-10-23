import java.util.ArrayList;
import java.util.Random;

/**
 * Clase gestora del tablero de juego.
 * Guarda una matriz de enteros representado el tablero.
 * Si hay una mina en una posición guarda el número -1
 * Si no hay una mina, se guarda cuántas minas hay alrededor.
 * Almacena la puntuación de la partida
 * @author jesusredondogarcia
 *
 */
public class ControlJuego {
	private final static int MINA = -1;
	final int MINAS_INICIALES = 20;
	final int LADO_TABLERO = 10;

	private int [][] tablero;
	private int puntuacion;
	
	
	public ControlJuego() {
		//Creamos el tablero:
		tablero = new int[LADO_TABLERO][LADO_TABLERO];
		
		//Inicializamos una nueva partida
		inicializarPartida();
	}
	
	
	/**Método para generar un nuevo tablero de partida:
	 * @pre: La estructura tablero debe existir. 
	 * @post: Al final el tablero se habrá inicializado con tantas minas como marque la variable MINAS_INICIALES. 
	 * 			El resto de posiciones que no son minas guardan en el entero cuántas minas hay alrededor de la celda
	 */
	public void inicializarPartida(){
		int numI;
		int numJ;
		int cont=0;
		
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				tablero[i][j]=0;
			}
		}
		//TODO: Repartir minas e inicializar puntaci�nn. Si hubiese un tablero anterior, lo pongo todo a cero para inicializarlo.
		while(cont<20) {
			numI =(int) (Math.random()*10);
			numJ =(int) (Math.random()*10);
			if(tablero[numI][numJ]!=MINA) {
				tablero[numI][numJ]=MINA;
				cont++;
			}
		}
		
		
		//Al final del metodo hay que guardar el numero de minas para las casillas que no son mina:
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				if (tablero[i][j] != MINA){
					tablero[i][j] = calculoMinasAdjuntas(i,j);
				}
			}
		}
		depurarTablero();
	}
	
	/**C�lculo de las minas adjuntas: 
	 * Para calcular el n�mero de minas tenemos que tener en cuenta que no nos salimos nunca del tablero.
	 * Por lo tanto, como mucho la i y la j valdr�n LADO_TABLERO-1.
	 * Por lo tanto, como poco la i y la j valdran 0.
	 * @param i: posicion vertical de la casilla a rellenar
	 * @param j: posicion horizontal de la casilla a rellenarif(
	 * @return : El numero de minas que hay alrededor de la casilla [i][j]
	 **/
	private int calculoMinasAdjuntas(int posY, int posX){
		int numMinas = 0;
		for (int i = posY-1; i <= posY+1; i++) {
			for (int j = posX-1; j <= posX+1; j++) {
				if((i>=0)&&(j>=0)&&(i<LADO_TABLERO)&&(j<LADO_TABLERO)) {
					if(tablero[i][j]==MINA) {
						numMinas++;
					}
				}
			}
		}
		return numMinas;
	}
	
	/**
	 * Método que nos permite 
	 * @pre : La casilla nunca debe haber sido abierta antes, no es controlado por el ControlJuego. Por lo tanto siempre sumaremos puntos
	 * @param i: posición verticalmente de la casilla a abrir
	 * @param j: posición horizontalmente de la casilla a abrir
	 * @return : Verdadero si no ha explotado una mina. Falso en caso contrario.
	 */
	public boolean abrirCasilla(int i, int j){
		if(tablero[i][j]==MINA) {
			return false;
		}else {
			puntuacion++;
			tablero[i][j] = calculoMinasAdjuntas(i, j);
			return true;
		}
	}
	
	
	
	/**
	 * M�todo que checkea si se ha terminado el juego porque se han abierto todas las casillas.
	 * @return Devuelve verdadero si se han abierto todas las celdas que no son minas.
	 **/
	public boolean esFinJuego(){
		boolean fin = true;
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				if(!String.valueOf(tablero[i][j]).equalsIgnoreCase("-")) {
					fin = false;
				}
			}
		};
		return fin;
	}
	
	
	/**
	 * Método que pinta por pantalla toda la información del tablero, se utiliza para depurar
	 */
	public void depurarTablero(){
		System.out.println("---------TABLERO--------------");
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				System.out.print(tablero[i][j]+"\t");
			}
			System.out.println();
		}
		System.out.println("\nPuntuaci�n: "+puntuacion); 
	}

	/**
	 * Método que se utiliza para obtener las minas que hay alrededor de una celda
	 * @pre : El tablero tiene que estar ya inicializado, por lo tanto no hace falta calcularlo, símplemente consultarlo
	 * @param i : posicion vertical de la celda.
	 * @param j : posicion horizontal de la cela.
	 * @return Un entero que representa el número de minas alrededor de la celda
	 */
	public int getMinasAlrededor(int posY, int posX) {
		return tablero[posY][posX];
	}

	/**
	 * Método que devuelve la puntuación actual
	 * @return Un entero con la puntuación actual
	 */
	public int getPuntuacion() {
		return puntuacion;
	}
	
}

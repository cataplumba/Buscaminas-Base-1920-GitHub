import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;



/**
 * Clase que implementa el listener de los botones del Buscaminas.
 * De alguna manera tendrá que poder acceder a la ventana principal.
 * Se puede lograr pasando en el constructor la referencia a la ventana.
 * Recuerda que desde la ventana, se puede acceder a la variable de tipo ControlJuego
 * @author jesusredondogarcia
 **
 */
public class ActionBoton implements ActionListener{
	final static int MINA = -1;
	private VentanaPrincipal ventana;
	private int posY;
	private int posX;

	

	public ActionBoton(VentanaPrincipal ventana,int posY,int posX) {
		this.ventana = ventana;
		this.posY = posY;
		this.posX = posX;
	}
	
	/**
	 *Acción que ocurrirá cuando pulsamos uno de los botones.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if((((JButton)e.getSource()).getText()).equals(String.valueOf(MINA))) {
			JOptionPane.showMessageDialog(null, "HAS PULSADO UNA MINA","FIN DEL JUEGO",1);
			ventana.mostrarFinJuego(true);
			
		}else {
			ventana.mostrarNumMinasAlrededor(posY, posX);
			//ventana.botonesJuego[posY][posX].setText(Integer.toString(ventana.getJuego().getMinasAlrededor(posY, posX)));
		}
	}

}

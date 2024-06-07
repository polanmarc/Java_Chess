package gui;

import joc.*;
import peces.*;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class EscacGUI extends JLabel implements MouseListener {
	
	private Peca pieza;
    private Point piezaLoc;
    private TaulerGUI tablero;
    private MyColor colorOriginalCasilla;

    public EscacGUI(MyColor color, Peca pieza, TaulerGUI tablero) {
        this.pieza = pieza;
        this.tablero = tablero; 
        this.piezaLoc = new Point();
        this.colorOriginalCasilla = color;
        initialize(color, pieza);
        añadirImagen(color, pieza);
        addMouseListener(this);
    }

	private void initialize(MyColor color, Peca pieza) {
		setOpaque(true);
		if (color == MyColor.NEGRE) {
			setBackground(Color.black);
		} else {
			setBackground(Color.white);
		}
	}
	
	public void añadirImagen(MyColor color, Peca pieza) {
		if (pieza != null) {
			String nombre = pieza.getClass().getSimpleName().toLowerCase();
			String equipo = pieza.toString().substring(1);
			String resultado = nombre + "_" + equipo + ".png";
			setIcon(new ImageIcon("img/"+resultado));
		}
	}
	
	public void quitarImagen() {
		setIcon(null);
	}
	
	public void quitarResaltado() {
	    setBackground((this.colorOriginalCasilla == MyColor.BLANC) ? Color.white : Color.black);
	    setBorder(null);
	}
	
	public Peca getPieza() {
		return pieza;
	}

	public TaulerGUI getTablero() {
		return tablero;
	}

	public void setTablero(TaulerGUI tablero) {
		this.tablero = tablero;
	}

	public MyColor getColorOriginalCasilla() {
		return colorOriginalCasilla;
	}

	public void setColorOriginalCasilla(MyColor colorOriginalCasilla) {
		this.colorOriginalCasilla = colorOriginalCasilla;
	}

	public void setPieza(Peca pieza) {
		this.pieza = pieza;
	}

	public Point getPiezaLoc() {
		return piezaLoc;
	}

	public void setPiezaLoc(Point piezaLoc) {
		this.piezaLoc = piezaLoc;
	}
	
	// Son 75x70 pixeles por casilla
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Point posCasilla = getLocation();
		
		int saberX = posCasilla.x - (posCasilla.x % 70);
		this.piezaLoc.x = saberX / 70;
		
		int saberY = posCasilla.y - (posCasilla.y % 70);
		this.piezaLoc.y = Math.abs((saberY / 70) - 7);
		
	    this.tablero.casillasSeleccionada(this.piezaLoc.x, this.piezaLoc.y); 

	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
}

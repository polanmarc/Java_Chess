package gui;

import joc.*;
import peces.*;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;

public class TaulerGUI {

	private JFrame taulerGUI;
	private JPanel panel;
	private EscacGUI[][] escacs;
	private Posicio posSelect;
	private Tauler tauler;
	private MyColor equipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TaulerGUI window = new TaulerGUI();
					window.taulerGUI.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TaulerGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.taulerGUI = new JFrame();
		this.equipo = MyColor.BLANC;
		this.taulerGUI.setBounds(100, 100, 600, 600);
		this.taulerGUI.setResizable(false);
		this.taulerGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.escacs = new EscacGUI[8][8];

		crearTablero();
	}

	public void crearTablero() {
		this.panel = new JPanel();
		this.taulerGUI.getContentPane().add(this.panel, BorderLayout.NORTH);
		this.panel.setLayout(new GridLayout(8, 8, 0, 0));

		this.tauler = new Tauler();
		this.tauler.crearPartida();

		for (int j = 7; j >= 0; j--) {
			for (int i = 0; i < 8; i++) {
				Posicio pos = new Posicio(i, j);
				Peca pieza = this.tauler.getPeca(pos);
				EscacGUI escac = new EscacGUI((((i + j) % 2) == 0 ? MyColor.NEGRE : MyColor.BLANC), pieza, this);
				this.escacs[i][j] = escac;
				this.panel.add(escac);
			}
		}

		this.taulerGUI.add(this.panel);
	}

	public void casillasSeleccionada(int x, int y) {
	    System.out.println("Pos: " + x + ", " + y);

	    try {
	        if (this.posSelect == null) {
	            Peca pieza = this.tauler.getPeca(new Posicio(x, y));
	            if (pieza != null) {
	                if (pieza.getEquip() == this.equipo) {
	                	if (pieza.hihaMovimentsPosibles(this.tauler)) {
		                    this.posSelect = new Posicio(x, y);
		                    System.out.println("Pieza seleccionada");
		                    LinkedList<Posicio> movimientosPosibles = pieza.movimentsPosibles(tauler);
		                    this.resalatarMovimientos(movimientosPosibles);
	            	    } else {
	            	        throw new InvalidMovementException("A002", "Amb aquesta peça no hi ha moviments possibles, escull una altre");
	            	    }
	                } else {
	                	throw new WrongTurnException();
	                }
	            } else {
	            	throw new InvalidMovementException("A001", "No hi ha peça en aquest escac del tauler");
	            }
	        } else {
	            Peca pieza = this.tauler.getPeca(this.posSelect);
	            if (this.posSelect != null) {
	            	if (this.equipo == pieza.getEquip()) {
	            	    if (pieza.movimentPosible(this.tauler, new Posicio(x, y))) {
	            	        this.equipo = (this.equipo == MyColor.BLANC) ? MyColor.NEGRE : MyColor.BLANC;
	            	        this.tauler.mouPeca(this.posSelect, new Posicio(x, y));
	            	        this.posSelect = null;
	            	        this.actualizarTablero();
	            	    } else {
	            	    	this.posSelect = null;
	            	        throw new InvalidMovementException("A002", "Amb aquesta peça no hi ha moviments possibles, escull una altre");
	            	    }
	            	} else {
	            		this.posSelect = null;
	                    throw new WrongTurnException();
	                }
	            } else {
	            	this.posSelect = null;
	                throw new InvalidMovementException("A001", "No hi ha peça en aquest escac del tauler");
	            }
	        }
	    } catch (InvalidMovementException ime) {
	        JOptionPane.showMessageDialog(null, ime.getMessage(), "Noooo.....", JOptionPane.ERROR_MESSAGE);
	    } catch (WrongTurnException wte) {
	        JOptionPane.showMessageDialog(null, "No és el teu torn.... ", "Noooo.....", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	public void resalatarMovimientos(LinkedList<Posicio> movimientsPosibles) {
		for (Posicio posicio : movimientsPosibles) {
			this.escacs[posicio.getX()][posicio.getY()].setBackground(new Color(255, 153, 153));
			this.escacs[posicio.getX()][posicio.getY()].setBorder(BorderFactory.createLineBorder(Color.RED));
		}
	}

	public void actualizarTablero() {
	    for (int j = 7; j >= 0; j--) {
	        for (int i = 0; i < 8; i++) {
	            Posicio pos = new Posicio(i, j);
	            Peca pieza = this.tauler.getPeca(pos);
	            EscacGUI escac = this.escacs[i][j];
	            escac.quitarResaltado();
	            escac.quitarImagen();
	            escac.añadirImagen(equipo, pieza);
	            escac.setPieza(pieza);
	        }
	    }
	}

}

package peces;

import java.util.LinkedList;

import joc.MyColor;
import joc.Posicio;
import joc.Tauler;

public class Rei extends Peca {

	public Rei(MyColor pCol, Posicio pos) {
		super(pCol, 100, pos);		
	}

	@Override
	public LinkedList<Posicio> posicionsPosibles() {
//		boolean [][] posicionsPosibles = new boolean [8][8];
		LinkedList<Posicio> posicionsPosibles= new LinkedList<Posicio>();
		
        int x = this.getPosicio().getX();
        int y = this.getPosicio().getY();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if (i == x || i == x+1 || i == x-1){
                    if(j == y || j == y+1 || j == y-1)
                        posicionsPosibles.add(new Posicio(i,j));
                }                   
            }
        }
        return posicionsPosibles;
	}

	@Override
	public boolean movimentPosible(Tauler tauler, Posicio posicioNova) {
		int x1 = getPosicio().getX();
        int y1 = getPosicio().getY();
        int x2 = posicioNova.getX();
        int y2 = posicioNova.getY();
        
        if(!this.posicionsPosibles().contains(new Posicio(x2,y2))){
        	return false;
        }
        if (tauler.pecesDelMateixEquip(this.equip).contains(new Posicio(x2,y2))){
        	return false;
        }
        return true;
	}

	@Override
	public String toString() {
		return "R"+super.toString();
	}
}

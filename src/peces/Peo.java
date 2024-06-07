package peces;

import java.util.LinkedList;

import joc.MyColor;
import joc.Posicio;
import joc.Tauler;

public class Peo extends Peca {

	public Peo(MyColor pCol, Posicio pos) {
		super(pCol, 1, pos);		
	}

	public LinkedList<Posicio> posicionsPerMenjar() {
//        boolean[][] posicionsPerMenjar = new boolean[8][8];
        LinkedList<Posicio> posicionsPerMenjar= new LinkedList<Posicio>();
        
        int x = this.getPosicio().getX();
        int y = this.getPosicio().getY();
        MyColor equip = this.getEquip();
        if (equip == MyColor.NEGRE) {
            if (0 < y && y  <= 7) {
                if (0 <= x && x < 7) {
                	posicionsPerMenjar.add(new Posicio(x + 1,y - 1));
                }
                if (0 < x && x  <= 7) {
                	posicionsPerMenjar.add(new Posicio(x - 1,y - 1));
                }
            }
        } else {
              if (0 <= y  && y  < 7) {
                if (0 <= x && x < 7) {
                	posicionsPerMenjar.add(new Posicio(x + 1,y + 1));
                }
                if (0 < x && x  <= 7) {
                	posicionsPerMenjar.add(new Posicio(x - 1,y + 1));
                }
            }
        }

        return posicionsPerMenjar;
    }
	
	@Override
	public LinkedList<Posicio> posicionsPosibles() {
		LinkedList<Posicio> posicionsPosibles= new LinkedList<Posicio>();
		
        int x = this.getPosicio().getX();
        int y = this.getPosicio().getY();
        MyColor equip = this.getEquip();
        if (equip == MyColor.BLANC) {
            if (0 < y && y  <= 7) {
                posicionsPosibles.add(new Posicio(x,y+1));
            }
        } else {            
            if (0 <= y  && y  < 7) {
                posicionsPosibles.add(new Posicio(x,y-1));
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
        
        if(this.posicionsPosibles().contains(new Posicio(x2,y2)) && (tauler.getCaselles()[x2][y2] instanceof SensePeca))
        	return true;    
        if (this.posicionsPerMenjar().contains(new Posicio(x2,y2)) && tauler.pecesEquipContrari(this.equip).contains(new Posicio(x2,y2)))
        	return true;
        if (this.equip == MyColor.BLANC && y1==1 && y2 == 3 && x2 == x1 )
        	return true;
        if (this.equip == MyColor.NEGRE && y1==6 && y2 == 4 && x2 == x1 )
        	return true;
   
        return false;
	}


	@Override
	public String toString() {
		return "P"+super.toString();
	}
}

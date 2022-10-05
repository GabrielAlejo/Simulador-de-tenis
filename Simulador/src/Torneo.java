
public class Torneo {
	protected String sNombre;
	protected int iCantidad;
	protected int iSetActual;
	protected boolean bQuienSaca;
	protected boolean bTieBreak;

	public Torneo(String nombre, int cantidad) {
		sNombre = nombre;
		iCantidad = cantidad;
		iSetActual = 0;
		
		int iRandom = (int)(Math.random()*10);
		bQuienSaca = (iRandom % 2 == 0) ? true : false;
		
		bTieBreak = false;
	}
	
	// MAIN
    
    public String RetornarNombre() {
        return sNombre;
    }
    
    public int RetornarCantidadSet() {
        return iCantidad;
    }
    
    // QUIEN SACA (random una vez, luego alterna)
	
	public boolean RetornarQuienSaca() {
	    return bQuienSaca;
	}
	
	public void InvertirQuienSaca() {
	    bQuienSaca = !bQuienSaca;
	}
	
	// TIE-BREAK
	
	public boolean RetornarTieBreak() {
        return bTieBreak;
    }
	
	public void EstadoTieBreak(boolean TieBreak) {
	    bTieBreak = TieBreak;
	}
	
	// SET ACTUAL
	
	public int RetornarSetActual() {
	    return iSetActual;
	}
	
	public void AumentarSet() {
	    iSetActual = iSetActual + 1;
    }
	
	public int RetornarCantSetGanar() {
	    int iCant;
	    iCant = (RetornarCantidadSet() / 2) + 1;
	    
	    return iCant;
	}
	
	// RESET
	
	public void ResetSetActual() {
	    iSetActual = 0;
	}
}

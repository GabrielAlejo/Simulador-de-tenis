
public class Jugador {
	protected String sNombre;
	protected int iProbabilidad;
	protected int iPunto;
	protected int iGame;
    protected int iGames[];
	protected int iSet[];

	public Jugador(String nombre, int probabilidad, int cantidad) {
		sNombre = nombre;
		iProbabilidad = probabilidad;
		iPunto = 0;
		iGames = new int [cantidad];
		iSet = new int[cantidad];
	}
	
	// MAIN //

	public String RetornarNombre() {
		return sNombre;
	}

	public int RetornarProbabilidad() {
		return iProbabilidad;
	}

	// PUNTOS //
	
	public int RetornarPunto() {
		return iPunto;
	}
	
	public String MostrarPunto() {
        String sPunto = null;

        switch (iPunto) {
            case 0: {
                sPunto = "0";
                break;
            }
            case 1: {
                sPunto = "15";
                break;
            }
            case 2: {
                sPunto = "30";
                break;
            }
            case 3: {
                sPunto = "40";
                break;
            }
            case 4: {
                sPunto = "AD";
            }
            default:
                return sPunto;
        }

        return sPunto;
    }
	
	public void SumarPunto() {
        iPunto = iPunto + 1;
    }
	
	public void PerderVentaja() {
        iPunto = iPunto - 1;
    }
	
	public void ResetPunto() {
        iPunto = 0;
    }
	
	// GAMES //
	
	public int RetornarGame() {
		return iGame;
	}

    public void SumarGame() {
        iGame = iGame + 1;
    }
    
    public void ResetGame() {
        iGame = 0;
    }
    
    public void GuardarGamesEnSet(int set, int games) {
        iGames[set] = games;
    }
    
    public int RetornarGamesEnSet(int set) {
        return iGames[set];
    }
    
    public void ResetGamesEnSet() {
        for (int i = 0; i < iGames.length; i++) {
            iGames[i] = 0;
        }
    }
    
    // SETS //

	public int RetornarSet(int set) {
		return iSet[set];
	}
	
	public int retornarCanSetGanados() {
	    int iCant = 0;
	    
	    for (int i = 0; i < iSet.length; i++) {
            iCant += (iSet[i] == 1) ? 1 : 0 ;
        }
	    
	    return iCant;
	}
	
	public void GanarSet(int set) {
	    iSet[set] = 1;
    }
	
	public void PerderSet(int set) {
        iSet[set] = 0;
    }
	
	public void ResetSets() {
	    for (int i = 0; i < iSet.length; i++) {
	        iSet[i] = 0;
	    }
	}
	
	// RESET VARIABLES //
	
	public void ResetVar() {
	    ResetPunto();
	    ResetGame();
	    ResetGamesEnSet();
	    ResetSets();
	}
}

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Simulador {
    static String sAutor = "GABRIEL ROSSETTO";
    static double dVersion = 0.1;
    
	protected Scanner sScanner;
	protected Jugador jJugador1, jJugador2;
	protected Torneo tTorneo1;

	public Simulador() {
		Bienvenida();
	}
	
	// BIENVENIDA

	public void Bienvenida() {
		System.out.println("** SIMULADOR DE TENIS ** " + sAutor + " - " + dVersion + " **");
		System.out.println("");

		ConfigurarTorneo();
	}
	
	// CONFIGURACION DEL TORNEO (nombre y cant de sets)

	public void ConfigurarTorneo() {
		sScanner = new Scanner(System.in);
		System.out.println("* CONFIGURAR TORNEO *");

		String sNombre;
		int iSets;

		System.out.print("Ingrese nombre del torneo: ");
		sNombre = sScanner.nextLine();

		System.out.print("Ingrese cantidad de sets para el torneo " + sNombre + ": ");
		iSets = sScanner.nextInt();

		System.out.println("");

		tTorneo1 = new Torneo(sNombre, iSets);

		ConfirmarTorneo();
	}
	
	// CONFIRMAR CONF TORNEO

	public void ConfirmarTorneo() {
		sScanner = new Scanner(System.in);
		System.out.println("* CONFIRMAR TORNEO *");
		System.out.println("Torneo: " + tTorneo1.RetornarNombre() + "; Con " + tTorneo1.RetornarCantidadSet() + " de sets.");
		System.out.println("");

		String sOpcion;
		System.out.println("* PRESIONE S PARA CONFIRMAR Y CARGAR JUGADORES*");
		System.out.println("* PRESIONE N PARA VOLVER A CONFIGURAR EL TORNEO *");
		System.out.println("");

		sOpcion = sScanner.next();

		switch (sOpcion) {
			case "S", "s": {
				CargarJugador(1);
				CargarJugador(2);

				ConfirmarJugadores();

				return;
			}
			default: {
				ConfigurarTorneo();
			}
		}
	}
	
	// CARGAR JUGADOR (1 y 2)

	public void CargarJugador(int iJugador) {
		sScanner = new Scanner(System.in);
		System.out.println("* CARGAR JUGADOR " + iJugador + " *");

		String sNombre;
		int iProbabilidad;

		System.out.print("Ingrese nombre del jugador: ");
		sNombre = sScanner.nextLine();

		System.out.print("Ingrese probabilidad de ganar para el jugador " + sNombre + ": ");
		iProbabilidad = sScanner.nextInt();

		System.out.println("");

		switch (iJugador) {
			case 1: {
				jJugador1 = new Jugador(sNombre, iProbabilidad, tTorneo1.RetornarCantidadSet());
			}
			case 2: {
				jJugador2 = new Jugador(sNombre, iProbabilidad, tTorneo1.RetornarCantidadSet());
			}
		}
	}
	
	// CONFIRMAR JUGADORES

	public void ConfirmarJugadores() {
		sScanner = new Scanner(System.in);
		System.out.println("* CONFIRMAR JUGADORES *");
		System.out.println("Jugador 1: " + jJugador1.RetornarNombre() + "; Con probabilidad de %" + jJugador1.RetornarProbabilidad() + " ganar.");
		System.out.println("Jugador 2: " + jJugador2.RetornarNombre() + "; Con probabilidad de %" + jJugador2.RetornarProbabilidad() + " ganar.");
		System.out.println("");

		String sOpcion;
		System.out.println("* PRESIONE S PARA CONFIRMAR Y EMPEZAR EL JUEGO *");
		System.out.println("* PRESIONE N PARA VOLVER A CARGAR LOS JUGADORES *");

		sOpcion = sScanner.next();

		System.out.println("");

		switch (sOpcion) {
			case "S", "s": {
				EmpezarJuego();

				return;
			}
			default: {
				CargarJugador(1);
				CargarJugador(2);

				ConfirmarJugadores();
			}
		}
	}
	
	// EMPEZAR JUEGO

	public void EmpezarJuego() {
		System.out.println("* EMPEZANDO *");
		System.out.println("");

		System.out.println("El jugador " + QuienSaca() + " tiene el saque.");

		MostrarScore();
		JugarPunto();
	}
	
	// RETORNAR QUÉ JUGADOR SACAR

	public String QuienSaca() {
		if (tTorneo1.RetornarQuienSaca()) {
			return jJugador1.RetornarNombre();
		} else {
			return jJugador2.RetornarNombre();
		}
	}
	
	// SCORING

	public void MostrarScore() {
		System.out.println("* SCORE *");

		MostrarScoreJugador(jJugador1, false);
		MostrarScoreJugador(jJugador2, false);
		System.out.println("");
	}

	public void MostrarScoreJugador(Jugador jJugador, boolean bFinal) {
		String sScore;
		String sSeparador = " | ";
		int iContadorSet = 0;

		sScore = jJugador.RetornarNombre();

		if (!bFinal) {
		    for (int i = 0; i < tTorneo1.RetornarSetActual(); i++) {
	            iContadorSet += jJugador.RetornarSet(i);
	        }
	        sScore += sSeparador;

	        sScore += iContadorSet;
	        sScore += sSeparador;

	        sScore += jJugador.RetornarGame();
	        sScore += sSeparador;

	        if (!tTorneo1.RetornarTieBreak()) {
	            sScore += jJugador.MostrarPunto();
	        } else {
	            sScore += jJugador.RetornarPunto();
	        }
	        
	        sScore += sSeparador;
		}
		else {
		    sScore += sSeparador;

	        for (int i = 0; i <= tTorneo1.RetornarSetActual(); i++) {
	            sScore += jJugador.RetornarGamesEnSet(i);
	            sScore += sSeparador;
	        }
		}
		
		System.out.println(sScore);
	}
	
	// JUGAR PUNTO

	public void JugarPunto() {
		int iPuntoJugador1 = ThreadLocalRandom.current().nextInt(0, jJugador1.RetornarProbabilidad() + 1);
		int iPuntoJugador2 = ThreadLocalRandom.current().nextInt(0, jJugador2.RetornarProbabilidad() + 1);
		
		if (tTorneo1.RetornarTieBreak()) {
		    if (iPuntoJugador1 > iPuntoJugador2) {
	            System.out.println("El punto del TIE-BREAK lo ganó " + jJugador1.RetornarNombre() + ".");
	            jJugador1.SumarPunto();
	        } else {
	            if (iPuntoJugador2 > iPuntoJugador1) {
	                System.out.println("El punto del TIE-BREAK lo ganó " + jJugador2.RetornarNombre() + ".");
	                jJugador2.SumarPunto();
	            } else {
	                JugarPunto();
	                return;
	            }
	        }
        } 
		else if (iPuntoJugador1 > iPuntoJugador2) {
			System.out.println("El punto lo ganó " + jJugador1.RetornarNombre() + ".");

			if (jJugador2.RetornarPunto() < 4) {
				jJugador1.SumarPunto();
			} else {
				jJugador2.PerderVentaja();
				System.out.println(jJugador2.RetornarNombre() + " perdió la ventaja.");
			}
		} 
		else {
			if (iPuntoJugador2 > iPuntoJugador1) {
				System.out.println("El punto lo ganó " + jJugador2.RetornarNombre() + ".");

				if (jJugador1.RetornarPunto() < 4) {
					jJugador2.SumarPunto();
				} else {
					jJugador1.PerderVentaja();
					System.out.println(jJugador1.RetornarNombre() + " perdió la ventaja.");
				}
			} else {
				JugarPunto();
				return;
			}
		}

		System.out.println("");

		// PUNTO EQUIVALENCIA
		// 0 0
		// 1 15
		// 2 30
		// 3 40
		// 4 AD // GANA
		// 5 GANA
		
		if (tTorneo1.RetornarTieBreak()) {
		    if (jJugador1.RetornarPunto() == 7 && jJugador2.RetornarPunto() < 7) {
	            GanarGame(jJugador1);
	            return;
	        }
		    else if (jJugador1.RetornarPunto() < 7 && jJugador2.RetornarPunto() == 7) {
                GanarGame(jJugador2);
                return;
            }
		    else {
		        MostrarScore();
		        JugarPunto();
		        
		        return;
		    }
		}

		if ( (jJugador1.RetornarPunto() == 4 && jJugador2.RetornarPunto() < 3) || (jJugador1.RetornarPunto() == 5 && jJugador2.RetornarPunto() == 3) ) {
			GanarGame(jJugador1);
			return;
		}

		if ( (jJugador2.RetornarPunto() == 4 && jJugador1.RetornarPunto() < 3) || (jJugador2.RetornarPunto() == 5 && jJugador1.RetornarPunto() == 3) ) {
			GanarGame(jJugador2);
			return;
		}

		MostrarScore();
		JugarPunto();
	}
	
	// GANAR JUEGO

	public void GanarGame(Jugador jJugador) {
		if (tTorneo1.RetornarTieBreak()) {
            System.out.println("[" + tTorneo1.sNombre + "] ¡" + jJugador.sNombre + " ganó el TIE-BREAK!");
            
			tTorneo1.EstadoTieBreak(false);
		} else {
	        System.out.println("[" + tTorneo1.sNombre + "] ¡" + jJugador.sNombre + " ganó el game!");
		}
		System.out.println("");

		jJugador1.ResetPunto();
		jJugador2.ResetPunto();

		jJugador.SumarGame();

		int jJugador1Games = jJugador1.RetornarGame();
		int jJugador2Games = jJugador2.RetornarGame();

		if ( (jJugador1Games < 6 && jJugador2Games < 6) || (jJugador1Games == 6 && jJugador2Games == 5) || (jJugador1Games == 5 && jJugador2Games == 6) || (jJugador1Games == 6 && jJugador2Games == 6) ) {
			tTorneo1.InvertirQuienSaca();
			System.out.println("El jugador " + QuienSaca() + " tiene el saque.");
			
			if (jJugador1Games == 6 && jJugador2Games == 6) {
	            tTorneo1.EstadoTieBreak(true);
	            System.out.println("* TIE-BREAK *");
	        }

			MostrarScore();
			JugarPunto();

			return;
		}

		System.out.println("[" + tTorneo1.sNombre + "] ¡" + jJugador.sNombre + " ganó el set!");
		System.out.println("");

		jJugador.GanarSet(tTorneo1.RetornarSetActual());

		jJugador1.GuardarGamesEnSet(tTorneo1.RetornarSetActual(), jJugador1.RetornarGame());
		jJugador2.GuardarGamesEnSet(tTorneo1.RetornarSetActual(), jJugador2.RetornarGame());

		if (jJugador.retornarCanSetGanados() == tTorneo1.RetornarCantSetGanar()) {
			System.out.println("[" + tTorneo1.sNombre + "] ¡" + jJugador.sNombre + " ganó el partido!");
			System.out.println("");

			System.out.println("* SCORE FINAL - " + tTorneo1.RetornarNombre() + " *");
			MostrarScoreJugador(jJugador1, true);
			MostrarScoreJugador(jJugador2, true);

			System.out.println("");
			ReiniciarJuego();
		} else {
			jJugador1.ResetGame();
			jJugador2.ResetGame();

			tTorneo1.AumentarSet();

			MostrarScore();
			JugarPunto();
		}
	}
	
	// FINAL

	public void ReiniciarJuego() {
		sScanner = new Scanner(System.in);
		System.out.println("* ¿COMO SEGUIR? *");
		System.out.println("");

		String sOpcion;
		System.out.println("* PRESIONE S PARA JUGAR REVANCHA (MISMO TORNEO Y JUGADORES) *");
		System.out.println("* PRESIONE N PARA UN NUEVO JUEGO *");

		sOpcion = sScanner.next();

		System.out.println("");

		switch (sOpcion) {
			case "S", "s": {
				ResetVars(false);
				EmpezarJuego();

				return;
			}
			default: {
				ResetVars(true);
				Bienvenida();
			}
		}
	}

	public void ResetVars(boolean bJuegoNuevo) {
		if (bJuegoNuevo) {
			jJugador1 = null;
			jJugador2 = null;
			tTorneo1 = null;
		} else {
			tTorneo1.ResetSetActual();

			jJugador1.ResetVar();
			jJugador2.ResetVar();
		}
	}
	
	// MAIN STATIC

	public static void main(String[] args) {
		Simulador simulador = new Simulador();
	}
}

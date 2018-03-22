/*
	Proyecto: 8-Puzzle
	
	Autores: Miguel C. Canedo R. 13-10214
             Andres A. Buelvas V. 13-10184

    Fecha: 23/03/2018
*/

/*
	Clase que representa cada estado que puede poseer un tablero 
	dentro del Grafo Implicito de Estados.
*/
class Puzzle{
	int[][] ochoPuzzle					// Tablero del estado.
	Integer id 							// Id que representa al estado.
	HashSet<Integer> listaAcciones 		// Lista de las acciones que se pueden tomar.
	int iZero 							// Fila donde se encuentra el espacio vacio.
	int jZero							// Columna donde se encuentra el espacio vacio.
	int heuCost							// Costo mediante la Heuristica Manhattan que posee este estado.
	

	/*
		Constructor de la clase que recibe un arreglo que representa el tablero del estado
		y las posicones (fila y columna) donde almacena a la casilla vacia.
	*/
	Puzzle(p, iZ, jZ){
		ochoPuzzle = p
		
		// Construimos el id.
		id = 0
		def counter = 1
		for(i in 0..2){
			for(j in 0..2){
				id += p[i][j] * counter
				counter *= 10
			}
		}

		iZero = iZ
		jZero = jZ
		getAcciones()			// Obtenemos las acciones logrables desde este estado.
		aplicarHeuristica()		// Calculamos el costo de este estado.
	}


	/*
		Metodo que segun la posicion de la casilla vacia, detecta cuales son las
		acciones que se pueden lograr desde este estado.
	*/
	def getAcciones(){
		// Posición a la que se puede mover el espacio vacio.
		// 0: Arriba, 1: Izquierda, 2: Derecha, 3: Abajo
		listaAcciones = new HashSet<Integer>()
		if(iZero != 0){
			listaAcciones << 0
		}
		
		if(jZero != 0){
			listaAcciones << 1
		}
		
		if(jZero != 2){
			listaAcciones << 2
		}
		
		if(iZero != 2){
			listaAcciones << 3
		}	
	}

	/*
		Metodo que calcula el costo aproximado que tendra este estado para alcanzar 
		el estado meta, aplicando al Heurista Manhattan.
	*/
	def aplicarHeuristica(){
		heuCost = 0
		for(i in 0..2){
			for(j in 0..2){
				def jCorrecto = 0
				def iCorrecto = 0
				if(ochoPuzzle[i][j] != 0){
					jCorrecto = (ochoPuzzle[i][j] - 1) % 3
					iCorrecto = (ochoPuzzle[i][j] - 1 - jCorrecto) / 3
				}

				heuCost += Math.abs(iCorrecto - i) + Math.abs(jCorrecto - j)
			}
		}
	}

	/*
		Metodo que determina si el estado es o no el estado meta.
	*/
	def compareToGoal(){
		for(i in 0..2){
			for(j in 0..2){
				def orden = ((3 * i) + j + 1) % 9
				if(ochoPuzzle[i][j] != orden){
					return false
				}
			}
		}

		true
	}


	/*
		Metodo que contruye una coleccion con todos los estados sucesores 
		del estado actual, segun las acciones que se puedan tomar.
	*/
	def getSucesores(){
		def sucesores = new ArrayList<Puzzle>()

		if(listaAcciones.contains(0)){
			def abajoPuzzle = copiarPuzzle()
			abajoPuzzle[iZero][jZero] = abajoPuzzle[iZero - 1][jZero];
			abajoPuzzle[iZero - 1][jZero] = 0
			def abPuzzleNuevo = new Puzzle(abajoPuzzle, iZero - 1, jZero)
			sucesores << abPuzzleNuevo
		}
		
		if(listaAcciones.contains(1)){
			def derechaPuzzle = copiarPuzzle()
			derechaPuzzle[iZero][jZero] = derechaPuzzle[iZero][jZero - 1]
			derechaPuzzle[iZero][jZero - 1] = 0
			def dePuzzleNuevo = new Puzzle(derechaPuzzle, iZero, jZero - 1)
			sucesores << dePuzzleNuevo
		}
		
		if(listaAcciones.contains(2)){
			def izquierdaPuzzle = copiarPuzzle()
			izquierdaPuzzle[iZero][jZero] = izquierdaPuzzle[iZero][jZero + 1]
			izquierdaPuzzle[iZero][jZero + 1] = 0
			def izPuzzleNuevo = new Puzzle(izquierdaPuzzle, iZero, jZero + 1)
			sucesores << izPuzzleNuevo
		}
		
		if(listaAcciones.contains(3)){
			def arribaPuzzle = copiarPuzzle()
			arribaPuzzle[iZero][jZero] = arribaPuzzle[iZero + 1][jZero]
			arribaPuzzle[iZero + 1][jZero] = 0
			def arPuzzleNuevo = new Puzzle(arribaPuzzle, iZero + 1, jZero)
			sucesores << arPuzzleNuevo
		}
		
		sucesores
	}

	/*
		Metodo que permite crear una copia del estado actual del tablero.
	*/
	def copiarPuzzle(){
		def copia = new int[3][3]
		for(i in 0..2){
			for(j in 0..2){
				copia[i][j] = ochoPuzzle[i][j]
			}
		}

		copia
	}	

	/*
		Metod que logra determinar si un estado podra o no llegar al estado meta.
		Es decir, determina si un  estado tiene o no solucion posible.
	*/
	def solvable(){
		def found = new int[9]
		def inv = 0
		for(i in 2..0){
			for(j in 2..0){
				if(ochoPuzzle[i][j] != 0){
					found[ochoPuzzle[i][j]] = 1
					for(int k = ochoPuzzle[i][j] - 1; k > 0; k--){
						inv += found[k]
					}
				}
			}
		}

		inv % 2 == 0
	}

	/*
		Metodo que construye la representacion en cadena de caracteres
		de los estados.
	*/
	String toString(){
		def str = ""
		for(i in 0..2){
			for(j in 0..2){
				str += ochoPuzzle[i][j] + " "
			}
			str += "\n"
		}
		
		str
	}
}
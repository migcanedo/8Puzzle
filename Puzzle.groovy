class Puzzle{
	int[][] ochoPuzzle
	Integer id
	HashSet<Integer> listaAcciones
	int iZero
	int jZero
	int heuCost
	
	Puzzle(p, iZ, jZ){
		ochoPuzzle = p
		id = 0
		int counter = 1

		for(i in 0..2){
			for(j in 0..2){
				id += p[i][j] * counter
				counter *= 10
			}
		}

		iZero = iZ
		jZero = jZ
		getAcciones()
		aplicarHeuristica()
	}

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

	def copiarPuzzle(){
		def copia = new int[3][3]
		for(i in 0..2){
			for(j in 0..2){
				copia[i][j] = ochoPuzzle[i][j]
			}
		}

		copia
	}	


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
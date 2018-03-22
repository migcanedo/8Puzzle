/*
	Proyecto: 8-Puzzle
	
	Autores: Miguel C. Canedo R. 13-10214
             Andres A. Buelvas V. 13-10184

    Fecha: 23/03/2018
*/

/*
	Clase que representa todos los caminos que se pueden
	construir con diferentes estados. Siendo esta clase una 
	implemnetacion de la interfaz Comparable para asi logra almacenarla
	dentro de una Cola de Prioridades.
*/
class SolutionPath implements Comparable<SolutionPath> {
	ArrayList<Puzzle> path 		//Coleccion de estados que representan el path.
	int lenght 					// Total de estados dentro del path
	

	/*
		Contructor vacio de la clase. Que crea una path vacio.
	*/
	SolutionPath(){
		path = new ArrayList<Puzzle>()
		lenght = 0
	}
	
	/*
		Constructor de la clase que recibe una coleccion de estados que seria la
		representacion del path y un entero con el numero atual de estados q posee.
	*/
	SolutionPath(p, l){
		path = p
		lenght = l
	}

	/*
		Metodo que permite la inclusion de un nuevo estado al final del path.
	*/
	def addState(p){
		path << p
		lenght += 1
	}

	/*
		Metodo que permite consultar cual es el ultimo estado dentro del path.
	*/
	def getEnd(){
		path.get(lenght - 1)
	}

	/*
		Metodo que permite calcular el costo total del camino segun la Heuritica 
		aplicada en su ultimo estado, mas la longitud del path.
	*/
	int getHeuCost(){
		(getEnd().heuCost) + lenght
	}

	/*
		Metodo declara de la interfaz Comparable, que permite la comparacion con otras 
		instancias de la clase.
	*/
	int compareTo(o){
		(getHeuCost() - o.getHeuCost())
	}

	/*
		Metodo que crea una copia exacta de la instancia y la retorna.
	*/
	def clone(){
		def p = (ArrayList<Puzzle>) path.clone()
		def l = lenght
		def copy = new SolutionPath(p, l)
		
		copy
	}

	/*
		Metodo que construye la representacion en cadena de caracteres
		del path de estados.
	*/
	String toString(){
		def str2 = ""
		path.each { estado ->
			str2 += estado.toString() + "\n"
		}

		str2
	}
}
/*
	Proyecto: 8-Puzzle
	
	Autores: Miguel C. Canedo R. 13-10214
             Andres A. Buelvas V. 13-10184

    Fecha: 23/03/2018
*/

/*
	Funcion que simula el recorrido que haria el Algoritmo de Busqueda
	A*, dado un estado inicial y retornaria el camino de menor costo desde 
	el estado inicial hasta el estado meta.
*/
SolutionPath AStarSearch(startingPuzzle){
	startSolution = new SolutionPath()
	openPaths = new PriorityQueue<SolutionPath>()
	openStates = new HashMap<Integer, SolutionPath>(200000)
	closedStates = new HashSet<Integer>(200000)

	startSolution.addState(startingPuzzle)					// Agregamos el estado Inicial al Camino Inicial.
	openStates.put(startingPuzzle.id, startSolution) 	// Marcamos al Estado Inicial como Estado Abierto.
	
	// Si el Estado Inicial posee solucion, agregamos el Camino Inicial a la Cola.
	if(startingPuzzle.solvable())
		openPaths.offer(startSolution)		
	

	// Mantenemos la iteracion mientras la Cola no este vacia.
	while(openPaths.size() > 0){
		def actualPath = openPaths.poll()				// Sacamos el camino de mayor prioridad de la Cola.
		openPaths.remove(actualPath.getEnd().id)	// Eliminamos de la Cola todos aquellos caminos que tenga como Estado Final igual al Camino sacado previamente.
		closedStates.add(actualPath.getEnd().id)	// Marcamos el Estado Final del Camino Actual como un estado cerrado.
		
		// Si el final del Camino Actual es igual al Estado Meta, lo retornamos.
		if(actualPath.getEnd().compareToGoal())
			return actualPath
		
		
		// Calculamos los Estados sucesores del Camino Actual.
		def pathSuccesors = actualPath.getEnd().getSucesores() 

		// Iteramos por cada sucesor que posea el Path actual.
		pathSuccesors.each { sucPath ->
			// Si el sucesor esta marcado como Cerrado, lo ignoramos.
			if(closedStates.contains(sucPath.id))
				return
			
			// Duplicamos el camino actual, para expandirlo con su Sucesor.
			def newPath = (SolutionPath) actualPath.clone()
			newPath.addState(sucPath)
			
			// Verificamos si el Estado Sucesor esta marcado como Estado Abierto.
			if(openStates.containsKey(sucPath.id)){
				// Si lo esta, y ademas su Heuristica por el camino actual es mejor que la poseia antes se procede a sustituir ese Camino por el Camino Actual.
				// En caso de que su Heuristica no sea mejor, se ignora ese camino.
				if(openStates.get(sucPath.id).getHeuCost() > newPath.heuCost){
					openPaths.remove(openStates.get(sucPath.id))
					openStates.put(sucPath.id, newPath)
					openPaths.offer(newPath)
				}else{
					return
				}
			// Si el Estado SUcesor no esta Abierto, se abre y se agrega el Camino Actual a la Cola.
			}else{
				openStates.put(sucPath.id, newPath)
				openPaths.offer(newPath)
			}
		}
	}

	// En caso de que la Cola quede vacia, se retornara null.
	null
}

/*
	Main del Programa.
*/
// Verificamos si se especific un archivo de entrada.
if (args.length < 1){
	println "Debe indicar un archivo de entrada."
	return
}

// Intentamos abri r el archivo especificado, si no existe 
// capturamos la excepcion.
try {
	def file = new File(this.args[0])

	// Leemos el archivo y creamos el arreglo que tendra el Puzzle Inicial.
	sP = (file.readLines() - "").collect { 
		it.split(" ").collect{
			it.toInteger()
		}
	}

	// Detectamos donde esta el '0' en el arreglo del Puzzle Inicial.
	iz = sP.findIndexValues {
		if (it.contains(0)){
		    jz = it.findIndexValues { x ->
	    		x == 0
	    	}[0]
	    }
	    it.contains 0
	}[0]

	// Creamos el Puzzle con el arreglo armado previamente.
	sP = new Puzzle(sP, iz, jz)

	// Buscamos el camino de menor costo mediante la Busqueda A*.
	solucion = AStarSearch(sP)

	// Si existe solucion la impirmimos
	if (solucion) print solucion

	//en caso contrario, notificamos que no existe solucion.
	else println "No tiene Solucion."
} catch (FileNotFoundException e){

	println "El archivo no existe."

}
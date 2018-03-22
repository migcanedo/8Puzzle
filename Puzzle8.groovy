// Retornara el path de solucion.
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



// MAIN

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

solucion = AStarSearch sP
if (solucion) print solucion
else println "No tiene Solucion."
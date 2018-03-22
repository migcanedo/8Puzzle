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

println sP.solvable()

path = new SolutionPath()
path.addState(sP)
path.addState(sP)
path.addState(sP)


println path
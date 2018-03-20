def file = new File(this.args[0])

// Leemos el archivo y creamos el arreglo que tendra el Puzzle Inicial.
sP = (file.readLines() - "").collect { 
	it.split(" ").collect{
		it.toInteger()
	}
}
class Puzzle{
	private int[][] ochoPuzzle
	private Integer id
	private HashSet<Integer> listaAcciones
	private int iZero
	private int jZero
	private int heuCost
	
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
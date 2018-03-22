class SolutionPath{
	private ArrayList<Puzzle> path
	private int lenght
	
	SolutionPath(){
		path = new ArrayList<Puzzle>()
		lenght = 0
	}
	
	SolutionPath(p, l){
		path = p
		lenght = l
	}


	def addState(p){
		path << p
		lenght += 1
	}


	String toString(){
		def str2 = ""
		path.each { estado ->
			str2 += estado.toString() + "\n"
		}

		str2
	}
}
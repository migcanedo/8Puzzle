class SolutionPath implements Comparable<SolutionPath> {
	ArrayList<Puzzle> path
	int lenght
	
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

	def getEnd(){
		path.get(lenght - 1)
	}

	int getHeuCost(){
		(getEnd().heuCost) + lenght
	}

	int compareTo(o){
		(getHeuCost() - o.getHeuCost())
	}

	def clone(){
		def p = (ArrayList<Puzzle>) path.clone()
		def l = lenght
		def copy = new SolutionPath(p, l)
		
		copy
	}

	String toString(){
		def str2 = ""
		path.each { estado ->
			str2 += estado.toString() + "\n"
		}

		str2
	}
}
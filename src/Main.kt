private var won = false
private var player = 1
private var symbol = 'O'

fun main(){
    while(true){
        println("Start game:(Y/N)")
        val users = readlnOrNull()?.firstOrNull() ?: ' '
        if(users.toUpperCase()=='Y'){
            ticTacToe()
        }else{
            break
        }
    }
}

private fun ticTacToe(){
    val board = createBoard()
    printBoard(board)
    while(!won) {
        while (true) {
            val (row, col) = getInput(player, symbol)
            if(row==-1 && col==-1){
                continue
            }
            if (isValidPlace(board, row, col)) {
                updateBoard(board, row, col, symbol)
            }else{
                println("Invalid input, try again")
                break
            }
            if (checkBoardForWin(board, symbol)) {
                won = true
                println("Congratulation, Player$player('$symbol') wins!")
                break
            }

            if (checkBoardForDraw(board)) {
                println("Match draw!")
                break
            }
            val newPlayer = switchCurrentPlayerAndSymbol()
            player = newPlayer.first
            symbol = newPlayer.second
            printBoard(board)
        }
    }
}

private fun isValidPlace(board: Array<Array<Char>>, row: Int, col: Int): Boolean {
    return row in 0..2 && col in 0..2 && board[row][col]=='_'
}

private fun printBoard(board: Array<Array<Char>>) {
    for(i in board){
        for(j in i){
            print(j)
        }
        println()
    }
}

private fun checkBoardForWin(board: Array<Array<Char>>,symbol: Char): Boolean {
    for(i in 0..<3){
        if((board[i][0]==symbol && board[i][1]==symbol && board[i][2]==symbol)
            || (board[0][i]==symbol && board[1][i]==symbol && board[2][i]==symbol)) {
            return true
        }
    }
    return ((board[0][0]==symbol && board[1][1]==symbol && board[2][2]==symbol)
            || (board[0][2]==symbol && board[1][1]==symbol && board[2][0]==symbol))
}

private fun updateBoard(board: Array<Array<Char>>, row: Int, col: Int, symbol: Char){
    board[row][col] = symbol
}

private fun switchCurrentPlayerAndSymbol(): Pair<Int,Char> {
    return if(player==1){
        Pair(2,'X')
    }else{
        Pair(1,'O')
    }
}

private fun getInput(player: Int, symbol: Char):Pair<Int,Int> {
    println("Player $player ('$symbol'), enter your move(row and column)")
    val move = readln()
    val rc = move.trim().filter{ it in '0'..'9' }
    if(rc.isEmpty())return Pair(-1,-1)
    val row: Int = rc[0].digitToInt()
    val col: Int = rc[1].digitToInt()
    return Pair(row,col)
}

private fun createBoard(): Array<Array<Char>> {
    return arrayOf(
        arrayOf('_','_','_'),
        arrayOf('_','_','_'),
        arrayOf('_','_','_'),
    )
}

private fun checkBoardForDraw(board: Array<Array<Char>>): Boolean {
    for(i in 0..<3){
        for(j in 0..<3){
            if(board[i][j]=='_'){
                return false
            }
        }
    }
    return true
}

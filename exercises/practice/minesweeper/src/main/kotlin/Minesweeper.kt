data class MinesweeperBoard(val inputBoard: List<String>) {
    private var outputBoard: MutableList<String> = mutableListOf()

    fun withNumbers(): List<String> {
        inputBoard.forEachIndexed( { indexRow, str ->
            val rowChar: ArrayList<Char> = arrayListOf()
            str.forEachIndexed({ indexCol,ch ->
                var count = 0
                count = mineCounter(ch, count, indexRow, indexCol)
                stringBuilder(count, rowChar)
            })
            outputBoard.add(rowChar.joinToString(separator = ""))
        })
        return outputBoard
    }

    private fun stringBuilder(count: Int, rowChar: ArrayList<Char>) {
        if (count == -1)
            rowChar.add('*')
        else if (count == 0)
            rowChar.add(' ')
        else
            rowChar.add(count.toString()[0])
    }

    private fun mineCounter(ch: Char, count: Int, indexRow: Int, indexCol: Int): Int {
        var count1 = count
        if (ch == '*')
            count1 = -1
        else
            for (i in -1..1)
                for (j in -1..1)
                    if (checkIndex(indexRow, i, indexCol, j) && checkBomb(indexRow, i, indexCol, j))
                        count1++
        return count1
    }

    private fun checkBomb(indexRow: Int, i: Int, indexCol: Int, j: Int) =
        inputBoard[indexRow + i][indexCol + j] == '*'

    private fun checkIndex(indexRow: Int, i: Int, indexCol: Int, j: Int) =
        indexRow + i >= 0 && indexRow + i <= inputBoard.lastIndex && indexCol + j >= 0 && indexCol + j <= inputBoard[indexRow].lastIndex
}


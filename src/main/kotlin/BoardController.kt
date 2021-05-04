class BoardController{
    fun add() {
        print("새 게시판 이름 입력 : ")
        val name = readLineTrim()
        print("새 게시판 코드 입력 : ")
        val code = readLineTrim()
        boardRepository.addBoard(name, code)
        println("${name} 게시판이 등록되었습니다.")
    }

}
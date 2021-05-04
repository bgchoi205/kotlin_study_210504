class BoardController{
    fun add() {
        print("새 게시판 이름 입력 : ")
        val name = readLineTrim()
        print("새 게시판 코드 입력 : ")
        val code = readLineTrim()
        boardRepository.addBoard(name, code)
        println("${name} 게시판이 등록되었습니다.")
    }

    fun list() {
        for(board in boardRepository.boards){
            println("번호 : ${board.id} / 게시판이름 : ${board.name} / 코드 : ${board.code}")
        }
    }

    fun delete(rq: Rq) {
        val id = rq.getIntParam("id", 0)
        if(id == 0){
            println("게시판 번호를 입력해주세요")
            return
        }
        val board = boardRepository.getBoardById(id)
        if(board == null){
            println("존재하지 않는 게시판입니다.")
            return
        }
        boardRepository.boards.remove(board)
        println("${board.name} 게시판 삭제완료")
    }
    fun modify(rq: Rq) {
        val id = rq.getIntParam("id", 0)
        if(id == 0){
            println("게시판 번호를 입력해주세요")
            return
        }
        val board = boardRepository.getBoardById(id)
        if(board == null){
            println("존재하지 않는 게시판입니다.")
            return
        }
        print("새 이름 : ")
        val name = readLineTrim()
        print("새 코드 : ")
        val code = readLineTrim()
        board.name = name
        board.code = code
        board.updateDate = Util.getDateNowStr()
        println("$id 번 게시판 수정 완료")
    }

}
class ArticleRepository{

    val articles = mutableListOf<Article>()
    var lastArticleId = 0

    fun addArticle(title: String, body: String, memberId : Int, boardId : Int): Int {
        val id = ++lastArticleId
        val regDate = Util.getDateNowStr()
        val updateDate = Util.getDateNowStr()
        articles.add(Article(id, title, body, memberId, boardId, regDate, updateDate))
        return id
    }

    fun makeTestArticles(){
        for(i in 1..30){
            addArticle("제목$i", "내용$i", i % 9 + 1, i % 2 + 1)
        }
    }

    fun getArticleById(id: Int): Article? {
        for(article in articles){
            if(article.id == id){
                return article
            }
        }
        return null
    }

    fun articlesFilter(keyword: String, page: Int, pageCount: Int): List<Article> {
        val filtered1Articles = articleFilterByKey(keyword)
        val filtered2Articles = articleFilterByPage(filtered1Articles,page, pageCount)
        return filtered2Articles
    }

    private fun articleFilterByPage(filtered1Articles : List<Article>, page: Int, pageCount: Int): List<Article> {
        val filtered2Articles = mutableListOf<Article>()
        val startIndex = filtered1Articles.lastIndex - ((page - 1) * pageCount)
        var endIndex = startIndex - pageCount + 1
        if(endIndex < 0){
            endIndex = 0
        }
        for(i in startIndex downTo endIndex){
            filtered2Articles.add(filtered1Articles[i])
        }
        return filtered2Articles
    }

    private fun articleFilterByKey(keyword: String): List<Article> {
        if(keyword.isEmpty()){
            return articles
        }
        val filteredArticles = mutableListOf<Article>()
        for(article in articles){
            if(article.title.contains(keyword)){
                filteredArticles.add(article)
            }
        }
        return filteredArticles
    }

}

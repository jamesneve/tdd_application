package models

case class Post(title: String, content: String) {
	require(title != null && !title.isEmpty, "Title must not be empty")
	require(content != null && !content.isEmpty, "Content must not be empty")
}

object Post {
	var posts = Set(
			Post("Melonpan", "Melonpan is nice"),
			Post("Nattoupan", "Nattoupan is not nice")
		)

	def findAll = posts.toList
	def add(post: Post) = (if(post != null) posts = posts + post)
}

package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

import models.Post

class Posts extends Controller {
	private val postForm: Form[Post] = Form(
		mapping(
			"title" -> nonEmptyText,
			"content" -> nonEmptyText
		)(Post.apply)(Post.unapply)
	)

	def index = Action { implicit request =>
		val posts = Post.findAll

		Ok(views.html.posts.index(posts, postForm))
	}

	def add = Action { implicit request =>
  	val newPostForm = postForm.bindFromRequest()

  	newPostForm.fold(
  		hasErrors = { postForm =>
  			Redirect(routes.Posts.index())
  		},

  		success = { postData =>
  			Post.add(Post(postData.title, postData.content))
  			Redirect(routes.Posts.index())
  		}
  	)
  }
}

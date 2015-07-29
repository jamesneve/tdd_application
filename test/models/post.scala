import org.scalatest._
import play.api.test._
import play.api.test.Helpers._
import org.scalatestplus.play._

import models.Post

class PostsSpec extends PlaySpec {
	// データ認証
	"A post" must {
		"be valid with valid input" in {
			val testPost = Post("Melonpan", "Melonpan is nice")
			testPost.title mustEqual "Melonpan"
			testPost.content mustEqual "Melonpan is nice"
		}

		"be invalid with no title" in {
			a [IllegalArgumentException] must be thrownBy {
				val testPost = Post(null, "Melonpan is nice")
			}

			a [IllegalArgumentException] must be thrownBy {
				val testPost = Post("", "Melonpan is nice")
			}
		}

		"be invalid with no content" in {
			a [IllegalArgumentException] must be thrownBy {
				val testPost = Post("Melonpan", null)
			}

			a [IllegalArgumentException] must be thrownBy {
				val testPost = Post("", null)
			}
		}
	}
	
	// メソッド
	"findAll" must {
		"get a list of posts" in {
			val posts = Post.findAll
			posts.isInstanceOf[List[models.Post]] mustEqual true
		}
	}

	"add(post: Post)" must {
		"add a post" in {
			val newPost = Post("Currypan", "Currypan is maa maa")
			Post.add(newPost)
			val posts = Post.findAll
			posts.contains(newPost) mustEqual true
		}
	}
}

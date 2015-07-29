import play.api._
import play.api.mvc._
import org.scalatest._
import play.api.test._
import play.api.test.Helpers._
import org.scalatestplus.play._
import play.api.data._
import play.api.data.Forms._

import controllers.Posts
import models.Post

class PostsControllerSpec extends PlaySpec {
	val controller = new Posts()

	"Posts controller index action" must {
		"return 200" in {
			val result = controller.index().apply(FakeRequest())
			status(result) mustEqual 200
		}
	}

	"Posts controller create action" must {
		"not create a post with invalid info" in {
			val result = controller.add().apply(FakeRequest().withFormUrlEncodedBody("title" -> null, "content" -> "Currypan is maa maa"))
			status(result) mustEqual 303
			Post.findAll.last.title must not be "Currypan"
		}

		"create a post with valid info and redirect" in {
			val result = controller.add().apply(FakeRequest().withFormUrlEncodedBody("title" -> "Currypan", "content" -> "Currypan is maa maa"))
			status(result) mustEqual 303
			Post.findAll.last.title mustEqual "Currypan"
		}
	}
}

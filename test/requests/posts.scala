import org.scalatest._
import play.api.test._
import play.api.test.Helpers._
import org.scalatestplus.play._
import play.api.libs.ws._
import play.api.mvc._
import Results._

import models.Post

class PostsRequestSpec extends PlaySpec with OneServerPerSuite with OneBrowserPerSuite with HtmlUnitFactory {
	"Index page" must {
		"have blog entries" in {
			Post.add(new Post("Arikui", "Arikui are nice"))
			go to(s"http://localhost:$port/posts/index")
			pageTitle mustBe "Posts"
			pageSource.contains("Arikui") mustEqual true
			pageSource.contains("Arikui are nice") mustEqual true
		}

		"be able to add a post with valid information" in {
			go to(s"http://localhost:$port/posts/index")
			pageTitle mustBe "Posts"
			click on name("title")
			textField("title").value = "Test"
			click on name("content")
			textField("content").value = "Testing 1234"
			click on name("submit")
			eventually {
				pageTitle mustBe "Posts"
				pageSource.contains("Testing 1234") mustBe true
			}
		}

		"not be able to add a post invalid information" in {
			go to(s"http://localhost:$port/posts/index")
			pageTitle mustBe "Posts"
			click on name("title")
			textField("title").value = "Test2"
			click on name("submit")
			eventually {
				pageTitle mustBe "Posts"
				pageSource.contains("Test2") mustBe false
			}
		}
	}
}

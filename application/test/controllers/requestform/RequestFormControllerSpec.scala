package controllers.requestform

import java.text.SimpleDateFormat
import java.util.Date

import forms.RequestFormCreate._
import model.form.{FormID, RequestForm}
import org.specs2.mock.Mockito
import play.api.libs.functional.syntax._
import play.api.libs.json._
import play.api.test.{FakeRequest, PlaySpecification, WithApplication}
import repository.form.RequestFormRepository

import scala.util.{Failure, Success}

class RequestFormControllerSpec extends PlaySpecification with Mockito {

  private val mockRequestFormRepository = mock[RequestFormRepository]
  private val requestFormControllerWithMock = new RequestFormController(mockRequestFormRepository)

  private val sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss")

  private val dummyRequestForm = RequestForm(
    FormID(1L),
    "Form xin nghi phep",
    "XN1",
    1,
    "<html>DON XIN NGHI PHEP</html>",
    Some("Team Leader"),
    Some("Manager"),
    Some("Director"),
    Some(new Date()),
    Some(new Date())
  )

  private val dummyRequestFormData = RequestFormCreateData(
    "Form xin nghi phep",
    "XN1",
    1,
    "<html>DON XIN NGHI PHEP</html>",
    Some("Team Leader"),
    Some("Manager"),
    Some("Director"),
    Some(new Date()),
    Some(new Date())
  )

  private val dateWrites: Writes[Date] = new Writes[Date] {
    def writes(d: Date): JsValue = JsString(sdf.format(d))
  }

  implicit val requestFormJsonWrite: Writes[RequestFormCreateData] = (
    (__ \ "title").write[String] and
    (__ \ "numberCode").write[String] and
    (__ \ "version").write[Int] and
    (__ \ "content").write[String] and
    (__ \ "levelApprove1").writeNullable[String] and
    (__ \ "levelApprove2").writeNullable[String] and
    (__ \ "levelApprove3").writeNullable[String] and
    (__ \ "startDate").writeNullable[Date](dateWrites) and
    (__ \ "endDate").writeNullable[Date](dateWrites)
  )(unlift(RequestFormCreateData.unapply))

  "RequestFormController" should {
    "createRequestForm" should {
      "return status 201 if create successfully" in new WithApplication() {
        mockRequestFormRepository.create(any[RequestForm]) returns Success(dummyRequestForm)

        val apiResult = call(
          requestFormControllerWithMock.createRequestForm,
          FakeRequest(POST, "/admin/form")
            .withJsonBody(Json.toJson(dummyRequestFormData))
        )

        status(apiResult) mustEqual 201
      }

      "return status 400" should {
        "if title is missing" in new WithApplication() {
          mockRequestFormRepository.create(any[RequestForm]) returns Failure(new Exception)

          val apiResult = call(
            requestFormControllerWithMock.createRequestForm,
            FakeRequest(POST, "/admin/form")
              .withJsonBody(Json.toJson(dummyRequestFormData.copy(title = "")))
          )

          status(apiResult) mustEqual 400
        }

        "if numberCode is missing" in new WithApplication() {
          mockRequestFormRepository.create(any[RequestForm]) returns Failure(new Exception)

          val apiResult = call(
            requestFormControllerWithMock.createRequestForm,
            FakeRequest(POST, "/admin/form")
              .withJsonBody(Json.toJson(dummyRequestFormData.copy(numberCode = "")))
          )
        }

        "if content is missing" in new WithApplication() {
          mockRequestFormRepository.create(any[RequestForm]) returns Failure(new Exception)

          val apiResult = call(
            requestFormControllerWithMock.createRequestForm,
            FakeRequest(POST, "/admin/form")
              .withJsonBody(Json.toJson(dummyRequestFormData.copy(content = "")))
          )
        }
      }
    }
  }
}

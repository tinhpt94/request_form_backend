package controllers.requestform

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

  private val dummyRequestForm = RequestForm(
    FormID(1L),
    "Form xin nghi phep",
    "XN1",
    1,
    "<html>DON XIN NGHI PHEP</html>",
    Some("Team Leader"),
    Some("Manager"),
    Some("Director"),
    true,
    false
  )

  private val dummyRequestFormData = RequestFormCreateData(
    "Form xin nghi phep",
    "XN1",
    1,
    "<html>DON XIN NGHI PHEP</html>",
    Some("Team Leader"),
    Some("Manager"),
    Some("Director"),
    true,
    false
  )

  implicit val requestFormJsonWrite: Writes[RequestFormCreateData] = (
    (__ \ "title").write[String] and
    (__ \ "numberCode").write[String] and
    (__ \ "version").write[Int] and
    (__ \ "content").write[String] and
    (__ \ "levelApprove1").writeNullable[String] and
    (__ \ "levelApprove2").writeNullable[String] and
    (__ \ "levelApprove3").writeNullable[String] and
    (__ \ "startDate").write[Boolean] and
    (__ \ "endDate").write[Boolean]
  )(unlift(RequestFormCreateData.unapply))

  "RequestFormController" should {
    "createRequestForm" should {
      "return status 201 if create successfully" in new WithApplication() {
        mockRequestFormRepository.create(any[RequestForm]) returns Success(dummyRequestForm)

        val apiResult = call(
          requestFormControllerWithMock.createRequestForm,
          FakeRequest(POST, "api/admin/form")
            .withJsonBody(Json.toJson(dummyRequestFormData))
        )

        status(apiResult) mustEqual 201
      }

      "return status 400" should {
        "if title is missing" in new WithApplication() {
          mockRequestFormRepository.create(any[RequestForm]) returns Failure(new Exception)

          val apiResult = call(
            requestFormControllerWithMock.createRequestForm,
            FakeRequest(POST, "api/admin/form")
              .withJsonBody(Json.toJson(dummyRequestFormData.copy(title = "")))
          )

          status(apiResult) mustEqual 400
        }

        "if version is missing" in new WithApplication() {
          mockRequestFormRepository.create(any[RequestForm]) returns Failure(new Exception)

          val apiResult = call(
            requestFormControllerWithMock.createRequestForm,
            FakeRequest(POST, "api/admin/form")
              .withJsonBody(Json.toJson(dummyRequestFormData).as[JsObject] - "version")
          )

          status(apiResult) mustEqual 400
        }

        "if numberCode is missing" in new WithApplication() {
          mockRequestFormRepository.create(any[RequestForm]) returns Failure(new Exception)

          val apiResult = call(
            requestFormControllerWithMock.createRequestForm,
            FakeRequest(POST, "api/admin/form")
              .withJsonBody(Json.toJson(dummyRequestFormData.copy(numberCode = "")))
          )

          status(apiResult) mustEqual 400
        }

        "if content is missing" in new WithApplication() {
          mockRequestFormRepository.create(any[RequestForm]) returns Failure(new Exception)

          val apiResult = call(
            requestFormControllerWithMock.createRequestForm,
            FakeRequest(POST, "api/admin/form")
              .withJsonBody(Json.toJson(dummyRequestFormData.copy(content = "")))
          )

          status(apiResult) mustEqual 400
        }

        "if startDate is missing" in new WithApplication() {
          mockRequestFormRepository.create(any[RequestForm]) returns Failure(new Exception)

          val apiResult = call(
            requestFormControllerWithMock.createRequestForm,
            FakeRequest(POST, "api/admin/form")
              .withJsonBody(Json.toJson(dummyRequestFormData).as[JsObject] - "startDate")
          )

          status(apiResult) must throwAn[Exception]
        }

        "if endDate is missing" in new WithApplication() {
          mockRequestFormRepository.create(any[RequestForm]) returns Failure(new Exception)

          val apiResult = call(
            requestFormControllerWithMock.createRequestForm,
            FakeRequest(POST, "api/admin/form")
              .withJsonBody(Json.toJson(dummyRequestFormData).as[JsObject] - "endDate")
          )

          status(apiResult) must throwAn[Exception]
        }
      }
    }
  }
}

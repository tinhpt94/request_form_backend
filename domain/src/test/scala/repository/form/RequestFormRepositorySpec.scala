package repository.form

import java.util.Date

import form.{FormDAO, FormRecord}
import model.form.{FormID, RequestForm}
import org.specs2.mock.Mockito
import play.api.test.PlaySpecification

import scala.util.{Failure, Success}

class RequestFormRepositorySpec extends PlaySpecification with Mockito {

  private val mockFormDAO = mock[FormDAO]
  private val formRepositoryWithMock = new RequestFormRepository(mockFormDAO)

  private val dummyForm = RequestForm(
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

  private val dummyFormRecord = FormRecord(
    1L,
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

  "FormRepository" should {
    "create" should {
      "return request form entity if create request form successfully" in {
        mockFormDAO.create(dummyFormRecord) returns Success(dummyFormRecord)
        formRepositoryWithMock.create(dummyForm) mustEqual Success(dummyForm)
      }

      "throw an exception if create request request form fail" in {
        mockFormDAO.create(dummyFormRecord) returns Failure(new Exception)
        formRepositoryWithMock.create(dummyForm).get must throwAn[Exception]
      }
    }
  }
}

package form

import play.api.test.PlaySpecification
import scalikejdbc.config.DBs
import scalikejdbc.specs2.mutable.AutoRollback

class FormDAOSpec extends PlaySpecification {

  DBs.setupAll()

  val formDAO = new FormDAO

  val dummyRecord = FormRecord(
    1L,
    "Form xin nghi phep",
    "XN1",
    1,
    "<html>DON XIN NGHI PHEP</html>",
    Some("Team Leader"),
    Some("Manager"),
    Some("Director"),
    true,
    true
  )

  "FormDAO" should {
    "create" should {
      "return form record if create successfully" in new AutoRollback {
        formDAO.create(dummyRecord).get mustEqual dummyRecord
      }
    }
  }
}

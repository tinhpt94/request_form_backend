package form

import scalikejdbc._

import scala.util.Try

class FormDAO {
  def create(formRecord: FormRecord)(implicit session: DBSession = AutoSession): Try[FormRecord] = Try {
    withSQL {
      val f = FormRecord.column
      insert.into(FormRecord)
        .namedValues(
          f.id -> formRecord.id,
          f.title -> formRecord.title,
          f.numberCode -> formRecord.numberCode,
          f.version -> formRecord.version,
          f.content -> formRecord.content,
          f.levelApprove1 -> formRecord.levelApprove1,
          f.levelApprove2 -> formRecord.levelApprove2,
          f.levelApprove3 -> formRecord.levelApprove3,
          f.startDate -> formRecord.startDate,
          f.endDate -> formRecord.endDate
        )
    }.update().apply()
    formRecord
  }
}

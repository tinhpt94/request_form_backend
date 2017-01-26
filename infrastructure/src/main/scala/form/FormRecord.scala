package form

import java.util.Date

import scalikejdbc._

case class FormRecord(
  id:            Long,
  title:         String,
  numberCode:    String,
  version:       Int,
  content:       String,
  levelApprove1: Option[String],
  levelApprove2: Option[String],
  levelApprove3: Option[String],
  startDate:     Option[Date],
  endDate:       Option[Date]
)

object FormRecord extends SQLSyntaxSupport[FormRecord] {

  override val tableName = "forms"

  def apply(rs: WrappedResultSet, n: ResultName[FormRecord]): FormRecord = FormRecord(
    id            = rs.long(n.id),
    title         = rs.string(n.title),
    numberCode    = rs.string(n.numberCode),
    version       = rs.int(n.version),
    content       = rs.string(n.content),
    levelApprove1 = rs.stringOpt(n.levelApprove1),
    levelApprove2 = rs.stringOpt(n.levelApprove2),
    levelApprove3 = rs.stringOpt(n.levelApprove3),
    startDate     = rs.dateOpt(n.startDate),
    endDate       = rs.dateOpt(n.endDate)
  )
}

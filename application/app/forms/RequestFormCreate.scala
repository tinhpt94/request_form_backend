package forms

import play.api.data.Form
import play.api.data.Forms._

object RequestFormCreate {

  val requestFormCreate = Form(
    mapping(
      "title" -> nonEmptyText,
      "numberCode" -> nonEmptyText,
      "version" -> number,
      "content" -> nonEmptyText,
      "levelApprove1" -> optional(text),
      "levelApprove2" -> optional(text),
      "levelApprove3" -> optional(text),
      "startDate" -> boolean,
      "endDate" -> boolean
    )(RequestFormCreateData.apply)(RequestFormCreateData.unapply)
  )

  case class RequestFormCreateData(
    title:         String,
    numberCode:    String,
    version:       Int,
    content:       String,
    levelApprove1: Option[String],
    levelApprove2: Option[String],
    levelApprove3: Option[String],
    startDate:     Boolean,
    endDate:       Boolean
  )

}

package model.form

import java.util.Date

case class RequestForm(
  id:            FormID,
  title:         String,
  numberCode:    String,
  version:       Int,
  content:       String,
  levelApprove1: Option[String],
  levelApprove2: Option[String],
  levelApprove3: Option[String],
  startDate:     Option[Date]   = None,
  endDate:       Option[Date]   = None
)
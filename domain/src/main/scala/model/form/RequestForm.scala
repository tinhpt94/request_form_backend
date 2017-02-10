package model.form

case class RequestForm(
  id:            FormID,
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
package model.user

import java.util.Date

import identity.Id
import model.form.{FormID, RequestForm}

case class User(role: Role) {

  def createForm(
    title:         String,
    numberCode:    String,
    version:       Int,
    content:       String,
    levelApprove1: Option[String],
    levelApprove2: Option[String],
    levelApprove3: Option[String],
    startDate:     Option[Date],
    endDate:       Option[Date]
  ): RequestForm = RequestForm(
    FormID(Id().randomID),
    title,
    numberCode,
    version,
    content,
    levelApprove1,
    levelApprove2,
    levelApprove3,
    startDate,
    endDate
  )
}

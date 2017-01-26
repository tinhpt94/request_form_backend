package repository.form

import javax.inject.Inject

import form.{FormDAO, FormRecord}
import model.form.{FormID, RequestForm}

import scala.util.Try

class RequestFormRepository @Inject() (formDAO: FormDAO) {

  def create(form: RequestForm): Try[RequestForm] = formDAO.create(entityToRecord(form)).map(recordToEntity)

  def entityToRecord(form: RequestForm): FormRecord = FormRecord(
    form.id.value,
    form.title,
    form.numberCode,
    form.version,
    form.content,
    form.levelApprove1,
    form.levelApprove2,
    form.levelApprove3,
    form.startDate,
    form.endDate
  )

  def recordToEntity(formRecord: FormRecord): RequestForm = RequestForm(
    FormID(formRecord.id),
    formRecord.title,
    formRecord.numberCode,
    formRecord.version,
    formRecord.content,
    formRecord.levelApprove1,
    formRecord.levelApprove2,
    formRecord.levelApprove3,
    formRecord.startDate,
    formRecord.endDate
  )
}

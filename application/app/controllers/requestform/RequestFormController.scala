package controllers.requestform

import javax.inject.Inject

import forms.RequestFormCreate._
import model.user.{Role, User}
import play.api.mvc.{Action, Controller}
import repository.form.RequestFormRepository
import responses.ErrorJson._

class RequestFormController @Inject() (formRepository: RequestFormRepository) extends Controller {

  def createRequestForm = Action { implicit request =>
    requestFormCreate.bindFromRequest().fold(
      hasErrors => BadRequest(parseError(hasErrors.errors)).as(JSON),
      data => {
        (for {
          _ <- formRepository.create(User(Role.ADMIN).createForm(
            data.title,
            data.numberCode,
            data.version,
            data.content,
            data.levelApprove1,
            data.levelApprove2,
            data.levelApprove3,
            data.startDate,
            data.endDate
          ))
        } yield {
          Created
        }).get
      }
    )
  }
}

package responses

import play.api.data.FormError
import play.api.libs.json._

object ErrorJson {
  def parseError(errors: Seq[FormError]): JsValue = {
    JsArray(errors.map(e => JsObject(Seq(
      "errors" -> JsObject(Seq(
        "key" -> JsString(e.key),
        "message" -> JsString(e.message)
      ))
    ))))
  }
}

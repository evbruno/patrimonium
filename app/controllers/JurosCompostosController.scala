package controllers

import javax.inject._
import models.JurosCompostos
import play.api.data.Forms._
import play.api.data._
import play.api.mvc._

// https://github.com/playframework/play-scala-forms-example/blob/2.6.x/app/controllers/WidgetController.scala

@Singleton
class JurosCompostosController @Inject()(cc: MessagesControllerComponents)
  extends MessagesAbstractController(cc) {

  val postUrl = routes.JurosCompostosController.formAction()

  val jurosCompostoForm: Form[JurosCompostos] = Form(
    mapping(
      "juros" -> bigDecimal,
      "aporte" -> bigDecimal,
      "interacoes" -> number(min = 1)
    )(JurosCompostos.apply)(JurosCompostos.unapply)
  )

  def indexAction = Action { implicit request: MessagesRequest[AnyContent] =>
    // Pass an unpopulated form to the template
    //Ok(views.html.listWidgets(widgets, form, postUrl))F

    val jc = JurosCompostos(0.65, 500, 12)
    val parcelas = JurosCompostos.parcelas(jc)
    val totalJuros = parcelas.foldRight(BigDecimal(0))(_.juros + _)

    Ok(views.html.juros_compostos(parcelas, totalJuros, jurosCompostoForm.fill(jc), postUrl))
  }

  def formAction = Action { implicit request: MessagesRequest[AnyContent] =>
    val errorFunction = { formWithErrors: Form[JurosCompostos] =>
      // This is the bad case, where the form had validation errors.
      // Let's show the user the form again, with the errors highlighted.
      // Note how we pass the form with errors to the template.
      BadRequest(views.html.juros_compostos(List.empty, 0, formWithErrors, postUrl))
    }

    val successFunction = { jc: JurosCompostos =>
      // This is the good case, where the form was successfully parsed as a JurosCompostos object.
      val parcelas = JurosCompostos.parcelas(jc)
      val totalJuros = parcelas.foldRight(BigDecimal(0))(_.juros + _)

      Ok(views.html.juros_compostos(parcelas, totalJuros, jurosCompostoForm.fill(jc), postUrl))
      // Redirect(routes.JurosCompostosController.indexAction()).flashing("info" -> "Widget added!")
    }

    val formValidationResult = jurosCompostoForm.bindFromRequest
    formValidationResult.fold(errorFunction, successFunction)
  }

}

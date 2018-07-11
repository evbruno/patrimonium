package controllers

import javax.inject._
import models.JurosCompostos
import play.api._
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def jurosCompostos(juros: Double, aporte: Double, interacoes: Int) = Action {
    val jc = JurosCompostos(juros, aporte, interacoes)
    val parcelas = JurosCompostos.parcelas(jc)
    val totalJuros = parcelas.foldRight(0d)(_.juros + _)
    Ok(views.html.juros_comp(jc, parcelas, totalJuros))
  }

  def todo = TODO
}

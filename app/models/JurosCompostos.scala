package models

case class JurosCompostos(juros: BigDecimal,
                          aporte: BigDecimal,
                          interacoes: Int) {

  assert(juros > 0)
  assert(interacoes > 0)

  override def toString =
    f"juros: $juros%.2f aporte: $aporte%.2f interacoes: $interacoes%02d"

}

case class ParcelaComJuros(parcela: Int,
                           valor: BigDecimal,
                           juros: BigDecimal)


object JurosCompostos {

  def parcelas(jc: JurosCompostos) =
    (1 to jc.interacoes).foldLeft(List[ParcelaComJuros]()) { (acc, num) =>

      val parcela =
        if (num == 1) ParcelaComJuros(1, jc.aporte, 0)
        else {
          val juros = (acc.last.valor * jc.juros) / 100d
          val valor = jc.aporte + acc.last.valor + juros
          ParcelaComJuros(num, valor, juros)
        }

      acc :+ parcela
    }

}

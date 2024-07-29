package com.brandoncano.capacitorcalculator.components

sealed class InformationDetails(val route: String) {
   data object Ceramic : InformationDetails("ceramic")
   data object Film : InformationDetails("film")
   data object Electrolytic: InformationDetails("electrolytic")
   data object SuperCapacitor: InformationDetails("supercapacitor")
   // This should ideally never be seen or used, it's just here to prevent an app crash in case a route fails
   data object SomethingWentWrong : InformationDetails("error")

   companion object {
      fun fromRoute(route: String): InformationDetails {
         return when (route) {
            Ceramic.route -> Ceramic
            Film.route -> Film
            Electrolytic.route -> Electrolytic
            SuperCapacitor.route -> SuperCapacitor
            else -> SomethingWentWrong
         }
      }
   }
}

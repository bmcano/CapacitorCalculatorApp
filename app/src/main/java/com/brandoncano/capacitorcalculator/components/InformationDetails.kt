package com.brandoncano.capacitorcalculator.components

sealed class InformationDetails(val route: String) {
   data object Ceramic : InformationDetails("ceramic")
   data object Electrolytic: InformationDetails("electrolytic")

   data object SomethingWentWrong : InformationDetails("error")

   companion object {
      fun fromRoute(route: String): InformationDetails {
         return when (route) {
            Ceramic.route -> Ceramic
            Electrolytic.route -> Electrolytic
            else -> SomethingWentWrong // in case we don't have a solid route we do not want to crash app
         }
      }
   }
}
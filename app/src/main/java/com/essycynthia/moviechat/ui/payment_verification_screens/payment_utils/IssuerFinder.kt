package com.essycynthia.moviechat.ui.payment_verification_screens.payment_utils


class IssuerFinder {

    companion object {
        fun detect(number: String): CardIssuer = when {
            isVisa(number) -> CardIssuer.VISA
            isMastercard(number) -> CardIssuer.MASTERCARD
            isAmericanExpress(number) -> CardIssuer.AMERICAN_EXPRESS
            else -> CardIssuer.OTHER
        }

        private fun isVisa(number: String) = number.isNotEmpty() && number.first() == '4'


        private fun isMastercard(number: String) = number.length >= 2 && number.substring(0, 2).toIntOrNull() in 51 until 56

        private fun isAmericanExpress(number: String) = number.length >= 2 && number.substring(0, 2).toIntOrNull() in 34 until 38
    }

}

enum class CardIssuer{
    VISA,
    MASTERCARD,
    AMERICAN_EXPRESS,
    OTHER
}
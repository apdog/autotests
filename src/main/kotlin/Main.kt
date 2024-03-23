package ru.netology

fun main() {
    commissionCalculation("Visa", 1_000.0, 10_000.0)
}

fun commissionCalculation(type: String, transferAmount: Double, previousMonthAmount: Double) {
    val dailyLimit = 150_000.0
    val monthlyLimit = 600_000.0
    val commissionRateMastercard = 0.006
    val commissionRateVisa = 0.0075
    val minCommissionVisa = 35.0
    val commissionAddMastercard = 20.0
    val commissionBorderMastercard = 75_000.0

    val totalCurrentMonthAmount = previousMonthAmount + transferAmount

    // Проверка на превышение лимитов
    if (transferAmount > dailyLimit) {
        println("Превышен суточный лимит перевода")
        return
    }

    if (totalCurrentMonthAmount + transferAmount > monthlyLimit) {
        println("Превышен месячный лимит перевода")
        return
    }

    // Расчет комиссии
    val commission = when (type) {
        "Mastercard" -> {
            if (totalCurrentMonthAmount >= commissionBorderMastercard) {
                transferAmount * commissionRateMastercard + commissionAddMastercard
            } else {
                0.0
            }
        }

        "Visa" -> {
            val calculatedCommission = transferAmount * commissionRateVisa
            if (calculatedCommission < minCommissionVisa) {
                minCommissionVisa
            } else {
                calculatedCommission
            }
        }

        "Мир" -> 0.0
        else -> {
            println("Неподдерживаемый тип карты")
            return
        }
    }

    println("Комиссия перевода с карты $type в размере $transferAmount рублей составила $commission рублей")
}
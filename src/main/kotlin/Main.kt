package ru.netology

fun main() {
    //задаем изначальные паарметры для рассчета
    val typeOfCard = "Visa"
    val transferAmount = 1_000.0
    val previousMonthAmount = 10_000.0
    //считаем комиссию
    val commission = commissionCalculation(typeOfCard, transferAmount, previousMonthAmount)
    //проверяем не получили ли мы отрицательное значение, означающее ошибку при расчете комиссии
    if (commission >= 0) {
        println("Комиссия перевода с карты $typeOfCard в размере $transferAmount рублей составила $commission рублей")
    } else {
        println("Ошибка при расчете комиссии.")
    }
}

// проверка дневного и месячного лимита, если лимит превышен возвращаем true
fun checkLimits(type: String, transferAmount: Double, totalCurrentMonthAmount: Double): Boolean {
    val dailyLimit = 150_000.0
    val monthlyLimit = 600_000.0
    // переменная для суммы переводов VK Pay
    var totalVkPayAmount = 0.0

    if (transferAmount > dailyLimit) {
        println("Превышен суточный лимит перевода")
        return true
    }

    if (totalCurrentMonthAmount + transferAmount > monthlyLimit) {
        println("Превышен месячный лимит перевода")
        return true
    }

    if (type == "VK Pay") {
        if (transferAmount > 15_000.0) {
            println("Превышен размер разового перевода VK Pay")
            return true
        }

        totalVkPayAmount += transferAmount
        if (totalVkPayAmount > 40_000.0) {
            println("Превышен месячный лимит переводов VK Pay")
            return true
        }
    }
    return false
}

fun commissionCalculation(type: String, transferAmount: Double, previousMonthAmount: Double): Double {
    val commissionRateMSMaestro = 0.006
    val minTransferAmountMSMaestro = 300.0
    val commissionAddMSMaestro = 20.0
    val commissionBorderMSMaestro = 75_000.0
    val commissionRateVisaMir = 0.0075
    val minCommissionVisaMir = 35.0


    val totalCurrentMonthAmount = previousMonthAmount + transferAmount

    if (checkLimits(type, transferAmount, totalCurrentMonthAmount)) {
        return -1.0
    } else {
        return when (type) {
            "Mastercard", "Maestro" -> {
                if (totalCurrentMonthAmount >= commissionBorderMSMaestro && transferAmount >= minTransferAmountMSMaestro) {
                    transferAmount * commissionRateMSMaestro + commissionAddMSMaestro
                } else {
                    0.0
                }
            }

            "Visa", "Мир" -> {
                val calculatedCommission = transferAmount * commissionRateVisaMir
                if (calculatedCommission < minCommissionVisaMir) {
                    minCommissionVisaMir
                } else {
                    calculatedCommission
                }
            }

            "VK pay" -> 0.0
            else -> {
                println("Неподдерживаемый тип карты")
                -1.0
            }
        }
    }
}

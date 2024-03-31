import org.junit.Assert.assertEquals
import org.junit.Test

class CommissionCalculationTest {

    @Test
    fun testMastercardMinCommission() {
        val type = "Mastercard"
        val transferAmount = 299.0
        val previousMonthAmount = 0.0
        val expectedCommission = 21.794
        val totalVkPayAmount = 0.0

        val result = commissionCalculation(type, transferAmount, previousMonthAmount, totalVkPayAmount)

        assertEquals(expectedCommission, result, 0.001)
    }

    @Test
    fun testMastercardWithoutCommission() {
        val type = "Mastercard"
        val transferAmount = 301.0
        val previousMonthAmount = 0.0
        val expectedCommission = 0.0
        val totalVkPayAmount = 0.0

        val result = commissionCalculation(type, transferAmount, previousMonthAmount, totalVkPayAmount)

        assertEquals(expectedCommission, result, 0.001)
    }

    @Test
    fun testMastercardUnderFreeLimitCommission() {
        val type = "Mastercard"
        val transferAmount = 75_000.0
        val previousMonthAmount = 0.0
        val expectedCommission = 470.0
        val totalVkPayAmount = 0.0

        val result = commissionCalculation(type, transferAmount, previousMonthAmount, totalVkPayAmount)

        assertEquals(expectedCommission, result, 0.001)
    }

    @Test
    fun testMastercardOverFreeLimitCommission() {
        val type = "Mastercard"
        val transferAmount = 75_000.0
        val previousMonthAmount = 1_000.0
        val totalVkPayAmount = 0.0
        val expectedCommission = 470.0

        val result = commissionCalculation(type, transferAmount, previousMonthAmount, totalVkPayAmount)

        assertEquals(expectedCommission, result, 0.001)
    }

    @Test
    fun testMaestroMinCommission() {
        val type = "Maestro"
        val transferAmount = 299.0
        val previousMonthAmount = 0.0
        val totalVkPayAmount = 0.0
        val expectedCommission = 21.794

        val result = commissionCalculation(type, transferAmount, previousMonthAmount, totalVkPayAmount)

        assertEquals(expectedCommission, result, 0.001)
    }

    @Test
    fun testMaestroWithoutCommission() {
        val type = "Maestro"
        val transferAmount = 301.0
        val previousMonthAmount = 0.0
        val totalVkPayAmount = 0.0
        val expectedCommission = 0.0

        val result = commissionCalculation(type, transferAmount, previousMonthAmount, totalVkPayAmount)

        assertEquals(expectedCommission, result, 0.001)
    }

    @Test
    fun testMaestroUnderFreeLimitCommission() {
        val type = "Maestro"
        val transferAmount = 75_000.0
        val previousMonthAmount = 0.0
        val totalVkPayAmount = 0.0
        val expectedCommission = 470.0

        val result = commissionCalculation(type, transferAmount, previousMonthAmount, totalVkPayAmount)

        assertEquals(expectedCommission, result, 0.001)
    }

    @Test
    fun testMaestroOverFreeLimitCommission() {
        val type = "Maestro"
        val transferAmount = 75_000.0
        val previousMonthAmount = 1_000.0
        val totalVkPayAmount = 0.0
        val expectedCommission = 470.0

        val result = commissionCalculation(type, transferAmount, previousMonthAmount, totalVkPayAmount)

        assertEquals(expectedCommission, result, 0.001)
    }

    @Test
    fun testVisaMinCommission() {
        val type = "Visa"
        val transferAmount = 100.0
        val previousMonthAmount = 0.0
        val totalVkPayAmount = 0.0
        val expectedCommission = 35.0

        val result = commissionCalculation(type, transferAmount, previousMonthAmount, totalVkPayAmount)

        assertEquals(expectedCommission, result, 0.001)
    }

    @Test
    fun testVisaBelowMinCommission() {
        val type = "Visa"
        val transferAmount = 5.0
        val previousMonthAmount = 0.0
        val totalVkPayAmount = 0.0
        val expectedCommission = 35.0

        val result = commissionCalculation(type, transferAmount, previousMonthAmount, totalVkPayAmount)

        assertEquals(expectedCommission, result, 0.001)
    }

    @Test
    fun testVisaCalculatedCommission() {
        val type = "Visa"
        val transferAmount = 11_000.0
        val previousMonthAmount = 0.0
        val totalVkPayAmount = 0.0
        val expectedCommission = 82.5

        val result = commissionCalculation(type, transferAmount, previousMonthAmount, totalVkPayAmount)

        assertEquals(expectedCommission, result, 0.001)
    }

    @Test
    fun testMirMinCommission() {
        val type = "Мир"
        val transferAmount = 100.0
        val previousMonthAmount = 0.0
        val totalVkPayAmount = 0.0
        val expectedCommission = 35.0

        val result = commissionCalculation(type, transferAmount, previousMonthAmount, totalVkPayAmount)

        assertEquals(expectedCommission, result, 0.001)
    }

    @Test
    fun testMirBelowMinCommission() {
        val type = "Мир"
        val transferAmount = 5.0
        val previousMonthAmount = 0.0
        val totalVkPayAmount = 0.0
        val expectedCommission = 35.0

        val result = commissionCalculation(type, transferAmount, previousMonthAmount, totalVkPayAmount)

        assertEquals(expectedCommission, result, 0.001)
    }

    @Test
    fun testMirCalculatedCommission() {
        val type = "Мир"
        val transferAmount = 11_000.0
        val previousMonthAmount = 0.0
        val totalVkPayAmount = 0.0
        val expectedCommission = 82.5

        val result = commissionCalculation(type, transferAmount, previousMonthAmount, totalVkPayAmount)

        assertEquals(expectedCommission, result, 0.001)
    }

    @Test
    fun testVkPayNoCommission() {
        val type = "VK pay"
        val transferAmount = 100.0
        val previousMonthAmount = 0.0
        val totalVkPayAmount = 0.0
        val expectedCommission = 0.0

        val result = commissionCalculation(type, transferAmount, previousMonthAmount, totalVkPayAmount)

        assertEquals(expectedCommission, result, 0.001)
    }

    @Test
    fun testInvalidCardType() {
        val type = "Invalid"
        val transferAmount = 100.0
        val previousMonthAmount = 0.0
        val totalVkPayAmount = 0.0
        val expectedCommission = -2.0

        val result = commissionCalculation(type, transferAmount, previousMonthAmount, totalVkPayAmount)

        assertEquals(expectedCommission, result, 0.001)
    }

    @Test
    fun testInvalidCheckLimits() {
        val type = "Invalid"
        val transferAmount = 100_000.0
        val previousMonthAmount = 555_555.0
        val totalVkPayAmount = 0.0
        val expectedCommission = -1.0

        val result = commissionCalculation(type, transferAmount, previousMonthAmount, totalVkPayAmount)

        assertEquals(expectedCommission, result, 0.001)
    }

}
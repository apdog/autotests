import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CheckLimitsTest {

    @Test
    fun testDailyLimitExceeds() {
        val type = "Mastercard"
        val transferAmount = 150_001.0
        val totalVkPayAmount = 0.0
        val totalCurrentMonthAmount = 0.0

        val result = checkLimits(type, transferAmount, totalCurrentMonthAmount, totalVkPayAmount)

        assertTrue(result)
    }

    @Test
    fun testDailyLimitNotExceeds() {
        val type = "Mastercard"
        val transferAmount = 150_000.0
        val totalVkPayAmount = 0.0
        val totalCurrentMonthAmount = 0.0

        val result = checkLimits(type, transferAmount, totalCurrentMonthAmount, totalVkPayAmount)

        assertFalse(result)
    }

    @Test
    fun testMonthlyLimitExceeds() {
        val type = "Mastercard"
        val transferAmount = 100_000.0
        val totalVkPayAmount = 0.0
        val totalCurrentMonthAmount = 600_000.0

        val result = checkLimits(type, transferAmount, totalCurrentMonthAmount, totalVkPayAmount)

        assertTrue(result)
    }

    @Test
    fun testMonthlyLimitNotExceeds() {
        val type = "Mastercard"
        val transferAmount = 100_000.0
        val totalVkPayAmount = 0.0
        val totalCurrentMonthAmount = 499_999.99

        val result = checkLimits(type, transferAmount, totalCurrentMonthAmount, totalVkPayAmount)

        assertFalse(result)
    }

    @Test
    fun testVkPayLimitExceeds() {
        val type = "VK Pay"
        val transferAmount = 15_001.0
        val totalVkPayAmount = 0.0
        val totalCurrentMonthAmount = 0.0

        val result = checkLimits(type, transferAmount, totalCurrentMonthAmount, totalVkPayAmount)

        assertTrue(result)
    }

    @Test
    fun testBeloVkPayLimitNotExceeds() {
        val type = "VK Pay"
        val transferAmount = 14_999.99
        val totalVkPayAmount = 0.0
        val totalCurrentMonthAmount = 0.0

        val result = checkLimits(type, transferAmount, totalCurrentMonthAmount, totalVkPayAmount)

        assertFalse(result)
    }

    @Test
    fun testMonthVkPayLimitNotExceeds() {
        val type = "VK Pay"
        val transferAmount = 14_999.99
        val totalVkPayAmount = 0.0
        val totalCurrentMonthAmount = 0.0

        val result = checkLimits(type, transferAmount, totalCurrentMonthAmount, totalVkPayAmount)

        assertFalse(result)
    }

    @Test
    fun testMonthVkPayLimitExceeds() {
        val type = "VK Pay"
        val transferAmount = 10_999.99
        val totalVkPayAmount = 30_444.0
        val totalCurrentMonthAmount = 0.0

        val result = checkLimits(type, transferAmount, totalCurrentMonthAmount, totalVkPayAmount)

        assertTrue(result)
    }

}
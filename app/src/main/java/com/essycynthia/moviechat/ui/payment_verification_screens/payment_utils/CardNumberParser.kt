package com.essycynthia.moviechat.ui.payment_verification_screens.payment_utils


class CardNumberParser(
    private val number: String,
    private val emptyChar: Char = DEFAULT_EMPTY_CHAR
) {
    lateinit var first: String
    lateinit var second: String
    lateinit var third: String
    lateinit var fourth: String

    init {
        splitCardNumber()
    }

    private fun initEmptyBlock() = "".padEnd(BLOCK_SIZE, emptyChar)

    private fun splitCardNumber() {
        first = getBlock(blockNumber = FIRST)
        second = getBlock(blockNumber = SECOND)
        third = getBlock(blockNumber = THIRD)
        fourth = getBlock(blockNumber = FOURTH)
    }

    private fun getBlock(blockNumber: Int): String {
        val length = number.length
        var block = initEmptyBlock()
        val start = (blockNumber - 1) * BLOCK_SIZE
        val end = blockNumber * BLOCK_SIZE

        if (length in start until end) {
            block = number
                .substring(start, length)
                .padEnd(BLOCK_SIZE, emptyChar)
        } else if (number.length >= start) {
            block = number.substring(start, end)
        }

        return block
    }

    companion object {
        private const val DEFAULT_EMPTY_CHAR = 'x'
        private const val BLOCK_SIZE = 4
        private const val FIRST = 1
        private const val SECOND = 2
        private const val THIRD = 3
        private const val FOURTH = 4
    }
}






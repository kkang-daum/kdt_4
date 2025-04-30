package org.example.ch3.section9

interface DataHandler {
    fun handleData(data: String)
    fun canProcess(dataType: String): Boolean
}

class DataProcessor(private val name: String) {
    companion object {
        const val MAX_BATCH_SIZE = 100
        private var processedCount = 0

        fun getProcessedCount(): Int {
            return processedCount
        }

        fun resetCounter() {
            println("resetCounter()....")
            processedCount = 0
        }
    }

    private var isActive: Boolean = true

    fun processData(data: String, dataType: String) {
        val handler = object : DataHandler {
            override fun handleData(data: String) {
                println("handleData() call.....")
            }

            override fun canProcess(dataType: String): Boolean {
                return true
            }
        }

        if (handler.canProcess(dataType)) {
            handler.handleData(data)
        }
    }
}


fun main() {

    val processor1 = DataProcessor("TextProcessor")
    processor1.processData("Hello, World!", "text")

    println(DataProcessor.getProcessedCount())
    DataProcessor.resetCounter()
}
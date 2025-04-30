package org.example.ch3.sec9_object

interface DataHandler {
    fun handleData(data: String) : Unit
    fun canProcess(dataType: String) : Boolean
}

class DataProcessor(private val name: String) {
    companion object {
        const val MAX_BATCH_SIZE = 100
        var processedCount = 0  // private ?
            private set(value) { field = value}

        fun resetCounter() { println("resetCounter() call")}
    }

    private var isActive = true

    fun processData(data: String, dataType: String) {
        val handler = object: DataHandler {
            override fun handleData(data: String) {
                println("handleData() call")
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
    processor1.processData("Hello, world!", "text")

    DataProcessor.processedCount
    DataProcessor.resetCounter()

}
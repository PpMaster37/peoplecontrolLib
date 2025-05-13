import java.util.random.RandomGenerator

class WorkStations {
    private var type: WorkStationType
    private val workers = mutableListOf<Worker>()
    private var tier: Int = 1
    private var positions: Int = 0
        get() {
            return this.tier
        }

    init {
        val arr =
            arrayOf(WorkStationType.INTWorkStation, WorkStationType.PHYWorkStation, WorkStationType.STRWorkStation)
        val g = RandomGenerator.of("")
        type = arr[g.nextInt(3)]
    }

    fun upgrade() {
        positions
    }
    fun calculateProduction(): Double{
        var x = 0.0
        for(worker in workers){
            x += worker.work()
        }
        return x
    }

    fun calculateConsumption(): Double{
        var x = 0.0
        for(worker in workers){
            x += (tier * 2.0 / (worker.prodLog()))
        }
        return x
    }

    fun setType(inputType:WorkStationType){
        type = inputType
    }

    fun addWorker(worker: Worker){
        workers.add(worker)
    }

    fun available(): Boolean{
        return workers.size < positions
    }
}

enum class WorkStationType() {
    INTWorkStation(),
    PHYWorkStation(),
    STRWorkStation(),
    NULLTYPE()
}
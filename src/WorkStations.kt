import java.util.random.RandomGenerator

class WorkStations {
    private var type: WorkStationType
    private val workers = mutableListOf<Worker>()
    private var tier: Int = 1
    private val positions: Int
        get() {
            return this.tier * 3
        }

    init {
        val arr =
            arrayOf(WorkStationType.INTWorkStation, WorkStationType.PHYWorkStation, WorkStationType.STRWorkStation)
        val g = RandomGenerator.of("")
        type = arr[g.nextInt(3)]
    }

    fun upgrade() {
        tier++
    }

    fun calculateProduction(): Double {
        var x = 0.0
        for (worker in workers) {
            x += worker.work()
        }
        if (type == WorkStationType.NULLTYPE) {
            return 0.0
        }
        return x
    }

    fun calculateConsumption(): Double {
        var x = 0.0
        for (worker in workers) {
            x += (tier * 2.0 / (worker.prodLog()))
        }
        x += (tier / 2) * (positions - workers.size)
        if (type == WorkStationType.NULLTYPE) {
            return 0.0
        }
        return x
    }

    fun setType(inputType: WorkStationType) {
        type = inputType
    }

    fun addWorker(worker: Worker) {
        worker.setWorkType(type)
        workers.add(worker)
    }

    fun available(): Boolean {
        return workers.size < positions
    }
}

enum class WorkStationType() {
    INTWorkStation(),
    PHYWorkStation(),
    STRWorkStation(),
    NULLTYPE()
}
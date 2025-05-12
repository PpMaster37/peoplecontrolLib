import java.util.random.RandomGenerator

class WorkStations {
    private lateinit var type: WorkStationType
    private var tier: Int =1
    private var positions: Int = 0
        get() {
          return tier
        }

    init{
        val arr =
            arrayOf(WorkStationType.INTWorkStation, WorkStationType.PHYWorkStation, WorkStationType.STRWorkStation)
        val g = RandomGenerator.of("")
        type = arr[g.nextInt(3)]
    }
    fun produce() {}
    fun upgrade() {}
    fun calculateProduction(){}
    fun calculateConsumption(){}
    fun setType(inputType:WorkStationType){
        type = inputType
    }
}

enum class WorkStationType() {
    INTWorkStation(),
    PHYWorkStation(),
    STRWorkStation()
}
import java.util.random.RandomGenerator
import kotlin.math.exp
import kotlin.math.sqrt

class Worker(workStation: WorkStationType) {
    private lateinit var id: String
    private var intStat: Int = 0
    private var phyStat: Int = 0
    private var strStat: Int = 0
    private var productivity = 100.00
    private var isWorking = false
    private var death = false
    private var workType: WorkStationType

    init {
        val generator = RandomGenerator.of("")
        intStat = generator.nextInt(WorkerConstants.StatMax.get)
        phyStat = generator.nextInt(WorkerConstants.StatMax.get)
        strStat = generator.nextInt(WorkerConstants.StatMax.get)
        workType = workStation
    }

    fun cycle() {
        val generator = RandomGenerator.of("")
        if(isWorking) {
            productivity -= sqrt(generator.nextDouble(productivity / 4.0))
        } else if (productivity<100){
            productivity *= (100.00-productivity) / 100.0 + 1
        }
        if (generator.nextInt(productivity.toInt()) == 1) {
            death = true
        }
    }

    fun work(): Double {
        return prodLog() * getSpecStat()
    }

    fun prodLog(): Double {
        return exp(productivity) + 1
    }

    fun getSpecStat(): Double {
        return when(workType){
            WorkStationType.INTWorkStation -> intStat.toDouble()
            WorkStationType.PHYWorkStation -> phyStat.toDouble()
            WorkStationType.STRWorkStation -> strStat.toDouble()
        }
    }
}
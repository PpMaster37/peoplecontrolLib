import java.util.random.RandomGenerator
import kotlin.math.exp
import kotlin.math.sqrt

class Worker(workStation: WorkStationType) {
    private val id: String
        get() {
            var x = workType.toString()
            x = x + getSpecStat().toString()
            return x
        }
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
        if (isWorking) {
            productivity -= sqrt(generator.nextDouble(productivity / 4.0))
        } else if (productivity < 100) {
            productivity *= (100.00 - productivity) / 100.0 + 1
        }
        if (generator.nextInt(productivity.toInt() * 2) == 1) {
            death = true
        }
    }

    fun work(): Double {
        when (workType) {
            WorkStationType.INTWorkStation -> intStat + 0.005
            WorkStationType.PHYWorkStation -> phyStat + 0.005
            WorkStationType.STRWorkStation -> strStat + 0.005
            WorkStationType.NULLTYPE -> intStat + 0
        }
        return prodLog() * getSpecStat()
    }

    fun prodLog(): Double {
        return exp(productivity) + 1
    }

    fun getSpecStat(): Double {
        return when (workType) {
            WorkStationType.INTWorkStation -> intStat.toDouble()
            WorkStationType.PHYWorkStation -> phyStat.toDouble()
            WorkStationType.STRWorkStation -> strStat.toDouble()
            WorkStationType.NULLTYPE -> 0.0
        }
    }

    fun setWorkType(workType: WorkStationType) {
        this.workType = workType
    }

    fun getDeadStatus(): Boolean {
        return death
    }

    fun getID(): String {
        return id
    }

    fun getWorking(): Boolean {
        return isWorking
    }

    fun nuke() {
        productivity = 1.0
        strStat = 1
        intStat = 1
        phyStat = 1
    }
}
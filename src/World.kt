import java.util.random.RandomGenerator

class World {
    private val workStations = mutableSetOf<WorkStations>()
    private val workers = mutableListOf<Worker>()

    init {
        val str = WorkStations()
        val int = WorkStations()
        val phy = WorkStations()
        str.setType(WorkStationType.STRWorkStation)
        int.setType(WorkStationType.INTWorkStation)
        phy.setType(WorkStationType.PHYWorkStation)
        workStations.addAll(setOf<WorkStations>(str, int, phy))
        workers.addAll(randomWorkerList())
        workStations.addAll(randomWorkStationSet())
    }

    fun cycle() {
        var products = 0.0
        for (workStation in workStations) {
            products += workStation.calculateProduction()
            products -= workStation.calculateConsumption()
        }
        for (worker in workers) {
            worker.cycle()
            if (worker.getAliveStatus()) {
                workers.remove(worker)
            }
        }
        print("Net Production: $products" )
        if (products < 0) {
            print("Objective failed, get back to work")
        }
    }

    fun getWorkers(): MutableList<Worker> {
        return workers
    }

    fun getWorkStations(): MutableSet<WorkStations> {
        return workStations
    }

    fun randomWorkerList(): List<Worker> {
        val generator = RandomGenerator.of("")
        val workerNumber = generator.nextInt(8,24)
        val workerList = mutableListOf<Worker>()
        for (i in 1..workerNumber) {
            workerList.add(Worker(WorkStationType.NULLTYPE))
        }
        return workerList
    }
    fun randomWorkStationSet(): Set<WorkStations> {
        val generator = RandomGenerator.of("")
        val stationNumber = generator.nextInt(1,5)
        val stationList = mutableSetOf<WorkStations>()
        for (i in 1..stationNumber) {
            stationList.add(WorkStations())
        }
        return stationList
    }
}
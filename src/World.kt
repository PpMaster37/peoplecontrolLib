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
    }

    fun cycle(){
        var products = 0.0
        for(workStation in workStations){
            products += workStation.calculateProduction()
            products -= workStation.calculateConsumption()
        }
        for (worker in workers){
            worker.cycle()
            if(worker.getAliveStatus()) {
                workers.remove(worker)
            }
        }
    }
    fun getWorkers(): MutableList<Worker>{
        return workers
    }
}
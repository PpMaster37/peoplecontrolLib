class World {
    private val workStations = mutableSetOf<WorkStations>()
    private val workers = mutableListOf<Worker>()

    init{

    }

    fun cycle(){
        for(workStation in workStations){
            workStation.produce()
        }
        for (workers in workers){
            workers.cycle()
        }
    }
}
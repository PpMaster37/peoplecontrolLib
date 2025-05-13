object Operator {
    private lateinit var currentWorld: World
    fun setWorld(input: World) {
        currentWorld = input
    }

    fun move(id: String, destination: WorkStations): Boolean {
        val obj = findWorker(id)
        if(obj.info && destination.available()){
            destination.addWorker(obj.worker)
            return true
        }
        return false
    }

    fun findWorker(id: String): returnObject{
        val workerArr = currentWorld.getWorkers()
        for (worker in workerArr) {
            if(worker.getID() == id) {
                return returnObject(worker, true)
            }
        }
        return returnObject(Worker(WorkStationType.NULLTYPE), false)
    }
}
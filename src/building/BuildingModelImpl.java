package building;

import scanerzus.Request;

/**
 * The BuildingModelImpl class is the implementation of the BuildingModelInterface.
 * Work as the liaison between the controller and the actual model (i.e. ControlUnit).
 */
public class BuildingModelImpl implements BuildingModelInterface {
  private ControlUnit controlUnit;

    /**
     * Constructor for the BuildingModelImpl class.
     * @param numFloors the number of floors in the building
     * @param numElevators the number of elevators in the building
     * @param elevatorCapacity the capacity of the elevators
     */
  public BuildingModelImpl(int numFloors, int numElevators, int elevatorCapacity) {
    this.controlUnit = new ControlUnit(numFloors, numElevators, elevatorCapacity);
  }


  @Override
  public boolean addRequest(Request request) throws IllegalArgumentException{

    return false;
  }

  @Override
  public void stopElevatorSystem() {
  }

  @Override
  public BuildingReport getElevatorSystemStatus() {
    BuildingReport report = new BuildingReport(this.controlUnit);
    return null;
  }

  @Override
  public boolean startElevatorSystem() {
    return false;
  }

  @Override
  public void takeStep(int numSteps) {

  }
}

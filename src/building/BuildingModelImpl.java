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
   *
   * @param numFloors        the number of floors in the building
   * @param numElevators     the number of elevators in the building
   * @param elevatorCapacity the capacity of the elevators
   */
  public BuildingModelImpl(int numFloors, int numElevators, int elevatorCapacity) {
    this.controlUnit = new ControlUnit(numFloors, numElevators, elevatorCapacity);
  }


  @Override
  public boolean addRequest(Request request) {
    return this.controlUnit.addRequest(request);
  }

  @Override
  public void stopElevatorSystem() {
    this.controlUnit.stopElevatorSystem();
  }

  @Override
  public BuildingReport getElevatorSystemStatus() {
    BuildingReport report = new BuildingReport(this.controlUnit);
    return null;
  }

  @Override
  public boolean startElevatorSystem() {
    return this.controlUnit.startElevatorSystem();
  }

  @Override
  public void takeStep(int numSteps) throws IllegalArgumentException {
    if (numSteps < 1) {
      throw new IllegalArgumentException("Number of steps must be greater than 0");
    }
    this.controlUnit.takeStep(numSteps);
  }
}

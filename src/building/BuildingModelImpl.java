package building;

import scanerzus.Request;

public class BuildingModelImpl implements BuildingModelInterface {
  private ControlUnit controlUnit;

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

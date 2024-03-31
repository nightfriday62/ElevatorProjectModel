package building;

import scanerzus.Request;
/**
 * This interface is used to represent a building.
 */
public interface BuildingModelInterface {
  public boolean addRequest(Request request);

  public void stopElevatorSystem();

  public boolean startElevatorSystem();

  public BuildingReport getElevatorSystemStatus();

  public void takeStep(int numSteps);

}

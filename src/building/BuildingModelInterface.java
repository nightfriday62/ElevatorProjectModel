package building;

import scanerzus.Request;

/**
 * This interface is used to represent the building's elevator system.
 * Class that implements this interface will be the liaison between the actual
 * modal (i.e. the ControlUnit) and the controller.
 */
public interface BuildingModelInterface {
  /**
   * This method is used to add a request to the building's elevator system.
   *
   * @param request The request to be added to the system.
   * @return True if the request was successfully added, false otherwise.
   */
  public boolean addRequest(Request request);

  /**
   * This method is used to stop the building's elevator system.
   */
  public void stopElevatorSystem();

  /**
   * This method is used to start the building's elevator system.
   *
   * @return True if the system was successfully started, false otherwise.
   */

  public boolean startElevatorSystem();

  /**
   * This method is used to get the status of the building's elevator system.
   *
   * @return A BuildingReport object that contains the status of the system.
   * The BuildingReport class is a wrapper class that uses the ControlUnit as
   * its information source.
   */
  public BuildingReport getElevatorSystemStatus();

  /**
   * This method is used to take a step in the building's elevator system.
   *
   * @param numSteps The number of steps to take in the system.
   */
  public void takeStep(int numSteps);

}

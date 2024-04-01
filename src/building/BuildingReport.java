package building;

import building.enums.ElevatorSystemStatus;
import elevator.ElevatorReport;
import java.util.LinkedList;
import scanerzus.Request;
import java.util.List;

/**
 * The BuildingReport class is a wrapper class that uses the ControlUnit as
 * its information source.
 */
public class BuildingReport {
  private ControlUnit controlUnit;

  /**
   * Constructor for the BuildingReport class.
   *
   * @param controlUnit the control unit to get the information from
   */
  public BuildingReport(ControlUnit controlUnit) {
    this.controlUnit = controlUnit;
  }

  /**
   * This method will provide a deep copy of the up requests.
   * (Requests objects are not recreated but the list is a new list.)
   *
   * @return a list of up requests.
   */
  public List<Request> getUpRequests() {
    return new LinkedList<Request>(controlUnit.getUpRequests());
  }

  /**
   * This method will provide a deep copy of the down requests.
   * (Requests objects are not recreated but the list is a new list.)
   *
   * @return a list of down requests.
   */
  public List<Request> getDownRequests() {
    return new LinkedList<Request>(controlUnit.getDownRequests());
  }

  /**
   * This method will pass the array of elevator reports from the control unit
   * to the caller.
   *
   * @return the array of elevator reports.
   */
  public ElevatorReport[] getElevatorReports() {
    return this.controlUnit.getElevatorReports();
  }

  /**
   * This method will provide the number of floors in the building.
   *
   * @return the number of floors in the building.
   */
  public int getNumFloors() {
    return this.controlUnit.getNumFloors();
  }

  /**
   * This method will provide the status of the elevator system.
   *
   * @return the status of the elevator system.
   */
  public ElevatorSystemStatus getSystemStatus() {
    return this.controlUnit.getSystemStatus();
  }

  /**
   * This method will provide the capacity of the elevators.
   *
   * @return the capacity of the elevators.
   */
  public int getElevatorCapacity() {
    return this.controlUnit.getElevatorCapacity();
  }

  /**
   * This method will provide the number of elevators in the building.
   *
   * @return the number of elevators in the building.
   */
  public int getNumElevators() {
    return this.controlUnit.getNumElevators();
  }
}

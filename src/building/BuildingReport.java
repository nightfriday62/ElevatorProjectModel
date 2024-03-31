package building;

import building.enums.ElevatorSystemStatus;
import elevator.ElevatorReport;
import scanerzus.Request;
import java.util.List;

public class BuildingReport {
  private ControlUnit controlUnit;
  public BuildingReport(ControlUnit controlUnit) {
    this.controlUnit = controlUnit;
  }

  public List<Request> getUpRequests() {
    return null;
  }

  public ElevatorReport[] getElevatorReports() {
    return null;
  }

  public int getNumFloors() {
    return 0;
  }

  public ElevatorSystemStatus getSystemStatus() {
    return null;
  }

  public List<Request> getDownRequests() {
    return null;
  }

  public int getElevatorCapacity() {
    return 0;
  }

  public int getNumElevators() {
    return 0;
  }
}

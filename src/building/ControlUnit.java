package building;

import building.enums.ElevatorSystemStatus;
import elevator.Elevator;
import elevator.ElevatorInterface;
import elevator.ElevatorReport;
import scanerzus.Request;
import java.util.ArrayList;
import java.util.List;

public class ControlUnit {
  private ElevatorSystemStatus status;
  private ElevatorInterface[] elevators;
  private List<Request> upRequests;
  private List<Request> downRequests;

  private int numFloors;
  private int numElevators;

  public ControlUnit(int numFloors, int numElevators, int elevatorCapacity) {
    this.numFloors = numFloors;
    this.numElevators = numElevators;
    this.status = ElevatorSystemStatus.running;
    this.elevators = new ElevatorInterface[numElevators];
    for (int i = 0; i < numElevators; i++) {
      elevators[i] = new Elevator(numFloors, elevatorCapacity);
    }
    this.upRequests = new ArrayList<>();
    this.downRequests = new ArrayList<>();
  }

  public boolean addRequest(Request request) throws IllegalArgumentException {
    if (isDownRequests(request)) {
      downRequests.add(request);
    } else if (isUpRequests(request)) {
      upRequests.add(request);
    } else {
      throw new IllegalArgumentException("Start floor and end floor cannot be the same");
    }
    return false;
  }

  private boolean isUpRequests(Request request) {
    return false;
  }

  private boolean isDownRequests(Request request) {
    return false;
  }

  public void takeStep(int numSteps) {
    for (int i = 0; i < numSteps; i++) {
      for (ElevatorInterface elevator : elevators) {
        elevator.step();
      }
    }
  }

  public boolean startElevatorSystem() {
    return false;
  }

  public void stopElevatorSystem() {
  }

  private void purgePendingRequests() {
  }

  private void distributeUpRequestToElevator() {
  }

  private void distributeDownRequestToElevator() {
  }

  public List<Request> getUpRequests() {
    return null;
  }

  public ElevatorReport[] getElevatorReports() {
    ElevatorReport[] reports = new ElevatorReport[numElevators];
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

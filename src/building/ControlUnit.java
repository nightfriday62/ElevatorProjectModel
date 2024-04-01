package building;

import building.enums.Direction;
import building.enums.ElevatorSystemStatus;
import elevator.Elevator;
import elevator.ElevatorInterface;
import elevator.ElevatorReport;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import scanerzus.Request;

/**
 * The ControlUnit class is the actual class that controls the elevator system.
 * It is responsible for distributing requests to the elevators
 * and managing the status of the system.
 */
public class ControlUnit {
  private ElevatorSystemStatus status;
  private ElevatorInterface[] elevators;
  private LinkedList<Request> upRequests;
  private LinkedList<Request> downRequests;

  private final int numFloors;
  private final int numElevators;
  private final int elevatorCapacity;

  /**
   * Constructor for the ControlUnit class.
   *
   * @param numFloors        the number of floors in the building
   * @param numElevators     the number of elevators in the building
   * @param elevatorCapacity the capacity of the elevators
   */

  public ControlUnit(int numFloors, int numElevators, int elevatorCapacity) {
    this.numFloors = numFloors;
    this.numElevators = numElevators;
    this.status = ElevatorSystemStatus.running;
    this.elevators = new ElevatorInterface[numElevators];
    this.elevatorCapacity = elevatorCapacity;
    for (int i = 0; i < numElevators; i++) {
      elevators[i] = new Elevator(numFloors, elevatorCapacity);
      elevators[i].start();
    }
    this.upRequests = new LinkedList<Request>();
    this.downRequests = new LinkedList<Request>();
  }

  /**
   * Adds a request to the control unit.
   * This method will add the request to the appropriate list of requests.
   * If the request is a request to go up, it will be added to the upRequests list.
   * If the request is a request to go down, it will be added to the downRequests list.
   * If the request is invalid (i.e. start floor and end floor are the same),
   * an IllegalArgumentException will be thrown.
   *
   * @param request the request to add
   * @return true if the request was added successfully, false otherwise
   * @throws IllegalArgumentException if the request has the same start and end floor.
   */
  public boolean addRequest(Request request) throws IllegalArgumentException {
    if (request == null) {
      throw new IllegalArgumentException("Request cannot be null");
    }
    if (isDownRequests(request)) {
      downRequests.add(request);
      return true;
    } else if (isUpRequests(request)) {
      upRequests.add(request);
      return true;
    } else {
      throw new IllegalArgumentException("Start floor and end floor cannot be the same");
    }
  }

  /**
   * Checks if the request is an up request.
   *
   * @param request the request to check
   * @return true if the request is an up request, false otherwise
   */
  private boolean isUpRequests(Request request) {
    return request.getEndFloor() > request.getStartFloor();
  }

  /**
   * Checks if the request is a down request.
   *
   * @param request the request to check
   * @return true if the request is a down request, false otherwise
   */
  private boolean isDownRequests(Request request) {
    return request.getEndFloor() < request.getStartFloor();
  }

  /**
   * Takes a step in the elevator system.
   * This method will take a step in the elevator system
   * by calling the step method on each elevator.
   *
   * @param numSteps the number of steps to take
   */
  public void takeStep(int numSteps) {
    for (int i = 0; i < numSteps; i++) {
      for (ElevatorInterface elevator : elevators) {
        elevator.step();
      }
    }
  }

  /**
   * Starts the elevator system.
   * If any elevator is not stopped nor door closed,
   * the system will not start and false will be returned.
   * This method will start the elevator system by calling the start method on each elevator.
   *
   * @return true if the system was started successfully, false otherwise
   */
  public boolean startElevatorSystem() {
    for (ElevatorInterface elevator : elevators) {
      if (elevator.getDirection() != Direction.STOPPED
          || !elevator.isDoorClosed() || elevator.getCurrentFloor() != 0) {
        return false;
      }
    }

    this.status = ElevatorSystemStatus.running;
    for (ElevatorInterface elevator : elevators) {
      elevator.start();
    }
    return true;

  }

  /**
   * Stops the elevator system.
   * This method will stop the elevator system by calling the stop method on each elevator.
   * This method will set the status of the system to stopping,
   * if not all elevators are at ground floor and stopped.
   * If all its elevators are stopped and doors are closed,
   * the system status will be set to outOfService.
   */
  public void stopElevatorSystem() {
    // Check if all the elevators are at ground floor and doors are open
    boolean allElevatorsStopped = true;
    for (ElevatorInterface elevator : elevators) {
      elevator.takeOutOfService();
      if (elevator.getCurrentFloor() != 0 && !elevator.isDoorClosed()
          && elevator.getDirection() != Direction.STOPPED) {
        allElevatorsStopped = false;
        break;
      }
    }
    if (allElevatorsStopped) {
      this.status = ElevatorSystemStatus.outOfService;
    } else {
      this.status = ElevatorSystemStatus.stopping;
    }
  }

  /**
   * Purges the pending requests.
   * This method will remove all the pending requests from the upRequests and downRequests lists.
   */
  private void purgePendingRequests() {
    upRequests.clear();
    downRequests.clear();
  }

  /**
   * This method will distribute the requests to the elevators.
   * It will distribute the up requests to the elevators that are at the ground floor
   * and are taking requests. The method will send requests
   * up to the maximum capacity of the elevator, or until there are no more requests.
   */
  private void distributeUpRequestToElevator() {
    if (upRequests.isEmpty()) {
      return;
    }
    for (ElevatorInterface elevator : elevators) {
      LinkedList<Request> requestsToSend = null;
      if (elevator.getCurrentFloor() == 0 && elevator.isTakingRequests()) {
        requestsToSend = new LinkedList<Request>();
        for (int i = 0; i < this.elevatorCapacity; i++) {
          requestsToSend.add(upRequests.pop());
          if (upRequests.isEmpty()) {
            break;
          }
        }
        elevator.processRequests(requestsToSend);
      }
      if (upRequests.isEmpty()) {
        break;
      }
    }
  }

  /**
   * This method will distribute the requests to the elevators.
   * It will distribute the down requests to the elevators that are at the ground floor
   * and are taking requests. The method will send requests
   * up to the maximum capacity of the elevator, or until there are no more requests.
   */
  private void distributeDownRequestToElevator() {
    if (downRequests.isEmpty()) {
      return;
    }
    for (ElevatorInterface elevator : elevators) {
      LinkedList<Request> requestsToSend = null;
      if (elevator.getCurrentFloor() == numFloors - 1 && elevator.isTakingRequests()) {
        requestsToSend = new LinkedList<Request>();
        for (int i = 0; i < this.elevatorCapacity; i++) {
          requestsToSend.add(downRequests.pop());
          if (downRequests.isEmpty()) {
            break;
          }
        }
        elevator.processRequests(requestsToSend);
      }
      if (downRequests.isEmpty()) {
        break;
      }
    }
  }

  /**
   * This method is going to collect the reports from the elevators.
   *
   * @return an array of ElevatorReport objects that contains the status of each elevator.
   */
  public ElevatorReport[] getElevatorReports() {
    ElevatorReport[] reports = new ElevatorReport[numElevators];
    for (int i = 0; i < numElevators; i++) {
      reports[i] = elevators[i].getElevatorStatus();
    }
    return reports;
  }

  /**
   * This method is going to get the number of floors in the building.
   *
   * @return the number of floors in the building.
   */
  public int getNumFloors() {
    return this.numFloors;
  }

  /**
   * This method is going to get the status of the elevator system.
   *
   * @return the status of the elevator system.
   */
  public ElevatorSystemStatus getSystemStatus() {
    return this.status;
  }

  /**
   * This is the getter function of the elevator capacity.
   *
   * @return the capacity of the elevators.
   */
  public int getElevatorCapacity() {
    return this.elevatorCapacity;
  }

  /**
   * This method is going to get the number of elevators in the building.
   *
   * @return the number of elevators in the building.
   */
  public int getNumElevators() {
    return this.numElevators;
  }
}

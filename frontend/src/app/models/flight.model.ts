import {StationModel} from "./station.model";

export class FlightModel {
  id: number;
  departureStationDTO: StationModel;
  arrivalStationDTO: StationModel;
  trainName: string;
  formatedDepartureTime: Date;
  formatedArrivalTime: Date;
  cost: number;
  name: string;
  freeSeatNumber: number;
}










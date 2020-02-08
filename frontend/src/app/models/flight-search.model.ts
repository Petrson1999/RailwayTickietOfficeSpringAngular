export class FlightSearchModel {
  departureStationId: number;
  arrivalStationId: number;
  dateTime: Date;

  constructor() {
    this.dateTime = new Date();
  }


}

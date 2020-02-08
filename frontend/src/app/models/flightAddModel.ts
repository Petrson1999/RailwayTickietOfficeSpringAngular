export class FlightAddModel {
  departureStationId:number;
  arrivalStationId: number;
  departureDate: Date;
  arrivalDate: Date;
  trainId: number;
  cost: number;

  constructor() {
    this.departureDate = new Date();
    this.arrivalDate = new Date();
  }

}

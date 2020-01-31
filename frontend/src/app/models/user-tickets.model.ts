export class UserTicketsModel {
  id: number;
  flightId: number;
  userId: number;
  cost: number;
  seatId: number;
  departureStation: string;
  arrivalStation: string;
  trainName: string;
  wagonNumber: string;
  seatNumber: number;
  departureTime: Date;
  arrivalTime: Date;
}

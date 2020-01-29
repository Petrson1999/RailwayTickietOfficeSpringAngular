import {SeatModel} from "./seat.model";
import {WagonTypeModel} from "./wagon-type.model";

export class WagonSeatsModel {
  id: number;
  trainId: number;
  name: string;
  wagonType: WagonTypeModel;
  seats: SeatModel[];
}

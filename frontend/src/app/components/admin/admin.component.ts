import {Component, OnInit, TemplateRef} from '@angular/core';
import {FlightModel} from "../../models/flight.model";
import {StationModel} from "../../models/station.model";
import {TrainModel} from "../../models/train.model";
import {WagonTypeModel} from "../../models/wagon-type.model";
import {WagonSeatsModel} from "../../models/wagon-seats.model";
import {HttpErrorResponse} from "@angular/common/http";
import {FlightService} from "../../services/flight.service";
import {ToastrService} from "ngx-toastr";
import {FlightAddModel} from "../../models/flightAddModel";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {StationService} from "../../services/station.service";
import {TrainService} from "../../services/train.service";
import {WagonService} from "../../services/wagon.service";
import {AddWagonTypeModel} from "../../models/add-wagon-type.model";
import {AddWagonModel} from "../../models/add-wagon.model";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  constructor(private trainService: TrainService, private stationService: StationService, private modalService: BsModalService,
              private flightService: FlightService, private toastr: ToastrService, private wagonService: WagonService) {
    this.flights = [];
    this.stations = [];
    this.wagonTypes = [];
    this.wagons = [];
    this.flightAddModel = new FlightAddModel();
    this.addStationName = "";
    this.addTrainName = "";
    this.addWagonTypeModel = new AddWagonTypeModel();
    this.selectedWagonType = new WagonTypeModel();
    this.addWagonModel = new AddWagonModel();
  }

  ngOnInit() {
    this.initFlights();
    this.initStations();
    this.initTrains();
    this.initWagonTypes();

  }

  modalRef: BsModalRef;
  flightsIsShow: boolean = true;
  stationsIsShow: boolean;
  trainsIsShow: boolean;
  wagonsIsShow: boolean;
  selectedArrivalStation: StationModel;
  selectedDepartureStation: StationModel;
  selectedTrain: TrainModel;
  selectedWagonType: WagonTypeModel;
  flightAddModel: FlightAddModel;
  addStationName: String;
  addTrainName: String;
  addWagonTypeModel: AddWagonTypeModel;
  addWagonModel: AddWagonModel;

  flights: FlightModel[];
  stations: StationModel[];
  trains: TrainModel[];
  wagonTypes: WagonTypeModel[];
  wagons: WagonSeatsModel[];

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  showFlights() {
    this.flightsIsShow = true;
    this.stationsIsShow = false;
    this.trainsIsShow = false;
    this.wagonsIsShow = false;
  }

  showStations() {
    this.flightsIsShow = false;
    this.stationsIsShow = true;
    this.trainsIsShow = false;
    this.wagonsIsShow = false;
  }

  showTrains() {
    this.flightsIsShow = false;
    this.stationsIsShow = false;
    this.trainsIsShow = true;
    this.wagonsIsShow = false;
  }

  showWagons() {
    this.flightsIsShow = false;
    this.stationsIsShow = false;
    this.trainsIsShow = false;
    this.wagonsIsShow = true;
  }

  initFlights() {
    this.flightService.getAllFlighs().subscribe(
      (data: any) => {
        if (data.succeeded) {
          this.flights = data.flightDTOS as FlightModel[];
        }
      },
      (error: HttpErrorResponse) => {
        this.toastr.error(error.error.message);
      }
    )
  }

  initStations() {
    this.stationService.getAllStantion().subscribe(
      (data: any) => {
        if (data.succeeded) {
          this.stations = data.stations as StationModel[];
        }
      },
      (error: HttpErrorResponse) => {
        this.toastr.error(error.error.message);
      });
  }

  initTrains() {
    this.trainService.getAllTrains().subscribe(
      (data: any) => {
        if (data.succeeded) {
          this.trains = data.trains as TrainModel[];
        }
      },
      (error: HttpErrorResponse) => {
        this.toastr.error(error.error.message);
      });
  }

  initWagonTypes(){
    this.wagonService.getAllWagonTypes().subscribe(
      (data: any) => {
        if (data.succeeded) {
          this.wagonTypes = data.wagonTypes as WagonTypeModel[];
          this.initWagons();
        }
      },
      (error: HttpErrorResponse) => {
        this.toastr.error(error.error.message);
      });
  }

  initWagons(){
    this.wagonService.getAllWagons().subscribe(
      (data: any) => {
        if (data.succeeded) {
          this.wagons = data.wagons as WagonSeatsModel[];
          if(this.wagonTypes.length >0){
            this.selectedWagonType = this.wagonTypes[0];
          }
        }
      },
      (error: HttpErrorResponse) => {
        this.toastr.error(error.error.message);
      });
  }

  addFlight() {
    if (!this.flightAddValidation()) {
      return;
    }
    this.flightAddModel.arrivalStationId = this.selectedArrivalStation.id;
    this.flightAddModel.departureStationId = this.selectedDepartureStation.id;
    this.flightAddModel.trainId = this.selectedTrain.id;
    this.flightService.addFlight(this.flightAddModel).subscribe(
      (data: any) => {
        if (data.succeeded) {
          this.toastr.success(data.message);
          this.initFlights();
        }
      },
      (error: HttpErrorResponse) => {
        this.toastr.error(error.error.message);
      }
    )
  }

  addStation() {
    if (this.addStationName.length === 0) {
      this.toastr.warning("name cannot be empty");
      return;
    }
    this.stationService.addStation(this.addStationName).subscribe(
      (data: any) => {
        if (data.succeeded) {
          this.toastr.success(data.message);
          this.initStations();
          this.modalRef.hide();
          this.addStationName = '';
        }
      },
      (error: HttpErrorResponse) => {
        this.toastr.error(error.error.message);
      }
    )
  }

  addTrain() {
    if (this.addTrainName.length === 0) {
      this.toastr.warning("name cannot be empty");
      return;
    }
    this.trainService.addTrain(this.addTrainName).subscribe(
      (data: any) => {
        if (data.succeeded) {
          this.toastr.success(data.message);
          this.initTrains();
          this.modalRef.hide();
          this.addTrainName = '';
        }
      },
      (error: HttpErrorResponse) => {
        this.toastr.error(error.error.message);
      }
    )
  }

  addWagonType() {
    if(this.wagonTypeAddValidation())
    this.wagonService.addWagonType(this.addWagonTypeModel).subscribe(
      (data: any) => {
        if (data.succeeded) {
          this.toastr.success(data.message);
          this.initWagonTypes();
          this.modalRef.hide();
          this.addWagonTypeModel = new AddWagonTypeModel();
        }
      },
      (error: HttpErrorResponse) => {
        this.toastr.error(error.error.message);
      }
    )
  }

  addWagon() {
    this.addWagonModel.typeId = this.selectedWagonType.id;
    this.addWagonModel.trainId = this.selectedTrain.id;
    if(this.wagonAddValidation())
      this.wagonService.addWagon(this.addWagonModel).subscribe(
        (data: any) => {
          if (data.succeeded) {
            this.toastr.success(data.message);
            this.initWagons();
            this.modalRef.hide();
            this.addWagonModel = new AddWagonModel();
          }
        },
        (error: HttpErrorResponse) => {
          this.toastr.error(error.error.message);
        }
      )
  }

  wagonTypeAddValidation(): boolean{
    let valid : boolean = true;
    if (this.addWagonTypeModel.name.length === 0) {
      this.toastr.warning("name cannot be empty");
      valid = false;
    }
    if (this.addWagonTypeModel.seatsCount === 0) {
      this.toastr.warning("select seat count");
      valid = false;
    }
    return valid;
  }

  wagonAddValidation(): boolean{
    let valid : boolean = true;
    if (this.addWagonModel.name.length === 0) {
      this.toastr.warning("name cannot be empty");
      valid = false;
    }
    if (this.addWagonModel.typeId === null) {
      this.toastr.warning("select wagon type");
      valid = false;
    }
    if (this.addWagonModel.trainId === null) {
      this.toastr.warning("select train");
      valid = false;
    }
    return valid;
  }

  flightAddValidation(): boolean {
    let valid: boolean = true;
    if (this.selectedArrivalStation === undefined || String(this.selectedArrivalStation).length === 0) {
      this.toastr.warning("select arrival station");
      valid = false;
    }
    if (this.selectedDepartureStation === undefined || String(this.selectedDepartureStation).length === 0) {
      this.toastr.warning("select departure station");
      valid = false;
    }
    if (this.selectedArrivalStation === this.selectedDepartureStation) {
      this.toastr.warning("select different stations");
      valid = false;
    }
    if (this.flightAddModel.departureDate < new Date()) {
      this.toastr.warning("date has passed");
      valid = false;
    }
    if (this.flightAddModel.departureDate > this.flightAddModel.arrivalDate) {
      this.toastr.warning("departure date later than arrival date");
      valid = false;
    }
    if (this.selectedTrain === undefined || String(this.selectedTrain).length === 0) {
      this.toastr.warning("select train");
      valid = false;
    }
    if (this.flightAddModel.cost === undefined || this.flightAddModel.cost === 0) {
      this.toastr.warning("select cost");
      valid = false;
    }
    return valid;
  }

  config = {
    displayKey: "name", //if objects array passed which key to be displayed defaults to description
    search: true, //true/false for the search functionlity defaults to false,
    height: 'auto', //height of the list so that if there are more no of items it can show a scroll defaults to auto. With auto height scroll will never appear
    placeholder: 'Select', // text to be displayed when no item is selected defaults to Select,
    customComparator: () => {
    }, // a custom function using which user wants to sort the items. default is undefined and Array.sort() will be used in that case,
    limitTo: 100, // a number thats limits the no of options displayed in the UI similar to angular's limitTo pipe
    moreText: 'more', // text to be displayed whenmore than one items are selected like Option 1 + 5 more
    noResultsFound: 'No results found!', // text to be displayed when no items are found while searching
    searchPlaceholder: 'Search', // label thats displayed in search input,
    searchOnKey: 'name', // key on which search should be performed this will be selective search. if undefined this will be extensive search on all keys
  };

}

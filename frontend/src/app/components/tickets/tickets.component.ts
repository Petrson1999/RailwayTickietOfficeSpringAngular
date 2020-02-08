import {Component, OnInit, TemplateRef} from '@angular/core';
import {StationService} from "../../services/station.service";
import {StationModel} from "../../models/station.model";
import {HttpErrorResponse} from "@angular/common/http";
import {ToastrService} from "ngx-toastr";
import {FlightModel} from "../../models/flight.model";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {SecurityService} from "../../services/security.service";
import {FlightSearchModel} from "../../models/flight-search.model";
import {FlightService} from "../../services/flight.service";
import {WagonSeatsModel} from "../../models/wagon-seats.model";
import {SeatModel} from "../../models/seat.model";
import {OrderFormModel} from "../../models/order-form.model";
import {TicketService} from "../../services/ticket.service";

@Component({
  selector: 'app-tickets',
  templateUrl: './tickets.component.html',
  styleUrls: ['./tickets.component.css']
})
export class TicketsComponent implements OnInit {

  constructor(private modalService: BsModalService, private stationService: StationService, private toastr: ToastrService,
              private ticketService: TicketService, private flightService: FlightService
  ) {
    this.stations = [];
    this.flights = null;
    this.securityService = new SecurityService();
    this.flightSearch = new FlightSearchModel();
    this.wagonSeats = [];
    this.orderForm = new OrderFormModel();
  }

  securityService: SecurityService;
  modalRef: BsModalRef;

  stations: StationModel[];
  flights: FlightModel[];
  wagonSeats: WagonSeatsModel[];

  selectedArrivalStation: StationModel;
  selectedDepartureStation: StationModel;
  selectedFlights: FlightModel;
  selectedWagon: WagonSeatsModel;
  selectedSeat: SeatModel;
  orderForm: OrderFormModel;

  flightSearch: FlightSearchModel;


  ngOnInit() {
    this.initStations();
  }


  openModal(template: TemplateRef<any>
  ) {
    this.modalRef = this.modalService.show(template);
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

  changeStation() {
    let buf = this.selectedArrivalStation;
    this.selectedArrivalStation = this.selectedDepartureStation;
    this.selectedDepartureStation = buf;
  }

  flightSerch() {
    if (this.flightSearchValidation()) {
      this.flightSearch.arrivalStationId = this.stations.find(x => x === this.selectedArrivalStation).id;
      this.flightSearch.departureStationId = this.stations.find(x => x === this.selectedDepartureStation).id;
      this.flightSearch.dateTime.setHours(this.flightSearch.dateTime.getHours() + 2);
      this.flightService.searchFlights(this.flightSearch).subscribe(
        (data: any) => {
          if (data.succeeded) {
            this.flights = data.flightDtos as FlightModel[];
          }
        },
        (error: HttpErrorResponse) => {
          this.toastr.error(error.error.message);
        });
    }
  }

  getFlightWagonDto(flightId: number, template: TemplateRef<any>) {
    this.selectedFlights = this.flights.find(x => x.id === flightId);
    this.flightService.getFlightWagonDto(flightId).subscribe(
      (data: any) => {
        if (data.succeeded) {
          this.wagonSeats = data.wagonDTOS as WagonSeatsModel[];
          for (let wagonSeatsModel of this.wagonSeats) {
            wagonSeatsModel.name = wagonSeatsModel.name + ' ' + wagonSeatsModel.wagonType.name + ' ' + wagonSeatsModel.seats.length + ' free seat';
          }
          if (this.wagonSeats.length !== 0) {
            this.selectedWagon = this.wagonSeats[0];
            if (this.wagonSeats[0].seats.length!== 0) {
              this.selectedSeat = this.wagonSeats[0].seats[0];
            }
          }
          this.openModal(template);
        }
      },
      (error: HttpErrorResponse) => {
        this.toastr.error(error.error.message);
      });
  }

  order(template: TemplateRef<any>) {
    if (this.selectedSeat === null || this.selectedSeat === undefined) {
      this.toastr.warning("select seat");
    } else {
      this.modalRef.hide();
      this.openModal(template);
    }
  }

  flightSearchValidation(): boolean {
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
    if (this.flightSearch.dateTime < new Date()) {
      this.toastr.warning("date has passed");
      valid = false;
    }
    return valid;
  }

  submitOrder() {
    this.orderForm.flightId = this.selectedFlights.id;
    this.orderForm.seatId = this.selectedSeat.id;
    this.orderForm.wagonId = this.selectedSeat.wagonId;
    this.orderForm.userId = +localStorage.getItem("constant_userId");
    this.ticketService.orderTicket(this.orderForm).subscribe(
      (data: any) => {
        if (data.succeeded) {
          this.toastr.success(data.message);
          this.modalRef.hide();
        }else {
          this.toastr.error(data.message);
        }
      },
      (error: HttpErrorResponse) => {
        this.toastr.error(error.error.message);
      });
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

  configWagonSelector = {
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

  configSeatSelector = {
    displayKey: "placeNumber", //if objects array passed which key to be displayed defaults to description
    search: true, //true/false for the search functionlity defaults to false,
    height: 'auto', //height of the list so that if there are more no of items it can show a scroll defaults to auto. With auto height scroll will never appear
    placeholder: 'Select', // text to be displayed when no item is selected defaults to Select,
    customComparator: () => {
    }, // a custom function using which user wants to sort the items. default is undefined and Array.sort() will be used in that case,
    limitTo: 100, // a number thats limits the no of options displayed in the UI similar to angular's limitTo pipe
    moreText: 'more', // text to be displayed whenmore than one items are selected like Option 1 + 5 more
    noResultsFound: 'No results found!', // text to be displayed when no items are found while searching
    searchPlaceholder: 'Search', // label thats displayed in search input,
    searchOnKey: 'placeNumber', // key on which search should be performed this will be selective search. if undefined this will be extensive search on all keys
  };


}

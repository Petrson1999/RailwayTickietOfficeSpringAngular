import { Component, OnInit } from '@angular/core';
import {FlightModel} from "../../models/flight.model";
import {StationModel} from "../../models/station.model";
import {TrainModel} from "../../models/train.model";
import {WagonTypeModel} from "../../models/wagon-type.model";
import {WagonSeatsModel} from "../../models/wagon-seats.model";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  constructor() {
    this.flights = [];
    this.stations = [];
    this.wagonTypes = [];
    this.wagons = [];
  }

  ngOnInit() {
  }

  flightsIsShow : boolean = true;
  stationsIsShow : boolean;
  trainsIsShow : boolean;
  wagonsIsShow : boolean;

  showFlights(){
    this.flightsIsShow = true;
    this.stationsIsShow = false;
    this.trainsIsShow = false;
    this.wagonsIsShow = false;
  }

  showStations(){
    this.flightsIsShow = false;
    this.stationsIsShow = true;
    this.trainsIsShow = false;
    this.wagonsIsShow = false;
  }

  showTrains(){
    this.flightsIsShow = false;
    this.stationsIsShow = false;
    this.trainsIsShow = true;
    this.wagonsIsShow = false;
  }

  showWagons(){
    this.flightsIsShow = false;
    this.stationsIsShow = false;
    this.trainsIsShow = false;
    this.wagonsIsShow = true;
  }

  flights: FlightModel[];
  stations: StationModel[];
  trains: TrainModel[];
  wagonTypes: WagonTypeModel[];
  wagons: WagonSeatsModel[];

}

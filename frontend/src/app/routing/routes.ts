import {Routes} from '@angular/router';
import {LandingComponent} from "../components/landing/landing.component";
import {TicketsComponent} from "../components/tickets/tickets.component";


export const appRoutes: Routes=[
    {path:'', component:LandingComponent},
    {path:'tickets', component:TicketsComponent}
];

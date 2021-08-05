import {Component} from "@angular/core";
import {HttpService} from "../http.service";

@Component({
  selector: "app-slaves-listing",
  template: `
  <div>Slaves listing</div>
  `
})
export class SlavesListingComponent{
  constructor(private http: HttpService) {
    http.getSlaves().subscribe((data) => {
      console.log(data)
    })


  }

}

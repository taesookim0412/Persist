import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class HttpService {
  root_url = "http://localhost:8080/"
  data = {};

  constructor(public httpClient: HttpClient) {
  }

  getSlaves() {
    return this.httpClient.get(this.root_url + "api/slaves")
  }

  getJobs() {
    return this.httpClient.get(this.root_url + "api/jobs")
  }

  customGet(url: string) {
    return this.httpClient.get(url);
  }

  // getAndStoreData(){
  //   this.httpClient.get(this.root_url + "api/slaves").subscribe(data => {
  //     this.data = data;
  //   })
}

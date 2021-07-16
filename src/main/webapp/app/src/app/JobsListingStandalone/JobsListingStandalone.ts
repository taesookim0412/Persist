import {Component, Input, OnInit} from "@angular/core";
import {Job} from "./Job";

export interface Jobs {
  [key: string]: {
    file: string,
    status: string,
    date: Date
  }
}

@Component({
  selector: 'app-jobs-standalone',
  styleUrls: ['JobsListing.scss'],
  template: `
    <div class="wrapper">
      Jobs
      <div *ngFor="let item of jobs | keyvalue">
        {{item.key}} {{item.value.status}} {{item.value.date.toLocaleTimeString()}} {{item.value.file}}
        <button (click)="cancel(item.key)">Cancel</button>
      </div>
    </div>`
})
export class JobsListingStandalone implements OnInit {
  jobs: Jobs = {};
  constructor() {
    this.pushSamples()
  }
  ngOnInit() {
  }


  cancel(index: string) {

  }

  pushSamples() {
    const sampleJob0 = {
      '0': {
        file: "url",
        status: "idle",
        date: new Date(Date.now())
      }
    }
    const sampleJob1 = {'1': sampleJob0[0]}
    const sampleJob2 = {'2': sampleJob0[0]}
    const sampleJob3 = {'3': sampleJob0[0]}
    const sampleJob4 = {'4': sampleJob0[0]}
    this.jobs = {
      ...sampleJob0,
      ...sampleJob1,
      ...sampleJob2,
      ...sampleJob3,
      ...sampleJob4
    }
  }


}


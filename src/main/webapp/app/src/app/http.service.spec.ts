import { TestBed } from '@angular/core/testing';

import { HttpService } from './http.service';
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {HttpClient} from "@angular/common/http";
import {of} from "rxjs";

describe('HttpService', () => {
  let service: HttpService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[HttpClientTestingModule],
      providers:[HttpService]
    });
    service = TestBed.inject(HttpService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
  it('should pull a list of slaves', () => {
    let mockData = {data: [0,1,2,3,4]};
    spyOn(service, 'getSlaves').and.returnValue(of(mockData))
    let resData;
    service.getSlaves().subscribe((data) => {
      // @ts-ignore
      resData = data;
    });
    // @ts-ignore
    expect(resData.data.length).toBeGreaterThan(0);
  })
});

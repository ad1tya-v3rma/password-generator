import { TestBed } from '@angular/core/testing';

import { AppUtilServiceService } from './app-util-service.service';

describe('AppUtilServiceService', () => {
  let service: AppUtilServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AppUtilServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

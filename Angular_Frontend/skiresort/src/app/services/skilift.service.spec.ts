import { TestBed } from '@angular/core/testing';

import { SkiliftService } from './skilift.service';

describe('SkiliftService', () => {
  let service: SkiliftService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SkiliftService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

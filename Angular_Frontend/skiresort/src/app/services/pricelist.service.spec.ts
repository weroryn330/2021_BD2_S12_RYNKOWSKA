import { TestBed } from '@angular/core/testing';

import { PricelistService } from './pricelist.service';

describe('PricelistService', () => {
  let service: PricelistService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PricelistService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

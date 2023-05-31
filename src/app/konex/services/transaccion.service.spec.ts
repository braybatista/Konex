import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { TransaccionService } from './transaccion.service';

// describe('TransaccionService', () => {
//   beforeEach(() => {
//     TestBed.configureTestingModule({
//       providers: [TransaccionService, HttpClient, HttpHandler]
//     });
//   });

//   it('service should be created', inject([TransaccionService, HttpClient], (service: TransaccionService) => {
//     expect(service).toBeTruthy();
//   }));
// });

describe('TransaccionService', () => {

  beforeEach(() => TestBed.configureTestingModule({
    imports: [HttpClientTestingModule], 
    providers: [TransaccionService]
  }));

   it('should be created', () => {
    const service: TransaccionService = TestBed.get(TransaccionService);
    expect(service).toBeTruthy();
   });

   it('should have anularTransaccion function', () => {
    const service: TransaccionService = TestBed.get(TransaccionService);
    expect(service.anularTransaccion).toBeTruthy();
   });
});

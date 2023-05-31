import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { TarjetaService } from './tarjeta.service';

// describe('TarjetaService', () => {
//   beforeEach(() => {
//     TestBed.configureTestingModule({
//       providers: [TarjetaService, HttpClient, HttpHandler]
//     });
//   });

//   it('should be created', inject([TarjetaService, HttpClient], (service: TarjetaService) => {
//     expect(service).toBeTruthy();
//   }));
// });

describe('TarjetaService', () => {

  beforeEach(() => TestBed.configureTestingModule({
    imports: [HttpClientTestingModule], 
    providers: [TarjetaService]
  }));

   it('should be created', () => {
    const service: TarjetaService = TestBed.get(TarjetaService);
    expect(service).toBeTruthy();
   });

   it('should have consultarTarjeta function', () => {
    const service: TarjetaService = TestBed.get(TarjetaService);
    expect(service.consultarTarjeta).toBeTruthy();
   });
});
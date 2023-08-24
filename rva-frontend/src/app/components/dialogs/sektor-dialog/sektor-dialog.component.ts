import { Component, Inject, OnDestroy, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subscription } from 'rxjs';
import { preduzece } from 'src/app/models/preduzece';
import { sektor } from 'src/app/models/sektor';
import { PreduzeceService } from 'src/app/services/preduzece.service';
import { SektorService } from 'src/app/services/sektor.service';

@Component({
  selector: 'app-sektor-dialog',
  templateUrl: './sektor-dialog.component.html',
  styleUrls: ['./sektor-dialog.component.css']
})
export class SektorDialogComponent implements OnInit, OnDestroy {

  preduzeca!: preduzece[];
  public flag!: number;
  subscription!: Subscription;

  constructor(public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<SektorDialogComponent>,
    @Inject (MAT_DIALOG_DATA) public data: sektor,
    public sektorService: SektorService,
    public preduzeceService: PreduzeceService) { }


  ngOnDestroy(): void {

    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.subscription = this.preduzeceService.getAllPreduzece().subscribe(
      preduzeca => {
        this.preduzeca = preduzeca
      }
    )
  }


  public compare(a:any, b:any){
    return a.id == b.id;
  }

  public addSektor(): void{
    this.sektorService.addSektor(this.data).subscribe(()=> {
      this.snackBar.open('Uspešno dodat sektor: ' + this.data.naziv, 'OK', {duration: 2500})
    }),
    (error: Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open('Došlo je do greške prilikom dodavanja novog sektora.', 'Zatvori', {duration: 2500})
    }
    this.dialogRef.close();

  }

  public updateSektor(): void{
    this.sektorService.updateSektor(this.data).subscribe(()=> {
      this.snackBar.open('Uspešno modifikovan sektor: ' + this.data.naziv, 'OK', {duration: 2500})
    }),
    (error: Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open('Došlo je do greške prilikom modifikacije postojećeg sektora.', 'Zatvori', {duration: 2500})
    }
    this.dialogRef.close();
  }

  public deleteSektor(): void{
    this.sektorService.deleteSektor(this.data.idSektor).subscribe(()=> {
      this.snackBar.open('Uspešno obrisan sektor: ' + this.data.naziv, 'OK', {duration: 2500})
    }),
    (error: Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open('Došlo je do greške prilikom brisanja sektora.', 'Zatvori', {duration: 2500})
    }
    this.dialogRef.close();
  }

  public cancel(): void{
    this.dialogRef.close();
    this.snackBar.open('Odustali ste.', 'Zatvori', {duration: 1000});
  }

}

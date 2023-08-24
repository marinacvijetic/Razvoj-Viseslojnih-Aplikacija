import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subscription } from 'rxjs/internal/Subscription';
import { obrazovanje } from 'src/app/models/obrazovanje';
import { radnik } from 'src/app/models/radnik';
import { sektor } from 'src/app/models/sektor';
import { ObrazovanjeService } from 'src/app/services/obrazovanje.service';
import { RadnikService } from 'src/app/services/radnik.service';
import { SektorService } from 'src/app/services/sektor.service';

@Component({
  selector: 'app-radnik-dialog',
  templateUrl: './radnik-dialog.component.html',
  styleUrls: ['./radnik-dialog.component.css']
})
export class RadnikDialogComponent implements OnInit {

  obrazovanja!: obrazovanje[];
  public flag!: number;
  subscription!: Subscription;

  constructor(public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<RadnikDialogComponent>,
    @Inject (MAT_DIALOG_DATA) public data: radnik,
    public radnikService: RadnikService,
    public sektorService: SektorService,
    public obrazovanjeService: ObrazovanjeService) { }


  ngOnDestroy(): void {

    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.subscription = this.obrazovanjeService.getAllObrazovanje().subscribe(
      obrazovanja => {
        this.obrazovanja = obrazovanja
      }
    )
  }


  public compare(a:any, b:any){
    return a.id == b.id
  }

  public addRadnik(): void{
    this.radnikService.addRadnik(this.data).subscribe(()=> {
      this.snackBar.open('Uspešno dodat radnik: ' + this.data.ime + ' ' + this.data.prezime, 'OK', {duration: 2500})
    }),
    (error: Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open('Došlo je do greške prilikom dodavanja novog radnika.', 'Zatvori', {duration: 2500})
    }
    this.dialogRef.close();

  }

  public updateRadnik(): void{
    this.radnikService.updateRadnik(this.data).subscribe(()=> {
      this.snackBar.open('Uspešno modifikovan radnik: ' + this.data.ime + ' ' + this.data.prezime, 'OK', {duration: 2500})
    }),
    (error: Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open('Došlo je do greške prilikom modifikacije postojećeg radnika.', 'Zatvori', {duration: 2500})
    }
    this.dialogRef.close();
  }

  public deleteRadnik(): void{
    this.radnikService.deleteRadnik(this.data.idRadnik).subscribe(()=> {
      this.snackBar.open('Uspešno obrisan radnik: ' + this.data.ime + ' ' + this.data.prezime, 'OK', {duration: 2500})
    }),
    (error: Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open('Došlo je do greške prilikom brisanja radnika.', 'Zatvori', {duration: 2500})
    }
    this.dialogRef.close();
  }

  public cancel(): void{
    this.dialogRef.close();
    this.snackBar.open('Odustali ste.', 'Zatvori', {duration: 1000});
  }
}

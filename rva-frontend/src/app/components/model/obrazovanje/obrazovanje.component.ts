import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { obrazovanje } from 'src/app/models/obrazovanje';
import { ObrazovanjeService } from 'src/app/services/obrazovanje.service';
import { ObrazovanjeDialogComponent } from '../../dialogs/obrazovanje-dialog/obrazovanje-dialog.component';

@Component({
  selector: 'app-obrazovanje-component',
  templateUrl: './obrazovanje.component.html',
  styleUrls: ['./obrazovanje.component.css']
})
export class ObrazovanjeComponent implements OnInit, OnDestroy {

  dataSource!: MatTableDataSource<obrazovanje>;
  displayedColumns = [`idObrazovanje`, `naziv`, `stepenStrucneSpreme`, `opis`, `actions`];
  subscription!: Subscription;
  @ViewChild(MatSort, {static:false}) sort!: MatSort;
  @ViewChild(MatPaginator, {static:false}) paginator!: MatPaginator;

  constructor(private obrazovanjeService: ObrazovanjeService,
              private dialog: MatDialog) { }
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.loadData();
  }

  public loadData(){
    this.subscription = this.obrazovanjeService.getAllObrazovanje()
    .subscribe(data => {this.dataSource = new MatTableDataSource(data)
                        this.dataSource.sort = this.sort
                        this.dataSource.paginator = this.paginator;
    }),
    (error: Error) => {console.log(error.name + " " + error.message)}
  }

  public openDialog(flag: number, idObrazovanje?: number, naziv?: string, stepenStrucneSpreme?: string, opis?: string): void {
    const dialogRef = this.dialog.open(ObrazovanjeDialogComponent, {data: {idObrazovanje, naziv, stepenStrucneSpreme, opis}});

    dialogRef.componentInstance.flag=flag;
    dialogRef.afterClosed().subscribe(res => {

        this.loadData();
      
    })
  }

  applyFilter(filterValue: any){
    filterValue = filterValue.trim();
    filterValue = filterValue.target.value;
    filterValue = filterValue.toLocaleLowerCase();
    this.dataSource.filter = filterValue;

  }

}

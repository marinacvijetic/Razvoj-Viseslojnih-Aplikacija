import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { preduzece } from 'src/app/models/preduzece';
import { PreduzeceService } from 'src/app/services/preduzece.service';
import { PreduzeceDialogComponent } from '../../dialogs/preduzece-dialog/preduzece-dialog.component';

@Component({
  selector: 'app-preduzece-component',
  templateUrl: './preduzece.component.html',
  styleUrls: ['./preduzece.component.css']
})
export class PreduzeceComponent implements OnInit, OnDestroy {

  dataSource!: MatTableDataSource<preduzece>;
  displayedColumns = [`idPreduzece`, `naziv`, `pib`, `sediste`, `opis`, `actions` ];
  subscription!: Subscription;
  @ViewChild(MatSort, {static:false}) sort!: MatSort;
  @ViewChild(MatPaginator, {static:false}) paginator!: MatPaginator;

  constructor(private preduzeceService: PreduzeceService,
              private dialog: MatDialog) { }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.loadData();
  }

  public loadData(){
    this.subscription = this.preduzeceService.getAllPreduzece()
    .subscribe(data => {this.dataSource = new MatTableDataSource(data)
                        this.dataSource.sort = this.sort
                        this.dataSource.paginator = this.paginator;
                      }),
    (error: Error) => (console.log(error.name + " " + error.message))
  }

  public openDialog(flag: number, idPreduzece?: number, naziv?: string, pib?: string, sediste?: string, opis?: string): void {
    const dialogRef = this.dialog.open(PreduzeceDialogComponent, {data: {idPreduzece, naziv, pib, sediste, opis}});

    dialogRef.componentInstance.flag=flag;
    dialogRef.afterClosed().subscribe(res => {
      
        this.loadData();
      
    });
  }

  public applyFilter(filterValue: any){
    filterValue = filterValue.target.value;
    filterValue = filterValue.trim();
    filterValue = filterValue.toLocaleLowerCase();
    this.dataSource.filter = filterValue;

  }

}

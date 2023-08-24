import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { preduzece } from 'src/app/models/preduzece';
import { sektor } from 'src/app/models/sektor';
import { SektorService } from 'src/app/services/sektor.service';
import { SektorDialogComponent } from '../../dialogs/sektor-dialog/sektor-dialog.component';

@Component({
  selector: 'app-sektor-component',
  templateUrl: './sektor.component.html',
  styleUrls: ['./sektor.component.css']
})
export class SektorComponent implements OnInit, OnDestroy {

  displayedColumns = ['idSektor', 'naziv', 'oznaka', 'preduzece', 'actions'];
  dataSource!: MatTableDataSource<sektor>;
  subscription!: Subscription;
  selectedSektorParent!: sektor;
  @ViewChild(MatSort, {static:false}) sort!: MatSort;
  @ViewChild(MatPaginator, {static:false}) paginator!: MatPaginator;

  constructor(private sektorService: SektorService,
    private dialog: MatDialog) { }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.loadData();
  }

  public loadData() {
    this.subscription = this.sektorService.getAllSektor().subscribe
      (data => { this.dataSource = new MatTableDataSource(data);

                //pretraga po nazivu ugnjezdenog objekta
        this.dataSource.filterPredicate = (data: any, filter: string) => {
          const accumulator = (currentTerm: any, key: any) => {
            return (key === 'preduzece' ? currentTerm + data.preduzece.naziv : currentTerm + data[key]);
          }
          const dataStr = Object.keys(data).reduce(accumulator, '').toLocaleLowerCase();
          const transformedFilter = filter.trim().toLocaleLowerCase();
          return dataStr.indexOf(transformedFilter) !== -1;
        }

                 this.dataSource.sort = this.sort
                 this.dataSource.paginator = this.paginator;
      }),
      (error: Error) => { console.log(error.name + " " + error.message) }
  }


  public openDialog(flag: number, idSektor?: number, naziv?: string, oznaka?: string, preduzece?: preduzece): void {
    const dialogRef = this.dialog.open(SektorDialogComponent, {data: {idSektor, naziv, oznaka, preduzece}});

    dialogRef.componentInstance.flag=flag;
    dialogRef.afterClosed().subscribe(res => {

        this.loadData();
      
    });
  }

  public selectRow(row: sektor){
    this.selectedSektorParent=row;
    console.log(this.selectedSektorParent);
  }

  public applyFilter(filterValue: any){
    filterValue = filterValue.target.value;
    filterValue = filterValue.trim();
    filterValue = filterValue.toLocaleLowerCase();
    this.dataSource.filter = filterValue;

  }
}




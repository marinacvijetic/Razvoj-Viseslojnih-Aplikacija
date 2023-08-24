import { Component, Input, OnChanges, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { obrazovanje } from 'src/app/models/obrazovanje';
import { radnik } from 'src/app/models/radnik';
import { sektor } from 'src/app/models/sektor';
import { RadnikService } from 'src/app/services/radnik.service';
import { RadnikDialogComponent } from '../../dialogs/radnik-dialog/radnik-dialog.component';

@Component({
  selector: 'app-radnik-component',
  templateUrl: './radnik.component.html',
  styleUrls: ['./radnik.component.css']
})
export class RadnikComponent implements OnInit, OnChanges {

  displayedColumns = ['idRadnik', 'brojLk', 'ime', 'prezime', 'obrazovanje', 'sektor', 'actions'];
  dataSource!: MatTableDataSource<radnik>;
  @Input() selectedSektorChild!: sektor; //input dekorator kako bi child komponenta mogla da preuzme neki propertie iz parent komponente
  @ViewChild(MatSort, {static:false}) sort!: MatSort;
  @ViewChild(MatPaginator, {static:false}) paginator!: MatPaginator;
  
  constructor(private radnikService: RadnikService,
              private dialog: MatDialog) { }


  ngOnChanges(): void {
    if(this.selectedSektorChild.idSektor)
    {
      this.loadData();
    }
  }

  ngOnInit(): void {
    this.loadData()
  }

  public loadData() {
    this.radnikService.getRadnikBySektor(this.selectedSektorChild.idSektor).subscribe
      (data => { this.dataSource = new MatTableDataSource(data)

        //pretraga po nazivu ugnjezdenog objekta
        this.dataSource.filterPredicate = (data: any, filter: string) => {
          const accumulator = (currentTerm: any, key: any) => {
            return (key === 'obrazovanje' ? currentTerm + data.obrazovanje.naziv : currentTerm + data[key]);
          }
          const dataStr = Object.keys(data).reduce(accumulator, '').toLocaleLowerCase();
          const transformedFilter = filter.trim().toLocaleLowerCase();
          return dataStr.indexOf(transformedFilter) !== -1;
        }
                 this.dataSource.sort = this.sort
                 this.dataSource.paginator = this.paginator }),
      (error: Error) => { console.log(error.name + " " + error.message) }
  }

  public openDialog(flag: number, idRadnik?: number, brojLk?: number, ime?: string, prezime?: string, obrazovanje?: obrazovanje, sektor?: sektor): void {
    const dialogRef = this.dialog.open(RadnikDialogComponent, {data: {idRadnik, brojLk, ime, prezime, obrazovanje, sektor}});

    dialogRef.componentInstance.flag=flag;
    if(flag===1)
    {
      dialogRef.componentInstance.data.sektor=this.selectedSektorChild;
    }

    dialogRef.afterClosed().subscribe(res => {
      this.loadData()
    })
  }

  public applyFilter(filterValue: any){
    filterValue = filterValue.target.value;
    filterValue = filterValue.trim();
    filterValue = filterValue.toLocaleLowerCase();
    this.dataSource.filter = filterValue;

  }

}

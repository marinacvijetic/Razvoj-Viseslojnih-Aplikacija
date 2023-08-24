import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatButtonModule } from '@angular/material/button'
import {MatIconModule} from '@angular/material/icon';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatExpansionModule} from '@angular/material/expansion';
import { MatTableModule } from '@angular/material/table';
import { HttpClientModule } from '@angular/common/http';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthorComponent } from './components/core/author/author.component';
import { HomeComponent } from './components/core/home/home.component';
import { AboutComponent } from './components/core/about/about.component';
import { ObrazovanjeComponent } from './components/model/obrazovanje/obrazovanje.component';
import { PreduzeceComponent } from './components/model/preduzece/preduzece.component';
import { SektorComponent } from './components/model/sektor/sektor.component';
import { RadnikComponent } from './components/model/radnik/radnik.component';
import { ObrazovanjeDialogComponent } from './components/dialogs/obrazovanje-dialog/obrazovanje-dialog.component';
import { PreduzeceDialogComponent } from './components/dialogs/preduzece-dialog/preduzece-dialog.component';
import { SektorDialogComponent } from './components/dialogs/sektor-dialog/sektor-dialog.component';
import { MatOptionModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { RadnikDialogComponent } from './components/dialogs/radnik-dialog/radnik-dialog.component';
import {MatSortModule} from '@angular/material/sort';
import { MatPaginatorModule } from '@angular/material/paginator';
import {MatCardModule} from '@angular/material/card';


@NgModule({
  declarations: [
    AppComponent,
    AuthorComponent,
    HomeComponent,
    AboutComponent,
    ObrazovanjeComponent,
    PreduzeceComponent,
    SektorComponent,
    RadnikComponent,
    ObrazovanjeDialogComponent,
    PreduzeceDialogComponent,
    SektorDialogComponent,
    RadnikDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatButtonModule,
    MatIconModule,
    MatSidenavModule,
    MatListModule,
    MatGridListModule,
    MatExpansionModule,
    BrowserAnimationsModule,
    MatTableModule,
    HttpClientModule,
    MatToolbarModule,
    MatSnackBarModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatOptionModule,
    MatSelectModule,
    MatSortModule,
    MatPaginatorModule,
    MatCardModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

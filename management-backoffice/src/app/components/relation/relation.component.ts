import {Component, Input} from '@angular/core';
import {TranslateModule} from "@ngx-translate/core";
import {CommonModule} from "@angular/common";
import {Router, RouterModule} from "@angular/router";
import {BreadcrumbsService} from "../navigation/top-nav/breadcrumbs.service";
import {ComponentsModule} from "../components.module";
import {PaginationComponent} from "../pagination/pagination.component";
import {FormGroup} from "@angular/forms";
import {tap} from "rxjs";

@Component({
  selector: 'mb-relation',
  templateUrl: './relation.component.html',
  standalone: true,
  imports: [TranslateModule, CommonModule, RouterModule, PaginationComponent]
})
export class RelationComponent {
  @Input()
  heads: string[] = [];

  @Input()
  createLink: string[] = [];

  @Input()
  allLink: string[] = [];

  @Input()
  title: string = "";
  @Input()
  pageForm: FormGroup| undefined;
  @Input()
  pages: number = 1;


  constructor(private bread: BreadcrumbsService,private router:Router) {
    this.pageForm?.valueChanges.subscribe(console.log);
  }

  createNew() {
    this.router.navigate(this.createLink);
  }
  showAll(){
    this.router.navigate(this.allLink);
  }
}

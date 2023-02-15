import {Component, Input} from '@angular/core';
import {TranslateModule} from "@ngx-translate/core";
import {CommonModule} from "@angular/common";
import {Router, RouterModule} from "@angular/router";
import {BreadcrumbsService} from "../navigation/top-nav/breadcrumbs.service";

@Component({
  selector: 'mb-relation',
  templateUrl: './relation.component.html',
  standalone: true,
  imports: [TranslateModule,CommonModule,RouterModule]
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


  constructor(private bread: BreadcrumbsService,private router:Router) {
  }

  createNew() {
    this.router.navigate(this.createLink);
  }
  showAll(){
    this.router.navigate(this.allLink);
  }
}

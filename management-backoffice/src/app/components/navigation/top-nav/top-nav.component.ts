import { Component } from '@angular/core';
import {TopNavService} from "./top-nav.service";

@Component({
  selector: 'mb-top-nav',
  templateUrl: './top-nav.component.html'
})
export class TopNavComponent {
  constructor(private service: TopNavService) {}

  getName(nodeId: string){
      return this.service.getName(nodeId);
  }
  isEnabled(nodeId:string){
      return this.service.isEnabled(nodeId);
  }
}

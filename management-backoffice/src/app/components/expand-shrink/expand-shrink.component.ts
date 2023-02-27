import {Component, Input} from '@angular/core';
import {CommonModule, NgClass} from "@angular/common";
import {TranslateModule} from "@ngx-translate/core";
import {BehaviorSubject, Subject} from "rxjs";

@Component({
  selector: 'mb-expand-shrink',
  templateUrl: './expand-shrink.component.html',
  imports: [
    CommonModule,
    TranslateModule
  ],
  standalone: true
})
export class ExpandShrinkComponent {
  @Input()
  creating: Subject<boolean> = new BehaviorSubject(true);
  @Input()
  title: string = "";

  shrink() {
    this.creating.next(false);
  }

  expand() {
    this.creating.next(true);
  }
}

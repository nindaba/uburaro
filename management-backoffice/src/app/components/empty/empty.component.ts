import {Component, Input} from '@angular/core';
import {TranslateModule} from "@ngx-translate/core";

@Component({
  selector: 'mb-empty',
  templateUrl: './empty.component.html',
  standalone: true,
  imports: [TranslateModule]
})
export class EmptyComponent {
  @Input()
  target: string = "";

}

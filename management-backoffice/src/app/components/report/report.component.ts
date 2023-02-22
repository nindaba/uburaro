import { Component } from '@angular/core';
import {NavNode} from "../../model/navigation.model";
import NavigationConfig from "../../../assets/content-config/navigation.json";

@Component({
  selector: 'mb-report',
  templateUrl: './report.component.html'
})
export class ReportComponent {
  tabs: NavNode[] = NavigationConfig["report-tabs"].nodes;
}

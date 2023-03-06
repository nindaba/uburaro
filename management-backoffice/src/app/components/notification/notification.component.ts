import { Component } from '@angular/core';
import {NotificationService} from "./notification.service";
import {MBNotification} from "../../model/navigation.model";
import {Subject} from "rxjs";

@Component({
  selector: 'mb-notification',
  templateUrl: './notification.component.html'
})
export class NotificationComponent {
  showing: Subject<MBNotification> = this.notificationService.getNotification();
  constructor(protected notificationService:NotificationService) {
  }
  close() {
    this.notificationService.close();
  }
}

import {Injectable} from "@angular/core";
import {NotificationService} from "../components/notification/notification.service";
import {MBNotification, NotificationStatus} from "../model/navigation.model";
import {BehaviorSubject, Subject} from "rxjs";

@Injectable()
export class NotificationServiceImpl implements NotificationService{
    showing: Subject<MBNotification> = new BehaviorSubject({status: NotificationStatus.NONE} as MBNotification);
    close(): void {
        this.showing.next({status: NotificationStatus.NONE});
    }

    notify(message: string, status: NotificationStatus = NotificationStatus.SUCCESS): void {
        this.showing.next({status: status,message: message});
        setTimeout(()=> this.showing.next({status: NotificationStatus.NONE}), 5000);
    }

    getNotification(): Subject<MBNotification> {
        return this.showing;
    }

}
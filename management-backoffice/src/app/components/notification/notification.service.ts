import {MBNotification, NotificationStatus} from "../../model/navigation.model";
import {Subject} from "rxjs";

export abstract class NotificationService{

    /**
     * Pops up the notification component
     *
     * @param message
     * @param status
     */
    abstract notify(message:string,status?:NotificationStatus):void;

    /**
     * closes the notification component
     */
    abstract close():void;

    /**
     * Gets Notification Subject
     */
    abstract getNotification(): Subject<MBNotification>;
}
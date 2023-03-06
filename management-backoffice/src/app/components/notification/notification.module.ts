import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotificationComponent } from './notification.component';
import {TranslateModule} from "@ngx-translate/core";



@NgModule({
    declarations: [
        NotificationComponent
    ],
    exports: [
        NotificationComponent
    ],
    imports: [
        CommonModule,
        TranslateModule
    ]
})
export class NotificationModule { }

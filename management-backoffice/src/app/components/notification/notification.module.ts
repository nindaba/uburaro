import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotificationComponent } from './notification.component';
import {TranslateModule} from "@ngx-translate/core";
import { ConfirmComponent } from './confirm.component';



@NgModule({
    declarations: [
        NotificationComponent,
        ConfirmComponent
    ],
    exports: [
        NotificationComponent,
        ConfirmComponent
    ],
    imports: [
        CommonModule,
        TranslateModule
    ]
})
export class NotificationModule { }

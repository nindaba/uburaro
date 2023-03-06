import {NgModule} from "@angular/core";
import {translatorConfig} from "./config/translator.config";
import {TranslateModule} from "@ngx-translate/core";
import {HttpClientModule} from "@angular/common/http";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";
import {EndpointConfig} from "./model/navigation.model";
import {endpointsConfig} from "./config/endpoints.config";
import {RelationComponent} from "./components/relation/relation.component";
import {MBItemService} from "./services/MBItem.service";
import {MBItemServiceImpl} from "./services/MBItem.service.impl";
import {OrderService} from "./components/inventory/order.service";
import {OrderServiceImpl} from "./services/order.service.impl";
import {ContractService} from "./components/rent/contract.service";
import {ContractServiceImpl} from "./services/contract.service.impl";
import {InvoiceService} from "./components/client/invoice.service";
import {InvoiceServiceImpl} from "./services/invoice.service.impl";
import {NotificationService} from "./components/notification/notification.service";
import {NotificationServiceImpl} from "./services/notification.service.impl";

@NgModule({
    imports: [
        TranslateModule.forRoot(translatorConfig),
        RouterModule,
        RelationComponent
    ],
    exports: [
        TranslateModule,
        HttpClientModule,
        CommonModule,
        RouterModule,
        RelationComponent
    ],
    providers: [
        {provide: EndpointConfig, useValue: endpointsConfig},
        {provide: MBItemService, useClass: MBItemServiceImpl},
        {provide: OrderService, useClass: OrderServiceImpl},
        {provide: ContractService, useClass: ContractServiceImpl},
        {provide: InvoiceService, useClass: InvoiceServiceImpl},
        {provide: NotificationService, useClass: NotificationServiceImpl},
    ]
})
export class AppCommonModule {

}
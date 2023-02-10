import {NgModule} from "@angular/core";
import {translatorConfig} from "./config/translator.config";
import {TranslateModule} from "@ngx-translate/core";
import {HttpClientModule} from "@angular/common/http";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";
import {EndpointConfig} from "./model/navigation.model";
import {endpointsConfig} from "./config/endpoints.config";

@NgModule({
    imports: [
        TranslateModule.forRoot(translatorConfig),
        RouterModule,
    ],
    exports:[
        TranslateModule,
        HttpClientModule,
        CommonModule,
        RouterModule,
    ],
    providers: [{provide: EndpointConfig, useValue: endpointsConfig}]
})
export class AppCommonModule {

}
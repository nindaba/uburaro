import {NgModule} from "@angular/core";
import {translatorConfig} from "./config/translator.config";
import {TranslateModule} from "@ngx-translate/core";
import {HttpClientModule} from "@angular/common/http";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";
import {EndpointConfig} from "./model/navigation.model";
import {endpointsConfig} from "./config/endpoints.config";
import {RelationComponent} from "./components/relation/relation.component";

@NgModule({
    imports: [
        TranslateModule.forRoot(translatorConfig),
        RouterModule,
        RelationComponent
    ],
    exports:[
        TranslateModule,
        HttpClientModule,
        CommonModule,
        RouterModule,
        RelationComponent
    ],
    providers: [{provide: EndpointConfig, useValue: endpointsConfig}]
})
export class AppCommonModule {

}